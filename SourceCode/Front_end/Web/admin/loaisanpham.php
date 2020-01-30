
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="img/core-img/icon.ico">
        <title>Loại sản phẩm</title>
        <c:set var="root" value="${pageContext.request.contextPath}"/>
        <link href="css/mos-style.css" rel='stylesheet'type='text/css' />
    </head>
    <body>
        <?php include "parts/header.php"?>
    <div id="wrapper">
        <?php include "parts/menu.php"?>
        <div id="rightContent">

        <h2>Quản lí loại sản phẩm</h2><br>
        <?php require_once(__DIR__."\model\loaisanpham.php");?>
        <?php $list_loaisanpham = lay_loaisampham(); ?>

        <a href="themLoaiSanPham.php"  class="button" > Thêm loại sản phẩm </a>
        <div class="searchp"> 
        <form  action="controllers/loaisanpham.php" method="post" enctype="multipart/form-data" >
            <input type="search" name="txt_search" value="" placeholder="search..." />
            <input type="hidden" name="command" value="search">
        </form>
        </div>
         <form  id="user-form" method="post" enctype="multipart/form-data" role="form">
            <table class='data' id = 'loaisanphamTables'>
             <tr class='data'>
				<th class="data" width="30px">STT</th>
				<th class="data">Mã loại SP</th>
				<th class="data">Loại sản phẩm</th>
				<th class="data">Ảnh</th>
                <th class="data">Tùy chọn</th>
			</tr>
            <?php $i=0 ; ?>
                <?php while ($loaisanpham= mysqli_fetch_array($list_loaisanpham)){ ?>
                    <tr >
                        <?php $i= $i +1; ?>
                        <td ><h4 ><?php echo $i; ?></h4></td>
                        <td ><h4 ><?php echo $loaisanpham['MaLoaiSP']; ?></h4></td>
                        <td ><h4 ><?php echo $loaisanpham['TenLoaiSP'] ?></h4></td>
						</td>
                        <?php $img = $loaisanpham['HinhAnh']; ?>
                        <td><?php echo '<img src="' .$img. '" style="max-width:50px;" />'; ?></td>
                        <td >
                            <a href="suaLoaiSanPham.php?malsp=<?php echo $loaisanpham['MaLoaiSP']; ?>">Sửa thông tin</a>
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
 