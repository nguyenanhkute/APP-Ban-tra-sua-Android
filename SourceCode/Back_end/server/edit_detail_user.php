<?php

if ($_SERVER['REQUEST_METHOD']=='POST') {

    $id = $_POST['id'];
	$name = $_POST['name'];
	$email = $_POST['email'];
	$address = $_POST['address'];
	$phone = $_POST['phone'];

    require_once 'conn.php';
	
    $sql = "UPDATE users
			SET HoTen ='$name', email='$email', Diachi ='$address', DienThoai='$phone'
			WHERE MaKH = '$id'";


  
    
	
    if ( mysqli_query($conn, $sql) ) {
		

		$result['success'] = "1";
		$result['message'] = "success";
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


?>
