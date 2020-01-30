<?php 
include_once('C:/xampp/htdocs/admin/conn.php');
 	function lay_hoadon() {
		$conn = connect();
    	$strSQL = "select * from hoadon ";
    	$result = mysqli_query($conn, $strSQL) Or die('<p>Không thể thực thi câu truy vấn.</p>');
    	return $result;
	}
	function lay_hoadon_mahd($mahd) {
	    $conn = connect();
        $strSQL = "SELECT * FROM chitiethoadon  hoadon  WHERE  mahd='".$mahd."'";
        $result = mysqli_query($conn, $strSQL) Or die('<p>Không thể thực thi câu truy vấn.</p>');
        return $result;
    }

    function lay_tinhtrang_mahd($mahd) {
        $conn = connect();
        $strSQL = "SELECT * FROM hoadon  WHERE  mahd='".$mahd."'";
        $result = mysqli_query($conn, $strSQL) Or die('<p>Không thể thực thi câu truy vấn.</p>');
        return $result;
    }

    function search_hoadon($search) {
        $conn = connect();
        $sql1 = "select * from hoadon where maHD like '%".$search."%' or MaKH like '%".$search."%'or NGAYDATHANG like '%".$search."%' or DIACHINGUOINHAN like '%".$search."%' or TINHTRANG like '%".$search."%' or TRIGIA like '%".$search."%'";
        $result = mysqli_query($conn,$sql1) or die('<p>Không thể thực thi câu truy vấn.</p>');
        return $result;
    }

    function update_hoadon($mahd,$tinhtrang) {
    $conn = connect();
        $sql = "update hoadon set tinhtrang='".$tinhtrang."' where mahd='".$mahd."'";
        mysqli_query($conn,$sql) or die('<p>Không thể thực thi câu truy vấn.</p>');
 
    }
?>