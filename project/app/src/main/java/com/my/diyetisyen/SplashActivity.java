package com.my.diyetisyen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.my.diyetisyen.databinding.ActivitySplashBinding;
import com.my.diyetisyen.diyetisyen.DiyetisyenAnasayfaActivity;
import com.my.diyetisyen.uye.UyeAnasayfaActivity;

public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding binding;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("XXX", "Splash açıldı");
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();

        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        girisKontrol();


    }

    private void girisKontrol() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            // Kullanıcı giriş yapmış ise
            kullaniciRolunuAl(user.getEmail());
        } else {
            // Kullanıcı giriş yapmamış ise
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
    }

    private void kullaniciRolunuAl(String userEmail) {
        DatabaseReference kullanicilarRef = mDatabase.getReference().child("kullanicilar");
        ValueEventListener kullaniciListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot kullaniciSnapshot : snapshot.getChildren()) {
                    String email = kullaniciSnapshot.child("email").getValue(String.class);
                    if (email != null && email.equals(userEmail)) {
                        Boolean diyetisyenMi = kullaniciSnapshot.child("diyetisyenMi").getValue(Boolean.class);
                        if (diyetisyenMi != null && diyetisyenMi) {
                            Log.e("TAG", email + " rolü: DİYETİSYEN");
                            Intent intent = new Intent(SplashActivity.this, DiyetisyenAnasayfaActivity.class);
                            startActivity(intent);
                            SplashActivity.this.finish();
                        } else {
                            Log.e("TAG", email + " rolü: ÜYE");
                            Intent intent = new Intent(SplashActivity.this, UyeAnasayfaActivity.class);
                            startActivity(intent);
                            SplashActivity.this.finish();
                        }
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        kullanicilarRef.addListenerForSingleValueEvent(kullaniciListener);


    }

}
