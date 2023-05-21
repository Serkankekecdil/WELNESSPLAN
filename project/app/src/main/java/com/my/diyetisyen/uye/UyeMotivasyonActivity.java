package com.my.diyetisyen.uye;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.my.diyetisyen.adapter.MesajAdapter;
import com.my.diyetisyen.databinding.ActivityUyeMotivasyonBinding;
import com.my.diyetisyen.modeller.MesajModel;

import java.util.ArrayList;
import java.util.Collections;

public class UyeMotivasyonActivity extends AppCompatActivity {

    private ActivityUyeMotivasyonBinding binding;

    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();

        binding = ActivityUyeMotivasyonBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getirMesajlar();
    }

    private void getirMesajlar() {
        DatabaseReference databaseRef = mDatabase.getReference();
        DatabaseReference motivationsRef = databaseRef.child("motivations");

        ValueEventListener motivationsListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<MesajModel> mesajlar = new ArrayList<>();

                for (DataSnapshot motivationSnapshot : snapshot.getChildren()) {
                    String tarih = motivationSnapshot.child("tarih").getValue(String.class);
                    String yazi = motivationSnapshot.child("yazi").getValue(String.class);
                    mesajlar.add(new MesajModel(tarih, yazi));
                }
                mesajlariYerlestir(mesajlar);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        motivationsRef.addListenerForSingleValueEvent(motivationsListener);

//        mesajlariYerlestir(mesajlar);
    }

    private void mesajlariYerlestir(ArrayList<MesajModel> mesajlar) {
        binding.rvMotivasyon.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvMotivasyon.setLayoutManager(layoutManager);
        Collections.reverse(mesajlar);
        MesajAdapter adapter = new MesajAdapter(this, mesajlar);
        binding.rvMotivasyon.setAdapter(adapter);
    }
}