-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 22, 2025 at 02:04 PM
-- Server version: 8.0.35
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ps2024-5_prof_pred`
--

-- --------------------------------------------------------

--
-- Table structure for table `angazovanje`
--

CREATE TABLE `angazovanje` (
  `id` bigint NOT NULL,
  `profesor_id` bigint DEFAULT NULL,
  `predmet_id` bigint DEFAULT NULL,
  `vrsta` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `angazovanje`
--

INSERT INTO `angazovanje` (`id`, `profesor_id`, `predmet_id`, `vrsta`) VALUES
(8, 5, 7, 'PREDAVANJA'),
(11, 5, 8, 'PREDAVANJA');

-- --------------------------------------------------------

--
-- Table structure for table `predmet`
--

CREATE TABLE `predmet` (
  `id` bigint NOT NULL,
  `naziv` varchar(50) DEFAULT NULL,
  `sifra` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `predmet`
--

INSERT INTO `predmet` (`id`, `naziv`, `sifra`) VALUES
(7, 'P1', '11111'),
(8, 'P2', '22222'),
(9, 'P3', '33333');

-- --------------------------------------------------------

--
-- Table structure for table `profesor`
--

CREATE TABLE `profesor` (
  `id` bigint NOT NULL,
  `ime` varchar(50) DEFAULT NULL,
  `prezime` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `lozinka` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `profesor`
--

INSERT INTO `profesor` (`id`, `ime`, `prezime`, `email`, `lozinka`) VALUES
(5, 'prof1', 'prof1', 'prof1@gmail.com', 'prof1'),
(6, 'prof2', 'prof2', 'prof2@gmail.com', 'prof2');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `angazovanje`
--
ALTER TABLE `angazovanje`
  ADD PRIMARY KEY (`id`),
  ADD KEY `profesor_id` (`profesor_id`),
  ADD KEY `predmet_id` (`predmet_id`);

--
-- Indexes for table `predmet`
--
ALTER TABLE `predmet`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `profesor`
--
ALTER TABLE `profesor`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `angazovanje`
--
ALTER TABLE `angazovanje`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `predmet`
--
ALTER TABLE `predmet`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `profesor`
--
ALTER TABLE `profesor`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `angazovanje`
--
ALTER TABLE `angazovanje`
  ADD CONSTRAINT `angazovanje_ibfk_1` FOREIGN KEY (`profesor_id`) REFERENCES `profesor` (`id`),
  ADD CONSTRAINT `angazovanje_ibfk_2` FOREIGN KEY (`predmet_id`) REFERENCES `predmet` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
