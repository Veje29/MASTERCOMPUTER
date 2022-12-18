package umn.ac.id.uas.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import umn.ac.id.uas.project.global.SharedPreference;
import umn.ac.id.uas.project.model.AuthenticationController;
import umn.ac.id.uas.project.retrofit.ApiErrorHandler;
import umn.ac.id.uas.project.retrofit.ApiService;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        ImageView signInButton = findViewById(R.id.sign_in_button);

        EditText email = findViewById(R.id.input_email);
        EditText password = findViewById(R.id.input_password);

        if(SharedPreference.getToken(this) != null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        signInButton.setOnClickListener(v -> {
            String textEmail = email.getText().toString();
            String textPassword = password.getText().toString();

            ApiService.endpoint().login(textEmail, textPassword).enqueue(new Callback<AuthenticationController>() {
                @Override
                public void onResponse(Call<AuthenticationController> call, Response<AuthenticationController> response) {
                    if(response.isSuccessful()) {
                        AuthenticationController.Result result = response.body().login();
                        Log.i("Result", result.toString());

                        if(result.getMessage().equalsIgnoreCase("Login Success")) {
                            SharedPreference.setUser(getApplicationContext(), result.getUser());
                            SharedPreference.setToken(getApplicationContext(), result.getToken());
                            Toast.makeText(SignInActivity.this, "Login Success, welcome " + result.getUser().getName(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        }
                    } else {
                        try {
                            Toast.makeText(SignInActivity.this, ApiErrorHandler.getErrorMessage(response.errorBody().string()), Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<AuthenticationController> call, Throwable t) {
                    Toast.makeText(SignInActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

        TextView registerButton = findViewById(R.id.textView);

        registerButton.setOnClickListener(v -> {
            startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
        });
    }
}