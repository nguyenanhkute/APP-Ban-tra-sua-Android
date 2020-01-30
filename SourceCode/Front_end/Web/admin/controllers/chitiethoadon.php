<?php
include_once('C:/xampp/htdocs/anatea/admin/model/chitiethoadon.php');
$command=$_POST['command'];

	if($command=="search"){
		$search = isset($_POST['txt_search'])?$_POST['txt_search']:'';

		if ($search!==""){
			$num = mysqli_fetch_row(search_chitiethoadon($search));
			echo $num;
			if ($num>0){
				echo "$num kết quả được tìm thấy với từ khóa <b>$search</b>";
				header("Location:/anatea/admin/timkiemChiTietHoaDon.php?keyword=$search");
			}else {
				echo "Không tìm thấy bất kì sản phẩm nào với từ khóa <b>$search</b>";
				header("Location:/anatea/admin/timkiemChiTietHoaDon.php?keyword=$search");
			}

		}else{
			header("Location:/anatea/admin/chitiethoadon.php");
		}
	}
	if($command=="search_hd"){
		$search = isset($_POST['txt_search'])?$_POST['txt_search']:'';
		$mahd= $_POST['MaHD'];
		
		if ($search!==""){
			$num = mysqli_fetch_row(search_cthd_mahd($search,$mahd));
			echo $num;
			if ($num>0){
				echo "$num kết quả được tìm thấy với từ khóa <b>$search</b>";
				header("Location:/anatea/admin/timkiemChiTietHoaDon.php?keyword=$search");
			}else {
				echo "Không tìm thấy bất kì sản phẩm nào với từ khóa <b>$search</b>";
				header("Location:/anatea/admin/timkiemChiTietHoaDon.php?keyword=$search");
			}

		}else{
			header("Location:/anatea/admin/chitiethoadon.php");
		}
	}
?>


