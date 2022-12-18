package umn.ac.id.uas.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import umn.ac.id.uas.project.adapter.IntelAdapter;
import umn.ac.id.uas.project.adapter.VgaCardAdapter;
import umn.ac.id.uas.project.global.SharedPreference;
import umn.ac.id.uas.project.model.AuthenticationController;
import umn.ac.id.uas.project.model.ProcessorIntelModel;
import umn.ac.id.uas.project.model.VgaCardModel;
import umn.ac.id.uas.project.retrofit.ApiErrorHandler;
import umn.ac.id.uas.project.retrofit.ApiService;

public class PickGraphicCardActivity extends AppCompatActivity {
    String[] vgaCards;
    int[] imageIcons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_graphic_card);

        findViewById(R.id.back_button).setOnClickListener(v -> {
            finish();
        });

        ApiService.endpoint().getVga().enqueue(new Callback<VgaCardModel>() {
            @Override
            public void onResponse(Call<VgaCardModel> call, Response<VgaCardModel> response) {
                if(response.isSuccessful()) {
                    ArrayList<VgaCardModel> vgas = response.body().getVga();

                    ArrayList<VgaCardModel> galax = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        galax = (ArrayList<VgaCardModel>) vgas.stream().filter(processor -> processor.getGeneration().contains("galax")).collect(Collectors.toList());
                    }

                    ArrayList<VgaCardModel> xfx = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        xfx = (ArrayList<VgaCardModel>) vgas.stream().filter(processor -> processor.getGeneration().contains("xfx")).collect(Collectors.toList());
                    }

                    RecyclerView galaxRecyclerView = findViewById(R.id.recycler_view_galax);
                    VgaCardAdapter vgaCardAdapter = new VgaCardAdapter(getApplicationContext(), galax);
                    galaxRecyclerView.setAdapter(vgaCardAdapter);
                    galaxRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                    RecyclerView xfxRecyclerView = findViewById(R.id.recycler_view_xfx);
                    VgaCardAdapter xfxVgaCardAdapter = new VgaCardAdapter(getApplicationContext(), xfx);
                    xfxRecyclerView.setAdapter(xfxVgaCardAdapter);
                    xfxRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                } else {
                    try {
                        Toast.makeText(getApplicationContext(), ApiErrorHandler.getErrorMessage(response.errorBody().string()), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<VgaCardModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}