package com.example.aplikasiabsensiswa.guru;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aplikasiabsensiswa.R;
import com.example.aplikasiabsensiswa.guru.LoginGuru;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterGuru extends AppCompatActivity {

    private EditText etNama, etNIP, etEmail, etPassword;
    private Button btnDaftar;

    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_guru);

        etNama = findViewById(R.id.etNama);
        etNIP = findViewById(R.id.etNIP);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnDaftar = findViewById(R.id.btnDaftar);

        database = FirebaseDatabase.getInstance().getReferenceFromUrl("https://aplikasi-absen-siswa-default-rtdb.firebaseio.com/");

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nama = etNama.getText().toString();
                String nip = etNIP.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                if (nama.isEmpty() || nip.isEmpty() || email.isEmpty() || password.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Data Belum Lengkap",Toast.LENGTH_SHORT).show();
                }else {
                    database = FirebaseDatabase.getInstance().getReference("users");
                    database.child(nama).child("nama").setValue(nama);
                    database.child(nama).child("nip").setValue(nip);
                    database.child(nama).child("email").setValue(email);
                    database.child(nama).child("password").setValue(password);

                    Toast.makeText(getApplicationContext(), "Berhasil Daftar",Toast.LENGTH_SHORT).show();
                    Intent daftar = new Intent(getApplicationContext(), LoginGuru.class);
                    startActivity(daftar);
                }
            }
        });
    }
}