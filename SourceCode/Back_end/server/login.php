<?php

if ($_SERVER['REQUEST_METHOD']=='POST') {

    $email = $_POST['email'];
    $password1= $_POST['password'];

	
    require_once 'conn.php';

    $sql = "SELECT * FROM khachhang WHERE email='$email' and matkhau ='$password1' ";

  
    
	
    if ( mysqli_query($conn, $sql) ) {
		$result = array();
		$result['login'] = array();
		$response = mysqli_query($conn, $sql);
		
		if(mysqli_num_rows($response) > 0){
        
			$row = mysqli_fetch_assoc($response);
				
			$index['name'] = $row["HoTen"];
			$index['email'] = $row["Email"];
			$index['id'] = $row["MaKH"];
			$index['address'] = $row["DiaChi"];
			$index['phone'] = $row["DienThoai"];
			
			
			array_push($result['login'], $index);

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
	 

    

}

?>
