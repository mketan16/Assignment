-- --------------------------------------------------------
-- Host:                         localhost
-- Server version:               10.4.11-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.0.0.6037
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for event_db
CREATE DATABASE IF NOT EXISTS `event_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `event_db`;

-- Dumping structure for table event_db.event
CREATE TABLE IF NOT EXISTS `event` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `eventName` varchar(50) DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `startTime` varchar(50) DEFAULT NULL,
  `endTime` varchar(50) DEFAULT NULL,
  `dow` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table event_db.event: ~4 rows (approximately)
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` (`Id`, `eventName`, `startDate`, `endDate`, `startTime`, `endTime`, `dow`) VALUES
	(739, 'ata', '2021-03-02', '2021-03-07', '00:00', '01:30', ' T W T F S S'),
	(740, 'ata', '2021-03-08', '2021-03-14', '00:00', '01:30', ' M T W T F S S'),
	(743, 'test', '2021-03-01', '2021-03-05', '00:00', '02:00', ' M T W T F'),
	(744, 'test', '2021-03-08', '2021-03-12', '00:00', '02:00', ' M T W T F'),
	(745, 'test', '2021-03-15', '2021-03-16', '00:00', '02:00', ' M T'),
	(746, 'klk', '2021-03-06', '2021-03-07', '02:00', '02:30', ' S S'),
	(747, 'klk', '2021-03-13', '2021-03-14', '02:00', '02:30', ' S S'),
	(748, 'klk', '2021-03-20', '2021-03-21', '02:00', '02:30', ' S S'),
	(749, 'klj', '2021-03-06', '2021-03-07', '04:30', '05:30', ' S S'),
	(750, 'klj', '2021-03-13', '2021-03-14', '04:30', '05:30', ' S S');
/*!40000 ALTER TABLE `event` ENABLE KEYS */;

-- Dumping structure for table event_db.user
CREATE TABLE IF NOT EXISTS `user` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table event_db.user: ~0 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`Id`, `userName`, `password`) VALUES
	(1, 'test', 'abc');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
