
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" href="img/core-img/icon.ico">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hóa đơn</title>
        <c:set var="root" value="${pageContext.request.contextPath}"/>
        <link href="css/mos-style.css" rel='stylesheet'type='text/css' />
    </head>
    <body>
        <?php include "parts/header.php"?>
    <div id="wrapper">
        <?php include "parts/menu.php"?>
        <div id="rightContent">

        <h2>Quản lí hóa đơn</h2>
        <?php require_once(__DIR__."\model\hoadon.php");?>
        <?php $list_hoadon = lay_hoadon(); ?>
        <div class="searchp"> 
        <form  action="controllers/hoadon.php" method="post" enctype="multipart/form-data" >
            <input type="search" name="txt_search" value="" placeholder="search..." />
            <input type="hidden" name="command" value="search">
        </form>
        </div>

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
        </div>
        <div class="clear"></div>
        <?php include "parts/footer.php"?>
        </div>
    </body>
</html>
