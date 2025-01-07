/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rafirizqullah_2210010288_tb;

/**
 *
 * @author ACER
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MasterBarang {

    private int idBarang;
    private String namaBarang;
    private String kategoriBarang;
    private int stokAwal;
    private String satuan;
    private double hargaSatuan;
    private Date tanggalDitambahkan;

    // Constructor tanpa parameter
    public MasterBarang() {
    }

    // Constructor dengan parameter
    public MasterBarang(int idBarang, String namaBarang, String kategoriBarang, int stokAwal, String satuan, double hargaSatuan, Date tanggalDitambahkan) {
        this.idBarang = idBarang;
        this.namaBarang = namaBarang;
        this.kategoriBarang = kategoriBarang;
        this.stokAwal = stokAwal;
        this.satuan = satuan;
        this.hargaSatuan = hargaSatuan;
        this.tanggalDitambahkan = tanggalDitambahkan;
    }

    // Getter dan Setter
    public int getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(int idBarang) {
        this.idBarang = idBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getKategoriBarang() {
        return kategoriBarang;
    }

    public void setKategoriBarang(String kategoriBarang) {
        this.kategoriBarang = kategoriBarang;
    }

    public int getStokAwal() {
        return stokAwal;
    }

    public void setStokAwal(int stokAwal) {
        this.stokAwal = stokAwal;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public double getHargaSatuan() {
        return hargaSatuan;
    }

    public void setHargaSatuan(double hargaSatuan) {
        this.hargaSatuan = hargaSatuan;
    }

    public Date getTanggalDitambahkan() {
        return tanggalDitambahkan;
    }

    public void setTanggalDitambahkan(Date tanggalDitambahkan) {
        this.tanggalDitambahkan = tanggalDitambahkan;
    }

    // Method untuk menyimpan data ke database
    public void simpan(Connection conn) throws SQLException {
        String query = "INSERT INTO master_barang (nama_barang, kategori_barang, stok_awal, satuan, harga_satuan, tanggal_ditambahkan) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, this.namaBarang);
            stmt.setString(2, this.kategoriBarang);
            stmt.setInt(3, this.stokAwal);
            stmt.setString(4, this.satuan);
            stmt.setDouble(5, this.hargaSatuan);
            stmt.setDate(6, this.tanggalDitambahkan);
            stmt.executeUpdate();
        }
    }

    // Method untuk mengambil semua data dari tabel
    public static List<MasterBarang> getAll(Connection conn) throws SQLException {
        List<MasterBarang> listBarang = new ArrayList<>();
        String query = "SELECT * FROM master_barang";

        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                MasterBarang barang = new MasterBarang();
                barang.setIdBarang(rs.getInt("id_barang"));
                barang.setNamaBarang(rs.getString("nama_barang"));
                barang.setKategoriBarang(rs.getString("kategori_barang"));
                barang.setStokAwal(rs.getInt("stok_awal"));
                barang.setSatuan(rs.getString("satuan"));
                barang.setHargaSatuan(rs.getDouble("harga_satuan"));
                barang.setTanggalDitambahkan(rs.getDate("tanggal_ditambahkan"));

                listBarang.add(barang);
            }
        }
        return listBarang;
    }

    public static void createTable(Connection conn) throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS master_barang ("
                + "id_barang INT AUTO_INCREMENT PRIMARY KEY, "
                + "nama_barang VARCHAR(255) NOT NULL, "
                + "kategori_barang VARCHAR(100) NOT NULL, "
                + "stok_awal INT NOT NULL, "
                + "satuan VARCHAR(50) NOT NULL, "
                + "harga_satuan DOUBLE NOT NULL, "
                + "tanggal_ditambahkan DATE NOT NULL"
                + ")";

        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(query);
            System.out.println("Tabel master_barang berhasil dibuat (jika belum ada).");
        }
    }

}
