package com.my.diyetisyen.uye;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.my.diyetisyen.SplashActivity;
import com.my.diyetisyen.databinding.ActivityUyeProfilBinding;

public class UyeProfilActivity extends AppCompatActivity {
    private ActivityUyeProfilBinding binding;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("XXX", "Üye Profil açıldı");

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();

        binding = ActivityUyeProfilBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        uyeBilgileriniGetir();
        handleClicks();
    }

    private void handleClicks() {
        binding.cardCikisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UyeProfilActivity.this, "Çıkış Yapılıyor...", Toast.LENGTH_SHORT).show();
                cikisYap();
            }
        });
    }

    private void cikisYap() {
        mAuth.signOut();
        Intent intent = new Intent(UyeProfilActivity.this, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void uyeBilgileriniGetir() {
        DatabaseReference databaseRef = mDatabase.getReference();
        DatabaseReference kullanicilarRef = databaseRef.child("kullanicilar").child(mAuth.getCurrentUser().getUid());

        ValueEventListener kullaniciBilgisi = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String adSoyad = snapshot.child("adSoyad").getValue(String.class);
                Boolean diyetisyenMi = snapshot.child("diyetisyenMi").getValue(Boolean.class);
                String email = snapshot.child("email").getValue(String.class);

                Log.e("adSoyad", adSoyad);
                Log.e("diyetisyenMi", diyetisyenMi.toString());
                Log.e("email", email);

                verileriYerlestir(adSoyad, diyetisyenMi, email);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        kullanicilarRef.addListenerForSingleValueEvent(kullaniciBilgisi);
//        UyeBilgisiModel uyeBilgisi = new UyeBilgisiModel("test@gmail.com");
//
//        binding.tvEmail.setText(uyeBilgisi.getEmail());

    }

    private void verileriYerlestir(String adSoyad, Boolean diyetisyenMi, String email) {
        binding.tvEmail.setText(email);
        binding.tvNameAndSurname.setText(adSoyad);
        String rol = "";
        if (diyetisyenMi) {
            rol = "Diyetisyen";
        } else {
            rol = "Üye";
        }
        binding.tvRole.setText(rol);
    }
}