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

public class MasterSupplier {
    private int idSupplier;
    private String namaSupplier;
    private String alamatSupplier;
    private String teleponSupplier;
    private String emailSupplier;
    private Date tanggalBergabung;

    // Constructor tanpa parameter
    public MasterSupplier() {}

    // Constructor dengan parameter
    public MasterSupplier(int idSupplier, String namaSupplier, String alamatSupplier, String teleponSupplier, String emailSupplier, Date tanggalBergabung) {
        this.idSupplier = idSupplier;
        this.namaSupplier = namaSupplier;
        this.alamatSupplier = alamatSupplier;
        this.teleponSupplier = teleponSupplier;
        this.emailSupplier = emailSupplier;
        this.tanggalBergabung = tanggalBergabung;
    }

    // Getter dan Setter
    public int getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(int idSupplier) {
        this.idSupplier = idSupplier;
    }

    public String getNamaSupplier() {
        return namaSupplier;
    }

    public void setNamaSupplier(String namaSupplier) {
        this.namaSupplier = namaSupplier;
    }

    public String getAlamatSupplier() {
        return alamatSupplier;
    }

    public void setAlamatSupplier(String alamatSupplier) {
        this.alamatSupplier = alamatSupplier;
    }

    public String getTeleponSupplier() {
        return teleponSupplier;
    }

    public void setTeleponSupplier(String teleponSupplier) {
        this.teleponSupplier = teleponSupplier;
    }

    public String getEmailSupplier() {
        return emailSupplier;
    }

    public void setEmailSupplier(String emailSupplier) {
        this.emailSupplier = emailSupplier;
    }

    public Date getTanggalBergabung() {
        return tanggalBergabung;
    }

    public void setTanggalBergabung(Date tanggalBergabung) {
        this.tanggalBergabung = tanggalBergabung;
    }

    // Method untuk menyimpan data ke database
    public void simpan(Connection conn) throws SQLException {
        String query = "INSERT INTO master_supplier (nama_supplier, alamat_supplier, telepon_supplier, email_supplier, tanggal_bergabung) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, this.namaSupplier);
            stmt.setString(2, this.alamatSupplier);
            stmt.setString(3, this.teleponSupplier);
            stmt.setString(4, this.emailSupplier);
            stmt.setDate(5, this.tanggalBergabung);
            stmt.executeUpdate();
        }
    }

    // Method untuk mengambil semua data dari tabel
    public static List<MasterSupplier> getAll(Connection conn) throws SQLException {
        List<MasterSupplier> listSupplier = new ArrayList<>();
        String query = "SELECT * FROM master_supplier";

        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                MasterSupplier supplier = new MasterSupplier();
                supplier.setIdSupplier(rs.getInt("id_supplier"));
                supplier.setNamaSupplier(rs.getString("nama_supplier"));
                supplier.setAlamatSupplier(rs.getString("alamat_supplier"));
                supplier.setTeleponSupplier(rs.getString("telepon_supplier"));
                supplier.setEmailSupplier(rs.getString("email_supplier"));
                supplier.setTanggalBergabung(rs.getDate("tanggal_bergabung"));

                listSupplier.add(supplier);
            }
        }
        return listSupplier;
    }

    // Method untuk membuat tabel master_supplier jika belum ada
    public static void createTable(Connection conn) throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS master_supplier ("
            + "id_supplier INT AUTO_INCREMENT PRIMARY KEY, "
            + "nama_supplier VARCHAR(255) NOT NULL, "
            + "alamat_supplier TEXT NOT NULL, "
            + "telepon_supplier VARCHAR(50), "
            + "email_supplier VARCHAR(100), "
            + "tanggal_bergabung DATE NOT NULL"
            + ")";

        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(query);
            System.out.println("Tabel master_supplier berhasil dibuat (jika belum ada).");
        }
    }
}
