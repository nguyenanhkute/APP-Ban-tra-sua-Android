
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="img/core-img/icon.ico">
        <title>Hóa đơn</title>
        <c:set var="root" value="${pageContext.request.contextPath}"/>
        <link href="css/mos-style.css" rel='stylesheet'type='text/css' />
    </head>
    <body>
        <?php include "parts/header.php"?>
    <div id="wrapper">
        <?php include "parts/menu.php"?>
        <div id="rightContent">

        <h2>Quản lí chi tiết hóa đơn</h2>
        <?php require_once(__DIR__."\model\chitiethoadon.php");?>
        <?php $list_chitiethoadon = lay_chitiethoadon(); ?>
        <div class="searchp"> 
        <form  action="controllers/chitiethoadon.php" method="post" enctype="multipart/form-data" >
            <input type="search" name="txt_search" value="" placeholder="search..." />
            <input type="hidden" name="command" value="search">
        </form>
        </div>
         <form  id="user-form" method="post" enctype="multipart/form-data" role="form">
            <table class='data' id = 'hoadonTables'>
             <tr class='data'>
                <th class="data" width="30px">STT</th>
                <th class="data">Mã HD</th>
                <th class="data">Mã Sản Phẩm</th>
                <th class="data">Số Lượng</th>
                <th class="data">Thành Tiền</th>
            </tr>
            <?php $i=0 ; ?>
                <?php while ($cthd= mysqli_fetch_array($list_chitiethoadon)){ ?>
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
        </form>
        </div>
        <div class="clear"></div>
        <?php include "parts/footer.php"?>
        </div>
    </body>
</html>
