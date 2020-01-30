<?php
if ($_SERVER['REQUEST_METHOD'] =='GET'){

	require_once "conn.php";
	$TENLSP= $_GET['tenlsp'];
	$sql= "select MaSP,TenSP,sanpham.HinhAnh,Gia,sanpham.MaLoaiSP from sanpham, loaisanpham where sanpham.MaLoaiSP = loaisanpham.MaLoaiSP and loaisanpham.TenLoaiSP = '".$TENLSP."' ";
	if ( mysqli_query($conn, $sql) ) {
		$result = array();
		$result['LoaiSPsanpham'] = array();
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
			
				array_push($result['LoaiSPsanpham'], $index);

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