package umn.ac.id.uas.project.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import umn.ac.id.uas.project.R;
import umn.ac.id.uas.project.TopSpecificationActivity;
import umn.ac.id.uas.project.model.ComputerPackage;
import umn.ac.id.uas.project.model.TopSpecification;
import umn.ac.id.uas.project.model.VgaCardModel;
import umn.ac.id.uas.project.retrofit.ApiService;

public class TopSpecificationAdapter extends RecyclerView.Adapter<TopSpecificationAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ComputerPackage> computerPackages;

    public TopSpecificationAdapter(Context context, ArrayList<ComputerPackage> computerPackages) {
        this.context = context;
        this.computerPackages = computerPackages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.top_specification_row, parent, false);

        return new ViewHolder(view, computerPackages, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(ApiService.getBaseUrl()+"img/computer/"+computerPackages.get(position).getImage_path()).placeholder(R.drawable.zeus_300_x_computer).into(holder.image);
        holder.title.setText(computerPackages.get(position).getTitle());

        holder.topSpecificationContainer.setOnClickListener(v -> {
            ComputerPackage pc = computerPackages.get(position);

            Intent intent = new Intent(context.getApplicationContext(), TopSpecificationActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("pc", pc);
            context.getApplicationContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return computerPackages.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView image;
        private LinearLayout topSpecificationContainer;

        public ViewHolder(@NonNull View itemView, ArrayList<ComputerPackage> computerPackages, Context context) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            image = itemView.findViewById(R.id.image);
            topSpecificationContainer = itemView.findViewById(R.id.top_specification_container);
        }
    }
}
