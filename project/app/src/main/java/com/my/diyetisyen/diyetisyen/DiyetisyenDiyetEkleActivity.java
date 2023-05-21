package com.my.diyetisyen.diyetisyen;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.my.diyetisyen.databinding.ActivityDiyetisyenDiyetEkleBinding;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DiyetisyenDiyetEkleActivity extends AppCompatActivity {

    private ActivityDiyetisyenDiyetEkleBinding binding;
    private FirebaseDatabase mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDatabase = FirebaseDatabase.getInstance();

        binding = ActivityDiyetisyenDiyetEkleBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        handleClicks();

    }

    private void handleClicks() {
        binding.cardDiyetiOnayla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diyetBilgileriniAl();
            }
        });

    }

    private void diyetBilgileriniAl() {
        String pazartesi = binding.etPazartesi.getText().toString().trim();
        String sali = binding.etSali.getText().toString().trim();
        String carsamba = binding.etCarsamba.getText().toString().trim();
        String persembe = binding.etPersembe.getText().toString().trim();
        String cuma = binding.etCuma.getText().toString().trim();
        String cumartesi = binding.etCumartesi.getText().toString().trim();
        String pazar = binding.etPazar.getText().toString().trim();

        if (pazartesi.isEmpty()) {
            Toast.makeText(this, "Pazartesi diyetini giriniz", Toast.LENGTH_SHORT).show();
        } else if (sali.isEmpty()) {
            Toast.makeText(this, "Salı diyetini giriniz", Toast.LENGTH_SHORT).show();
        } else if (carsamba.isEmpty()) {
            Toast.makeText(this, "Çarşamba diyetini giriniz", Toast.LENGTH_SHORT).show();
        } else if (persembe.isEmpty()) {
            Toast.makeText(this, "Perşembe diyetini giriniz", Toast.LENGTH_SHORT).show();
        } else if (cuma.isEmpty()) {
            Toast.makeText(this, "Cuma diyetini giriniz", Toast.LENGTH_SHORT).show();
        } else if (cumartesi.isEmpty()) {
            Toast.makeText(this, "Cumartesi diyetini giriniz", Toast.LENGTH_SHORT).show();
        } else if (pazar.isEmpty()) {
            Toast.makeText(this, "Pazar diyetini giriniz", Toast.LENGTH_SHORT).show();
        } else {
            diyetiGuncelle(pazartesi, sali, carsamba, persembe, cuma, cumartesi, pazar);
        }
    }

    private void diyetiGuncelle(String pazartesi, String sali, String carsamba, String persembe, String cuma, String cumartesi, String pazar) {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            LocalDate now = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            String formattedDate = now.format(formatter);
            DatabaseReference databaseRef = mDatabase.getReference();
            DatabaseReference dietsRef = databaseRef.child("diets");

            dietsRef.child("pazartesi").setValue(pazartesi);
            dietsRef.child("sali").setValue(sali);
            dietsRef.child("carsamba").setValue(carsamba);
            dietsRef.child("persembe").setValue(persembe);
            dietsRef.child("cuma").setValue(cuma);
            dietsRef.child("cumartesi").setValue(cumartesi);
            dietsRef.child("pazar").setValue(pazar);
            dietsRef.child("tarih").setValue(formattedDate);

        }


        onBackPressed();

    }
}