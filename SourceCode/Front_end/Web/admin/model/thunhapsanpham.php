<?php
include_once('C:/xampp/htdocs/admin/conn.php');
	function chart_sp($start,$end){
		$conn = connect();
    	$strSQL = "select sp.tensp, count(ct.masp) as soluong from chitiethoadon ct , sanpham sp , hoadon hd where hd.MaHD=ct.mahd and ct.Masp=sp.MaSP and hd.NgayDatHang between '".$start."' and '".$end."' GROUP by sp.tensp";
    	$result =mysqli_query($conn, $strSQL) Or die('<p>Không thể thực thi câu truy vấn.</p>');
    	return $result;
    }
    function chart_slsp(){
        $conn = connect();
        $strSQL = "select sp.tensp, count(ct.masp) as soluong from chitiethoadon ct , sanpham sp , hoadon hd where hd.MaHD=ct.mahd and ct.Masp=sp.MaSP GROUP by sp.tensp";
        $result =mysqli_query($conn, $strSQL) Or die('<p>Không thể thực thi câu truy vấn.</p>');
        return $result;
    }

	function thongke_doanhthu($start,$end){
		$conn = connect();
    	$strSQL = "select * from hoadon where NgayDatHang BETWEEN '".$start."' and '".$end."'";
    	$result =mysqli_query($conn, $strSQL) Or die('<p>Không thể thực thi câu truy vấn.</p>');
    	return $result;
	}

	function tong_doanhthu($start,$end){
		$conn = connect();
    	$strSQL = "select sum(trigia) as tongdt,count(mahd) as slhoahon from hoadon where NgayDatHang BETWEEN '".$start."' and '".$end."'";
    	$result =mysqli_query($conn, $strSQL) Or die('<p>Không thể thực thi câu truy vấn.</p>');
    	return $result;
	}

    function thongke(){
        $conn = connect();
        $strSQL = "select sum(hd.trigia) as tongdt,count(hd.mahd) as slhoahon, sum(ct.SoLuong) as slsp from hoadon hd, chitiethoadon ct WHERE hd.MaHD=ct.MaHD";
        $result =mysqli_query($conn, $strSQL) Or die('<p>Không thể thực thi câu truy vấn.</p>');
        return $result;
    }
?>