package com.example.aplikasiabsensiswa.guru;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aplikasiabsensiswa.Classes.Kelas;
import com.example.aplikasiabsensiswa.R;
import com.example.aplikasiabsensiswa.guru.Siswa.AllSiswa;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailedKelas extends AppCompatActivity {


    public EditText jurusan;
    public EditText kelas;

    public Button updateButton;
    public Button deleteButton;

    DatabaseReference kelasRef = FirebaseDatabase.getInstance().getReference("Kelas");
    public void updateKelas(String jurusan, Integer kelas, String key){
        Kelas updatedKelas = new Kelas(jurusan, kelas);
        kelasRef.child(key).setValue(updatedKelas);
    }

    public void deleteKelas(String key){
        new AlertDialog.Builder(DetailedKelas.this)
                .setTitle("Delete Confirmation")
                .setMessage("Are you sure you want to delete this class?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        kelasRef.child(key).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(DetailedKelas.this, "Data has been deleted", Toast.LENGTH_SHORT).show();
                                Intent kelasIntent = new Intent(DetailedKelas.this, EditKelas.class);
                                startActivity(kelasIntent);
                                finish();
                            }
                        });
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detailed_kelas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent intent = getIntent();
        TextView title = findViewById(R.id.detailedKelasTitle);
        title.setText(intent.getIntExtra("kelas", 1) + " " + intent.getStringExtra("jurusan"));

        jurusan = findViewById(R.id.textDetailedJurusan);
        kelas = findViewById(R.id.textDetailedKelas);
        jurusan.setText(intent.getStringExtra("jurusan"));
        kelas.setText("" + intent.getIntExtra("kelas", 1) );
        updateButton = findViewById(R.id.updateKelas);
        deleteButton = findViewById(R.id.deleteKelas);
        Button lihatSiswa = findViewById(R.id.lihatSiswa);

        updateButton.setOnClickListener(v -> {
            String inputedJurusan = jurusan.getText().toString();
            String inputedKelas = kelas.getText().toString();

            updateKelas(inputedJurusan, Integer.parseInt(inputedKelas), intent.getStringExtra("kelasKey"));
            Toast.makeText(DetailedKelas.this, "Data has been updated", Toast.LENGTH_SHORT).show();

        });

        lihatSiswa.setOnClickListener(v -> {
            Intent siswaIntent = new Intent(DetailedKelas.this, AllSiswa.class);
            siswaIntent.putExtra("kelasKey", intent.getStringExtra("kelasKey"));
            startActivity(siswaIntent);
        });

        deleteButton.setOnClickListener(v -> {
            deleteKelas(intent.getStringExtra("kelasKey"));
        });


    }
}