<?php 
include_once('C:/xampp/htdocs/admin/conn.php');
 	function lay_loaisampham() {
		$conn = connect();
    	$strSQL = "select * from loaisanpham ";
    	$result = mysqli_query($conn, $strSQL) Or die('<p>Không thể thực thi câu truy vấn.</p>');
    	return $result;
	}
	function lay_loaisampham_malsp($malsp) {
	$conn = connect();
    $strSQL = "select * from loaisanpham where maloaisp='".$malsp."'";
    $result = mysqli_query($conn, $strSQL) Or die('<p>Không thể thực thi câu truy vấn.</p>');
    return $result;
}
	function lay_malsp($TENLSP) {
		$conn = connect();
    	$strSQL = "select MaLoaiSP from loaisanpham WHERE TenLoaiSP ='".$TENLSP."' ";
    	$result = mysqli_query($conn, $strSQL) Or die('<p>Không thể thực thi câu truy vấn.</p>');
    	return $result;
   	}

   	function update_loaisanpham($maloaisp,$tenloaisp,$anh) {
    	$conn = connect();
        $sql = "update loaisanpham set TenLoaiSP='".$tenloaisp."', HinhAnh ='".$anh."'where maloaisp='".$maloaisp."'";
        mysqli_query($conn,$sql) or die('<p>Không thể thực thi câu truy vấn.</p>');
	}

    function insert_loaisanpham($tenloaisp,$anh) {
        $conn = connect();
        $sql1 = "insert into loaisanpham VALUES (AUTO_MALSP(),'".$tenloaisp."','".$anh."')";
		echo $sql1;
        mysqli_query($conn,$sql1) or die('<p>Không thể thực thi câu truy vấn.</p>');
           
    }

    function search_loaisanpham($search) {
        $conn = connect();
        $sql1 = "select * from loaisanpham where maloaisp like '%".$search."%' or tenloaisp like '%".$search."%' ";
        $result = mysqli_query($conn,$sql1) or die('<p>Không thể thực thi câu truy vấn.</p>');
        return $result;
    }
?>