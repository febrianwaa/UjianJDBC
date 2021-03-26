-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 26, 2021 at 10:25 AM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `belajarmysql`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllSalary` (IN `gaji` INT(100))  NO SQL
SELECT * FROM salary WHERE salary.salary >= gaji$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ulangBulan` (`dateFrom` DATETIME, `platfon` INT, `bunga` REAL, `lamapinjaman` INT)  BEGIN
    
   		DECLARE df DATETIME;
        DECLARE lama INT;
        DECLARE intTotalAngsuran REAL;
        DECLARE intAngsuranBunga REAL;
        DECLARE intSisaPinjaman REAL;
        DECLARE intAngsuranPokok REAL;
       
  
    SET lama = 0;
    
    IF ( DATE_FORMAT(dateFrom ,'%d-%m-%Y') = '00-00-0000') THEN
               SET df = CURRENT_DATE();
           ELSE
               SET df = dateFrom;
           END IF;
    
    DROP TEMPORARY TABLE IF EXISTS dummy;
    
    CREATE TEMPORARY TABLE dummy(
       angsuranke int,
       tanggal VARCHAR(100),
       totalAngsuran REAL,
       angsuranPokok REAL,
       angsuranBunga REAL,
       sisaPinjaman REAL
       
    );

    WHILE  lama < lamapinjaman DO
           
           
 
 			SET intTotalAngsuran  = hitungAngsuran(platfon,bunga,lamapinjaman);
           IF ( lama <1) THEN
             
            
              SET intAngsuranBunga = angsuranBunga(30 , bunga , platfon);
              SET intAngsuranPokok = intTotalAngsuran - intAngsuranBunga;
              SET intSisaPinjaman =  platfon-intAngsuranPokok;
              
           ELSE
           
             SET intAngsuranBunga = angsuranBunga(30 , bunga , intSisaPinjaman);
             SET intAngsuranPokok = intTotalAngsuran - intAngsuranBunga;
             SET intSisaPinjaman =  intSisaPinjaman-intAngsuranPokok;
             
           END IF;
        
           
             
            SET intAngsuranPokok = intTotalAngsuran - intAngsuranBunga;
            
           
  
            insert into dummy  values (lama +1 , DATE_FORMAT(df ,'%d-%m-%Y'),intTotalAngsuran,intAngsuranPokok,intAngsuranBunga,intSisaPinjaman);
            
            SET df = DATE_ADD(df, INTERVAL 1 MONTH);
            SET lama = lama +1;
               
    END WHILE;
    
    select * from dummy ;
    
END$$

--
-- Functions
--
CREATE DEFINER=`root`@`localhost` FUNCTION `angsuranBunga` (`hari` INT, `bunga` REAL, `sisapinjaman` REAL) RETURNS DOUBLE BEGIN
          
           DECLARE hasil REAL;
          
           SET hasil =  bunga/360*30*sisapinjaman;
           RETURN hasil ;
           

 END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `hitungAngsuran` (`platfon` INT, `bunga` REAL, `lamapinjaman` INT) RETURNS DOUBLE BEGIN
           DECLARE bungaperbulan REAL;
           DECLARE hasil REAL;
           SET bungaperbulan = bunga/12;
           RETURN bungaperbulan*platfon*(POWER(((1+bungaperbulan)),lamapinjaman))/(POWER(((1+bungaperbulan)),lamapinjaman)-1);
           

 END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `interest` (`interest` INT(100), `nilai` INT(100)) RETURNS INT(100) NO SQL
return (nilai * interest/100) + nilai$$

CREATE DEFINER=`root`@`localhost` FUNCTION `tambah` (`nilaiA` INT(100), `id` INT(100)) RETURNS INT(100) NO SQL
BEGIN

DECLARE modulus INT;
set modulus = MOD(id,2);
IF(modulus=0) then
RETURN nilaiA + 2000;
ELSE
RETURN nilaiA+1000;
END IF;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `absensi`
--

CREATE TABLE `absensi` (
  `id` int(11) NOT NULL,
  `nik` varchar(50) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `absensi`
--

INSERT INTO `absensi` (`id`, `nik`, `start_date`, `end_date`) VALUES
(1, 'N01', '2020-10-03 00:00:00', '2020-10-03 00:00:00'),
(2, 'N02', '2020-10-03 00:00:00', '2020-10-03 00:00:00'),
(3, 'N03', '2020-10-03 09:00:00', '2020-10-03 15:00:00'),
(4, 'N05', '2020-10-03 09:00:00', '2020-10-03 15:00:00'),
(5, 'N01', '2020-10-04 09:00:00', '2020-10-04 15:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `biodata`
--

CREATE TABLE `biodata` (
  `nik` varchar(50) NOT NULL,
  `nama` varchar(100) DEFAULT NULL,
  `alamat` varchar(100) DEFAULT NULL,
  `id_salary` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `biodata`
--

INSERT INTO `biodata` (`nik`, `nama`, `alamat`, `id_salary`) VALUES
('2222222', 'febri', 'bogor', 321),
('N01', 'febri', 'bogor', 321),
('N02', 'febri', 'bogor', 321),
('N03', 'febri', 'bogor', 321),
('N04', 'febri', 'bogor', 321);

-- --------------------------------------------------------

--
-- Table structure for table `bonus`
--

CREATE TABLE `bonus` (
  `worker_ref_id` int(100) NOT NULL,
  `bonus_date` datetime NOT NULL,
  `bonus_amount` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `bonus`
--

INSERT INTO `bonus` (`worker_ref_id`, `bonus_date`, `bonus_amount`) VALUES
(1, '2016-02-20 00:00:00', 5000),
(2, '2016-06-11 00:00:00', 3000),
(3, '2016-02-20 00:00:00', 4000),
(1, '2016-02-20 00:00:00', 4500),
(2, '2016-06-11 00:00:00', 3500),
(9, '2018-04-12 00:00:00', 6000);

-- --------------------------------------------------------

--
-- Table structure for table `salary`
--

CREATE TABLE `salary` (
  `id` int(11) NOT NULL,
  `salary` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `salary`
--

INSERT INTO `salary` (`id`, `salary`) VALUES
(1, 5000),
(2, 6000),
(4, 4000),
(5, 7000);

-- --------------------------------------------------------

--
-- Table structure for table `title`
--

CREATE TABLE `title` (
  `worker_ref_id` int(100) NOT NULL,
  `worker_title` varchar(100) NOT NULL,
  `affected_from` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `title`
--

INSERT INTO `title` (`worker_ref_id`, `worker_title`, `affected_from`) VALUES
(1, 'Manager', '2016-02-20 00:00:00'),
(2, 'Executive', '2016-06-11 00:00:00'),
(8, 'Executive', '2016-06-11 00:00:00'),
(5, 'Manager', '2016-06-11 00:00:00'),
(4, 'Asst. Manager', '2016-06-11 00:00:00'),
(7, 'Executive', '2016-06-11 00:00:00'),
(6, 'Lead', '2016-06-11 00:00:00'),
(3, 'Lead', '2016-06-11 00:00:00'),
(9, 'HR', '2018-02-21 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `worker`
--

CREATE TABLE `worker` (
  `worker_id` int(11) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `salary` int(100) NOT NULL,
  `joining_date` datetime NOT NULL,
  `department` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `worker`
--

INSERT INTO `worker` (`worker_id`, `first_name`, `last_name`, `salary`, `joining_date`, `department`) VALUES
(1, 'Monika', 'Arora', 100000, '2014-02-20 09:00:00', 'HR'),
(2, 'Niharika', 'Verma', 80000, '2014-06-11 09:00:00', 'Admin'),
(3, 'Vishal', 'Singhal', 300000, '2014-02-20 09:00:00', 'HR'),
(4, 'Amitabh', 'Singh', 500000, '2014-02-20 09:00:00', 'Admin'),
(5, 'Vivek', 'Bhati', 500000, '2014-06-11 09:00:00', 'Admin'),
(6, 'Vipul', 'Diwan', 200000, '2014-06-11 09:00:00', 'Account'),
(7, 'Satish', 'Kumar', 75000, '2014-01-20 09:00:00', 'Account'),
(8, 'Geetika', 'Chauhan', 90000, '2014-04-11 09:00:00', 'Admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `absensi`
--
ALTER TABLE `absensi`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `biodata`
--
ALTER TABLE `biodata`
  ADD PRIMARY KEY (`nik`);

--
-- Indexes for table `salary`
--
ALTER TABLE `salary`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `worker`
--
ALTER TABLE `worker`
  ADD PRIMARY KEY (`worker_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `absensi`
--
ALTER TABLE `absensi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `salary`
--
ALTER TABLE `salary`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `worker`
--
ALTER TABLE `worker`
  MODIFY `worker_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
