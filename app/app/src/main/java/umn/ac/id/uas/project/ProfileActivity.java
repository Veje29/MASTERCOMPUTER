package umn.ac.id.uas.project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import umn.ac.id.uas.project.global.SharedPreference;
import umn.ac.id.uas.project.model.AuthenticationController;
import umn.ac.id.uas.project.model.UserModel;
import umn.ac.id.uas.project.retrofit.ApiService;

public class ProfileActivity extends AppCompatActivity {
    CircleImageView photoProfile;
    private static final int RESULT_LOAD_IMG = 2500;
    private static int REQUEST_TAKE_PICTURE = 100;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 101;

    public static boolean checkAndRequestPermissions(final Activity context) {
        int WExtstorePermission = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int cameraPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA);

        List<String> listPermissionsNeeded = new ArrayList<>();

        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (WExtstorePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(context, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_ID_MULTIPLE_PERMISSIONS:
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "FlagUp Requires Access to Camera.", Toast.LENGTH_SHORT).show();
                } else if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "FlagUp Requires Access to Your Storage.", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        photoProfile = findViewById(R.id.profile_image);

        String photoProfilePath = ApiService.getBaseUrl() + SharedPreference.getUser(getApplicationContext()).getPhoto_profile_path();
        Log.i("Profile picture", ApiService.getBaseUrl() + SharedPreference.getUser(getApplicationContext()).getPhoto_profile_path());

        Picasso.get().load(photoProfilePath).memoryPolicy(MemoryPolicy.NO_CACHE).networkPolicy(NetworkPolicy.NO_CACHE).placeholder(R.drawable.profile_alexis_sanchez).error(R.drawable.profile_alexis_sanchez).into(photoProfile);

        TextView name = findViewById(R.id.name);
        name.setText(SharedPreference.getUser(getApplicationContext()).getName());

        TextView email = findViewById(R.id.profile_email);
        TextView phoneNumber = findViewById(R.id.profile_whatsapp);
        UserModel user = SharedPreference.getUser(getApplicationContext());
        email.setText(user.getEmail());
        phoneNumber.setText(user.getPhone_number());

        photoProfile.setOnClickListener(v -> {
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            photoPickerIntent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(photoPickerIntent, "Select picture"), RESULT_LOAD_IMG);
        });

        Button takePhotoButton = findViewById(R.id.take_photo_button);
        takePhotoButton.setOnClickListener(v -> {
            if (checkAndRequestPermissions(this)) {
                Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePicture, REQUEST_TAKE_PICTURE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUEST_TAKE_PICTURE && data != null) {
            Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
            photoProfile.setImageBitmap(selectedImage);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            selectedImage.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
            byte[] inputData = bos.toByteArray();
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                String imageBase64encode = Base64.getEncoder().encodeToString(inputData);

                Log.i("Base64 decode", imageBase64encode);

                ApiService.endpoint().changeProfilePicture("Bearer " + SharedPreference.getToken(getApplicationContext()), imageBase64encode).enqueue(new Callback<UserModel>() {
                    @Override
                    public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                        if (response.isSuccessful()) {
                            updateToken();
                        } else {
                            try {
                                Toast.makeText(ProfileActivity.this, response.errorBody().string(), Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<UserModel> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        }

        if (resultCode == RESULT_OK && requestCode == RESULT_LOAD_IMG) {
            Uri selectedImageUri = data.getData();

            InputStream iStream = null;
            try {
                iStream = getContentResolver().openInputStream(selectedImageUri);
                byte[] inputData = ProfileActivity.getBytes(iStream);
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    String imageBase64encode = Base64.getEncoder().encodeToString(inputData);

                    Log.i("Base64 decode", imageBase64encode);

                    ApiService.endpoint().changeProfilePicture("Bearer " + SharedPreference.getToken(getApplicationContext()), imageBase64encode).enqueue(new Callback<UserModel>() {
                        @Override
                        public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                            if (response.isSuccessful()) {
                                updateToken();
                            } else {
                                try {
                                    Toast.makeText(ProfileActivity.this, response.errorBody().string(), Toast.LENGTH_SHORT).show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<UserModel> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }

            photoProfile.setImageBitmap(bitmap);
        }

    }

    private void updateToken() {
        ApiService.endpoint().getUser("Bearer " + SharedPreference.getToken(this)).enqueue(new Callback<AuthenticationController>() {
            @Override
            public void onResponse(Call<AuthenticationController> call, Response<AuthenticationController> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(ProfileActivity.this, "Successfully updated profile picture", Toast.LENGTH_SHORT).show();
                    UserModel user = response.body().getUser();
                    SharedPreference.setUser(getApplicationContext(), user);
                } else {
                    try {
                        Toast.makeText(ProfileActivity.this, response.errorBody().string(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<AuthenticationController> call, Throwable t) {

            }
        });
    }

    public static byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }
}