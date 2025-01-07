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

    // Constructor, Getter, dan Setter
    public MasterSupplier() {}

    // Getters dan Setters
    // ... (lengkapi dengan metode getter dan setter untuk semua atribut)

    // Method untuk menyimpan data
    public void saveToDatabase(Connection conn) throws SQLException {
        String query = "INSERT INTO master_supplier (nama_supplier, alamat_supplier, telepon_supplier, email_supplier, tanggal_bergabung) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, namaSupplier);
        stmt.setString(2, alamatSupplier);
        stmt.setString(3, teleponSupplier);
        stmt.setString(4, emailSupplier);
        stmt.setDate(5, new java.sql.Date(tanggalBergabung.getTime()));
        stmt.executeUpdate();
        stmt.close();
    }

    // Method untuk mengambil data
    public static MasterSupplier getById(Connection conn, int id) throws SQLException {
        String query = "SELECT * FROM master_supplier WHERE id_supplier = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        MasterSupplier supplier = null;
        if (rs.next()) {
            supplier = new MasterSupplier();
            supplier.idSupplier = rs.getInt("id_supplier");
            supplier.namaSupplier = rs.getString("nama_supplier");
            supplier.alamatSupplier = rs.getString("alamat_supplier");
            supplier.teleponSupplier = rs.getString("telepon_supplier");
            supplier.emailSupplier = rs.getString("email_supplier");
            supplier.tanggalBergabung = rs.getDate("tanggal_bergabung");
        }
        rs.close();
        stmt.close();
        return supplier;
    }
    public static List<MasterSupplier> getAll(Connection conn) throws SQLException {
        String query = "SELECT * FROM master_supplier";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        List<MasterSupplier> list = new ArrayList<>();
        while (rs.next()) {
            MasterSupplier supplier = new MasterSupplier();
            supplier.idSupplier = rs.getInt("id_supplier");
            supplier.namaSupplier = rs.getString("nama_supplier");
            supplier.alamatSupplier = rs.getString("alamat_supplier");
            supplier.teleponSupplier = rs.getString("telepon_supplier");
            supplier.emailSupplier = rs.getString("email_supplier");
            supplier.tanggalBergabung = rs.getDate("tanggal_bergabung");
            list.add(supplier);
        }
        rs.close();
        stmt.close();
        return list;
    }
}

