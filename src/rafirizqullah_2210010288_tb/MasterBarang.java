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

    // Constructor, Getter, dan Setter
    public MasterBarang() {}

    // Getters dan Setters
    // ... (lengkapi dengan metode getter dan setter untuk semua atribut)

    // Method untuk menyimpan data
    public void saveToDatabase(Connection conn) throws SQLException {
        String query = "INSERT INTO master_barang (nama_barang, kategori_barang, stok_awal, satuan, harga_satuan, tanggal_ditambahkan) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, namaBarang);
        stmt.setString(2, kategoriBarang);
        stmt.setInt(3, stokAwal);
        stmt.setString(4, satuan);
        stmt.setDouble(5, hargaSatuan);
        stmt.setDate(6, new java.sql.Date(tanggalDitambahkan.getTime()));
        stmt.executeUpdate();
        stmt.close();
    }

    // Method untuk mengambil data
    public static MasterBarang getById(Connection conn, int id) throws SQLException {
        String query = "SELECT * FROM master_barang WHERE id_barang = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        MasterBarang barang = null;
        if (rs.next()) {
            barang = new MasterBarang();
            barang.idBarang = rs.getInt("id_barang");
            barang.namaBarang = rs.getString("nama_barang");
            barang.kategoriBarang = rs.getString("kategori_barang");
            barang.stokAwal = rs.getInt("stok_awal");
            barang.satuan = rs.getString("satuan");
            barang.hargaSatuan = rs.getDouble("harga_satuan");
            barang.tanggalDitambahkan = rs.getDate("tanggal_ditambahkan");
        }
        rs.close();
        stmt.close();
        return barang;
        
    }
    
      public static List<MasterBarang> getAll(Connection conn) throws SQLException {
        String query = "SELECT * FROM master_barang";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        List<MasterBarang> list = new ArrayList<>();
        while (rs.next()) {
            MasterBarang barang = new MasterBarang();
            barang.idBarang = rs.getInt("id_barang");
            barang.namaBarang = rs.getString("nama_barang");
            barang.kategoriBarang = rs.getString("kategori_barang");
            barang.stokAwal = rs.getInt("stok_awal");
            barang.satuan = rs.getString("satuan");
            barang.hargaSatuan = rs.getDouble("harga_satuan");
            barang.tanggalDitambahkan = rs.getDate("tanggal_ditambahkan");
            list.add(barang);
        }
        rs.close();
        stmt.close();
        return list;
    }
}

