package umn.ac.id.uas.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        ImageView backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> {
            finish();
        });

        TextView processor, vga, motherboard, powerSupply;
        processor = findViewById(R.id.processor);
        vga = findViewById(R.id.textView8);
        motherboard = findViewById(R.id.pick_motherboard);
        powerSupply = findViewById(R.id.pick_power_supply);

        processor.setOnClickListener(v -> {
            startActivity(new Intent(this, PickProcessorActivity.class));
        });

        vga.setOnClickListener(v -> {
            startActivity(new Intent(this, PickGraphicCardActivity.class));
        });

        motherboard.setOnClickListener(v -> {
            startActivity(new Intent(this, PickMotherboardActivity.class));
        });

        powerSupply.setOnClickListener(v -> {
            startActivity(new Intent(this, PickPowerSupplyActivity.class));
        });
    }
}