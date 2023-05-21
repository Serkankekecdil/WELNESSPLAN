package com.my.diyetisyen.uye;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.my.diyetisyen.adapter.BesinBilgisiAdapter;
import com.my.diyetisyen.databinding.ActivityUyeYemeklerBinding;
import com.my.diyetisyen.modeller.BesinBilgisiModel;

import java.util.ArrayList;

public class UyeYemeklerActivity extends AppCompatActivity {

    private ActivityUyeYemeklerBinding binding;

    private FirebaseDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDatabase = FirebaseDatabase.getInstance();

        binding = ActivityUyeYemeklerBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getirBesinler();
    }

    private void getirBesinler() {
        DatabaseReference databaseRef = mDatabase.getReference();
        DatabaseReference foodsRef = databaseRef.child("foods");

        ValueEventListener foodsListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<BesinBilgisiModel> besinler = new ArrayList<>();

                for (DataSnapshot besinSnapshot : snapshot.getChildren()) {
                    String isim = besinSnapshot.child("ad").getValue(String.class);
                    String kalori = besinSnapshot.child("kalori").getValue(String.class);
                    besinler.add(new BesinBilgisiModel(isim, kalori));
                }
                besinleriYerlestir(besinler);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        foodsRef.addListenerForSingleValueEvent(foodsListener);


    }

    private void besinleriYerlestir(ArrayList<BesinBilgisiModel> besinler) {
        binding.rvBesinler.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvBesinler.setLayoutManager(layoutManager);
        BesinBilgisiAdapter adapter = new BesinBilgisiAdapter(this, besinler);
        binding.rvBesinler.setAdapter(adapter);


        // binding.rvMotivasyon.setHasFixedSize(true);
        //        binding.rvMotivasyon.setLayoutManager(layoutManager);
        //
        //        MesajAdapter adapter = new MesajAdapter(this, mesajlar);
        //        binding.rvMotivasyon.setAdapter(adapter);

    }
}