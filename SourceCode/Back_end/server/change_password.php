<?php

if ($_SERVER['REQUEST_METHOD']=='POST') {

    $id = $_POST['id'];
	$pass = $_POST['pass'];
	$passold = $_POST['passold'];

    require_once 'conn.php';

	$sql_checkpass  = "Select * From Khachhang where MatKhau = '$passold' and MaKH = '$id'";
	
	if($result123 = mysqli_query($conn, $sql_checkpass))
	{
		$rowcount=mysqli_num_rows($result123);
		if($rowcount == 0)
		{
			$result['success'] = "2";
			$result['message'] = "success";
			echo json_encode($result);
			mysqli_close($conn);
		}
		else
		{
			$sql = "UPDATE Khachhang
			SET  MatKhau='$pass'
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
		
	}

//}


?>
