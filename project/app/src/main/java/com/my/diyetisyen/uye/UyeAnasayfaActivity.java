package com.my.diyetisyen.uye;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.my.diyetisyen.databinding.ActivityUyeAnasayfaBinding;

public class UyeAnasayfaActivity extends AppCompatActivity {

    private ActivityUyeAnasayfaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("XXX", "Üye Anasayfa açıldı");
        binding = ActivityUyeAnasayfaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        handleClicks();
    }

    private void handleClicks() {
        binding.cardDiyetListesi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UyeAnasayfaActivity.this, UyeDiyetListesiActivity.class));
            }
        });
        binding.cardMotivasyon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UyeAnasayfaActivity.this, UyeMotivasyonActivity.class));
            }
        });
        binding.cardBesinler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UyeAnasayfaActivity.this, UyeYemeklerActivity.class));
            }
        });
        binding.cardProfilim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UyeAnasayfaActivity.this, UyeProfilActivity.class));
            }
        });
    }


}