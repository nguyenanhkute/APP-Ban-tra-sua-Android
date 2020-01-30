<?php
if ($_SERVER['REQUEST_METHOD'] =='GET'){

	require_once "conn.php";
	$MaKH = $_GET['makhachhang'];
	$sql="SELECT TenSP, Gia, HinhAnh FROM hoadon AS A INNER JOIN chitiethoadon AS B ON A.MaHD = B.MaHD INNER JOIN sanpham AS C ON B.MaSP = C.MaSP WHERE MaKH='$MaKH'";
	
	if ( mysqli_query($conn, $sql) ) {
		$result = array();
		$result['sanpham'] = array();
		$response = mysqli_query($conn, $sql);
		
		$temp = mysqli_num_rows($response);
		if(mysqli_num_rows($response) > 0)
		{
			while($temp > 0){
			
				$row = mysqli_fetch_assoc($response);
					
				$index['tensp'] = $row["TenSP"];
				$index['hinhanh'] = $row["HinhAnh"];
				$index['gia'] = $row["Gia"];
			
				array_push($result['sanpham'], $index);

				$result['success'] = "1";
				$result['message'] = "success";
				
				$temp = $temp - 1;
			}
			echo json_encode($result);
			mysqli_close($conn);
		}
		else {
		$result['success'] = "0";
		$result['message'] = "error";
		echo json_encode($result);

		mysqli_close($conn);

		}
	}
	
}
?>