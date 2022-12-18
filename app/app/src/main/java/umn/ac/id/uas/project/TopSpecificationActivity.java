package umn.ac.id.uas.project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import umn.ac.id.uas.project.model.ComputerPackage;
import umn.ac.id.uas.project.model.TopSpecification;
import umn.ac.id.uas.project.retrofit.ApiService;

public class TopSpecificationActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_specification);

        ComputerPackage pc = (ComputerPackage) getIntent().getSerializableExtra("pc");

        ImageView pcImageResource = findViewById(R.id.pc_image_resource);
        TextView name = findViewById(R.id.pc_name);
        TextView description = findViewById(R.id.description);

        Picasso.get().load(ApiService.getBaseUrl()+"img/computer/"+pc.getImage_path()).into(pcImageResource);
        name.setText(pc.getTitle());
        description.setText(pc.getDescription());
    }
}