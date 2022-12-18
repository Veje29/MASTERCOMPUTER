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
import umn.ac.id.uas.project.model.VgaCardModel;
import umn.ac.id.uas.project.retrofit.ApiService;

public class VgaCardAdapter extends RecyclerView.Adapter<VgaCardAdapter.ViewHolder> {
    private Context context;
    private ArrayList<VgaCardModel> vgaCards;

    public VgaCardAdapter(Context context, ArrayList<VgaCardModel> vgaCards) {
        this.context = context;
        this.vgaCards = vgaCards;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.vga_card_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.image.setImageResource(R.drawable.vga_card_icon);
        holder.description.setText(vgaCards.get(position).getDescription());

        holder.price.setText(String.valueOf(vgaCards.get(position).getPrice()));

        holder.addItemButton.setOnClickListener(v -> {
            ApiService.endpoint().pickVga("Bearer " + SharedPreference.getToken(context), vgaCards.get(position).getId()).enqueue(new Callback<UserPackagePick>() {
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
        return vgaCards.size();
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
