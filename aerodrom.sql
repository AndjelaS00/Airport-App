-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 26, 2022 at 04:38 AM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 8.0.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `aerodrom`
--

-- --------------------------------------------------------

--
-- Table structure for table `karta`
--

CREATE TABLE `karta` (
  `id_leta` int(100) NOT NULL,
  `ime_leta` varchar(255) NOT NULL,
  `od` varchar(255) NOT NULL,
  `do` varchar(255) NOT NULL,
  `vreme_polaska` varchar(255) NOT NULL,
  `vreme_dolaska` varchar(255) NOT NULL,
  `br_mesta` int(255) NOT NULL,
  `ime` varchar(255) NOT NULL,
  `prezime` varchar(255) NOT NULL,
  `id_p` varchar(255) NOT NULL,
  `broj_pasosa` varchar(255) NOT NULL,
  `novac` int(255) NOT NULL,
  `id_karte` int(255) NOT NULL,
  `cena` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `karta`
--

INSERT INTO `karta` (`id_leta`, `ime_leta`, `od`, `do`, `vreme_polaska`, `vreme_dolaska`, `br_mesta`, `ime`, `prezime`, `id_p`, `broj_pasosa`, `novac`, `id_karte`, `cena`) VALUES
(1, 'Beograd->Pariz', 'Beograd', 'Pariz', '8:00', '10:00', 50, 'Andjela', 'Stanojevic', '1', '100', 10000, 1, '300'),
(2, 'Podgorica->Beograd', 'Podgorica', 'Beograd', '17:30', '18:30', 50, 'Nikola', 'Veljkovic', '2', '200', 34500, 2, '70'),
(3, 'London->Bec', 'London', 'Bec', '15:15', '16:40', 100, 'Ivana', 'Djokanovic', '3', '300', 7500, 3, '178'),
(4, 'Podgorica->Pariz', 'Podgorica', 'Pariz', '17:35', '18:55', 100, 'Aleksandra', 'Misovic', '4', '400', 32000, 4, '150'),
(5, 'Beograd->London', 'Beograd', 'London', '15:15', '17:40', 70, 'Mila', 'Andjelkovic', '5', '500', 17450, 5, '500'),
(6, 'Beograd->Pariz', 'Beograd', 'Pariz', '22:30', '23:59', 100, 'Sladjana', 'Stanojevic', '7', '700', 9000, 6, '300'),
(6, 'London->Beograd', 'London', 'Beograd', '17:38', '19:55', 100, 'Ivana', 'Djokanovic', '3', '300', 7300, 7, '200'),
(8, 'Beograd->London', 'Beograd', 'London', '15:15', '17:40', 70, 'Ivana', 'Djokanovic', '3', '300', 6800, 8, '500'),
(5, 'Podgorica->Pariz', 'Podgorica', 'Pariz', '17:35', '18:55', 100, 'Ivana', 'Djokanovic', '3', '300', 6650, 9, '150'),
(4, 'Podgorica->Beograd', 'Podgorica', 'Beograd', '17:30', '18:30', 50, 'Ivana', 'Djokanovic', '3', '300', 6580, 10, '70'),
(8, 'Beograd->London', 'Beograd', 'London', '15:15', '17:40', 69, 'Andjela', 'Stanojevic', '1', '100', 9500, 11, '500');

-- --------------------------------------------------------

--
-- Table structure for table `korisnik`
--

CREATE TABLE `korisnik` (
  `id_korisnika` int(11) NOT NULL,
  `ime` varchar(255) NOT NULL,
  `prezime` varchar(255) NOT NULL,
  `lozinka` varchar(255) NOT NULL,
  `korisnicko_ime` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `korisnik`
--

INSERT INTO `korisnik` (`id_korisnika`, `ime`, `prezime`, `lozinka`, `korisnicko_ime`) VALUES
(1, 'Sladja', 'Simic', 's', 's');

-- --------------------------------------------------------

--
-- Table structure for table `let`
--

CREATE TABLE `let` (
  `id_leta` int(100) NOT NULL,
  `ime_leta` varchar(255) NOT NULL,
  `od` varchar(255) NOT NULL,
  `do` varchar(255) NOT NULL,
  `br_mesta` int(255) NOT NULL,
  `vreme_polaska` varchar(255) NOT NULL,
  `vreme_dolaska` varchar(255) NOT NULL,
  `cena` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `let`
--

INSERT INTO `let` (`id_leta`, `ime_leta`, `od`, `do`, `br_mesta`, `vreme_polaska`, `vreme_dolaska`, `cena`) VALUES
(2, 'Beograd->Pariz', 'Beograd', 'Pariz', 100, '22:30', '23:59', '300'),
(1, 'Beograd->Pariz', 'Beograd', 'Pariz', 50, '8:00', '10:00', '130'),
(3, 'Beograd->Podgorica', 'Beograd', 'Podgorica', 50, '22:30', '23:30', '70'),
(4, 'Podgorica->Beograd', 'Podgorica', 'Beograd', 50, '17:30', '18:30', '70'),
(5, 'Podgorica->Pariz', 'Podgorica', 'Pariz', 100, '17:35', '18:55', '150'),
(6, 'London->Beograd', 'London', 'Beograd', 100, '17:38', '19:55', '200'),
(7, 'London->Bec', 'London', 'Bec', 100, '15:15', '16:40', '178');

-- --------------------------------------------------------

--
-- Table structure for table `putnik`
--

CREATE TABLE `putnik` (
  `id_p` varchar(255) NOT NULL,
  `ime` varchar(255) NOT NULL,
  `prezime` varchar(255) NOT NULL,
  `broj_pasosa` varchar(255) NOT NULL,
  `novac` int(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `putnik`
--

INSERT INTO `putnik` (`id_p`, `ime`, `prezime`, `broj_pasosa`, `novac`) VALUES
('1', 'Andjela', 'Stanojevic', '100', 9500),
('2', 'Nikola ', 'Veljkovic', '200', 34500),
('3', 'Ivana', 'Djokanovic', '300', 6580),
('4', 'Aleksandra', 'Misovic', '400', 32000),
('5', 'Mila', 'Andjelkovic', '500', 17450),
('6', 'Sladjana', 'Savic', '600', 1000),
('7', 'Sladjana', 'Stanojevic', '700', 9000),
('8', 'Ognjen', 'Stanojevic', '800', 15000),
('9', 'Joakim', 'Veljkovic', '900', 18000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `korisnik`
--
ALTER TABLE `korisnik`
  ADD PRIMARY KEY (`id_korisnika`);

--
-- Indexes for table `let`
--
ALTER TABLE `let`
  ADD PRIMARY KEY (`id_leta`);

--
-- Indexes for table `putnik`
--
ALTER TABLE `putnik`
  ADD PRIMARY KEY (`id_p`),
  ADD UNIQUE KEY `broj_pasosa` (`broj_pasosa`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `korisnik`
--
ALTER TABLE `korisnik`
  MODIFY `id_korisnika` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
