package com.example.aplikasiabsensiswa.guru;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aplikasiabsensiswa.Classes.Kelas;
import com.example.aplikasiabsensiswa.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class AddKelas extends AppCompatActivity {
    Button addButton;
    EditText editText;
    EditText editText2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_kelas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get the class name from EditText
        editText = findViewById(R.id.textJurusan);
        editText2 = findViewById(R.id.textKelas);
        addButton = findViewById(R.id.submitKelas);

        addButton.setOnClickListener(v -> {
            String Jurusan = editText.getText().toString();
            int Kelas = Integer.parseInt(editText2.getText().toString());

            Kelas kelas = new Kelas(Jurusan, Kelas);

            DatabaseReference classRef = FirebaseDatabase.getInstance().getReference("Kelas");
            String key = classRef.push().getKey();
            classRef.child(key).setValue(kelas);
            Intent intent = new Intent(AddKelas.this, EditKelas.class);
            startActivity(intent);
            finish();
        });


    }
}