
<!DOCTYPE html>
<html>
  <head>
        <link rel="icon" href="img/core-img/icon.ico">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thống kê</title>
        <c:set var="root" value="${pageContext.request.contextPath}"/>
        <link href="css/mos-style.css" rel='stylesheet'type='text/css' />
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  </head>
  <body>

    <?php include "parts/header.php"?>
    <div id="wrapper">
    <?php include "parts/menu.php"?>
    <div id="rightContent">

    <h2 style=" text-align: center">THỐNG KÊ DOANH THU</h2>
    </br>
    <form action="" method="post">
        <div for="start">Từ ngày :
            <input type="date" id="start" name="start"  min="2016-01-01" max="2030-12-31">
            Tới ngày :
            <input type="date" id="end" name="end"  min="2016-01-01" max="2030-12-31">
            <button type="submit">Thống kê</button>
        </div>
    </form>
    </br><hr align= "center" width="90%" /></br>

	<?php 
        require_once(__DIR__."/model/thunhapsanpham.php");
        if(isset($_POST["start"])and isset($_POST["end"])) { 
        $start= $_POST["start"];
        $end= $_POST["end"];
        $start_new = date("d/m/Y", strtotime($start));
        $end_new = date("d/m/Y", strtotime($end));
         echo "<script> document.getElementById('start').value = '".$start."' </script>";
         echo "<script> document.getElementById('end').value = '".$end."' </script>";

        $list_hoadon = thongke_doanhthu($start,$end); 
  
        $doanhthu= mysqli_fetch_array(tong_doanhthu($start,$end));

        $row= mysqli_num_rows($list_hoadon);

        if ($row>0) {

	?>

<br><br>
	<div style="font-size: 17px;color: black "><b>Tổng doanh thu :
		<input style= "width: 120px;text-align: right;"value="<?php echo number_format($doanhthu['tongdt']).' VND';?>">
		<?php echo '&emsp;'; ?>
        Tổng số lượng hóa đơn:
        <input style= "width: 50px;text-align: right;"value=" <?php echo $doanhthu['slhoahon'] ?>">
	</b></div><br>
    <form  id="user-form" method="post" enctype="multipart/form-data" role="form">
            <table class='data' id = 'hoadonTables'>
             <tr class='data'>
                <th class="data" width="30px">STT</th>
                <th class="data">Mã HD</th>
                <th class="data">Mã KH</th>
                <th class="data">Ngày đặt hàng</th>
                <th class="data">Địa chỉ người nhận</th>
                <th class="data">Tình trạng</th>
                <th class="data">Trị giá</th>
                <th class="data">Tùy chọn</th>
            </tr>
            <?php $i=0 ; ?>
                <?php while ($hoadon= mysqli_fetch_array($list_hoadon)){ ?>
                    <tr >
                        <?php $i= $i +1; ?>
                        <td ><h4 ><?php echo $i; ?></h4></td>
                        <td ><h4 ><?php echo $hoadon['MaHD']; ?></h4></td>
                        <td ><h4 ><?php echo $hoadon['MaKH'] ?></h4></td>
                        <td ><h4 ><?php echo $hoadon['NgayDatHang'] ?></h4></td>
                        <td ><h4 ><?php echo $hoadon['DiaChiNguoiNhan'] ?></h4></td>
                        <td ><h4 ><?php echo $hoadon['TinhTrang'] ?></h4></td>
                        <td ><h4 ><?php echo $hoadon['TriGia'] ?></h4></td>
                        <td>
                            <a href="CTHD_MaHD.php?MaHD=<?php echo $hoadon['MaHD']; ?>">chi tiết</a>
                        </td>
                    </tr>
                <?php } ?>
            </table>   
        </form>
    <?php
	}else { echo "<h2 style='text-align: center;color: black'>Không có sản phẩm nào được bán </h2> ";}}
    ?>
	</div>
	<div class="clear"></div>
	<?php include "parts/footer.php"?>
	</div>
</body>
</html>
