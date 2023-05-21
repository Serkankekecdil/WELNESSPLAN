package com.my.diyetisyen.diyetisyen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.my.diyetisyen.R;
import com.my.diyetisyen.databinding.ActivityDiyetisyenAnasayfaBinding;
import com.my.diyetisyen.databinding.ActivityUyeAnasayfaBinding;
import com.my.diyetisyen.uye.UyeAnasayfaActivity;
import com.my.diyetisyen.uye.UyeDiyetListesiActivity;
import com.my.diyetisyen.uye.UyeMotivasyonActivity;
import com.my.diyetisyen.uye.UyeProfilActivity;
import com.my.diyetisyen.uye.UyeYemeklerActivity;

public class DiyetisyenAnasayfaActivity extends AppCompatActivity {

    private ActivityDiyetisyenAnasayfaBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDiyetisyenAnasayfaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        handleClicks();
    }

    private void handleClicks() {
        binding.cardDiyetEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DiyetisyenAnasayfaActivity.this, DiyetisyenDiyetEkleActivity.class));
            }
        });
        binding.cardMesajGonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DiyetisyenAnasayfaActivity.this, DiyetisyenMotivasyonEkleActivity.class));
            }
        });
        binding.cardBesinEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DiyetisyenAnasayfaActivity.this, DiyetisyenYemekEkleActivity.class));
            }
        });
        binding.cardProfilim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DiyetisyenAnasayfaActivity.this, DiyetisyenProfilActivity.class));
            }
        });
    }

}