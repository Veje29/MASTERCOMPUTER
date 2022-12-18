package umn.ac.id.uas.project.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import umn.ac.id.uas.project.PackageSelectionActivity;
import umn.ac.id.uas.project.R;
import umn.ac.id.uas.project.model.ComputerPackage;
import umn.ac.id.uas.project.retrofit.ApiService;

public class PackageAdapter extends RecyclerView.Adapter<PackageAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ComputerPackage> computerPackages;

    public PackageAdapter(Context context, ArrayList<ComputerPackage> computerPackages) {
        this.context = context;
        this.computerPackages = computerPackages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.package_row, parent, false);

        return new ViewHolder(view, context, computerPackages);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(computerPackages.get(position).getTitle());
        holder.price.setText(String.valueOf(computerPackages.get(position).getPrice()));
        holder.description.setText(computerPackages.get(position).getDescription());
        Picasso.get().load(ApiService.getBaseUrl() + "img/computer/" + computerPackages.get(position).getImage_path()).into(holder.photo);
//        holder.photo.setImageResource(computerPackages.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return computerPackages.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title, description, price;
        private ImageView photo;

        public ViewHolder(@NonNull View itemView, Context context, ArrayList<ComputerPackage> computerPackages) {
            super(itemView);

            title = itemView.findViewById(R.id.package_title);
            price = itemView.findViewById(R.id.package_price);
            description = itemView.findViewById(R.id.package_description);
            photo = itemView.findViewById(R.id.photo);

            itemView.setOnClickListener(v -> {
                Intent packageSelectionIntent = new Intent(context, PackageSelectionActivity.class);

                String titleString = title.getText().toString();
                String descriptionString = description.getText().toString();
                String priceString = price.getText().toString();
                String photo_path = computerPackages.get(getAdapterPosition()).getImage_path();

                packageSelectionIntent.putExtra("title", titleString);
                packageSelectionIntent.putExtra("description", descriptionString);
                packageSelectionIntent.putExtra("price", priceString);
                packageSelectionIntent.putExtra("photo", photo_path);

                context.startActivity(packageSelectionIntent);
            });
        }
    }
}
