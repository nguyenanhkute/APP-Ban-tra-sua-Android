<?php 
include_once('C:/xampp/htdocs/admin/conn.php');
 	function lay_nam() {
		$conn = connect();
    	$strSQL = "select YEAR(ngaydathang) AS nam from hoadon GROUP BY YEAR(ngaydathang) ";
    	$result = mysqli_query($conn, $strSQL) Or die('<p>Không thể thực thi câu truy vấn.</p>');
    	return $result;
	}
	function lay_doanhthu_thang($year) {
	    $conn = connect();
        $strSQL = "select month(ngaydathang) as thang,sum(trigia) as tongtien from hoadon where year(ngaydathang)='".$year."' GROUP by month(ngaydathang)";
        $result = mysqli_query($conn, $strSQL) Or die('<p>Không thể thực thi câu truy vấn.</p>');
        return $result;
    }
    function lay_doanhthu_lsp() {
        $conn = connect();
        $strSQL = "select lsp.TenLoaiSP as tenlsp, COUNT(ct.SoLuong) sl from loaisanpham lsp, sanpham sp, chitiethoadon ct WHERE lsp.MaLoaiSP=sp.MaLoaiSP and sp.MaSP = ct.MaSP GROUP BY lsp.TenLoaiSP";
        $result = mysqli_query($conn, $strSQL) Or die('<p>Không thể thực thi câu truy vấn.</p>');
        return $result;
    }
    function lay_doanhthu() {
        $conn = connect();
        $strSQL = "select lsp.TenLoaiSP as tenloaisp, year(hd.NgayDatHang) nam , sum(trigia) tong from hoadon hd , chitiethoadon ct , sanpham sp, loaisanpham lsp where hd.MaHD=ct.MaHD and ct.MaSP=sp.MaSP and lsp.MaLoaiSP= sp.MaLoaiSP GROUP by lsp.TenLoaiSP,year(hd.NgayDatHang)";
        $result = mysqli_query($conn, $strSQL) Or die('<p>Không thể thực thi câu truy vấn.</p>');
        return $result;
    }
?>