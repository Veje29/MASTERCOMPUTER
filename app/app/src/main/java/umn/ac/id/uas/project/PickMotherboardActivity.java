package umn.ac.id.uas.project;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import umn.ac.id.uas.project.adapter.MotherboardAdapter;
import umn.ac.id.uas.project.adapter.VgaCardAdapter;
import umn.ac.id.uas.project.model.MotherboardModel;
import umn.ac.id.uas.project.model.VgaCardModel;
import umn.ac.id.uas.project.retrofit.ApiErrorHandler;
import umn.ac.id.uas.project.retrofit.ApiService;

public class PickMotherboardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_motherboard);

        findViewById(R.id.back_button).setOnClickListener(v -> {
            finish();
        });

        ApiService.endpoint().getMotherboards().enqueue(new Callback<MotherboardModel>() {
            @Override
            public void onResponse(Call<MotherboardModel> call, Response<MotherboardModel> response) {
                if(response.isSuccessful()) {
                    ArrayList<MotherboardModel> motherboards = response.body().getMotherboards();

                    RecyclerView recyclerView = findViewById(R.id.recycler_view);
                    MotherboardAdapter motherboardAdapter = new MotherboardAdapter(getApplicationContext(), motherboards);
                    recyclerView.setAdapter(motherboardAdapter);
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
            public void onFailure(Call<MotherboardModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}