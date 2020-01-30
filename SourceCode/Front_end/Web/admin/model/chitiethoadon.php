<?php 
include_once('C:/xampp/htdocs/admin/conn.php');
 	function lay_chitiethoadon() {
		$conn = connect();
    	$strSQL = "select * from chitiethoadon ";
    	$result = mysqli_query($conn, $strSQL) Or die('<p>Không thể thực thi câu truy vấn.</p>');
    	return $result;
	}

    function search_chitiethoadon($search) {
        $conn = connect();
        $sql1 = "select * from chitiethoadon where maHD like '%".$search."%' or MaSP like '%".$search."%'or THANHTIEN like '%".$search."%' or SOLUONG like '%".$search."%'";
        $result = mysqli_query($conn,$sql1) or die('<p>Không thể thực thi câu truy vấn.</p>');
        return $result;
    }

    function search_cthd_mahd($search,$mahd) {
        $conn = connect();
        $sql1 = "select * from chitiethoadon where maHD like '%".$search."%' or MaSP like '%".$search."%'or THANHTIEN like '%".$search."%' or SOLUONG like '%".$search."%' where maHD = '".$mahd."'";
        $result = mysqli_query($conn,$sql1) or die('<p>Không thể thực thi câu truy vấn.</p>');
        return $result;
    }
?>