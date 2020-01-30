
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

        <h2>Quản lí sản phẩm</h2>

        <?php require_once(__DIR__."\model\loaisanpham.php");?>
        <?php $keyword= $_GET['keyword'];
             $list_loaisanpham = search_loaisanpham($keyword) ;

             $search = isset($keyword)?$keyword:'';
        $num = mysqli_num_rows(search_loaisanpham($search));


        if ($num>0) { ?>
            <h3><?php echo $num ?> kết quả được tìm thấy với từ khóa '<?php echo $keyword ?>'</h3><br>
            <table class='data' id = 'sanphamTables'>
             <tr class='data'>
                <th  class='data'width='30px'>STT</th>
                <th class='data'id = "masp">Mã Loại SP</th>
                <th class='data'>Tên Loại SP</th>
                <th class='data'>Sửa thông tin</th>
            </tr> 
                <?php $i=0 ; ?>
                <?php while ($loaisanpham= mysqli_fetch_array($list_loaisanpham)){ ?>
                    <tr >
                        <?php $i= $i +1; ?>
                        <td ><h4 ><?php echo $i; ?></h4></td>
                        <td ><h4 ><?php echo $loaisanpham['MaLoaiSP']; ?></h4></td>
                        <td ><h4 ><?php echo $loaisanpham['TenLoaiSP'] ?></h4></td>
                        
                        <td >
                            <a href="suaLoaiSanPham.php?malsp=<?php echo $loaisanpham['MaLoaiSP']; ?>">Sửa thông tin</a>
                        </td>
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
