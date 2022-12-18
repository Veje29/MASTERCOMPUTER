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
import umn.ac.id.uas.project.model.MotherboardModel;
import umn.ac.id.uas.project.model.PowerSupplyModel;
import umn.ac.id.uas.project.model.UserPackagePick;
import umn.ac.id.uas.project.retrofit.ApiService;

public class PowerSupplyAdapter extends RecyclerView.Adapter<PowerSupplyAdapter.ViewHolder> {
    private Context context;
    private ArrayList<PowerSupplyModel> powerSupplies;

    public PowerSupplyAdapter(Context context, ArrayList<PowerSupplyModel> powerSupplies) {
        this.context = context;
        this.powerSupplies = powerSupplies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.power_supply_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.image.setImageResource(R.drawable.motherboard);
        holder.description.setText(powerSupplies.get(position).getDescription());

        holder.price.setText(String.valueOf(powerSupplies.get(position).getPrice()));

        holder.addItemButton.setOnClickListener(v -> {
            ApiService.endpoint().pickPowerSupply("Bearer " + SharedPreference.getToken(context), powerSupplies.get(position).getId()).enqueue(new Callback<UserPackagePick>() {
                @Override
                public void onResponse(Call<UserPackagePick> call, Response<UserPackagePick> response) {
                    if(response.isSuccessful()) {
                        Toast.makeText(context, response.body().pickProcessor(), Toast.LENGTH_SHORT).show();
                    } else {
                        try {
                            Log.i("error 31", response.errorBody().string());
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
        return powerSupplies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView description;
        private ImageView image;
        private TextView price;
        ImageView addItemButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            description = itemView.findViewById(R.id.description);
            image = itemView.findViewById(R.id.image);
            price = itemView.findViewById(R.id.price);
            addItemButton = itemView.findViewById(R.id.add_item_button);
        }
    }
}
