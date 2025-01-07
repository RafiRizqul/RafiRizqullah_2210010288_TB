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

    // Constructor, Getter, dan Setter
    public TransaksiBarangMasuk() {}

    // Getters dan Setters
    // ... (lengkapi dengan metode getter dan setter untuk semua atribut)

    // Method untuk menyimpan data
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

    // Method untuk mengambil data
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
}
