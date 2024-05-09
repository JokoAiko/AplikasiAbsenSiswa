package com.example.aplikasiabsensiswa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Menggunakan handler untuk menunda perpindahan ke halaman utama
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Mulai HalamanAwalActivity setelah 2 detik
                Intent intent = new Intent(MainActivity.this, HalamanAwal.class);
                startActivity(intent);
                finish();
            }
        }, 2000); // Delay selama 2 detik
    }
}


