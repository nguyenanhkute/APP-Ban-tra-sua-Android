
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

        <?php require_once(__DIR__."\model\sanpham.php");?>
        <?php $keyword= $_GET['keyword'];
             $list_sanpham = search_sanpham($keyword) ;

             $search = isset($keyword)?$keyword:'';
        $num = mysqli_num_rows(search_sanpham($search));


        if ($num>0) { ?>
            <h3><?php echo $num ?> kết quả được tìm thấy với từ khóa '<?php echo $keyword ?>'</h3><br>
            <table class='data' id = 'sanphamTables'>
             <tr class='data'>
                <th  class='data'width='30px'>STT</th>
                <th class='data'id = "masp">Mã SP</th>
                <th class='data'>Tên Loại SP</th>
                <th class='data'>Sản phẩm</th>
                <th class='data'>Ảnh</th>
                <th class='data'>Giá</th>
                <th class='data'>Mô tả</th>
                <th class='data'>Sửa thông tin</th>
            </tr> 
                <?php $i=0 ; ?>
                <?php while ($sanpham= mysqli_fetch_array($list_sanpham)){ ?>
                    <tr >
                        <?php $i= $i +1; ?>
                        <td ><h4 ><?php echo $i; ?></h4></td>
                        <td ><h4 ><?php echo $sanpham['MaSP']; ?></h4></td>
                        <td ><h4 ><?php echo $sanpham['TenLoaiSP'] ?></h4></td>
                        <td >
                            <h4 ><?php echo $sanpham['TenSP']; ?></h4>
                        </td>
                        <?php $img = $sanpham['HinhAnh']; ?>
                        <td><?php echo '<img src="img/product-img/' .$img. '?time=' . time() . '" style="max-width:50px;" />'; ?></td>

                        <td ><?php echo $sanpham ? number_format($sanpham['Gia'],0,',',',') : 0; ?></td>
                        <td style="width: 15%" name = 'mota'><?php echo $sanpham['MoTa'] ?></td>
                        
                        <td >
                            <a href="suaSanPham.php?masp=<?php echo $sanpham['MaSP']; ?>">Sửa thông tin</a>
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
