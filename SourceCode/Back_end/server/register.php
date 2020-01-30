<?php

if ($_SERVER['REQUEST_METHOD'] =='POST'){

    $name1 = $_POST['username'];
    $email1 = $_POST['email'];
    $password1 = $_POST['pass'];
	$address1 = $_POST['address'];
    $phone1 = $_POST['phone'];
	
	echo $name1;

    require_once 'conn.php';


	$sql_checkemail = "Select * from KHACHHANG where Email = '$email1'";
	$temp = 1;
	
	if ($result123 = mysqli_query($conn, $sql_checkemail))
	{
		$rowcount=mysqli_num_rows($result123);
		if ($rowcount == 1)
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
		$sql = "INSERT INTO KHACHHANG VALUES (AUTO_MAKH(),'$email1', '$name1', '$address1','$phone1', '$password1')";

		if ( mysqli_query($conn, $sql) ) {
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
	
}

?>
