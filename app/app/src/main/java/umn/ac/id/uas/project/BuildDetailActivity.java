package umn.ac.id.uas.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import umn.ac.id.uas.project.model.MotherboardModel;
import umn.ac.id.uas.project.model.PowerSupplyModel;
import umn.ac.id.uas.project.model.ProcessorIntelModel;
import umn.ac.id.uas.project.model.VgaCardModel;

import java.text.NumberFormat;
import java.util.Locale;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class BuildDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_detail);
        LayoutInflater layoutInflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');
        kursIndonesia.setDecimalFormatSymbols(formatRp);

        Bundle extras = getIntent().getExtras();
        ProcessorIntelModel processor = (ProcessorIntelModel) extras.getSerializable("processor");
        VgaCardModel vga = (VgaCardModel) extras.getSerializable("vga");
        MotherboardModel motherboard = (MotherboardModel) extras.getSerializable("motherboard");
        PowerSupplyModel powerSupply = (PowerSupplyModel) extras.getSerializable("power_supply");

        LinearLayout rootContainer = findViewById(R.id.root_container);

        if(processor != null) {
            Log.i("info", "processor inflate");
            View processorView = layoutInflater.inflate(R.layout.processor_row, null);
            ((ImageView)processorView.findViewById(R.id.processor_image)).setImageResource(R.drawable.processor_icon);
            ((TextView)processorView.findViewById(R.id.processor_description)).setText(processor.getDescription());
            ((TextView)processorView.findViewById(R.id.price)).setText(kursIndonesia.format(processor.getPrice()));
            ((LinearLayout)processorView.findViewById(R.id.add_item_container)).setVisibility(View.GONE);
            rootContainer.addView(processorView);
        }

        if(vga != null) {
            Log.i("info", "vga inflate");
            View vgaView = layoutInflater.inflate(R.layout.vga_card_row, null);
            ((TextView)vgaView.findViewById(R.id.description)).setText(vga.getDescription());
            ((TextView)vgaView.findViewById(R.id.price)).setText(kursIndonesia.format(vga.getPrice()));
            ((LinearLayout)vgaView.findViewById(R.id.add_item_container)).setVisibility(View.GONE);
            rootContainer.addView(vgaView);
        }

        if(motherboard != null) {
            Log.i("info", "motherboard inflate");
            View motherboardView = layoutInflater.inflate(R.layout.motherboard_row, null);
            ((TextView)motherboardView.findViewById(R.id.description)).setText(motherboard.getDescription());
            ((TextView)motherboardView.findViewById(R.id.price)).setText(kursIndonesia.format(motherboard.getPrice()));
            ((LinearLayout)motherboardView.findViewById(R.id.add_item_container)).setVisibility(View.GONE);
            rootContainer.addView(motherboardView);
        }

        if(powerSupply != null) {
            Log.i("info", "psu inflate");
            View powerSupplyView = layoutInflater.inflate(R.layout.power_supply_row, null);
            ((TextView)powerSupplyView.findViewById(R.id.description)).setText(powerSupply.getDescription());
            ((TextView)powerSupplyView.findViewById(R.id.price)).setText(kursIndonesia.format(powerSupply.getPrice()));
            ((LinearLayout)powerSupplyView.findViewById(R.id.add_item_container)).setVisibility(View.GONE);
            rootContainer.addView(powerSupplyView);
        }
    }
}