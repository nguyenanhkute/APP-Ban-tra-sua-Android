<?php
if ($_SERVER['REQUEST_METHOD'] =='GET'){
	require_once "conn.php";
	$sql="SELECT * FROM loaisanpham";
	
	if ( mysqli_query($conn, $sql) ) {
		$result = array();
		$result['loaisanpham'] = array();
		$response = mysqli_query($conn, $sql);
		
		$temp = mysqli_num_rows($response);
		if(mysqli_num_rows($response) > 0)
		{
			while($temp > 0){
			
				$row = mysqli_fetch_assoc($response);
				
				$index['maloaisp'] = $row["MaLoaiSP"];	
				$index['tenloaisp'] = $row["TenLoaiSP"];
				$index['hinhanh'] = $row["HinhAnh"];
			
				array_push($result['loaisanpham'], $index);

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