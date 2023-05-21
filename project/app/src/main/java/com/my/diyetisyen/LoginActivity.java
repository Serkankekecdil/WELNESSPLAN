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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.my.diyetisyen.databinding.ActivityLoginBinding;
import com.my.diyetisyen.diyetisyen.DiyetisyenAnasayfaActivity;
import com.my.diyetisyen.uye.UyeAnasayfaActivity;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("XXX", "Login açıldı");
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        handleClicks();

    }

    private void handleClicks() {
        binding.tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.tilEmail.getEditText().getText().toString().trim();
                String parola = binding.tilPassword.getEditText().getText().toString().trim();

                Log.e("email", email);
                Log.e("parola", parola);

                if (email.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Email adresi girmelisiniz", Toast.LENGTH_SHORT).show();
                } else if (parola.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Parola girmelisiniz", Toast.LENGTH_SHORT).show();
                } else {
                    loginProcess(email, parola);
                }

            }
        });
    }

    private void loginProcess(String email, String parola) {
        mAuth.signInWithEmailAndPassword(email, parola)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.e("TAG", "Giriş başarılı, şimdi role değerini alalım");
                            kullaniciRolunuAl(email);
                        } else {
                            Toast.makeText(LoginActivity.this, "Email veya Parola Hatalı", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
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
                            Intent intent = new Intent(LoginActivity.this, DiyetisyenAnasayfaActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            LoginActivity.this.finish();
                        } else {
                            Log.e("TAG", email + " rolü: ÜYE");
                            Intent intent = new Intent(LoginActivity.this, UyeAnasayfaActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            LoginActivity.this.finish();
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