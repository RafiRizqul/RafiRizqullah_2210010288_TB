-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 07 Jan 2025 pada 17.05
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gudang_db`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `master_barang`
--

CREATE TABLE `master_barang` (
  `id_barang` int(11) NOT NULL,
  `nama_barang` varchar(255) NOT NULL,
  `kategori_barang` varchar(100) NOT NULL,
  `stok_awal` int(11) NOT NULL,
  `satuan` varchar(50) NOT NULL,
  `harga_satuan` double NOT NULL,
  `tanggal_ditambahkan` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `master_barang`
--

INSERT INTO `master_barang` (`id_barang`, `nama_barang`, `kategori_barang`, `stok_awal`, `satuan`, `harga_satuan`, `tanggal_ditambahkan`) VALUES
(2, 'Lampu LED 80 WATT', 'Elektronik', 7, 'Biji', 80000, '2025-01-07'),
(3, 'Meja', 'Pecah Belah', 10, 'Biji', 250000, '2025-01-07');

-- --------------------------------------------------------

--
-- Struktur dari tabel `master_supplier`
--

CREATE TABLE `master_supplier` (
  `id_supplier` int(11) NOT NULL,
  `nama_supplier` varchar(255) NOT NULL,
  `alamat_supplier` text NOT NULL,
  `telepon_supplier` varchar(50) DEFAULT NULL,
  `email_supplier` varchar(100) DEFAULT NULL,
  `tanggal_bergabung` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `master_supplier`
--

INSERT INTO `master_supplier` (`id_supplier`, `nama_supplier`, `alamat_supplier`, `telepon_supplier`, `email_supplier`, `tanggal_bergabung`) VALUES
(1, 'Rafi', 'BJB', '082194832932', 'rafirizkullah@gmail.com', '2025-01-07'),
(2, 'Rafi RIzkullah', 'BJM', '0812373737', 'rr@gmail.com', '2025-01-07');

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi_barang_masuk`
--

CREATE TABLE `transaksi_barang_masuk` (
  `id_transaksi` int(11) NOT NULL,
  `tanggal_transaksi` date NOT NULL,
  `id_barang` int(11) NOT NULL,
  `id_supplier` int(11) NOT NULL,
  `jumlah_masuk` int(11) NOT NULL,
  `total_harga` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `transaksi_barang_masuk`
--

INSERT INTO `transaksi_barang_masuk` (`id_transaksi`, `tanggal_transaksi`, `id_barang`, `id_supplier`, `jumlah_masuk`, `total_harga`) VALUES
(1, '2025-01-07', 2, 1, 9, 720000),
(2, '2025-01-07', 3, 2, 3, 750000);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `master_barang`
--
ALTER TABLE `master_barang`
  ADD PRIMARY KEY (`id_barang`);

--
-- Indeks untuk tabel `master_supplier`
--
ALTER TABLE `master_supplier`
  ADD PRIMARY KEY (`id_supplier`);

--
-- Indeks untuk tabel `transaksi_barang_masuk`
--
ALTER TABLE `transaksi_barang_masuk`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `id_barang` (`id_barang`),
  ADD KEY `id_supplier` (`id_supplier`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `master_barang`
--
ALTER TABLE `master_barang`
  MODIFY `id_barang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT untuk tabel `master_supplier`
--
ALTER TABLE `master_supplier`
  MODIFY `id_supplier` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `transaksi_barang_masuk`
--
ALTER TABLE `transaksi_barang_masuk`
  MODIFY `id_transaksi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `transaksi_barang_masuk`
--
ALTER TABLE `transaksi_barang_masuk`
  ADD CONSTRAINT `transaksi_barang_masuk_ibfk_1` FOREIGN KEY (`id_barang`) REFERENCES `master_barang` (`id_barang`),
  ADD CONSTRAINT `transaksi_barang_masuk_ibfk_2` FOREIGN KEY (`id_supplier`) REFERENCES `master_supplier` (`id_supplier`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
