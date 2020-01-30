
<!DOCTYPE html>
<html>
<head>
        <link rel="icon" href="img/core-img/icon.ico">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tìm kiếm</title>
        <c:set var="root" value="${pageContext.request.contextPath}"/>
        <link href="css/mos-style.css" rel='stylesheet'type='text/css' />
        
</head>
<body>
    <?php include "parts/header.php"?>
    <div id="wrapper">
        <?php include "parts/menu.php"?>
        <div id="rightContent">

        <h2>Quản lí hóa đơn</h2>

        <?php require_once(__DIR__."\model\chitiethoadon.php");?>
        <?php $keyword= $_GET['keyword'];
             $list_chitiethoadon = search_chitiethoadon($keyword) ;

             $search = isset($keyword)?$keyword:'';
        $num = mysqli_num_rows(search_chitiethoadon($search));

        if ($num>0) { ?>
            <h3><?php echo $num ?> kết quả được tìm thấy với từ khóa '<?php echo $keyword ?>'</h3><br>
            
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
            <?php }
            else {?>
                   <h3>Không tìm thấy bất kì sản phẩm nào với từ khóa '<?php echo $keyword ?>'</h3>
            <?PHP } ?>
        </div>
        <div class="clear"></div>
        <?php include "parts/footer.php"?>
        </div>
        
    </body>
</html>
