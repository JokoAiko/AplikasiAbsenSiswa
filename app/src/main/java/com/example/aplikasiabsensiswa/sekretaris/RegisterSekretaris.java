package com.example.aplikasiabsensiswa.sekretaris;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.Toast;

import com.example.aplikasiabsensiswa.R;
import com.example.aplikasiabsensiswa.sekretaris.LoginSekretaris;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterSekretaris extends AppCompatActivity {

    private EditText etNama, etNIS, etEmail, etPassword;
    private Button btnDaftar;
    private DatabaseReference database;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_sekretaris);

        etNama = findViewById(R.id.etNama);
        etNIS =findViewById(R.id.etNIS);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnDaftar = findViewById(R.id.btnDaftar);

        database = FirebaseDatabase.getInstance().getReferenceFromUrl("https://aplikasi-absen-siswa-default-rtdb.firebaseio.com/");

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nama = etNama.getText().toString();
                String nis = etNIS.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                if (nama.isEmpty() || nis.isEmpty() || email.isEmpty() || password.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Data Belum Lengkap",Toast.LENGTH_SHORT).show();
                }else {
                    database = FirebaseDatabase.getInstance().getReference("userSekretaris");
                    database.child(nama).child("nama").setValue(nama);
                    database.child(nama).child("nis").setValue(nis);
                    database.child(nama).child("email").setValue(email);
                    database.child(nama).child("password").setValue(password);

                    Toast.makeText(getApplicationContext(), "Berhasil Daftar",Toast.LENGTH_SHORT).show();
                    Intent daftar = new Intent(getApplicationContext(), LoginSekretaris.class);
                    startActivity(daftar);
                }
            }
        });
    }
}