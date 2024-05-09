package com.example.aplikasiabsensiswa.siswa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.aplikasiabsensiswa.R;

import org.w3c.dom.Text;

public class MenuSiswa extends AppCompatActivity {

    private Button btnUpload, btnRiwayat;
    private TextView tvNama, tvNIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_siswa);
    }
}
