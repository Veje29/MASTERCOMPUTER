package umn.ac.id.uas.project;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import umn.ac.id.uas.project.adapter.MotherboardAdapter;
import umn.ac.id.uas.project.adapter.PowerSupplyAdapter;
import umn.ac.id.uas.project.model.MotherboardModel;
import umn.ac.id.uas.project.model.PowerSupplyModel;
import umn.ac.id.uas.project.retrofit.ApiErrorHandler;
import umn.ac.id.uas.project.retrofit.ApiService;

public class PickPowerSupplyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_power_supply);

        findViewById(R.id.back_button).setOnClickListener(v -> {
            finish();
        });

        ApiService.endpoint().getPowerSupplies().enqueue(new Callback<PowerSupplyModel>() {
            @Override
            public void onResponse(Call<PowerSupplyModel> call, Response<PowerSupplyModel> response) {
                if(response.isSuccessful()) {
                    ArrayList<PowerSupplyModel> powerSupplies = response.body().getPowerSupplies();

                    Log.i("Power supplies", powerSupplies.toString());

                    RecyclerView recyclerView = findViewById(R.id.recycler_view);
                    PowerSupplyAdapter powerSupplyAdapter = new PowerSupplyAdapter(getApplicationContext(), powerSupplies);
                    recyclerView.setAdapter(powerSupplyAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                } else {
                    try {
                        Toast.makeText(getApplicationContext(), ApiErrorHandler.getErrorMessage(response.errorBody().string()), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<PowerSupplyModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}