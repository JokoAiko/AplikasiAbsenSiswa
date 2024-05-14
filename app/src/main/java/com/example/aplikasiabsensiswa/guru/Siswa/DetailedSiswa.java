package com.example.aplikasiabsensiswa.guru.Siswa;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
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

public class DetailedSiswa extends AppCompatActivity {

    EditText nama, nis, address;
    Button update, delete;

    RadioGroup genderGroup;

    DatabaseReference siswaRef = FirebaseDatabase.getInstance().getReference("Siswa");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detailed_siswa);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nama = findViewById(R.id.textNamaSiswaUpdate);
        nis = findViewById(R.id.textNisSiswaUpdate);
        address = findViewById(R.id.textAddressSiswaUpdate);
        genderGroup = findViewById(R.id.genderGroupUpdate);

        nama.setText(getIntent().getStringExtra("siswaNama"));
        nis.setText(getIntent().getStringExtra("siswaNis"));
        address.setText(getIntent().getStringExtra("siswaAddress"));

        if (getIntent().getStringExtra("siswaGender").equals("male")) {
            genderGroup.check(R.id.maleUpdate);
        } else {
            genderGroup.check(R.id.femaleUpdate);
        }

        update = findViewById(R.id.btnUpdateSiswa);
        delete = findViewById(R.id.btnDeleteSiswa);

        update.setOnClickListener(v -> {
            String inputedNama = nama.getText().toString();
            String inputedNis = nis.getText().toString();
            String inputedAddress = address.getText().toString();
            int selectedGenderId = genderGroup.getCheckedRadioButtonId();
            String selectedGender = selectedGenderId == R.id.maleUpdate ? "male" : "female";

            Siswa updatedSiswa = new Siswa(inputedNis, inputedAddress, selectedGender, inputedNama);
            updatedSiswa.setKelas_id(getIntent().getStringExtra("kelasKey"));
            siswaRef.child(getIntent().getStringExtra("siswaKey")).setValue(updatedSiswa);
            finish();
        });

        delete.setOnClickListener(v -> {
            siswaRef.child(getIntent().getStringExtra("siswaKey")).removeValue();
            finish();
        });



    }
}