<?php
if ($_SERVER['REQUEST_METHOD'] =='GET'){
	require_once "conn.php";
	$MaSP= $_GET['masp'];
	$sql= "select * from sanpham where MaSP = '".$MaSP."' ";
	if ( mysqli_query($conn, $sql) ) {
		$result = array();
		$result['MaSPsanpham'] = array();
		$response = mysqli_query($conn, $sql);
		
		$temp = mysqli_num_rows($response);
		if(mysqli_num_rows($response) > 0)
		{
			while($temp > 0){
			
				$row = mysqli_fetch_assoc($response);
				
				$index['masp'] = $row["MaSP"];	
				$index['tensp'] = $row["TenSP"];
				$index['hinhanh'] = $row["HinhAnh"];
				$index['gia'] = $row["Gia"];
				$index['maloaisp'] = $row["MaLoaiSP"];
				
				$index['mota'] = $row["MoTa"];
			
				array_push($result['MaSPsanpham'], $index);

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