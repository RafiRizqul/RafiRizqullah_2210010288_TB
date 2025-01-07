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

public class TransaksiBarangMasuk {
    private int idTransaksi;
    private Date tanggalTransaksi;
    private int idBarang;
    private int idSupplier;
    private int jumlahMasuk;
    private double totalHarga;

    // Constructor tanpa parameter
    public TransaksiBarangMasuk() {}

    // Constructor dengan parameter
    public TransaksiBarangMasuk(int idTransaksi, Date tanggalTransaksi, int idBarang, int idSupplier, int jumlahMasuk, double totalHarga) {
        this.idTransaksi = idTransaksi;
        this.tanggalTransaksi = tanggalTransaksi;
        this.idBarang = idBarang;
        this.idSupplier = idSupplier;
        this.jumlahMasuk = jumlahMasuk;
        this.totalHarga = totalHarga;
    }

    // Getter dan Setter
    public int getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public Date getTanggalTransaksi() {
        return tanggalTransaksi;
    }

    public void setTanggalTransaksi(Date tanggalTransaksi) {
        this.tanggalTransaksi = tanggalTransaksi;
    }

    public int getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(int idBarang) {
        this.idBarang = idBarang;
    }

    public int getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(int idSupplier) {
        this.idSupplier = idSupplier;
    }

    public int getJumlahMasuk() {
        return jumlahMasuk;
    }

    public void setJumlahMasuk(int jumlahMasuk) {
        this.jumlahMasuk = jumlahMasuk;
    }

    public double getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(double totalHarga) {
        this.totalHarga = totalHarga;
    }

    // Method untuk menyimpan data ke database
    public void saveToDatabase(Connection conn) throws SQLException {
        String query = "INSERT INTO transaksi_barang_masuk (tanggal_transaksi, id_barang, id_supplier, jumlah_masuk, total_harga) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setDate(1, new java.sql.Date(tanggalTransaksi.getTime()));
        stmt.setInt(2, idBarang);
        stmt.setInt(3, idSupplier);
        stmt.setInt(4, jumlahMasuk);
        stmt.setDouble(5, totalHarga);
        stmt.executeUpdate();
        stmt.close();
    }

    // Method untuk mengambil data berdasarkan id transaksi
    public static TransaksiBarangMasuk getById(Connection conn, int id) throws SQLException {
        String query = "SELECT * FROM transaksi_barang_masuk WHERE id_transaksi = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        TransaksiBarangMasuk transaksi = null;
        if (rs.next()) {
            transaksi = new TransaksiBarangMasuk();
            transaksi.idTransaksi = rs.getInt("id_transaksi");
            transaksi.tanggalTransaksi = rs.getDate("tanggal_transaksi");
            transaksi.idBarang = rs.getInt("id_barang");
            transaksi.idSupplier = rs.getInt("id_supplier");
            transaksi.jumlahMasuk = rs.getInt("jumlah_masuk");
            transaksi.totalHarga = rs.getDouble("total_harga");
        }
        rs.close();
        stmt.close();
        return transaksi;
    }
    
    // Method untuk mengambil semua data transaksi
    public static List<TransaksiBarangMasuk> getAll(Connection conn) throws SQLException {
        String query = "SELECT * FROM transaksi_barang_masuk";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        List<TransaksiBarangMasuk> list = new ArrayList<>();
        while (rs.next()) {
            TransaksiBarangMasuk transaksi = new TransaksiBarangMasuk();
            transaksi.idTransaksi = rs.getInt("id_transaksi");
            transaksi.tanggalTransaksi = rs.getDate("tanggal_transaksi");
            transaksi.idBarang = rs.getInt("id_barang");
            transaksi.idSupplier = rs.getInt("id_supplier");
            transaksi.jumlahMasuk = rs.getInt("jumlah_masuk");
            transaksi.totalHarga = rs.getDouble("total_harga");
            list.add(transaksi);
        }
        rs.close();
        stmt.close();
        return list;
    }

    // Method untuk membuat tabel transaksi_barang_masuk jika belum ada
    public static void createTable(Connection conn) throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS transaksi_barang_masuk ("
            + "id_transaksi INT AUTO_INCREMENT PRIMARY KEY, "
            + "tanggal_transaksi DATE NOT NULL, "
            + "id_barang INT NOT NULL, "
            + "id_supplier INT NOT NULL, "
            + "jumlah_masuk INT NOT NULL, "
            + "total_harga DOUBLE NOT NULL, "
            + "FOREIGN KEY (id_barang) REFERENCES master_barang(id_barang), "
            + "FOREIGN KEY (id_supplier) REFERENCES master_supplier(id_supplier)"
            + ")";

        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(query);
            System.out.println("Tabel transaksi_barang_masuk berhasil dibuat (jika belum ada).");
        }
    }
}
