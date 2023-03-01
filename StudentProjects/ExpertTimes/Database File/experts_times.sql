-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 10, 2023 at 09:58 AM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 7.4.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `experts_times`
--

-- --------------------------------------------------------

--
-- Table structure for table `devicemapping`
--

CREATE TABLE `devicemapping` (
  `id` int(10) UNSIGNED NOT NULL,
  `deviceId` int(11) NOT NULL,
  `equipmentId` int(11) NOT NULL,
  `description` varchar(255) NOT NULL,
  `date` date DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `devicemapping`
--

INSERT INTO `devicemapping` (`id`, `deviceId`, `equipmentId`, `description`, `date`) VALUES
(1, 1, 0, ' gbdsfbs', NULL),
(2, 2, 9090, ' qwfqf', NULL),
(3, 2, 456, ' ssd', '2023-02-10');

-- --------------------------------------------------------

--
-- Table structure for table `device_data`
--

CREATE TABLE `device_data` (
  `id` int(11) NOT NULL,
  `device_id` varchar(50) DEFAULT NULL,
  `devicedate` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  `distance` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `device_data`
--

INSERT INTO `device_data` (`id`, `device_id`, `devicedate`, `time`, `distance`) VALUES
(1, '100cs', '0000-00-00', '08:00:00', 34.5);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `devicemapping`
--
ALTER TABLE `devicemapping`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `device_data`
--
ALTER TABLE `device_data`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `devicemapping`
--
ALTER TABLE `devicemapping`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `device_data`
--
ALTER TABLE `device_data`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
