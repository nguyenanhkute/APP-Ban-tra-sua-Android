<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" href="img/core-img/icon.ico">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <title>Sửa loại sản phẩm</title>
        <c:set var="root" value="${pageContext.request.contextPath}"/>
        <link href="/css/mos-style.css" rel='stylesheet' type='text/css' />
        <script>
            $(document).ready(function(){
                $(document).on('change', '#file', function(){
                    var name = document.getElementById("file").files[0].name;
                    var form_data = new FormData();
                    var ext = name.split('.').pop().toLowerCase();
                    if(jQuery.inArray(ext, ['gif','png','jpg','jpeg']) == -1) {
                        alert("Invalid Image File");
                    }
                    var oFReader = new FileReader();
                    oFReader.readAsDataURL(document.getElementById("file").files[0]);
                    var f = document.getElementById("file").files[0];
                    var fsize = f.size||f.fileSize;
                    if(fsize > 2000000){
                        alert("Image File Size is very big");
                    }
                    else{
                        form_data.append("file", document.getElementById('file').files[0]);
                        $.ajax({
                            url:"upload.php",
                            method:"POST",
                            data: form_data,
                            contentType: false,
                            cache: false,
                            processData: false,
                            success:function(data){
                                $('#uploaded_image').html(data);
                                var fullPath = document.getElementById("img1").src;
                                var filename = fullPath.replace(/^.*[\\\/]/, '');
                                // or, try this, 
                                document.getElementById("name_image").value = filename;
                            }
                        });
                    }
                });
            });
        </script>
    </head>
    <body>
        <?php include 'parts/header.php'?>
        <form action="controllers/loaisanpham.php" method="post" enctype="multipart/form-data">
        <div id="wrapper">
            <?php include "parts/menu.php" ?>
            <div id="rightContent">
                <h2>Quản lí loại sản phẩm </h2>
                <?php require_once(__DIR__."\model\loaisanpham.php");?>
                <?php $maloaisp= $_GET['malsp'];?>
                <?php $loaisanpham = mysqli_fetch_array(lay_loaisampham_malsp($maloaisp));?>

                    <table border="1", width="850" align="center">
                         <tr>
                            <td width="125px"><b >Mã sản phẩm</b></td>
                            <td><input required type="text" class="sedang" name="maLoaiSP" value="<?php echo $maloaisp ?>"readonly ></td>
                        </tr>
                        <tr>
                            <td width="125px"><b>Tên sản phẩm</b></td>
                            <td><input required type="text" class="sedang" name="tenLoaiSP" value="<?php echo $loaisanpham['TenLoaiSP'] ?>"></td>
                        </tr>
						<tr>
                            <td width="125px"><b>Ảnh</b></td>
                            <td><input required type="text" class="sedang" name="AnhLSP" value="<?php echo $loaisanpham['HinhAnh'] ?>"></td>
                        </tr>
                        
                        <tr><td>
                            <input type="hidden" name="command" value="update">
                            <input type="submit" class="button" value="Lưu dữ liệu">
                        </td></tr>
                    </table>
                </div> 
            <div class ="clear"></div>

            <?php include "parts/footer.php" ?>
            
         </div>
       </form> 
    </body>
</html>
