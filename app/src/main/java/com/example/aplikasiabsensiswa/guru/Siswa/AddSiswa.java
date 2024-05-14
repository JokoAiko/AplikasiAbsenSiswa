package com.example.aplikasiabsensiswa.guru.Siswa;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aplikasiabsensiswa.Classes.Siswa;
import com.example.aplikasiabsensiswa.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddSiswa extends AppCompatActivity {

    Button submit;
    EditText nama, nis, address;
    RadioGroup gender;

    DatabaseReference siswaRef = FirebaseDatabase.getInstance().getReference("Siswa");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_siswa);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        submit = findViewById(R.id.submitAddSiswa);
        nama = findViewById(R.id.textNamaSiswa);
        nis = findViewById(R.id.textNisSiswa);
        address = findViewById(R.id.textAddressSiswa);
        gender = findViewById(R.id.genderGroup);


        submit.setOnClickListener(v -> {
            Intent thisIntent = getIntent();

            int selectedId = gender.getCheckedRadioButtonId();
            RadioButton radioButton = findViewById(selectedId);
            String gender = radioButton.getText().toString();
            String nama = this.nama.getText().toString();
            String nis = this.nis.getText().toString();
            String address = this.address.getText().toString();

            Siswa siswa = new Siswa(nis, address, gender, nama);
            siswa.setKelas_id(thisIntent.getStringExtra("kelasKey"));
            siswaRef.push().setValue(siswa);
            Intent intent = new Intent(AddSiswa.this, AllSiswa.class);
            intent.putExtra("kelasKey", thisIntent.getStringExtra("kelasKey"));
            startActivity(intent);
            finish();


        });

    }
}