package com.example.aplikasiabsensiswa.Classes;

import android.content.Intent;

public class Kelas {
    public String Jurusan;
    public Integer Kelas;

    public Long studentCount;

    // Make sure the 's' in 'studentCount' is lowercase
    public void setStudentCount(Long studentCount) {
        this.studentCount = studentCount;
    }

    // Add a getter for studentCount
    public Long getStudentCount() {
        return this.studentCount;
    }

    public Kelas(){

    }

    public Kelas(String Jurusan, Integer Kelas){
        this.Jurusan = Jurusan;
        this.Kelas = Kelas;
    }

}
