package umn.ac.id.uas.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import umn.ac.id.uas.project.global.SharedPreference;
import umn.ac.id.uas.project.model.BuildDetailModel;
import umn.ac.id.uas.project.model.UserModel;
import umn.ac.id.uas.project.retrofit.ApiService;

public class BuildPcActivity extends AppCompatActivity {
    private Button lihatRancanganButton = null;
    BuildDetailModel userPackage = null;

    @Override
    protected void onResume() {
        super.onResume();

        lihatRancanganButton.setVisibility(View.GONE);

        getUserPackage();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_pc);

        lihatRancanganButton = findViewById(R.id.lihat_rancangan_button);

        TextView name = findViewById(R.id.hello_name);
        name.setText("Hello, " + SharedPreference.getUser(getApplicationContext()).getName());

        TextView processorButton = findViewById(R.id.processor);
        processorButton.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), PickProcessorActivity.class));
        });

        TextView graphicCardButton = findViewById(R.id.textView8);
        graphicCardButton.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), PickGraphicCardActivity.class));
        });

        TextView motherboardButton = findViewById(R.id.pick_motherboard);
        motherboardButton.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), PickMotherboardActivity.class));
        });

        TextView powerSupplyButton = findViewById(R.id.pick_power_supply);
        powerSupplyButton.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), PickPowerSupplyActivity.class));
        });

        lihatRancanganButton.setOnClickListener(v -> {
            Intent buildDetailIntent = new Intent(this, BuildDetailActivity.class);

            buildDetailIntent.putExtra("processor", userPackage.getProcessor());
            buildDetailIntent.putExtra("vga", userPackage.getVga());
            buildDetailIntent.putExtra("motherboard", userPackage.getMotherboard());
            buildDetailIntent.putExtra("power_supply", userPackage.getPowerSupply());

//            Log.i("motherboard", userPackage.getMotherboard().toString());
//            Log.i("power supply", userPackage.getPowerSupply().toString());
//            return;

            startActivity(buildDetailIntent);
        });
    }

    private void getUserPackage() {
        Log.i("Token", "Bearer " + SharedPreference.getToken(this));
        ApiService.endpoint().getUserPackage("Bearer " + SharedPreference.getToken(this)).enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if(response.isSuccessful()) {
                    userPackage = response.body().getUserPackage();
                    try {
                        Log.i("user package", userPackage.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if(userPackage == null) return;

                    lihatRancanganButton.setVisibility(View.VISIBLE);
                } else {
                    try {
                        Toast.makeText(BuildPcActivity.this, response.errorBody().string(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(BuildPcActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}