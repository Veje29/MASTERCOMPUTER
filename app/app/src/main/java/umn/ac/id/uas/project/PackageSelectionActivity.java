package umn.ac.id.uas.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import umn.ac.id.uas.project.retrofit.ApiService;

public class PackageSelectionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_selection);

        ImageView backButton = findViewById(R.id.imageView);
        backButton.setOnClickListener(v -> {
            finish();
        });

        Bundle extras = getIntent().getExtras();

        String titleString = extras.getString("title");
        String descriptionString = extras.getString("description");
        String priceString = extras.getString("price");
        String photoString = extras.getString("photo");

        TextView packagePrice, packageDescription, packageTitle;
        ImageView packagePhoto;

        packagePrice = findViewById(R.id.package_price);
        packageDescription = findViewById(R.id.package_description);
        packageTitle = findViewById(R.id.package_title);
        packagePhoto = findViewById(R.id.package_photo);

        packagePrice.setText(priceString);
        packageDescription.setText(descriptionString);
        packageTitle.setText(titleString);
//        packagePhoto.setImageResource(photoInt);
        Picasso.get().load(ApiService.getBaseUrl() + "img/computer/" + photoString).into(packagePhoto);
    }
}