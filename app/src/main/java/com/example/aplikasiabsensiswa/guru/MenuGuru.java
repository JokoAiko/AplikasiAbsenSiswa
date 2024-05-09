package com.example.aplikasiabsensiswa.guru;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplikasiabsensiswa.R;

public class MenuGuru extends AppCompatActivity {

    TextView tvNama, tvNIP;
    Button btnLogout;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAMA = "nama";
    private static final String KEY_NIP = "nip";

    @SuppressLint({"SetTextI18n", "ApplySharedPref"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_guru);

        tvNama = findViewById(R.id.tvNama);
        tvNIP = findViewById(R.id.tvNIP);
        btnLogout = findViewById(R.id.btnLogout);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        String nama = sharedPreferences.getString(KEY_NAMA,null);
        String nip = sharedPreferences.getString(KEY_NIP,null);

        if (nama != null && nip != null){
            tvNama.setText(nama);
            tvNIP.setText(nip);
        }

        btnLogout.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.commit();
            Toast.makeText(MenuGuru.this, "Berhasil Logout",Toast.LENGTH_SHORT).show();
            finish();
        });

    }
}