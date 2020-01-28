/*
SQLyog Ultimate v10.42 
MySQL - 5.5.5-10.4.6-MariaDB : Database - db_lapangan
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_lapangan` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `db_lapangan`;

/*Table structure for table `lapangan` */

DROP TABLE IF EXISTS `lapangan`;

CREATE TABLE `lapangan` (
  `id_lapang` int(5) NOT NULL,
  `nama_lapang` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id_lapang`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `lapangan` */

insert  into `lapangan`(`id_lapang`,`nama_lapang`) values (3,'LAPANGAN 5'),(5,'LAPANGAN 3');

/*Table structure for table `pegawai` */

DROP TABLE IF EXISTS `pegawai`;

CREATE TABLE `pegawai` (
  `username` varchar(15) NOT NULL,
  `nama_pegawai` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `pegawai` */

insert  into `pegawai`(`username`,`nama_pegawai`,`password`) values ('ahmad','dila','ahmad'),('ucok','ucok baba','1234'),('ucok1','ucok2','ucok1');

/*Table structure for table `tarif` */

DROP TABLE IF EXISTS `tarif`;

CREATE TABLE `tarif` (
  `pk_tarif` int(11) NOT NULL,
  `id_lapang` int(11) DEFAULT NULL,
  `jam_mulai` time DEFAULT NULL,
  `jam_selesai` time DEFAULT NULL,
  `harga` int(11) DEFAULT NULL,
  PRIMARY KEY (`pk_tarif`),
  KEY `id_lapang` (`id_lapang`),
  CONSTRAINT `tarif_ibfk_1` FOREIGN KEY (`id_lapang`) REFERENCES `lapangan` (`id_lapang`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tarif` */

insert  into `tarif`(`pk_tarif`,`id_lapang`,`jam_mulai`,`jam_selesai`,`harga`) values (1,3,'08:00:00','11:59:00',100000),(2,3,'12:00:00','15:59:00',120000);

/*Table structure for table `transaksi` */

DROP TABLE IF EXISTS `transaksi`;

CREATE TABLE `transaksi` (
  `kode_transaksi` int(5) NOT NULL AUTO_INCREMENT,
  `nama_club` varchar(20) DEFAULT NULL,
  `id_lapang` int(5) DEFAULT NULL,
  `tgl_boking` date DEFAULT NULL,
  `jam_mulai` time DEFAULT NULL,
  `jam_selesai` time DEFAULT NULL,
  `bayar` int(10) DEFAULT NULL,
  `username` varchar(15) NOT NULL,
  `biaya` int(11) DEFAULT NULL,
  PRIMARY KEY (`kode_transaksi`),
  KEY `username` (`username`),
  KEY `id_lapang` (`id_lapang`),
  CONSTRAINT `transaksi_ibfk_3` FOREIGN KEY (`username`) REFERENCES `pegawai` (`username`),
  CONSTRAINT `transaksi_ibfk_5` FOREIGN KEY (`id_lapang`) REFERENCES `lapangan` (`id_lapang`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `transaksi` */

insert  into `transaksi`(`kode_transaksi`,`nama_club`,`id_lapang`,`tgl_boking`,`jam_mulai`,`jam_selesai`,`bayar`,`username`,`biaya`) values (1,'bujang club',3,'2020-01-22','09:00:00','10:00:00',20000,'ahmad',100000),(2,'UNIK om',3,'2020-01-28','13:00:00','14:00:00',50000,'ahmad',120000),(3,'pandang',3,'2020-01-29','14:00:00','15:00:00',50000,'ahmad',120000),(4,'PAK PAK FC',3,'2020-02-29','12:00:00','14:00:00',50000,'ahmad',500000),(5,'PAK PAK FC',3,'2020-02-29','12:00:00','14:00:00',50000,'ahmad',500000);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
