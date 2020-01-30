<?php

if ($_SERVER['REQUEST_METHOD']=='GET') {

    $id = $_GET['id'];

    require_once 'conn.php';

	
    $sql = "SELECT * FROM khachhang WHERE makh='$id'";


  
    
	
    if ( mysqli_query($conn, $sql) ) {
		$result = array();
		$result['read'] = array();
		$response = mysqli_query($conn, $sql);
		
		if(mysqli_num_rows($response) > 0){
        
			$row = mysqli_fetch_assoc($response);
				
			$index['name'] = $row["HoTen"];
			$index['email'] = $row["Email"];
			$index['phone'] = $row["DienThoai"];
			$index['address'] = $row["DiaChi"];
			
			array_push($result['read'], $index);

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
