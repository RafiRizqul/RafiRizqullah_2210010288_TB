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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {
    // Konfigurasi database
    private static final String URL = "jdbc:mysql://localhost:3306/gudang_db"; // Ganti dengan nama database Anda
    private static final String USERNAME = "root"; // Ganti dengan username database Anda
    private static final String PASSWORD = ""; // Ganti dengan password database Anda

    // Method untuk mendapatkan koneksi
    public static Connection getConnection() throws SQLException {
        try {
            // Memuat driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MySQL tidak ditemukan!", e);
        }

        // Membuat koneksi ke database
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        MasterBarang.createTable(conn);
        MasterSupplier.createTable(conn);
        TransaksiBarangMasuk.createTable(conn);
        return conn;
    }

    // Method untuk menutup koneksi
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
