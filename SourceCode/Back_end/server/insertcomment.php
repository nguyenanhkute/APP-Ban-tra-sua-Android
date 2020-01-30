<?php
	require_once 'conn.php';

	//$target_path = "uploadfiles/";
	 if($_SERVER['REQUEST_METHOD']=='POST')
	 {
		    $tensp = $_POST['tensp'];
			$makh = $_POST['makh'];
			$mota = $_POST['mota'];
			$hinhanh = $_POST['hinhanh'];
			
			require_once 'conn.php';

			$sql_masp = "Select sp.masp from sanpham as sp where sp.tensp = '$tensp' ";
			$result1 = mysqli_query($conn, $sql_masp);
			$row = mysqli_fetch_array($result1);
			$masp = $row['masp'];

			$sql = "Insert into danhgia values(AUTO_MADG(),'$masp','$makh','$mota','$hinhanh')";
	
			if (mysqli_query($conn, $sql) ) {
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
