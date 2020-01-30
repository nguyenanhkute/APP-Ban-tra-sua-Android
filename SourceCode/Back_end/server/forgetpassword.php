<?php

if ($_SERVER['REQUEST_METHOD'] =='POST'){

    $email1 = $_POST['email'];
    $password1 = $_POST['pass'];
    $phone1 = $_POST['phone'];

	
	
	
    require_once 'conn.php';

	$sql_checkemail = "Select * from KHACHHANG where Email = '$email1'";
	$temp = 1;
	
	if ($result1 = mysqli_query($conn, $sql_checkemail))
	{
		$rowcount=mysqli_num_rows($result1);
		if ($rowcount == 0)
		{
			$temp = 0;
			$result["success"] = "2";
			$result["message"] = "error";
			
			echo json_encode($result);
			mysqli_close($conn);
		}
		
	}

	if($temp == 1)
	{
		$sql_checkphone  = "Select * from KHACHHANG where DienThoai = '$phone1'";
		if ($result2 = mysqli_query($conn, $sql_checkphone))
		{
			$rowcount=mysqli_num_rows($result2);
			if ($rowcount == 0)
			{
				$temp = 0;
				$result["success"] = "3";
				$result["message"] = "error";
				
				echo json_encode($result);
				mysqli_close($conn);
			}
		
		}
	}
	
	if($temp == 1)
	{
		$sql  = "Update KHACHHANG Set MatKhau = '$password1' Where Email = '$email1' and DienThoai = '$phone1'";
		if ($result3 = mysqli_query($conn, $sql))
		{
				$result["success"] = "1";
				$result["message"] = "error";
				
				echo json_encode($result);
				mysqli_close($conn);
		
		}else {

			$result["success"] = "0";
			$result["message"] = "error";

			echo json_encode($result);
			mysqli_close($conn);
		}
	}
	
}

?>
