-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 01, 2019 lúc 03:01 PM
-- Phiên bản máy phục vụ: 10.4.6-MariaDB
-- Phiên bản PHP: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `bantrasua`
--

DELIMITER $$
--
-- Các hàm
--
CREATE DEFINER=`root`@`localhost` FUNCTION `AUTO_MADG` () RETURNS VARCHAR(7) CHARSET latin1 BEGIN 
	DECLARE fMADG VARchar(7) DEFAULT "";
    DECLARE fMAX_MADG int DEFAULT 0;
	SELECT  COUNT(MaDG) INTO fMAX_MADG FROM danhgia;

	IF(fMAX_MADG <= 0) then 
		SET fMAX_MADG = 1;
	ELSE 
		SET fMAX_MADG = fMAX_MADG + 1;
    END IF;
    
	IF(fMAX_MADG < 10) then
		SET fMADG = CONCAT("HD0000",CONVERT(fMAX_MADG,CHAR(1)));
	ELSEIF(fMAX_MADG < 100) THEN 
		SET fMADG = CONCAT("HD000", CONVERT(fMAX_MADG,CHAR(2)));
	ELSEIF(fMAX_MADG < 1000)THEN 
		SET fMADG = CONCAT("HD00", CONVERT(fMAX_MADG,CHAR(3)));
	ELSEIF(fMAX_MADG < 10000)THEN
		SET fMADG = CONCAT("HD", CONVERT(fMAX_MADG,CHAR(4)));
	ELSEIF(fMAX_MADG < 100000)THEN 
		SET fMADG = CONCAT("HD" , CONVERT(fMAX_MADG,CHAR(5)));
    END IF;
	
RETURN fMADG;
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `AUTO_MAHD` () RETURNS VARCHAR(7) CHARSET utf8 COLLATE utf8_unicode_ci BEGIN 
	DECLARE fMAHD VARchar(7) DEFAULT "";
    DECLARE fMAX_MAHD int DEFAULT 0;
	SELECT  COUNT(MaHD) INTO fMAX_MAHD FROM hoadon;

	IF(fMAX_MAHD <= 0) then 
		SET fMAX_MAHD = 1;
	ELSE 
		SET fMAX_MAHD = fMAX_MAHD + 1;
    END IF;
    
	IF(fMAX_MAHD < 10) then
		SET fMAHD = CONCAT("HD0000",CONVERT(fMAX_MAHD,CHAR(1)));
	ELSEIF(fMAX_MAHD < 100) THEN 
		SET fMAHD = CONCAT("HD000", CONVERT(fMAX_MAHD,CHAR(2)));
	ELSEIF(fMAX_MAHD < 1000)THEN 
		SET fMAHD = CONCAT("HD00", CONVERT(fMAX_MAHD,CHAR(3)));
	ELSEIF(fMAX_MAHD < 10000)THEN
		SET fMAHD = CONCAT("HD", CONVERT(fMAX_MAHD,CHAR(4)));
	ELSEIF(fMAX_MAHD < 100000)THEN 
		SET fMAHD = CONCAT("HD" , CONVERT(fMAX_MAHD,CHAR(5)));
    END IF;
	
RETURN fMAHD;
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `AUTO_MAKH` () RETURNS VARCHAR(7) CHARSET latin1 BEGIN 
	DECLARE fMAKH VARCHAR(7);
    DECLARE fMAX_MAKH int;
    
	SELECT  COUNT(MaKH) INTO fMAX_MAKH FROM KHACHHANG;

	IF(fMAX_MAKH <= 0) then 
		SET fMAX_MAKH = 1;
	ELSE 
		SET fMAX_MAKH = fMAX_MAKH + 1;
    END IF;
    
	IF(fMAX_MAKH < 10) then
		SET fMAKH = CONCAT('KH0000' , CONVERT(fMAX_MAKH,VARCHAR(1)));
	ELSEIF(fMAX_MAKH < 100) THEN 
		SET fMAKH = CONCAT('KH000',CONVERT(fMAX_MAKH,VARCHAR(2)));
	ELSEIF(fMAX_MAKH < 1000)THEN 
		SET fMAKH = CONCAT('KH00',CONVERT(fMAX_MAKH,VARCHAR(3)));
	ELSEIF(fMAX_MAKH < 10000)THEN
		SET fMAKH = CONCAT('KH0',CONVERT(fMAX_MAKH,VARCHAR(4)));
	ELSEIF(fMAX_MAKH < 100000)THEN 
		SET fMAKH = CONCAT('KH',CONVERT(fMAX_MAKH,VARCHAR(5)));
    END IF;
	
RETURN fMAKH;
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `AUTO_MALSP` () RETURNS CHAR(10) CHARSET utf8 COLLATE utf8_unicode_ci BEGIN 
	DECLARE fMALSP char(7) DEFAULT "";
    DECLARE fMAX_MALSP int DEFAULT 0;
	SELECT  COUNT(MaLoaiSP) INTO fMAX_MALSP FROM loaisanpham;

	IF(fMAX_MALSP <= 0) then 
		SET fMAX_MALSP = 1;
	ELSE 
		SET fMAX_MALSP = fMAX_MALSP + 1;
    END IF;
    
	IF(fMAX_MALSP < 10) then
		SET fMALSP = CONCAT('LSP000',CONVERT(fMAX_MALSP,CHAR(1)));
	ELSEIF(fMAX_MALSP < 100) THEN 
		SET fMALSP = CONCAT('LSP00',CONVERT(fMAX_MALSP,CHAR(2)));
	ELSEIF(fMAX_MALSP < 1000)THEN 
		SET fMALSP = CONCAT('LSP0',CONVERT(fMAX_MALSP,CHAR(3)));
	ELSEIF(fMAX_MALSP < 10000)THEN
		SET fMALSP = CONCAT('LSP',CONVERT(fMAX_MALSP,CHAR(4)));
    END IF;
	
RETURN fMALSP;
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `AUTO_MANV` () RETURNS CHAR(7) CHARSET utf8 COLLATE utf8_unicode_ci BEGIN 
	DECLARE fMANV char(7) DEFAULT "";
    DECLARE fMAX_MANV int DEFAULT 0;
	SELECT  COUNT(MaNV) INTO fMAX_MANV FROM NHANVIEN;

	IF(fMAX_MANV <= 0) then 
		SET fMAX_MANV = 1;
	ELSE 
		SET fMAX_MANV = fMAX_MANV + 1;
    END IF;
    
	IF(fMAX_MANV < 10) then
		SET fMANV = CONCAT('NV0000',CONVERT(fMAX_MANV,CHAR(1)));
	ELSEIF(fMAX_MANV < 100) THEN 
		SET fMANV = CONCAT('NV000',CONVERT(fMAX_MANV,CHAR(2)));
	ELSEIF(fMAX_MANV < 1000)THEN 
		SET fMANV = CONCAT('NV00',CONVERT(fMAX_MANV,CHAR(3)));
	ELSEIF(fMAX_MANV < 10000)THEN
		SET fMANV = CONCAT('NV0',CONVERT(fMAX_MANV,CHAR(4)));
	ELSEIF(fMAX_MANV < 100000)THEN 
		SET fMANV = CONCAT('NV',CONVERT(fMAX_MANV,CHAR(5)));
    END IF;
	
RETURN fMANV;
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `AUTO_MASP` () RETURNS CHAR(10) CHARSET utf8 COLLATE utf8_unicode_ci BEGIN 
	DECLARE fMASP char(7) DEFAULT "";
    DECLARE fMAX_MASP int DEFAULT 0;
	SELECT  COUNT(MaSP) INTO fMAX_MASP FROM SANPHAM;

	IF(fMAX_MASP <= 0) then 
		SET fMAX_MASP = 1;
	ELSE 
		SET fMAX_MASP = fMAX_MASP + 1;
    END IF;
    
	IF(fMAX_MASP < 10) then
		SET fMASP = CONCAT('SP0000',CONVERT(fMAX_MASP,CHAR(1)));
	ELSEIF(fMAX_MASP < 100) THEN 
		SET fMASP = CONCAT('SP000',CONVERT(fMAX_MASP,CHAR(2)));
	ELSEIF(fMAX_MASP < 1000)THEN 
		SET fMASP = CONCAT('SP00',CONVERT(fMAX_MASP,CHAR(3)));
	ELSEIF(fMAX_MASP < 10000)THEN
		SET fMASP = CONCAT('SP0',CONVERT(fMAX_MASP,CHAR(4)));
	ELSEIF(fMAX_MASP < 100000)THEN 
		SET fMASP = CONCAT('SP',CONVERT(fMAX_MASP,CHAR(5)));
    END IF;
	
RETURN fMASP;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitiethoadon`
--

CREATE TABLE `chitiethoadon` (
  `MaHD` varchar(7) COLLATE utf8_unicode_ci NOT NULL,
  `MaSP` varchar(7) COLLATE utf8_unicode_ci NOT NULL,
  `ThanhTien` int(15) DEFAULT NULL,
  `SoLuong` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `chitiethoadon`
--

INSERT INTO `chitiethoadon` (`MaHD`, `MaSP`, `ThanhTien`, `SoLuong`) VALUES
('HD00001', 'SP00002', 60000, 2),
('HD00002', 'SP00001', 20000, 1),
('HD00002', 'SP00002', 60000, 2),
('HD00003', 'SP00001', 20000, 1),
('HD00003', 'SP00002', 60000, 2),
('HD00004', 'SP00001', 20000, 1),
('HD00004', 'SP00002', 60000, 2),
('HD00005', 'SP00001', 20000, 1),
('HD00005', 'SP00002', 60000, 2),
('HD00006', 'SP00001', 20000, 1),
('HD00006', 'SP00002', 60000, 2),
('HD00007', 'SP00001', 20000, 1),
('HD00007', 'SP00002', 60000, 2),
('HD00008', 'SP00001', 20000, 1),
('HD00008', 'SP00002', 60000, 2),
('HD00009', 'SP00002', 30000, 1),
('HD00010', 'SP00002', 30000, 1),
('HD00011', 'SP00002', 30000, 1),
('HD00012', 'SP00002', 30000, 1),
('HD00013', 'SP00002', 30000, 1),
('HD00014', 'SP00002', 30000, 1),
('HD00015', 'SP00002', 30000, 1),
('HD00016', 'SP00002', 30000, 1),
('HD00017', 'SP00002', 30000, 1),
('HD00018', 'SP00002', 30000, 1),
('HD00019', 'SP00002', 30000, 1),
('HD00020', 'SP00002', 30000, 1),
('HD00021', 'SP00002', 30000, 1),
('HD00022', 'SP00002', 30000, 1),
('HD00023', 'SP00002', 30000, 1),
('HD00024', 'SP00002', 30000, 1),
('HD00025', 'SP00002', 30000, 1),
('HD00026', 'SP00002', 30000, 1),
('HD00027', 'SP00002', 30000, 1),
('HD00028', 'SP00002', 30000, 1),
('HD00029', 'SP00002', 30000, 1),
('HD00030', 'SP00002', 30000, 1),
('HD00031', 'SP00002', 30000, 1),
('HD00032', 'SP00002', 30000, 1),
('HD00033', 'SP00002', 30000, 1),
('HD00034', 'SP00002', 30000, 1),
('HD00035', 'SP00002', 30000, 1),
('HD00036', 'SP00002', 30000, 1),
('HD00037', 'SP00002', 30000, 1),
('HD00038', 'SP00002', 30000, 1),
('HD00039', 'SP00002', 30000, 1),
('HD00040', 'SP00002', 30000, 1),
('HD00041', 'SP00002', 30000, 1),
('HD00042', 'SP00002', 30000, 1),
('HD00043', 'SP00002', 30000, 1),
('HD00044', 'SP00002', 30000, 1),
('HD00045', 'SP00002', 30000, 1),
('HD00046', 'SP00002', 30000, 1),
('HD00047', 'SP00002', 30000, 1),
('HD00048', 'SP00002', 30000, 1),
('HD00049', 'SP00002', 30000, 1),
('HD00050', 'SP00002', 30000, 1),
('HD00051', 'SP00002', 30000, 1),
('HD00052', 'SP00002', 30000, 1),
('HD00053', 'SP00002', 30000, 1),
('HD00054', 'SP00007', 20000, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `danhgia`
--

CREATE TABLE `danhgia` (
  `MaDG` varchar(7) NOT NULL,
  `MaSP` varchar(7) NOT NULL,
  `MaKH` varchar(7) NOT NULL,
  `Mota` varchar(500) NOT NULL,
  `HinhAnh` varchar(10000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `danhgia`
--

INSERT INTO `danhgia` (`MaDG`, `MaSP`, `MaKH`, `Mota`, `HinhAnh`) VALUES
('HD00001', 'SP00002', 'KH00011', '', 'iVBORw0KGgoAAAANSUhEUgAAAHgAAAB4CAIAAAC2BqGFAAAAA3NCSVQICAjb4U/gAAAERElEQVR4 nO3dy2tcZRjH8ed53zOXk0ymuU7amFaT0tJIIJGiYkRQLHj5D9SFCEq7dCFC3XflQl0K4t6NuLSC C91YqFJvNa2X3qK1zZhM085kJufyPi4mTROYrb8U/H0WszjMHM75zss7z1mNDg4OCv333G5fwP8F Q4MwNAhDgzA0CEODMDQIQ4MwNAhDgzA0CEODMDQIQ4MwNAhDgzA0CEODMDQIQ4MwNAhDgzA0CEOD MDQIQ4MwNAhDgzA0CEODMDQIQ4MwNAhDgzA0CEODMDQIQ4MwNAhDgzA0CEODMDQIQ4MwNAhDgzA0 CEODMDQIQ4MwNAhDgzA0CEODMDQIQ4MwNAhDgzA0CEODMDQIQ4MwNAhDgzA0CEODMDQIQ4MwNAhD gzA0CEODMDQIQ4MwNAhDgzA0CEODMDQIQ4MwNAhDgzA0CEODMDQIQ4MwNEi02xdwP1ITb5J6cUFM xVTUNo8Ht3lQJGRORts6VxiZ0NJtzRY7K5fiRMypueA233/vnPwD9p4yJ6VcMncvlqmISCGXTiR9 qaQ+HCtPvjE6n6l9X7izT+OHO/Hpmxc/7FxY99r9brof6eKK7sFUSrmoiZrsC/4xN1II+mO+esmn 3fTtSJ6u7H974Oh7y999kfzpc0u8TFdGTx54/MSSvp9cyEUyJ37bivblcnn37ug+5UxyJ97keDxz cmKhMjw0OVR7eWT2QLvwbVJP1Yas8G7tqY/++eGz7JoP0iqIE1lJWovJ6pu1R3+6uXQjSnXnObmi e0h9KGfy7ODUC5Xpd659dU4aLrdadejUAwuvX1n/wH49vGfM5/Zl86oUXa6ulIXgxJtcbjbO1dZm q7Wz2eUo7Ng6OHX0UAiSenmp/9An9fNnXaNVkDTSv5qNj9fOPzN+aLgtD0pfu9lK1Jxs7sXd11zl etqsFmO/c4MWhu7JzIpBD/rKhU7dBelPxZuY15/b9f7+/pKLrhUTLUZBJQqyEYncze1NRqXUkmz7 vNHF0L2oumD1Uj5uJRHJVRInajImJU3yW4Vw9c5KX3VgPhvY8BKnIndnkkFfPqrDV1qrWxPhFobu wdRtePd1p/7i4JGBJKgEJ6GUh1f6j/yytpypLm+sf54vnajN7++Il0QkZC5UUnurOr+UNM/kK2qS 7UzLqaOHyCT1stRcPTY585zujRObkerx4bnpePhU/cyKpt5k8fbN2YmpV+PDcapTWXEhGn9t7yNj ceXU9W9uuA1V9SaybZvmA0sP3fUYBRkolp8fPThXGitk9tv6yqe3fl+2TjFI5iR1YU/mnhx+6ImB yWqx2Eqzi42/Tzf+aLiQOYkzaUc75miG7mHroc5UnIXuJuBzM+ddkOBETZxaqhbMykHVJFNT1VzU iarJRiRxJjlXNB5/DEEYGoShQRgahKFBGBqEoUEYGoShQRgahKFBGBqEoUEYGoShQRgahKFBGBqE oUH+Bb8xX/vUnUaZAAAAAElFTkSuQmCC'),
('HD00002', 'SP00002', 'KH00011', '', 'iVBORw0KGgoAAAANSUhEUgAAAHgAAAB4CAIAAAC2BqGFAAAAA3NCSVQICAjb4U/gAAAERElEQVR4\nnO3dy2tcZRjH8ed53zOXk0ymuU7amFaT0tJIIJGiYkRQLHj5D9SFCEq7dCFC3XflQl0K4t6NuLSC\nC91YqFJvNa2X3qK1zZhM085kJufyPi4mTROYrb8U/H0WszjMHM75zss7z1mNDg4OCv333G5fwP8F\nQ4MwNAhDgzA0CEODMDQIQ4MwNAhDgzA0CEODMDQIQ4MwNAhDgzA0CEODMDQIQ4MwNAhDgzA0CEOD\nMDQIQ4MwNAhDgzA0CEODMDQIQ4MwNAhDgzA0CEODMDQIQ4MwNAhDgzA0CEODMDQIQ4MwNAhDgzA0\nCEODMDQIQ4MwNAhDgzA0CEODMDQIQ4MwNAhDgzA0CEODMDQIQ4MwNAhDgzA0CEODMDQIQ4MwNAhD\ngzA0CEODMDQIQ4MwNAhDgzA0CEODMDQIQ4MwNAhDgzA0CEODMDQIQ4MwNEi02xdwP1ITb5J6cUFM\nxVTUNo8Ht3lQJGRORts6VxiZ0NJtzRY7K5fiRMypueA233/vnPwD9p4yJ6VcMncvlqmISCGXTiR9\nqaQ+HCtPvjE6n6l9X7izT+OHO/Hpmxc/7FxY99r9brof6eKK7sFUSrmoiZrsC/4xN1II+mO+esmn\n3fTtSJ6u7H974Oh7y999kfzpc0u8TFdGTx54/MSSvp9cyEUyJ37bivblcnn37ug+5UxyJ97keDxz\ncmKhMjw0OVR7eWT2QLvwbVJP1Yas8G7tqY/++eGz7JoP0iqIE1lJWovJ6pu1R3+6uXQjSnXnObmi\ne0h9KGfy7ODUC5Xpd659dU4aLrdadejUAwuvX1n/wH49vGfM5/Zl86oUXa6ulIXgxJtcbjbO1dZm\nq7Wz2eUo7Ng6OHX0UAiSenmp/9An9fNnXaNVkDTSv5qNj9fOPzN+aLgtD0pfu9lK1Jxs7sXd11zl\netqsFmO/c4MWhu7JzIpBD/rKhU7dBelPxZuY15/b9f7+/pKLrhUTLUZBJQqyEYncze1NRqXUkmz7\nvNHF0L2oumD1Uj5uJRHJVRInajImJU3yW4Vw9c5KX3VgPhvY8BKnIndnkkFfPqrDV1qrWxPhFobu\nwdRtePd1p/7i4JGBJKgEJ6GUh1f6j/yytpypLm+sf54vnajN7++Il0QkZC5UUnurOr+UNM/kK2qS\n7UzLqaOHyCT1stRcPTY585zujRObkerx4bnpePhU/cyKpt5k8fbN2YmpV+PDcapTWXEhGn9t7yNj\nceXU9W9uuA1V9SaybZvmA0sP3fUYBRkolp8fPThXGitk9tv6yqe3fl+2TjFI5iR1YU/mnhx+6ImB\nyWqx2Eqzi42/Tzf+aLiQOYkzaUc75miG7mHroc5UnIXuJuBzM+ddkOBETZxaqhbMykHVJFNT1VzU\niarJRiRxJjlXNB5/DEEYGoShQRgahKFBGBqEoUEYGoShQRgahKFBGBqEoUEYGoShQRgahKFBGBqE\noUH+Bb8xX/vUnUaZAAAAAElFTkSuQmCC\n'),
('HD00003', 'SP00001', 'KH00011', '', 'iVBORw0KGgoAAAANSUhEUgAAAHgAAAB4CAIAAAC2BqGFAAAAA3NCSVQICAjb4U/gAAABuElEQVR4\nnO3dPU4DMRgG4c/e/GyASEskBDRQovT0dIjLcBgqDkVJT48oIN2SbNacwhMpzHOCVyPLhRunrutC\n9eVDD/gvDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQ\nEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEEND\nDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNGRy6AHHrKSIiHYXJ3niia5oMkYqcTNdvtw+\nGrqifYoc6fny/uP709AV5bGsFmd389Xr5t3QFQ0pL+eLvu9/ytbQFeVUNkN/Omsv0rRp2/bQe47W\nkGPof9fd9cPu3NAVlchNKW/D19PVOvkPS1UlRR5jlhvv6LpSiZJiO+4NTRizbx2IVAxNMTTE0BBD\nQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwN\nMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQ/4A30svu3wsFmQAAAAASUVORK5CYII=\n'),
('HD00004', 'SP00001', 'KH00011', '', 'iVBORw0KGgoAAAANSUhEUgAAAHgAAAB4CAIAAAC2BqGFAAAAA3NCSVQICAjb4U/gAAABuElEQVR4\nnO3dPU4DMRgG4c/e/GyASEskBDRQovT0dIjLcBgqDkVJT48oIN2SbNacwhMpzHOCVyPLhRunrutC\n9eVDD/gvDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQ\nEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEEND\nDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNGRy6AHHrKSIiHYXJ3niia5oMkYqcTNdvtw+\nGrqifYoc6fny/uP709AV5bGsFmd389Xr5t3QFQ0pL+eLvu9/ytbQFeVUNkN/Omsv0rRp2/bQe47W\nkGPof9fd9cPu3NAVlchNKW/D19PVOvkPS1UlRR5jlhvv6LpSiZJiO+4NTRizbx2IVAxNMTTE0BBD\nQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwN\nMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQ/4A30svu3wsFmQAAAAASUVORK5CYII=\n'),
('HD00005', 'SP00001', 'KH00011', '', 'iVBORw0KGgoAAAANSUhEUgAAAHgAAAB4CAIAAAC2BqGFAAAAA3NCSVQICAjb4U/gAAABuElEQVR4\nnO3dPU4DMRgG4c/e/GyASEskBDRQovT0dIjLcBgqDkVJT48oIN2SbNacwhMpzHOCVyPLhRunrutC\n9eVDD/gvDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQ\nEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEEND\nDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNGRy6AHHrKSIiHYXJ3niia5oMkYqcTNdvtw+\nGrqifYoc6fny/uP709AV5bGsFmd389Xr5t3QFQ0pL+eLvu9/ytbQFeVUNkN/Omsv0rRp2/bQe47W\nkGPof9fd9cPu3NAVlchNKW/D19PVOvkPS1UlRR5jlhvv6LpSiZJiO+4NTRizbx2IVAxNMTTE0BBD\nQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwN\nMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQ/4A30svu3wsFmQAAAAASUVORK5CYII=\n'),
('HD00006', 'SP00001', 'KH00011', '', 'iVBORw0KGgoAAAANSUhEUgAAAHgAAAB4CAIAAAC2BqGFAAAAA3NCSVQICAjb4U/gAAABuElEQVR4\nnO3dPU4DMRgG4c/e/GyASEskBDRQovT0dIjLcBgqDkVJT48oIN2SbNacwhMpzHOCVyPLhRunrutC\n9eVDD/gvDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQ\nEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEEND\nDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNGRy6AHHrKSIiHYXJ3niia5oMkYqcTNdvtw+\nGrqifYoc6fny/uP709AV5bGsFmd389Xr5t3QFQ0pL+eLvu9/ytbQFeVUNkN/Omsv0rRp2/bQe47W\nkGPof9fd9cPu3NAVlchNKW/D19PVOvkPS1UlRR5jlhvv6LpSiZJiO+4NTRizbx2IVAxNMTTE0BBD\nQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwN\nMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQ/4A30svu3wsFmQAAAAASUVORK5CYII=\n'),
('HD00007', 'SP00001', 'KH00011', '', 'iVBORw0KGgoAAAANSUhEUgAAAHgAAAB4CAIAAAC2BqGFAAAAA3NCSVQICAjb4U/gAAAD/0lEQVR4\nnO3bT2gcZRzG8ef37uzuJJv9162aaBRqMbbSP9CKQRER8Sb1JCr0Ip68CJ485eJB0INHTx4EBfHg\nQQnowUtpVXqwVpAiFpOgJmn+2KTJZnc7OzPv66FpYi/Zk08oPp/TwMDLy3d+DO8cxhqNBuS/5/Z7\nA/8XCk2i0CQKTaLQJApNotAkCk2i0CQKTaLQJApNotAkCk2i0CQKTaLQJApNotAkCk2i0CQKTaLQ\nJApNotAkCk2i0CQKTaLQJApNotAkCk2i0CQKTaLQJApNotAkCk2i0CTRfm/gLhAMFhAMzu9eBwOA\nyCM3GHwASjkM8CH0iwbABYR/zbFCDxZ5ZA6RRy9COUPmEAzlHH0HD1jAEVc7W5mYKDUBLObtc+tz\n07bs7Y5FCnEc78/27xLBkDoMZfDOqgnSAso5APQLKHp4hDPVw2+PTf4c1j9NZ752y965V8ZOHu0U\nv8uXvO1OtOkX5b0VAqIcT4+Mvxw/3HKlXppMt2en8/nU7GaEJ3DgvQeefXfh/Le2Uskss+ANtcrI\nR/c89+Xs5c9tYXcdTfTegvk3GsfONo9O9+Y+cwurLn1h/PjpreHz2WJaCK/fe3JlY/WTZM4F82bB\nzMN8kt50eHX4ken2bOZuLaJTxyDj5dqLzYmpP899sXH1t7VrX61dnbp24bHxQ5N5M85wotT6sbuU\n334dW0AhIHP4qb9ardXMLIRw65ZCD/BMcXR5/foV2+wW4R0yh79vrF/y1x9tjgKIzBXyAGwfQnZ4\n750ZALPtGwo9QOyiNetX+ih6AHBAEqHbT2KLguGX4tZYVLEA2x5c5AYLOIZ6v9PbGWco9EBXCt1D\ncWsIRQsO8PD5sMep0oGlpJOb+/7G/POtI6OJBfOAD+YdvLPwUu3whd5fSWH7AVhQ6EEubs4vD/k3\nD556qF8aSlEP0VTjccv8N+l8MPywPPN7nLwz9tTxJB5OUenjwTx+v/Fky8Uft3/d+a6BjncDlTPU\nKyNv3T95Oqm2O5txtbKYdz5YvDgTuplDv4CGlV4bPXGmML7V3vCGeqV+OVn9cOXSH9Z1wQG3h1qh\n95Y5WEDkcbBab5WHut3uYm8jcaHgHQCPUPIG+JF46L5acyS1ueTGRq+TGwx3fIIrNIne0SQKTaLQ\nJApNotAkCk2i0CQKTaLQJApNotAkCk2i0CQKTaLQJApNotAkCk2i0CQKTaLQJApNotAkCk2i0CQK\nTaLQJApNotAkCk2i0CQKTaLQJApNotAkCk2i0CQKTaLQJApNotAkCk2i0CQKTaLQJApNotAkCk2i\n0CQKTaLQJApNotAkCk2i0CQKTaLQJApNotAkCk2i0CQKTaLQJApNotAkCk2i0CT/AE8nNjAJc9kk\nAAAAAElFTkSuQmCC\n'),
('HD00008', '', 'KH00002', 'aaaa', 'HCM'),
('HD00009', '', 'KH00002', 'aaaa', 'HCM'),
('HD00010', '', 'KH00002', 'aaaa', 'iVBORw0KGgoAAAANSUhEUgAAAHgAAAB4CAIAAAC2BqGFAAAAA3NCSVQICAjb4U/gAAABuElEQVR4\nnO3dPU4DMRgG4c/e/GyASEskBDRQovT0dIjLcBgqDkVJT48oIN2SbNacwhMpzHOCVyPLhRunrutC\n9eVDD/gvDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQ\nEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEEND\nDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNGRy6AHHrKSIiHYXJ3niia5oMkYqcTNdvtw+\nGrqifYoc6fny/uP709AV5bGsFmd389Xr5t3QFQ0pL+eLvu9/ytbQFeVUNkN/Omsv0rRp2/bQe47W\nkGPof9fd9cPu3NAVlchNKW/D19PVOvkPS1UlRR5jlhvv6LpSiZJiO+4NTRizbx2IVAxNMTTE0BBD\nQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwN\nMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQ/4A30svu3wsFmQAAAAASUVORK5CYII=\n'),
('HD00011', '', 'KH00002', '', 'iVBORw0KGgoAAAANSUhEUgAAAHgAAAB4CAIAAAC2BqGFAAAAA3NCSVQICAjb4U/gAAABuElEQVR4\nnO3dPU4DMRgG4c/e/GyASEskBDRQovT0dIjLcBgqDkVJT48oIN2SbNacwhMpzHOCVyPLhRunrutC\n9eVDD/gvDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQ\nEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEEND\nDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNGRy6AHHrKSIiHYXJ3niia5oMkYqcTNdvtw+\nGrqifYoc6fny/uP709AV5bGsFmd389Xr5t3QFQ0pL+eLvu9/ytbQFeVUNkN/Omsv0rRp2/bQe47W\nkGPof9fd9cPu3NAVlchNKW/D19PVOvkPS1UlRR5jlhvv6LpSiZJiO+4NTRizbx2IVAxNMTTE0BBD\nQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwN\nMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQ/4A30svu3wsFmQAAAAASUVORK5CYII=\n'),
('HD00012', 'SP00001', 'KH00011', '', 'iVBORw0KGgoAAAANSUhEUgAAAHgAAAB4CAIAAAC2BqGFAAAAA3NCSVQICAjb4U/gAAACUElEQVR4\nnO3aS2oUURSA4XNvdadb86B9xAc40eAjo8zcgIKPmUMXIO7BHbkMnToQBEVwpjhqTE+CJOmuexwY\ndAPlH4j/N6saHX4K7qmiymw2C/179bQH+F8YGmJoiKEhhoYYGmJoiKEhhoYYGmJoiKEhhoYYGmJo\niKEhhoYYGmJoiKEhhoYYGmJoiKEhhoYYGmJoiKEHVjJGLbJEyYiILCf3DT2wLHE4ilGLViPib+7R\n6Y519mSJSR8lo2Rcb939emncyod+v/jb7rBKRqsx7uPF+d1n23c/dQct8k5s+kQPbNm16SoezG4+\n2bj16uub97GofV7ZumDogY1bLLt4vn779fzju7o46mJayveDhYfhwDJzrZWdbuPz4by2WF9Gl5Fd\nMfTQSqkt55P+ak4ioi9xXKOk693QstSjrr49nD+d3ds8biVajTbpWzedTk97tjNllLHs4tvB/sMb\nu4/KtXPHuRtbLy/uud4NrGSsaoxabK5NH1/e2Ztsj1f55ecPQw+s5Mlrd5ao2VY1IqLr0/VuYH8+\nbpSMjNq13xcehhRDQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBD\nQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwN\nMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE\n0BBDQwwNMTTE0BBDQwwN+QVIF23yXZtzKwAAAABJRU5ErkJggg==\n'),
('HD00013', 'SP00001', 'KH00011', '', 'iVBORw0KGgoAAAANSUhEUgAAAHgAAAB4CAIAAAC2BqGFAAAAA3NCSVQICAjb4U/gAAAEbklEQVR4\nnO3cS2hcVRzH8d//3DuPdMxkMn3lYcE+pEppGh+o1IVu7EYQgrrI1sfSjQiC4FLoyqXgotu6toKK\nLnyB4qNQ7UJtjRXzTtvESTJ3MnPvPX8XM03SCOIN+BO8/y+zuDPcGYbPPZxz5i5GarUarH8/919/\ngbxk0KQMmpRBkzJoUgZNyqBJGTQpgyZl0KQMmpRBkzJoUgZNyqBJGTQpgyZl0KQMmpRBkzJoUgZN\nyqBJGTQpgyZl0KQMmpRBkzJoUgZNyqBJGTQpgyZl0KQMmpRBkzJoUgZNyqBJGTQpgyZl0KQMmlRm\naJWdB5tPA906FoUoUkGgXuDRe+S3zNDOQxRAj3L76+0AgSJQFFJ4h0DR3wGA7lk7LkzeCrO+QaVH\nJgrvttBVUE4Qi8LJCQxM9h07Uaj7QK63Gheia5/o4obTv//k/3dBuVzO+h4BnKLoUUzhFBB4gQoc\nkDh5tnzkleFHLqNxzk+9j/n1PTo5dPJ4q/htZzFx+V0SdjGifSHFE6U7n6oc3huW2u32hfVf34tn\nm0UEHveH9Rf3j52d+epjt9TX0cThqlu6eHP+jbsef2aq8Y6fTfJKnXlEB96/UDs5Wb/3w9bv5zE9\nV0gmRsbGo9KXnfmoIM8fHF9oLJ/r/BJ6OEXqAGDZxSLu6dLhd5vXvEAdIMjbjJ15gI2WBybqx1+f\n/fx888pPK0sf3Ljy2synD47cfTqtVxK5L9x7eW2hlCJQRAUAKHqEqV5sL1VrA07yxrtVZuhHS0Mz\na8tTcWPDqbqkFWIxWv3CL91THQYgTmPEiUPiUEogisRBFJ008S4AthbSvJUZuuTCONpIHAKFwhU8\nVNCKO0FQgJefsTFSqKfSo0wFTj3gx6Qqq01VVYHL5X46M/QP4dpw/2BBpS/uaRa9nCrtu9lab4f4\nLJo5UztytOnaoe9ejMShnGCi/9hHnfyuhNjFYthorZ8+ePSxzuCl9vW2prUkeHXwgVEtn127FIvO\nRH+M7zt0pjAyv3JjTTtFr6Np6eWhhw4Vq28ufNMIvAC5WwcBAJL179gSSUdL1ZdGHx5vV6IoqvZV\nrsr6W9Nf/yhNUcSi1bD03NCpJ4PRRrQaevTd0f99fPPtue9+kwhwzuf0J2Jm6G6B4kBl4EChspK0\n5tZW/jon7C9WhvcMFL1Md1aXW+txDpe/29slNAAPFZH01i4i2CYpIqqaSu/uksvlNmNHu7nX0d02\nOJHA9xZTB6TbJwSvAoTaO7+rvHljJJ9lhu7eS+oebM4Y/vYxu2MW7hLndnbulhka20z/4QiVW0M7\nz+V4Z8vNoEkZNCmDJmXQpAyalEGTMmhSBk3KoEkZNCmDJmXQpAyalEGTMmhSBk3KoEkZNCmDJmXQ\npAyalEGTMmhSBk3KoEkZNCmDJmXQpAyalEGTMmhSBk3KoEkZNCmDJmXQpAyalEGTMmhSBk3KoEkZ\nNCmDJmXQpAyalEGTMmhSBk3KoEkZNCmDJmXQpAyalEGTMmhSBk3KoEkZNKk/AS/Gbir3pdWgAAAA\nAElFTkSuQmCC\n'),
('HD00014', 'SP00001', 'KH00011', '', 'iVBORw0KGgoAAAANSUhEUgAAAHgAAAB4CAIAAAC2BqGFAAAAA3NCSVQICAjb4U/gAAAEh0lEQVR4\nnO3YO2wcVRTG8e/cufvwJvZ6vU6UmBhQHB4KUUgi8YiQkKBDEVJIkSZFaKhokOgi6BAUVEh0lFRA\ngRvokFASIooERaIACQcRO8bJ2oqd9T5mZmfuobBxYst0+EMTzq+cmd25+utq5t6R0dFRmJ3n/usB\n/F9YaBILTfIwhBaF6NYja1TgAwBkDiqIdJtrODz1bjsjOABwASr3D4pCBaUcKijnKOcIgthDdD13\ncNTWD0PotV4PVgYgqodRP10/9GypWQn5Qt6dXr1xKbndLfkHf0UTVatV6g3/beUcpYAoIAjwd2sB\nTtWnLuw7OZOufKY3vvYLvZI7t+/YVDr0Y9IKAnVwev96goLO6ACgnOnLuw6crj0+IUOLSK4s3/wq\nnW2X1Qc8PTT+9tjRD+d+uJy3VJxKWAhLFzvzn+5/9Y0/lqez2Vj8xv9wFHJGl3MV4HzjyPnGM9/0\nZ7/UuSWXnZo4MpVWf+rfySJ5a/zYn/cWP09+F0BFIBoESZpmXs6WHvu2O5tGqiIC3uOjkKuOnteJ\nofrZkSffn7/4RXvmWrc1vTzz3q1Lr4xPvaTNAD3um9e7CyUVAD5AFD4g8bget8bqo30PACXebAYK\nGrqay8nqvoXV5Z91RQWVDJLrzfTed37p0PCeCBI5FwWoqgrSCCpIHXaniDUPkVQyqCCJqGMuZOjc\noRz5eBAHCbkgOIhIrqqdOCp5BX6JOuOlESAAwWvmNGRRUM2PY3fcS+JIReEDdUoXMrQE/dV1JoYb\nzcRFioFDcBgbuMPV5kKyWs5xuXPrtbGDezK3tubLHGoDqJMzI09937vlA1S2Lgd3WiFfhonHarfz\nwt6Dz+WNmfZSyMOQ8+80Tjzqah+vXo8lzMYrR5uTr0eTv63eaSON4PbnlXf3Pj8Z7f7g7tW2ZBAh\nL++kiJ9JBUGdNCu7Lux98Qkd6Xe69UptDr2PWlduZG0n4hRlX3rzkRNncOBuvyOaN2r1a+niJ62r\nt/N4bV+eRJt25Ds+5iKGXiMKB4wPj+4p1Xr9/lx/ZbB5tyeKRqU2sWu0nGM+breSDvlxsWkwxQ0N\nQHT9Taiq2LyrXjulqpHKIEIQRAE+sB/NGwq6M1ynAheQOQVQDshl0ykAIqJAbYDcYX19wv3EsaHY\noQEEh3JAAFK39Zm78fm07+G2+5rKVPjQousTeds3m/7zKbJCrqOLyEKTWGgSC01ioUksNImFJrHQ\nJBaaxEKTWGgSC01ioUksNImFJrHQJBaaxEKTWGgSC01ioUksNImFJrHQJBaaxEKTWGgSC01ioUks\nNImFJrHQJBaaxEKTWGgSC01ioUksNImFJrHQJBaaxEKTWGgSC01ioUksNImFJrHQJBaaxEKTWGgS\nC01ioUksNImFJrHQJBaaxEKTWGgSC01ioUksNImFJrHQJBaaxEKTWGgSC01ioUksNImFJrHQJBaa\nxEKTWGgSC01ioUksNImFJrHQJBaaxEKTWGgSC01ioUksNImFJrHQJBaaxEKTWGgSC01ioUksNImF\nJrHQJBaaxEKTWGiSvwBd+YU0UqvfFQAAAABJRU5ErkJggg==\n'),
('HD00015', 'SP00001', 'KH00011', '', 'iVBORw0KGgoAAAANSUhEUgAAAHgAAAB4CAIAAAC2BqGFAAAAA3NCSVQICAjb4U/gAAAET0lEQVR4\nnO3b3YtUdRzH8c/3d86ZGddZd1xGLS1J7AFas7KEHi6kbiO9KvDe+g+iKAL7A6KboOu66QG6CSsh\nIpCg7EEIDES0zXWxZbVln8aZMzO/37eL2cqtnVoEP0V9XpfDOYczb37zPT8OjDUaDciNF/7pG/i/\nUGgShSZRaBKFJlFoEoUmUWgShSZRaBKFJlFoEoUmUWgShSZRaBKFJlFoEoUmUWgShSZRaBKFJlFo\nEoUmUWgShSZRaBKFJlFoEoUmUejr5AYA5us9XqGvkznckNbdL7+RN/Mft/7lDIW+bpkjjzAgGbrZ\n3x+v0H9kjkpKrQLViGgwD25wQ0joZ6kS4e4TNnZwdNcDeTNmdrnb+nh+8qNwyTyUObIh0ySr1Wrk\nb/JvZyjzwaMO1YjgbvAASwaDAzhY3/3CzQ9/h4W34uQHNtPPwtM77r27Xf2iO+sGwNa+qv6V9Wch\nxSeKW5/cuGs839DqlR9e/fFY52IrdyDtqTVfbx54eebzr7pXegEpICRsqo+8sfXx4+dPvZlNm2tF\nr4M5MuDI2MTh5sT73QvvYWq6Eg9vueeusvZlf7ab+zPN+35o/fxO+1w06wcEhwf0y7Jb2KGR244v\nTPaytVe0tncrosEcPfOd1bGnGne8MnXi3aVzZ+euHJs789zMZw9u37Xfx4q+7a1s/n7xUoC5IXMA\nMEeZ4VRntlnfnIZUhkL/ZhCiluyR6k0Xl+dO+3ye0A+w5FPlwsl0ZWLj1kryEEIlop2t2tkVCTHG\n7C/3ewr9u8ETsBryTrfs5WaOZDCzPrzTLatZngyn86vbKpuKlK49MbnfXoy1Ou08Dk2t0Kt0gp8J\ny9tHx7e0LYWV4TAaw/1hfKq31C7w9cL0Y83dO8pV2+JqsmeLOz/tTJfDN9QKvWLwsy/cvp2fnh6J\nz4/v296zWr871i9e2rQfCCfaP2UpfDI3eb7aO7rtwJ6ylqeUp7QzVo5uebRW1N5eOOs+dEZre7di\n8O4CQIBt3rDhxa0P7e3V58ur9erGC956debk+biYgmWOuhWHd+47FG5ZWlrKE6qN0TOty69d/uZi\naiHYsDmt0GuIIVUittUbzWJkrlfOtOZL8xjghjwiAHnCaG1kx0ijSLjUWZwtl/sB9S6WK8jT2tdU\n6FUG69qQBntkM0OyaKgkAIjXDIaQPGaWJyQgAAnoB+QJw4aH3nWsMsjkCNlgYToAZL4q8UAKZr9+\nHrFy2PARrYchi0KTKDSJQpMoNIlCkyg0iUKTKDSJQpMoNIlCkyg0iUKTKDSJQpMoNIlCkyg0iUKT\nKDSJQpMoNIlCkyg0iUKTKDSJQpMoNIlCkyg0iUKTKDSJQpMoNIlCkyg0iUKTKDSJQpMoNIlCkyg0\niUKTKDSJQpMoNIlCkyg0iUKTKDSJQpMoNIlCkyg0iUKTKDSJQpMoNIlCkyg0iUKTKDSJQpMoNIlC\nkyg0iUKTKDSJQpMoNMkv2sFaSvv/7eYAAAAASUVORK5CYII=\n'),
('HD00016', 'SP00002', 'KH00011', '', 'iVBORw0KGgoAAAANSUhEUgAAAHgAAAB4CAIAAAC2BqGFAAAAA3NCSVQICAjb4U/gAAABH0lEQVR4\nnO3QQREAIRDAsOMUrH+1qKA8SBR0umbm47z/dsArjI4YHTE6YnTE6IjREaMjRkeMjhgdMTpidMTo\niNERoyNGR4yOGB0xOmJ0xOiI0RGjI0ZHjI4YHTE6YnTE6IjREaMjRkeMjhgdMTpidMToiNERoyNG\nR4yOGB0xOmJ0xOiI0RGjI0ZHjI4YHTE6YnTE6IjREaMjRkeMjhgdMTpidMToiNERoyNGR4yOGB0x\nOmJ0xOiI0RGjI0ZHjI4YHTE6YnTE6IjREaMjRkeMjhgdMTpidMToiNERoyNGR4yOGB0xOmJ0xOiI\n0RGjI0ZHjI4YHTE6YnTE6IjREaMjRkeMjhgdMTpidMToiNERoyNGR4yOGB0xOmJ0xOiI0ZENvBkB\nIHxFXCsAAAAASUVORK5CYII=\n'),
('HD00017', '', 'KH00002', 'mada', 'iVBORw0KGgoAAAANSUhEUgAAAHgAAAB4CAIAAAC2BqGFAAAAA3NCSVQICAjb4U/gAAABuElEQVR4\nnO3dPU4DMRgG4c/e/GyASEskBDRQovT0dIjLcBgqDkVJT48oIN2SbNacwhMpzHOCVyPLhRunrutC\n9eVDD/gvDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQ\nEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEEND\nDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNGRy6AHHrKSIiHYXJ3niia5oMkYqcTNdvtw+\nGrqifYoc6fny/uP709AV5bGsFmd389Xr5t3QFQ0pL+eLvu9/ytbQFeVUNkN/Omsv0rRp2/bQe47W\nkGPof9fd9cPu3NAVlchNKW/D19PVOvkPS1UlRR5jlhvv6LpSiZJiO+4NTRizbx2IVAxNMTTE0BBD\nQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwN\nMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQ/4A30svu3wsFmQAAAAASUVORK5CYII=\n'),
('HD00018', 'SP00001', 'KH00002', 'mada', 'iVBORw0KGgoAAAANSUhEUgAAAHgAAAB4CAIAAAC2BqGFAAAAA3NCSVQICAjb4U/gAAABuElEQVR4\nnO3dPU4DMRgG4c/e/GyASEskBDRQovT0dIjLcBgqDkVJT48oIN2SbNacwhMpzHOCVyPLhRunrutC\n9eVDD/gvDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQ\nEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEEND\nDA0xNMTQEENDDA0xNMTQEENDDA0xNMTQEENDDA0xNGRy6AHHrKSIiHYXJ3niia5oMkYqcTNdvtw+\nGrqifYoc6fny/uP709AV5bGsFmd389Xr5t3QFQ0pL+eLvu9/ytbQFeVUNkN/Omsv0rRp2/bQe47W\nkGPof9fd9cPu3NAVlchNKW/D19PVOvkPS1UlRR5jlhvv6LpSiZJiO+4NTRizbx2IVAxNMTTE0BBD\nQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwN\nMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQwwNMTTE0BBDQ/4A30svu3wsFmQAAAAASUVORK5CYII=\n'),
('HD00019', 'SP00002', 'KH00011', '?dasdasdasdasd', 'iVBORw0KGgoAAAANSUhEUgAAAHgAAAB4CAIAAAC2BqGFAAAAA3NCSVQICAjb4U/gAAAEgklEQVR4\nnO3czWtcVRzG8ed37r3zmmnSEE2TprU1ImKlUFsswagFN4K4kIqLouDOhYLosqDoov4b3bt3US3Z\niNiFIja+bCqmmLfWNibpZObOzL3nuJikqWhXMk+58fkygTDDMIcPJ/f+5ixiIyMjUIPPPegF/F8S\nNClBkxI0KUGTEjQpQZMSNClBkxI0KUGTEjQpQZMSNClBkxI0KUGTEjQpQZMSNClBkxI0KUGTEjQp\nQZMSNClBkxI0KUGTEjQpQZMSNClBkxI0KUGTEjQpQZMSNClBkxI0KUGTEjQpQZMSNClBkxI0KUGT\nEjQpQZMSNClBkxI0KUGTEjQpQZMSNClBkxI0KUGTokJb+PfnM4fYb78aBQRDsN1fLCAYc5kDiQp9\nP69aD1ul7VdzQ+wRe+QGC+g5JB7OM5c5kOIHvQAAaCUoZwghHPbVM7WpiWTIe/97e32us7SW5M0S\nSvmDXuJ/LqpUKszPswADDP2fnUV4GPBK7eiHB2eRRCu+1UvsxPiR16vT19Zvrll3D1w6jPn/o0u5\nfzLZP1Ob3Bfi5e7GXHt5Oe72HKKA2fLE+YdOf3rjypXOzdwAIIa9OnnsjfzQO4uXFpOs6Pftge/o\nvH9bc0g83ho99v7o09etvWX59PjUm40nFm6v/oGO8+HjA8/O3fr1s95vBhcMMARgfmt1ZuzoaBrm\nsz9zK/auZlyjHZCHcLJx8Gx9+oOluZ+x2YlQv42zj548P3b6tY3LQ5XqVHnfR+n1arD+PbBfOcMP\na4uz9YmL6bW/XWsK2MD/Hh3ggSjY7PDhL+4szNtm5lDJ0Erw+eKP0di+57ORqFyy3G9kaSex/kjX\nf6Qx1kN31Cf3mwsL1MChbWccrpcrN1vNYM55l5sr5djqddbyVqNU6bZTmB1BvZTlUQB2BsHY40BU\nW0U3i4u9nUGA7l+jk65v9jpHSsNJjmDIHLoRGlHpYZRvhDRN0+/c+kvVw97QdbtfUqIoeq5x6Ot0\npeMKv6UHDh17eCAtuy/vLLww/MipMFzJfBRQ6+G9xvGVdvN7v95N7OKtqy+OP34un6p2g/OIAoaz\n6EL9VJZll5oLxXemjHdRQDtGNcPLU0+96x77aWNlOU+P18asknyy+NUv2EyCecOZ+qG3J0741eZ8\ntpZE8TNDkyuufeHGN9d90wWEgo931DnaAibrI7Plif1WWm5tXO4stkIWBXjAO1jAcFyZaUxN21Av\n+Ku9299uLuV75biDDe0dyhl6Dr0IFlDN0I639+r2aOEDADMLIcBZ33cPTB3Us45gcB65QzCUc2QO\nnRj9McN2ho0QbY/SsbfQ/8p+zxxS3NjQFpA5AMhs94guGLwDsH1KV/YAkNvuu4IVflNTofumd8n6\nlPdeHO6elP7zjUWv2LfyAiVoUoImJWhSgiYlaFKCJiVoUoImJWhSgiYlaFKCJiVoUoImJWhSgiYl\naFKCJiVoUoImJWhSgiYlaFKCJiVoUoImJWhSgiYlaFKCJiVoUoImJWhSgiYlaFKCJiVoUoImJWhS\ngiYlaFKCJvUXqjh9dh6WlaMAAAAASUVORK5CYII=\n');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoadon`
--

CREATE TABLE `hoadon` (
  `MaHD` varchar(7) COLLATE utf8_unicode_ci NOT NULL,
  `MaKH` varchar(7) COLLATE utf8_unicode_ci NOT NULL,
  `NgayDatHang` datetime DEFAULT NULL,
  `DiaChiNguoiNhan` varchar(80) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TinhTrang` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TriGia` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `hoadon`
--

INSERT INTO `hoadon` (`MaHD`, `MaKH`, `NgayDatHang`, `DiaChiNguoiNhan`, `TinhTrang`, `TriGia`) VALUES
('HD00001', 'KH00001', '0000-00-00 00:00:00', 'so27,duong to vinh dien quan 9 tphcm', 'Đã xác nhận', 123123),
('HD00002', 'KH00001', '0000-00-00 00:00:00', 'so27,duong to vinh dien quan 9 tphcm', 'Đã xác nhận', 123123),
('HD00003', 'KH00001', '0000-00-00 00:00:00', 'so27,duong to vinh dien quan 9 tphcm', 'Đã xác nhận', 123123),
('HD00004', 'KH00001', '0000-00-00 00:00:00', 'so27,duong to vinh dien quan 9 tphcm', 'Đã xác nhận', 123123),
('HD00005', 'KH00001', '0000-00-00 00:00:00', 'so27,duong to vinh dien quan 9 tphcm', 'Đã xác nhận', 123123),
('HD00006', 'KH00001', '0000-00-00 00:00:00', 'so27,duong to vinh dien quan 9 tphcm', 'Đã xác nhận', 123123),
('HD00007', 'KH00001', '0000-00-00 00:00:00', 'so27,duong to vinh dien quan 9 tphcm', 'Đã xác nhận', 123123),
('HD00008', 'KH00001', '0000-00-00 00:00:00', 'so27,duong to vinh dien quan 9 tphcm', 'Đã xác nhận', 123123),
('HD00009', 'KH00011', '0000-00-00 00:00:00', 'hcm', 'Đã xác nhận', 30),
('HD00010', 'KH00011', '0000-00-00 00:00:00', 'hcm', 'Đã xác nhận', 3000),
('HD00011', 'KH00011', '0000-00-00 00:00:00', 'hcm', 'Đã xác nhận', 3000),
('HD00012', 'KH00011', '0000-00-00 00:00:00', 'hcm', 'Đã xác nhận', 3000),
('HD00013', 'KH00011', '0000-00-00 00:00:00', 'hcm', 'Đã xác nhận', 3000),
('HD00014', 'KH00011', '0000-00-00 00:00:00', 'hcm', 'Đã xác nhận', 3000),
('HD00015', 'KH00011', '0000-00-00 00:00:00', 'hcm', 'Đã xác nhận', 30000),
('HD00016', 'KH00011', '0000-00-00 00:00:00', 'hcm', 'Đã xác nhận', 30000),
('HD00017', 'KH00011', '0000-00-00 00:00:00', 'hcm', 'Đã xác nhận', 30000),
('HD00018', 'KH00011', '0000-00-00 00:00:00', 'hcm', 'Đã xác nhận', 30000),
('HD00019', 'KH00011', '0000-00-00 00:00:00', 'hcm', 'Đã xác nhận', 30000),
('HD00020', 'KH00011', '0000-00-00 00:00:00', 'hcm', 'Đã xác nhận', 30000),
('HD00021', 'KH00011', '0000-00-00 00:00:00', 'hcm', 'Đã xác nhận', 30000),
('HD00022', 'KH00011', '0000-00-00 00:00:00', 'hcm', 'Đã xác nhận', 30000),
('HD00023', 'KH00011', '0000-00-00 00:00:00', 'hcm', 'Đã xác nhận', 30000),
('HD00024', 'KH00011', '0000-00-00 00:00:00', 'hcm', 'Đã xác nhận', 30000),
('HD00025', 'KH00011', '0000-00-00 00:00:00', 'hcm', 'Đã xác nhận', 30000),
('HD00026', 'KH00011', '0000-00-00 00:00:00', 'hcm', 'Đã xác nhận', 30000),
('HD00027', 'KH00011', '0000-00-00 00:00:00', 'hcm', 'Đã xác nhận', 30000),
('HD00028', 'KH00011', '0000-00-00 00:00:00', 'hcm', 'Đã xác nhận', 30000),
('HD00029', 'KH00011', '0000-00-00 00:00:00', 'hcm', 'Đã xác nhận', 30000),
('HD00030', 'KH00011', '0000-00-00 00:00:00', 'hcm', 'Đã xác nhận', 30000),
('HD00031', 'KH00011', '0000-00-00 00:00:00', 'hcm', 'Đã xác nhận', 30000),
('HD00032', 'KH00011', '0000-00-00 00:00:00', 'hcm', 'Đã xác nhận', 30000),
('HD00033', 'KH00011', '0000-00-00 00:00:00', 'hcm', 'Đã xác nhận', 30000),
('HD00034', 'KH00011', '0000-00-00 00:00:00', 'hcm', 'Đã xác nhận', 30000),
('HD00035', 'KH00011', '0000-00-00 00:00:00', 'hcm', 'Đã xác nhận', 30000),
('HD00036', 'KH00011', '0000-00-00 00:00:00', 'hcm', 'Đã xác nhận', 30000),
('HD00037', 'KH00011', '0000-00-00 00:00:00', 'hcm', 'Đã xác nhận', 30000),
('HD00038', 'KH00011', '0000-00-00 00:00:00', 'hcm', 'Đã xác nhận', 30000),
('HD00039', 'KH00011', '0000-00-00 00:00:00', 'hcm', 'Đã xác nhận', 30000),
('HD00040', 'KH00011', '0000-00-00 00:00:00', 'hcm', 'Đã xác nhận', 30000),
('HD00041', 'KH00011', '0000-00-00 00:00:00', 'hcm', 'Đã xác nhận', 30000),
('HD00042', 'KH00011', '0000-00-00 00:00:00', 'hcm', 'Đã xác nhận', 30000),
('HD00043', 'KH00011', '0000-00-00 00:00:00', 'hcm', 'Đã xác nhận', 30000),
('HD00044', 'KH00011', '0000-00-00 00:00:00', 'hcm', 'Đã xác nhận', 30000),
('HD00045', 'KH00011', '0000-00-00 00:00:00', 'hcm', 'Đã xác nhận', 30000),
('HD00046', 'KH00011', '0000-00-00 00:00:00', 'hcm', 'Đã xác nhận', 30000),
('HD00047', 'KH00011', '0000-00-00 00:00:00', 'hcm', 'Đã xác nhận', 30000),
('HD00048', 'KH00011', '0000-00-00 00:00:00', 'hcm', 'Đã xác nhận', 30000),
('HD00049', 'KH00011', '0000-00-00 00:00:00', 'hcm', 'Đã xác nhận', 30000),
('HD00050', 'KH00011', '0000-00-00 00:00:00', 'hcm', 'Đã xác nhận', 30000),
('HD00051', 'KH00011', '0000-00-00 00:00:00', 'hcm', 'Đã xác nhận', 30000),
('HD00052', 'KH00011', '0000-00-00 00:00:00', 'hcm', 'Đã xác nhận', 30000),
('HD00053', 'KH00011', '0000-00-00 00:00:00', 'hcm', 'Đã xác nhận', 30000),
('HD00054', 'KH00011', '0000-00-00 00:00:00', 'hcm', 'Đã xác nhận', 20000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khachhang`
--

CREATE TABLE `khachhang` (
  `MaKH` varchar(7) COLLATE utf8_unicode_ci NOT NULL,
  `Email` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `HoTen` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DiaChi` varchar(80) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DienThoai` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MatKhau` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `khachhang`
--

INSERT INTO `khachhang` (`MaKH`, `Email`, `HoTen`, `DiaChi`, `DienThoai`, `MatKhau`) VALUES
('KH00001', 'fghsf09@gmail.com', 'nguyen van an', 'so27,duong to vinh dien quan 9 tphcm', '035764228', 'Nam'),
('KH00002', 'hat009@gmail.com', 'Truong nhu y', 'to dan pho 1, thi tran cát tien, lam dong', '032457242', 'Nu'),
('KH00003', 'dattinh9@gmail.com', 'doan van nghia', 'binh chan, ho chi minh', '065223777', 'Nam'),
('KH00004', 'havhht003623@gmail.com', 'le thanh toan', 'di an binh duong', '0757544653', 'Nam'),
('KH00005', 'hongduy09@gmail.com', 'nguyen van hau', 'thanh my loi, ho chi minh', '0855454228', 'Nam'),
('KH00006', 'hkjkbb87877@gmail.com', 'truong thi thi thuy', 'sa dec, ben tre', '094545427', 'Nu'),
('KH00007', 'thanhthanh93@gmail.com', 'nguyen thi anh', 'thanh an, ha tinh', '09764422', 'Nu'),
('KH00008', 'hahao8899@gmail.com', 'anh nguyen thi tran', 'chu du, dong thap', '0267678778', 'Nu'),
('KH00009', 'hoahongat009@gmail.com', 'doc tu hao', 'ben nghe, cat ba', 'Nu', '9/5/1997'),
('KH00010', 'kimhanhnguyen777@gmail.com', 'van an', 'da lat, lam dong', '05768988', 'Nam'),
('KH00011', 'nguyenltng@gmail.com', 'Nguyên', 'hcm', '0123123123', '1'),
('KH00012', 'nguyenlt@gmail.com', 'Nguyueen', 'HCM', '0123123123', 'mk'),
('KH00013', 'anh@gmail.com', 'Anh', 'a', '123123123', 'a'),
('KH00014', '', '', '', '', ''),
('KH00015', 'aaa@gmail.com', 'asd', '', '', '');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaisanpham`
--

CREATE TABLE `loaisanpham` (
  `MaLoaiSP` varchar(7) COLLATE utf8_unicode_ci NOT NULL,
  `TenLoaiSP` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `HinhAnh` varchar(100) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `loaisanpham`
--

INSERT INTO `loaisanpham` (`MaLoaiSP`, `TenLoaiSP`, `HinhAnh`) VALUES
('LSP0001', 'Trà sữa', '395.jpg'),
('LSP0002', 'Trà ', '246.jpg'),
('LSP0003', 'Soda', '349.jpg'),
('LSP0004', 'caffe', '158.jpg'),
('LSP0005', 'nước ngọt', '143.jpg'),
('LSP0006', 'Sinh tố', '541.jpg'),
('LSP0007', 'Nước ép', '972.jpg'),
('LSP0008', 'aa', '783.jpg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `MaSP` varchar(7) COLLATE utf8_unicode_ci NOT NULL,
  `MaLoaiSP` varchar(7) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TenSP` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `HinhAnh` varchar(150) COLLATE utf8_unicode_ci DEFAULT NULL,
  `Gia` int(11) DEFAULT NULL,
  `MoTa` varchar(500) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`MaSP`, `MaLoaiSP`, `TenSP`, `HinhAnh`, `Gia`, `MoTa`) VALUES
('SP00001', 'LSP0001', 'Trà sữa trân châu đường đen', '541.jpg', 20000, 'a'),
('SP00002', 'LSP0001', 'Trà sữa trân châu socola', '252.jpg', 30000, 'Thành phần:- Lá trà đen- Siro socola - Sữa đặc- Đá viên- Bột cacao nguyên chất- Trà sữa trân châu socola có chứa rất nhiều chất dinh dưỡng, kèm theo đó có vô số hương vị khác nhau cho các bạn lựa chọn, có hương vị khoai môn, bạc hà, việt quất, hồng trà, socola,…. Một số ly trà sữa kết hợp với nhiều hương vị khác nhau đem lại mùi vị khá đặc biệt thu hút các bạn trẻ. Ngoài ra, trong trà sữa còn chứa thêm topping trân châu thơm ngon.'),
('SP00003', 'LSP0001', 'Trà sữa thái xanh', 'https://static.wixstatic.com/media/70b069_fd25131f1acf48ccbee8dfa805e97d46~mv2.jpg/v1/fill/w_720,h_720,al_c,lg_1,q_85/70b069_fd25131f1acf48ccbee8dfa80', 25000, 'Thành phần:\r\n- Lá trà xanh(lá trà thái)\r\n- Sữa đặc\r\n- Đá viên\r\n- Trà sữa thái xanh có chứa rất nhiều chất dinh dưỡng, kèm theo đó có là hương vị thái xanh mang lại cảm giác mới lạ cho người uống so với các loại trà sữa truyền thống khác. Ngoài ra, trong trà sữa còn chứa thêm topping thạch rau câu vị dứa bổ dưỡng.'),
('SP00004', 'LSP0001', 'Trà sữa việt quất', 'https://sieuthinguyenlieu.com/profiles/sieuthinguyenlieucom/uploads/attach/post/images/tra-sua-khoai-mon.jpg', 28000, 'Thành phần:\r\n- Lá trà đen\r\n- Siro việt quất\r\n- Sữa đặc, đường cát\r\n- Đá viên\r\n- Trà sữa việt quất có chứa rất nhiều chất dinh dưỡng, kèm theo đó có vô số hương vị khác nhau cho các bạn lựa chọn, có hương vị khoai môn, bạc hà, việt quất, hồng trà, socola,…. Một số ly trà sữa kết hợp với nhiều hương vị khác nhau đem lại mùi vị khá đặc biệt thu hút các bạn trẻ. Ngoài ra, trong trà sữa còn chứa thêm topping trân châu thơm ngon.'),
('SP00005', 'LSP0001', 'Trà sữa dâu', 'http://hoiquanamthuc.com/wp-content/uploads/2015/06/Cach-lam-tra-sua-dau-tay-thom-ngon-danh-tang-cho-cac-be-yeu-7.jpg', 15000, 'Thành phần:\r\n- Lá trà đen\r\n- Siro dâu\r\n- Sữa đặc, đường cát\r\n- Đá viên\r\n- Dâu tây\r\n- Trà sữa dâu có chứa rất nhiều chất dinh dưỡng, kèm theo đó có hương vị dâu tạo cảm giác ngon miệng cho khách hàng. Hương vị chua dịu hòa với vị trà sữa ngọt ngào, mùi thơm của dâu tây đem đến sự yêu thích từ người thưởng thức. Ngoài ra, trong trà sữa còn chứa thêm topping trân châu thơm ngon.'),
('SP00006', 'LSP0002', 'Trà đào thêm đào', 'https://tchmobileapp.s3.ap-southeast-1.amazonaws.com/menufrontend/5b03966a1acd4d5bbd67239c_tradao.png', 25000, 'Thành phần:\r\n- Hồng trà\r\n- Đào ngâm \r\n- Đường trắng\r\n- Siro đào\r\n- Đá viên\r\n- Các lợi ích khi uống trà đào\r\n+ Giải nhiệt, thanh lọc cơ thể: Sử dụng trà đào giúp bạn giảm ngay cơn khát. Các nghiên cứu khoa học cho thấy, trong quả có 80% là chất xơ bổ sung cho cơ thể, nhuận tràng.\r\n+ Ngăn ngừa thiếu máu:Một trong những loại quả tự nhiên chứa cực nhiều chất sắt. Khi uống tự tay làm giúp tái tạo hemoglobin cần thiết để tạo hồng cầu, ngăn ngừa thiếu máu. Ngoài ra, trong đó có chứa chất ức chế kết tập'),
('SP00007', 'LSP0002', 'Trà đào hạt chia', 'http://tradaovien.com/wp-content/uploads/2019/04/k1-copy-1024x768.png', 20000, 'Thành phần:\r\n- Hồng trà\r\n- Đào ngâm \r\n- Hạt chia\r\n- Đường trắng\r\n- Siro đào\r\n- Đá viên\r\n- Các lợi ích khi uống trà đào\r\n+ Giải nhiệt, thanh lọc cơ thể: Sử dụng trà đào giúp bạn giảm ngay cơn khát. Các nghiên cứu khoa học cho thấy, trong quả có 80% là chất xơ bổ sung cho cơ thể, nhuận tràng.\r\n+ Ngăn ngừa thiếu máu:Một trong những loại quả tự nhiên chứa cực nhiều chất sắt. Khi uống tự tay làm giúp tái tạo hemoglobin cần thiết để tạo hồng cầu, ngăn ngừa thiếu máu. Ngoài ra, trong đó có chứa chất ức'),
('SP00008', 'LSP0002', 'Trà chanh lạnh', 'https://longgeylang.com.vn/upload/sanpham/tra-chanh-lanh-1556.jpg', 20000, 'Thành phần:\r\n- Chanh lạnh\r\n- Trà chanh\r\n- Đường trắng\r\n- Đá viên\r\n- Các lợi ích khi uống trà chanh\r\n+ Thêm chanh vào trà không chỉ làm tăng hương vị mà mang lại rất nhiều lợi ích sức khỏe. Trà chanh là thức uống tốt nhất để bắt đầu buổi sáng của bạn. Chanh có chứa vitamin C, một chất chống oxy hóa giúp bảo vệ hệ thống miễn dịch, giảm huyết áp, ngăn ngừa cảm lạnh thông thường.\r\n+ Hỗ trợ tiêu hóa\r\n+ Giúp giảm cân\r\n+ Kiểm soát lượng đường trong máu\r\n+ Ngăn ngừa ung thư\r\n+ Giải độc cơ thể\r\n+ Điều tr'),
('SP00009', 'LSP0002', 'Trà đào cam sả', 'https://phadincoffee.vn/wp-content/uploads/2019/05/c48ed6c7cce229bc70f3.jpg', 20000, 'Thành phần:\r\n- Hồng trà\r\n- Đào ngâm \r\n- Cam sả\r\n- Đường trắng\r\n- Siro đào\r\n- Đá viên\r\n- Các lợi ích khi uống trà đào\r\n+ Giải nhiệt, thanh lọc cơ thể: Sử dụng trà đào giúp bạn giảm ngay cơn khát. Các nghiên cứu khoa học cho thấy, trong quả có 80% là chất xơ bổ sung cho cơ thể, nhuận tràng.\r\n+ Ngăn ngừa thiếu máu:Một trong những loại quả tự nhiên chứa cực nhiều chất sắt. Khi uống tự tay làm giúp tái tạo hemoglobin cần thiết để tạo hồng cầu, ngăn ngừa thiếu máu. Ngoài ra, trong đó có chứa chất ức c'),
('SP00010', 'LSP0003', 'Soda bạc hà', 'https://cdn.shortpixel.ai/client/q_glossy,ret_img,w_500/https://icofood.store/wp-content/uploads/2018/12/mojito.jpg', 15000, 'Thành phần nguyên liệu:\r\n- Soda: 1 lon\r\n- Chanh tươi: 2 lát\r\n- Đường nước: 10ml\r\n- Siro bạc hà: 25ml\r\n- Lá bạc hà\r\n- Đá viên\r\nThành phần dinh dưỡng\r\n- Hương vị the mát mang đến cảm giác sảng khoái cho người thưởng thức. Soda bạc hà thường được sử dụng phổ biến vào mùa hè oi bức, hay những ngày trời nắng nóng. Điều thú vị mà bạn sẽ phấn khích là soda bạc hà không chỉ có công dụng giải khát, bên cạnh đó còn là loại thức uống giúp bổ sung năng lượng cực hiệu quả'),
('SP00011', 'LSP0003', 'Soda đào', 'http://karaokebaonhu.com/wp-content/uploads/2017/09/soda-dao.jpg', 17000, 'Thành phần nguyên liệu:\r\n- Soda: 1 lon\r\n- trái cây: đào\r\n- Đường nước: 10ml\r\n- Siro đào: 25ml\r\n- Đá viên\r\nThành phần dinh dưỡng\r\n- Là loại nước tinh khiết giàu khoáng chất, chứa nhiều nguyên tố có lợi cho sức khỏe như Kali, Magie hay Canxi giúp xương chắc khỏe\r\n- Hương vị the mát mang đến cảm giác sảng khoái cho người thưởng thức. Soda đào thường được sử dụng phổ biến vào mùa hè oi bức, hay những ngày trời nắng nóng. Điều thú vị mà bạn sẽ phấn khích là soda đào không chỉ có công dụng giải khát, '),
('SP00012', 'LSP0002', 'Sinh tố bơ 123', '964.jpg', 100000, 'Avc'),
('SP00013', 'LSP0002', 'Sinh tố ch123123uối - đậu phộng', '619.jpg', 30000, 'dưa hấu'),
('SP00014', 'LSP0002', 'Sinh tố ch123123uối - đậu phộng', '493.jpg', 30000, 'dưa hấu'),
('SP00015', 'LSP0006', 'Sinh tố xoài', 'https://monngonsaigon.net/wp-content/uploads/2018/12/cac-mon-sinh-to-ngon.jpg', 25000, 'Sinh tố xoài');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD PRIMARY KEY (`MaHD`,`MaSP`),
  ADD KEY `CTDH_MaSP_FK` (`MaSP`);

--
-- Chỉ mục cho bảng `danhgia`
--
ALTER TABLE `danhgia`
  ADD PRIMARY KEY (`MaDG`);

--
-- Chỉ mục cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`MaHD`),
  ADD KEY `DH_MaKH_FK` (`MaKH`);

--
-- Chỉ mục cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`MaKH`);

--
-- Chỉ mục cho bảng `loaisanpham`
--
ALTER TABLE `loaisanpham`
  ADD PRIMARY KEY (`MaLoaiSP`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`MaSP`),
  ADD KEY `SP_MaLoaiSP_FK` (`MaLoaiSP`);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD CONSTRAINT `CTDH_MaDH_FK` FOREIGN KEY (`MaHD`) REFERENCES `hoadon` (`MaHD`),
  ADD CONSTRAINT `CTDH_MaSP_FK` FOREIGN KEY (`MaSP`) REFERENCES `sanpham` (`MaSP`);

--
-- Các ràng buộc cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `DH_MaKH_FK` FOREIGN KEY (`MaKH`) REFERENCES `khachhang` (`MaKH`);

--
-- Các ràng buộc cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD CONSTRAINT `SP_MaLoaiSP_FK` FOREIGN KEY (`MaLoaiSP`) REFERENCES `loaisanpham` (`MaLoaiSP`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
