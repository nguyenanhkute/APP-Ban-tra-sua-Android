<?php
include_once('C:/xampp/htdocs/admin/model/loaisanpham.php');
	$command=$_POST['command'];

	if($command=="update"){
		$malsp = $_POST['maLoaiSP'];
		$tenlsp= $_POST['tenLoaiSP'];
		$anh_name=$_POST['AnhLSP'];
		update_loaisanpham($malsp,$tenlsp,$anh_name);
		header('location: /admin/loaisanpham.php');
	}

	if($command=="insert"){
		$tenlsp= $_POST['tenLSP'];
		$anh_name=$_POST['AnhLSP'];
		insert_loaisanpham($tenlsp,$anh_name);
		header('location: /admin/loaisanpham.php');
		
	}	

	if($command=="search"){
		$search = isset($_POST['txt_search'])?$_POST['txt_search']:'';

		if ($search!==""){
		$num = mysqli_fetch_row(search_loaisanpham($search));
		echo $num;
		if ($num>0){
			echo "$num kết quả được tìm thấy với từ khóa <b>$search</b>";
			header("Location:/admin/timkiemLoaiSanPham.php?keyword=$search");
		}else {
			echo "Không tìm thấy bất kì sản phẩm nào với từ khóa <b>$search</b>";
			header("Location:/admin/timkiemLoaiSanPham.php?keyword=$search");
		}

	}else{
		header("Location:/anatea/admin/loaisanpham.php");
	}
}
?>