package umn.ac.id.uas.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.TextPaint;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import umn.ac.id.uas.project.model.AuthenticationController;
import umn.ac.id.uas.project.retrofit.ApiErrorHandler;
import umn.ac.id.uas.project.retrofit.ApiService;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        TextView title1 = findViewById(R.id.title1);
        TextView title2 = findViewById(R.id.title2);

        TextPaint paint = title1.getPaint();
        float width = paint.measureText(title1.getText().toString());

        Shader textShader = new LinearGradient(0, 0, width, title1.getTextSize(),
                new int[]{
                        Color.parseColor("#CC2B5E"),
                        Color.parseColor("#753A88"),
                }, null, Shader.TileMode.CLAMP);
        title1.getPaint().setShader(textShader);

        paint = title2.getPaint();
        width = paint.measureText(title2.getText().toString());

        textShader = new LinearGradient(0, 0, width, title2.getTextSize(),
                new int[]{
                        Color.parseColor("#CC2B5E"),
                        Color.parseColor("#753A88"),
                }, null, Shader.TileMode.CLAMP);
        title2.getPaint().setShader(textShader);

        ImageView signUpButton = findViewById(R.id.sign_up_button);

        signUpButton.setOnClickListener(v -> {
            EditText name, email, password, phoneNumber;
            name = findViewById(R.id.input_name);
            email = findViewById(R.id.input_email);
            password = findViewById(R.id.input_password);
            phoneNumber = findViewById(R.id.input_phone_number);
            ApiService.endpoint().registerUser(name.getText().toString(), email.getText().toString(), password.getText().toString(), phoneNumber.getText().toString()).enqueue(new Callback<AuthenticationController>() {
                @Override
                public void onResponse(Call<AuthenticationController> call, Response<AuthenticationController> response) {
                    if(response.isSuccessful()) {
                        String message = response.body().registerUser();

                        Toast.makeText(SignUpActivity.this, message, Toast.LENGTH_SHORT).show();

                        if(message.equalsIgnoreCase("User was successfully created.")) {
                            finish();
                        }
                    } else {
                        try {
                            Toast.makeText(SignUpActivity.this, ApiErrorHandler.getErrorMessage(response.errorBody().string()), Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<AuthenticationController> call, Throwable t) {
                    Toast.makeText(SignUpActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}