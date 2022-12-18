package umn.ac.id.uas.project.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import umn.ac.id.uas.project.R;
import umn.ac.id.uas.project.global.SharedPreference;
import umn.ac.id.uas.project.model.ProcessorIntelModel;
import umn.ac.id.uas.project.model.UserPackagePick;
import umn.ac.id.uas.project.retrofit.ApiService;

public class IntelAdapter extends RecyclerView.Adapter<IntelAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ProcessorIntelModel> processors;

    public IntelAdapter(Context context, ArrayList<ProcessorIntelModel> processors) {
        this.context = context;
        this.processors = processors;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.processor_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.image.setImageResource(R.drawable.processor_icon);
        if(processors.get(position).getGeneration().contains("amd")) {
            holder.image.setImageResource(R.drawable.amd_icon);
        }

        holder.description.setText(processors.get(position).getDescription());
        Log.i("price", String.valueOf(processors.get(position).getPrice()));
        Log.i("TextView", holder.price.toString());
        holder.price.setText("Rp. " + processors.get(position).getPrice());
        
        holder.addItemButton.setOnClickListener(v -> {
            ApiService.endpoint().pickProcessor("Bearer " + SharedPreference.getToken(context), processors.get(position).getId()).enqueue(new Callback<UserPackagePick>() {
                @Override
                public void onResponse(Call<UserPackagePick> call, Response<UserPackagePick> response) {
                    if(response.isSuccessful()) {
                        Toast.makeText(context, response.body().pickProcessor(), Toast.LENGTH_SHORT).show();
                    } else {
                        try {
                            Log.i("error 30", response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<UserPackagePick> call, Throwable t) {
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    @Override
    public int getItemCount() {
        return processors.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView description;
        private ImageView image;
        private TextView price;
        private ImageView addItemButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            description = itemView.findViewById(R.id.processor_description);
            image = itemView.findViewById(R.id.processor_image);
            price = itemView.findViewById(R.id.price);
            addItemButton = itemView.findViewById(R.id.add_item_button);
        }
    }
}
