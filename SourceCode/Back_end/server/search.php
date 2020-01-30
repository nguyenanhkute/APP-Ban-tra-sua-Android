<?php
	require_once 'conn.php';

	if(isset($_GET['key'])){
		$key = $_GET['key'];
	
		$query = "Select * From sanpham where tenSP like '%$key%'";
		$result = mysqli_query($conn, $query);
		
		$response = array();
		while($row = mysqli_fetch_assoc($result)){
			array_push($response, array( 
										'id' => $row['MaSP'],
									   'name' => $row['TenSP'],
									   'price'=> $row['Gia'],
									   'image'=> $row['HinhAnh']
									   )
			);
		}
		
		
		echo json_encode($response);
	}else{
		$query = "Select * From sanpham";
		$result = mysqli_query($conn, $query);
		
		$response = array();
		while($row = mysqli_fetch_assoc($result)){
			array_push($response, array(
										'id' => $row['MaSP'],
									   'name' => $row['TenSP'],
									   'price'=> $row['Gia'],
									   'image'=> $row['HinhAnh']
									   )
			);
		}
		
		echo json_encode($response);
	}
		mysqli_close($conn);


?>
