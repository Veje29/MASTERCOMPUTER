package umn.ac.id.uas.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import umn.ac.id.uas.project.model.ProcessorIntelModel;
import umn.ac.id.uas.project.retrofit.ApiErrorHandler;
import umn.ac.id.uas.project.retrofit.ApiService;

public class PickProcessorActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] processors;
    int[] imageIcons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_processor);

        findViewById(R.id.back_button).setOnClickListener(v -> {
            finish();
        });

        processors = new String[]{
                "Pilih Brand Processor",
                "Intel",
                "AMD"
        };

        imageIcons = new int[] {
                R.drawable.tw_lightning_bolt,
                R.drawable.intel_logo,
                R.drawable.amd_logo
        };

        Spinner processorSpinner = findViewById(R.id.processor_spinner);
        processorSpinner.setOnItemSelectedListener(this);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(),processors, imageIcons);
        processorSpinner.setAdapter(customAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        switch(processors[position]) {
            case "Intel" :
                ApiService.endpoint().getProcessors().enqueue(new Callback<ProcessorIntelModel>() {
                    @Override
                    public void onResponse(Call<ProcessorIntelModel> call, Response<ProcessorIntelModel> response) {
                        if(response.isSuccessful()) {
                            ArrayList<ProcessorIntelModel> processors = response.body().getProcessors();

                            ArrayList<ProcessorIntelModel> intelI9 = null;
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                                intelI9 = (ArrayList<ProcessorIntelModel>) processors.stream().filter(processor -> processor.getGeneration().contains("i9") && processor.getType().equalsIgnoreCase("intel")).collect(Collectors.toList());
                            }

                            ArrayList<ProcessorIntelModel> intelI7 = null;
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                                intelI7 = (ArrayList<ProcessorIntelModel>) processors.stream().filter(processor -> processor.getGeneration().contains("i7") && processor.getType().equalsIgnoreCase("intel")).collect(Collectors.toList());
                            }

                            Log.i("Status", "Successfully fetched processors");

                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProcessorRecyclerViewFragment(getApplicationContext(), intelI9, intelI7)).commit();
                        } else {
                            try {
                                Toast.makeText(PickProcessorActivity.this, ApiErrorHandler.getErrorMessage(response.errorBody().string()), Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ProcessorIntelModel> call, Throwable t) {
                        Toast.makeText(PickProcessorActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case "AMD" :
                ApiService.endpoint().getProcessors().enqueue(new Callback<ProcessorIntelModel>() {
                    @Override
                    public void onResponse(Call<ProcessorIntelModel> call, Response<ProcessorIntelModel> response) {
                        if(response.isSuccessful()) {
                            ArrayList<ProcessorIntelModel> processors = response.body().getProcessors();

                            ArrayList<ProcessorIntelModel> amd4 = null;
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                                amd4 = (ArrayList<ProcessorIntelModel>) processors.stream().filter(processor -> processor.getType().equalsIgnoreCase("amd")).collect(Collectors.toList());
                            }

                            ArrayList<ProcessorIntelModel> amd5 = null;
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                                amd5 = (ArrayList<ProcessorIntelModel>) processors.stream().filter(processor -> processor.getType().equalsIgnoreCase("amd")).collect(Collectors.toList());
                            }

                            Log.i("Status", "Successfully fetched processors");

                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProcessorAmdRecyclerViewFragment(getApplicationContext(), amd4, amd5)).commit();
                        } else {
                            try {
                                Toast.makeText(PickProcessorActivity.this, ApiErrorHandler.getErrorMessage(response.errorBody().string()), Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ProcessorIntelModel> call, Throwable t) {
                        Toast.makeText(PickProcessorActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            default:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment()).commit();
                Toast.makeText(this, "Please Pick a processor...", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}