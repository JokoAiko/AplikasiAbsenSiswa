package com.example.aplikasiabsensiswa.Classes;

public class Siswa {
    public String nis;
    public String address;
    public String gender;

    private Boolean isCheked = false;

    public String nama;

    private String password;

    private String key;

    private String kelas_id;

    public Siswa(String nis, String address, String gender, String nama) {
        this.nis = nis;
        this.address = address;
        this.gender = gender;
        this.nama = nama;
    }

    public boolean isChecked(){
        return  this.isCheked;
    }

    public void setCheked(boolean cheked){
        isCheked = cheked;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public Siswa() {
    }


    public String getKelas_id() {
        return kelas_id;
    }

    public void setKelas_id(String kelas_id) {
        this.kelas_id = kelas_id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
