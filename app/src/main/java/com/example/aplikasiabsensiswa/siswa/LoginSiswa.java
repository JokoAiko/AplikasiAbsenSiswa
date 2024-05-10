package com.example.aplikasiabsensiswa.siswa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.aplikasiabsensiswa.R;
import com.example.aplikasiabsensiswa.sekretaris.RegisterSekretaris;
import com.example.aplikasiabsensiswa.guru.MenuGuru;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginSiswa extends AppCompatActivity {


    private EditText etNama, etNIS, etPassword;
    private Button btnLogin, btnDaftar;
    SharedPreferences sharedPreferences;
    private DatabaseReference database;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAMA = "nama";
    private static final String KEY_NIS = "nis";
    private static final String KEY_PASSWORD = "pasword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_siswa);

        btnLogin = findViewById(R.id.btnLogin);
        btnDaftar = findViewById(R.id.btnDaftar);
        etNama = findViewById(R.id.etNama);
        etNIS = findViewById(R.id.etNIS);
        etPassword = findViewById(R.id.etPassword);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);

        String nama = sharedPreferences.getString(KEY_NAMA,null);

        if (nama != null){
            Intent intent = new Intent(LoginSiswa.this, MenuGuru.class);
            startActivity(intent);
        }

        btnDaftar.setOnClickListener(v -> {
            Intent intent2 = new Intent(LoginSiswa.this, RegisterSekretaris.class);
            startActivity(intent2);
        });

        btnLogin.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(KEY_NAMA, etNama.getText().toString());
            editor.putString(KEY_NIS, etNIS.getText().toString());
            editor.putString("Role", "siswa");
            editor.putString(KEY_PASSWORD, etPassword.getText().toString());
            editor.apply();

            database = FirebaseDatabase.getInstance().getReference("userSekretaris");

            if(KEY_NAMA.isEmpty() || KEY_NIS.isEmpty() || KEY_PASSWORD.isEmpty()){
                Toast.makeText(getApplicationContext(),"Lengkapi Data Terlebih Dahulu", Toast.LENGTH_SHORT).show();
            }else{
                database.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String nama = etNama.getText().toString();
                        if (snapshot.child(nama).exists()) {
                            String nip = snapshot.child(nama).child("nip").getValue(String.class);
                            String password = snapshot.child(nama).child("password").getValue(String.class);
                            if (nip.equals(etNIS.getText().toString()) && password.equals(etPassword.getText().toString())) {
                                Toast.makeText(getApplicationContext(),"Login Berhasil", Toast.LENGTH_SHORT).show();
                                Intent login = new Intent(getApplicationContext(), MenuGuru.class);
                                startActivity(login);
                                finish();

                            } else {
                                Toast.makeText(getApplicationContext(),"NIP atau password salah", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(),"Data Belum Terdaftar :(", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

        });

    }
}