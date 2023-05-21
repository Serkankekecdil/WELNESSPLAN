package com.my.diyetisyen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.my.diyetisyen.databinding.ActivityRegisterBinding;
import com.my.diyetisyen.modeller.YeniKullanici;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;

    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();


        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        handleClicks();
    }

    private void handleClicks() {
        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.tilEmail.getEditText().getText().toString().trim();
                String parola = binding.tilPassword.getEditText().getText().toString().trim();
                String adSoyad = binding.tilAdSoyad.getEditText().getText().toString().trim();
                Boolean diyetisyenMi = binding.switchDietitian.isChecked();

                Log.e("email", email);
                Log.e("parola", parola);
                Log.e("adSoyad", adSoyad);
                Log.e("diyetisyenMi", diyetisyenMi.toString());

                if (email.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Email adresi girmelisiniz", Toast.LENGTH_SHORT).show();
                } else if (parola.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Parola girmelisiniz", Toast.LENGTH_SHORT).show();
                } else if (adSoyad.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Adınızı ve Soyadınızı girmelisiniz", Toast.LENGTH_SHORT).show();
                } else {
                    registerProcess(email, parola, adSoyad, diyetisyenMi);
                }

            }
        });

    }

    private void registerProcess(String email, String parola, String adSoyad, Boolean diyetisyenMi) {
        mAuth.createUserWithEmailAndPassword(email, parola).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    kullanicilarTablosunaEkle(user, email, adSoyad, diyetisyenMi);
                } else {
                    Toast.makeText(RegisterActivity.this, "Kayıt İşlemi Başarısız", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void kullanicilarTablosunaEkle(FirebaseUser user, String email, String adSoyad, Boolean diyetisyenMi) {
        Log.e("TAG", "Kullanıcılar tablosuna ekleyebiliriz");
        YeniKullanici yeniKullanici = new YeniKullanici(adSoyad, user.getEmail(), diyetisyenMi);
        mDatabase.getReference().child("kullanicilar").child(user.getUid()).setValue(yeniKullanici);

        girisSayfasinaGit();
    }

    private void girisSayfasinaGit() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        RegisterActivity.this.finish();
    }
}