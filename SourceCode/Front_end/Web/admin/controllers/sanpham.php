<?php
include_once('C:/xampp/htdocs/admin/model/sanpham.php');
include_once('C:/xampp/htdocs/admin/model/loaisanpham.php');
	$command=$_POST['command'];
echo $command;/**/
	if($command=="update"){
		$masp = $_POST['maSP'];
		$tensp= $_POST['tenSP'];
		$gia= $_POST['gia'];
		$mota= $_POST['mota'];
		$anh_name=$_POST['AnhSP'];
		
		
		/*if(isset($_FILES["chonanh"])){
			$file_ext=strtolower(end(explode('.',$_FILES['chonanh']['name'])));
			 $expensions= array("jpeg","jpg","png");
       
      		if(in_array($file_ext,$expensions)== false){
         		$errors[]="Chỉ hỗ trợ upload file jpg, JPEG hoặc PNG.";		
         		$anh_name="";
      		}else {
				$anh_name = $_FILES['chonanh']['name'];
			}*/
			update_sanpham($masp,$tensp,$gia,$mota,$anh_name);
	
		header('location: /admin/sanpham.php');
	}

	if($command=="insert"){
		$tensp= $_POST['tenSP'];
		$gia= $_POST['gia'];
		$mota= $_POST['mota'];
		$tenlsp=$_POST['tenLSP'];
		$arr_malsp=mysqli_fetch_array(lay_malsp($tenlsp));
		$malsp=$arr_malsp['MaLoaiSP'];
		$anh_name=$_POST['AnhSP'];
		echo  $tensp,$arr_malsp,$tenlsp,$malsp, $gia, $mota, $anh_name;
		 echo $anh_name;
		/*if(isset($_FILES["image"])){
			$end_image = explode('.',$_FILES['image']['name']);
			$duoi=end($end_image);
			$file_ext=strtolower($duoi);
			 $expensions= array('gif','png','jpg','jpeg');
       
      		if(in_array($file_ext,$expensions)== false){
         		$errors[]="Chỉ hỗ trợ upload file jpg, JPEG hoặc PNG.";		
         		$anh_name="";
      		}else {

				$anh_name = $_FILES['image']['name'];
			}
			
		}*/
		insert_sanpham($malsp,$tensp,$anh_name,$gia,$mota);
		header('location: /admin/sanpham.php');
	}	

	if($command=="search"){
		$search = isset($_POST['txt_search'])?$_POST['txt_search']:'';

		if ($search!==""){
		$num = mysqli_fetch_row(search_sanpham($search));
		echo $num;
		if ($num>0){
			echo "$num kết quả được tìm thấy với từ khóa <b>$search</b>";
			header("Location:/admin/timkiemSanPham.php?keyword=$search");
		}else {
			echo "Không tìm thấy bất kì sản phẩm nào với từ khóa <b>$search</b>";
			header("Location:/admin/timkiemSanPham.php?keyword=$search");
		}

	}else{
		header("Location:/admin/sanpham.php");
	}
}
?>