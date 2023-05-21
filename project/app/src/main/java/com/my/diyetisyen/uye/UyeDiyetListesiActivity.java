package com.my.diyetisyen.uye;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.my.diyetisyen.databinding.ActivityUyeDiyetListesiBinding;
import com.my.diyetisyen.modeller.DiyetListesiModel;

public class UyeDiyetListesiActivity extends AppCompatActivity {
    private ActivityUyeDiyetListesiBinding binding;

    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();

        binding = ActivityUyeDiyetListesiBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getirDiyetListesi();

    }

    private void getirDiyetListesi() {
        DatabaseReference databaseRef = mDatabase.getReference();
        DatabaseReference dietsRef = databaseRef.child("diets");

        ValueEventListener haftalikDiyet = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String pazartesiDiet = snapshot.child("pazartesi").getValue(String.class);
                String saliDiet = snapshot.child("sali").getValue(String.class);
                String carsambaDiet = snapshot.child("carsamba").getValue(String.class);
                String persembeDiet = snapshot.child("persembe").getValue(String.class);
                String cumaDiet = snapshot.child("cuma").getValue(String.class);
                String cumartesiDiet = snapshot.child("cumartesi").getValue(String.class);
                String pazarDiet = snapshot.child("pazar").getValue(String.class);
                String tarih = snapshot.child("tarih").getValue(String.class);

                listeyiYerlestir(new DiyetListesiModel(pazartesiDiet, saliDiet, carsambaDiet, persembeDiet, cumaDiet, cumartesiDiet, pazarDiet), tarih);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        dietsRef.addListenerForSingleValueEvent(haftalikDiyet);

    }

    private void listeyiYerlestir(DiyetListesiModel haftalikDiyet, String tarih) {
        binding.tvPazartesi.setText(haftalikDiyet.getPazartesi());
        binding.tvSali.setText(haftalikDiyet.getSali());
        binding.tvCarsamba.setText(haftalikDiyet.getCarsamba());
        binding.tvPersembe.setText(haftalikDiyet.getPersembe());
        binding.tvCuma.setText(haftalikDiyet.getCuma());
        binding.tvCumartesi.setText(haftalikDiyet.getCumartesi());
        binding.tvPazar.setText(haftalikDiyet.getPazar());

        binding.tvTarih.setText("Diyetisyeniniz tarafından " + tarih + " tarihinde sizin için önerilmiş diyet listeniz aşağıdadır.");

    }
}