package com.my.diyetisyen.diyetisyen;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.my.diyetisyen.databinding.ActivityDiyetisyenMotivasyonEkleBinding;
import com.my.diyetisyen.modeller.MesajModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DiyetisyenMotivasyonEkleActivity extends AppCompatActivity {

    private ActivityDiyetisyenMotivasyonEkleBinding binding;
    private FirebaseDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance();

        binding = ActivityDiyetisyenMotivasyonEkleBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        handleClicks();
    }

    private void handleClicks() {

        binding.cardMesajGonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mesajGonder();
            }
        });

    }

    private void mesajGonder() {
        String mesaj = binding.etMesaj.getText().toString().trim();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            if (mesaj.isEmpty()) {
                Toast.makeText(this, "Mesaj girmelisiniz", Toast.LENGTH_SHORT).show();
            } else {
                LocalDate now = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
                String formattedDate = now.format(formatter);

                DatabaseReference databaseRef = mDatabase.getReference();
                DatabaseReference motivationsRef = databaseRef.child("motivations");
                MesajModel mesajModel = new MesajModel(formattedDate, mesaj);

                motivationsRef.push().setValue(mesajModel);

                onBackPressed();

            }
        }

    }
}