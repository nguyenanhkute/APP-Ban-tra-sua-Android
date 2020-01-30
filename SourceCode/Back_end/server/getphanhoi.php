<?php


	//$target_path = "uploadfiles/";
	 if($_SERVER['REQUEST_METHOD']=='GET')
	 {
		    $tensp = $_GET['tensp'];
			
			
			//$tensp = 'Trà sữa trân châu đường đen';
			
			require_once 'conn.php';

			$sql_masp = "Select sp.masp from sanpham as sp where sp.tensp = '$tensp' ";
			$result1 = mysqli_query($conn, $sql_masp);
			$row = mysqli_fetch_array($result1);
			$masp = $row['masp'];

			$sql = "Select * From Danhgia Where MaSP = '$masp'";
	
			if ( mysqli_query($conn, $sql) ) {
				$result = array();
				$result['phanhoi'] = array();
				$response = mysqli_query($conn, $sql);
				
				$temp = mysqli_num_rows($response);
				if(mysqli_num_rows($response) > 0)
				{
					while($temp > 0){
					
						$row = mysqli_fetch_assoc($response);
						
						// Lấy tên kh
						$makh123 = $row["MaKH"];
						
						$sql_makh = "Select kh.Hoten from khachhang as kh where kh.makh = '$makh123' ";
						if ($result1 = mysqli_query($conn, $sql_makh))
						{
							$row1 = mysqli_fetch_array($result1);
							$index['tenkh'] = $row1['Hoten'];
						}
						
						
						// Lấy tên sản phẩm
						$masp123 = $row["MaSP"];
						
						$sql_masp1 = "Select sp.TenSP from sanpham as sp where sp.masp = '$masp123' ";
						if ($result1 = mysqli_query($conn, $sql_masp1))
						{
							$row2 = mysqli_fetch_array($result1);
							$index['tensp'] = $row2['TenSP'];
						}
						
						
						$index['hinhanh'] = $row["HinhAnh"];
						$index['mota'] = $row["Mota"];
					
						array_push($result['phanhoi'], $index);

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
