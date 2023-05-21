package com.my.diyetisyen.diyetisyen;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.my.diyetisyen.databinding.ActivityDiyetisyenYemekEkleBinding;
import com.my.diyetisyen.modeller.BesinBilgisiModel;

public class DiyetisyenYemekEkleActivity extends AppCompatActivity {

    private ActivityDiyetisyenYemekEkleBinding binding;
    private FirebaseDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance();

        binding = ActivityDiyetisyenYemekEkleBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        handleClicks();

    }

    private void handleClicks() {
        binding.cardBesinEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                besinEkle();
            }
        });

    }

    private void besinEkle() {
        String besinAdi = binding.etBesinAdi.getText().toString().trim();
        String kalori = binding.etKalori.getText().toString().trim();

        if (besinAdi.isEmpty()) {
            Toast.makeText(this, "Besin adı eklemelisiniz", Toast.LENGTH_SHORT).show();
        } else if (kalori.isEmpty()) {
            Toast.makeText(this, "Kalori değeri eklemelisiniz", Toast.LENGTH_SHORT).show();
        } else {
            DatabaseReference databaseRef = mDatabase.getReference();
            DatabaseReference foodsRef = databaseRef.child("foods");
            BesinBilgisiModel besinModel = new BesinBilgisiModel(besinAdi, kalori);

            foodsRef.push().setValue(besinModel);
            onBackPressed();
        }
    }
}