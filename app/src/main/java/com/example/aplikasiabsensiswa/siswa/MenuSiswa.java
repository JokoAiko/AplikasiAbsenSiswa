package com.example.aplikasiabsensiswa.siswa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.aplikasiabsensiswa.R;

import org.w3c.dom.Text;

public class MenuSiswa extends AppCompatActivity {

    private Button btnUpload, btnRiwayat;
    private TextView tvNama, tvNIS;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAMA = "nama";
    private static final String KEY_NIP = "nip";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_siswa);

        tvNama = findViewById(R.id.tvNama);
        tvNIS = findViewById(R.id.tvNIS);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        String nama = sharedPreferences.getString(KEY_NAMA,null);
        String nip = sharedPreferences.getString(KEY_NIP,null);
    }
}
