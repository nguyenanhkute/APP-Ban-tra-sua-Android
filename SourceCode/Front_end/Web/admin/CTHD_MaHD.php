
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
        <?php $mahd= $_GET['MaHD'];?>
        <?php $list_cthd = lay_hoadon_mahd($mahd);?>
        <?php $ttrang = lay_tinhtrang_mahd($mahd);?>
    <FORM action="controllers/hoadon.php" method="POST" enctype="multipart/form-data">
        <div>Tình trạng 
            <input  hidden name="mahd" value="<?php echo $_GET['MaHD'];?>">
            <select  name="tinhtrang">
                <?php $tinhtrang= mysqli_fetch_array($ttrang); ?>
                <option selected ="selected"><?php echo $tinhtrang['TinhTrang'] ?></option>
                <option >Đang giao </option>
                <option >Đã giao </option>
                <option >Đã xác nhận </option>

            </select>
        </div>
            <table class='data' id = 'hoadonTables'>
             <tr class='data'>
                <th class="data" width="30px">STT</th>
                <th class="data">Mã HD</th>
                <th class="data">Mã Sản Phẩm</th>
                <th class="data">Số Lượng</th>
                <th class="data">Thành Tiền</th>
            </tr>
            <?php $i=0 ; ?>
                <?php while ($cthd= mysqli_fetch_array($list_cthd)){ ?>
                    <tr >
                        <?php $i= $i +1; ?>
                        <td ><h4 ><?php echo $i; ?></h4></td>
                        <td ><h4 ><?php echo $cthd['MaHD']; ?></h4></td>
                        <td ><h4 ><?php echo $cthd['MaSP'] ?></h4></td>
                        <td ><h4 ><?php echo $cthd['SoLuong'] ?></h4></td>
                        <td ><h4 ><?php echo $cthd['ThanhTien'] ?></h4></td>
                    </tr>
                <?php } ?>
            </table>   
        <input type="hidden" name="command" value="tinhtrang">
        <input type="submit" class="button" value="Lưu dữ liệu" >
    </FORM>
        </div>
        <div class="clear"></div>
        <?php include "parts/footer.php"?>
        </div>
    </body>
</html>
