<?php
include_once('C:/xampp/htdocs/anatea/admin/model/hoadon.php');
$command=$_POST['command'];
	if($command=="search"){
		$search = isset($_POST['txt_search'])?$_POST['txt_search']:'';

		if ($search!==""){
		$num = mysqli_fetch_row(search_hoadon($search));
		echo $num;
		if ($num>0){
			echo "$num kết quả được tìm thấy với từ khóa <b>$search</b>";
			header("Location:/anatea/admin/timkiemHoaDon.php?keyword=$search");
		}else {
			echo "Không tìm thấy bất kì sản phẩm nào với từ khóa <b>$search</b>";
			header("Location:/anatea/admin/timkiemHoaDon.php?keyword=$search");
		}

		}
	}
	else if($command=="tinhtrang"){
		$tt = $_POST['tinhtrang'];
		$mahd = $_POST['mahd'];
		update_hoadon($mahd,$tt);
		header("Location:/anatea/admin/hoadon.php");
	}else{
		header("Location:/anatea/admin/hoadon.php");
	}
?>