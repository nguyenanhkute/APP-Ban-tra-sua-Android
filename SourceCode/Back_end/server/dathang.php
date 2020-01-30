<?php
if ($_SERVER['REQUEST_METHOD'] =='POST'){
	

	$makh = $_POST['makh'];
    $tongtien = $_POST['tongtien'];
	$mangsanpham = $_POST['mangsanpham'];
	$mangsoluong = $_POST['mangsoluong'];

	/*$makh = "KH00011";
	$mangsoluong = "1,";
	$mangsanpham = "SP00002,";
	$tongtien = "30,000"; */
	$tt = (int)$tongtien*1000; 
	
	require_once 'conn.php';
	// Lấy mã HOADON tự động/*//*
	$sql = "Select AUTO_MAHD() AS MAHD ";
	$result1 = mysqli_query($conn,$sql);
	$row = mysqli_fetch_assoc($result1);
	$mahoahon = $row['MAHD'];

	
	//Lấy địa chỉ giao hàng.
	$diachi = '';
	$sql_khachhang = "Select * From KHACHHANG where MaKH ='$makh'";
	if($response = mysqli_query($conn, $sql_khachhang))
	{
		$row = mysqli_fetch_assoc($response);
		$diachi = $row['DiaChi'];
	}

	// Tạo hóa đơn
	
	$date = 'date("Y-m-d H:i:s")';
	$sql_hoadon = "insert into HOADON values('$mahoahon','$makh','$date','$diachi','Đã xác nhận',$tt)";

		$result123 = mysqli_query($conn, $sql_hoadon);
	
	// Thêm chi tiết hóa đơn
	$a = explode (',', $mangsanpham);
	$b = explode (',', $mangsoluong);
	
	for($i = 0; $i < count($a); $i++){
		if($a[$i] == '')
		{
			break;
		}
		
		// Lấy giá của sản phẩm. 
		$sql_gia = "select gia from sanpham where MaSP='$a[$i]'";
		$result2 = mysqli_query($conn,$sql_gia);
		$row = mysqli_fetch_array($result2);
		$gia = $row['gia'];
		

		$giatien = $gia*$b[$i];
		
		$sql_cthd = "Insert into chitiethoadon values('$mahoahon', '$a[$i]', $giatien, $b[$i])";	
	
		mysqli_query($conn, $sql_cthd);
	
		
	}

	
		if ($result123 ) {
			$result["success"] = "1";
			$result["message"] = "success";

			echo json_encode($result);
			mysqli_close($conn);

		} else {

			$result["success"] = "0";
			$result["message"] = "error";

			echo json_encode($result);
			mysqli_close($conn);
		}
		
}

?>
