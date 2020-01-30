

<html>
    <head>
        <link rel="icon" href="img/core-img/icon.ico">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin</title>
        <c:set var="root" value="${pageContext.request.contextPath}"/>
        <link href="css/mos-style.css" rel='stylesheet'type='text/css' />
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    </head>
    <body >
        <?php include "parts/header.php"?>
    <div id="wrapper">
        <?php include "parts/menu.php"?>
        <div id="rightContent">
            <br>
        <h2 style= "text-align: center;">QUẢN LÝ WEBSITE BÁN TRÀ SỮA</h2><br>
        <div id="mainleft">
            <div id="piechart"></div>

            <?php 
                require_once(__DIR__."/model/thunhapsanpham.php");
                    
                $list_sanpham = chart_slsp(); 
                $chart_dt[]=["tensp","soluong"];
  
                while ($dt = mysqli_fetch_array( $list_sanpham)) {
                    settype($dt["soluong"], "int");
                    $chart_dt[]= [$dt["tensp"],$dt["soluong"]];
                }
    
            ?>
                
                <script type="text/javascript">

          // Load google charts
                    google.charts.load('current', {'packages':['corechart']});
                    google.charts.setOnLoadCallback(drawChart);

                    // Draw the chart and set the chart values
                    function drawChart() {
                        var data = google.visualization.arrayToDataTable(
                                <?php echo json_encode($chart_dt);?>
                        );

                        var tit= 'Tỉ lệ số lượng sản phẩm bán ra';
          // Optional; add a title and set the width and height of the chart
                         var options = {'title':tit, is3D:true};

          // Display the chart inside the <div> element with id="piechart"
                        var chart = new google.visualization.PieChart(document.getElementById('piechart'));
                            chart.draw(data, options);
                        }
                    </script>
            </div>

            <div id="mainright">
            <?php 
        
                $thongke = thongke(); 
                $doanhthu= mysqli_fetch_array(thongke());
            ?>
            <br>
            <div  style= "font-size:16.5px;text-align: left;color: #228B22;">
                Tổng doanh thu :<?php echo number_format($doanhthu['tongdt']).' VND';?>
                <br><br>
                Tổng số hóa đơn :<?php echo $doanhthu['slhoahon'] ?> (HD)
                <br><br>
                Tổng số sản phẩm : <?php echo $doanhthu['slsp'] ?> (SP)
            </div><br>
        </div>

        <div id="mainleft1">
            <?php 
                require_once(__DIR__."/model/sanpham.php");
                $list_sp_lsp = lay_sp_lsp(); 
            ?>

            <form  id="user-form" method="post" enctype="multipart/form-data" role="form">
            <table class='data' id = 'hoadonTables'>
             <tr class='data'>
                <th class="data" width="30px">STT</th>
                <th class="data">Mã LSP</th>
                <th class="data">Tên LSP</th>
                <th class="data">Số lượng</th>
            </tr>
            <?php $i=0 ; ?>
                <?php while ($sp_lsp= mysqli_fetch_array($list_sp_lsp)){ ?>
                    <tr >
                        <?php $i= $i +1; ?>
                        <td ><h4 ><?php echo $i; ?></h4></td>
                        <td ><h4 ><?php echo $sp_lsp['MaLoaiSP']; ?></h4></td>
                        <td ><h4 ><?php echo $sp_lsp['TenLoaiSP'] ?></h4></td>
                        <td ><h4 ><?php echo $sp_lsp['sl'] ?></h4></td>
                    </tr>
                <?php } ?>
            </table>   
            </form>
        </div>
            
        <div >
            <div id="piechart1" ></div>
            <?php 
                $list= lay_sp_lsp(); 
                $chart_dt1[]=["tenlsp","SL"];

                while ($dt = mysqli_fetch_array($list)){
                        $chart_dt1[]= [$dt['TenLoaiSP'],(int)$dt['sl']];
                }
            ?>

            <script type="text/javascript">
    // Load google charts
                google.charts.load('current', {'packages':['corechart']});
                google.charts.setOnLoadCallback(drawChart1);

      // Draw the chart and set the chart values
                function drawChart1() {
                    var data = google.visualization.arrayToDataTable(
                         <?php echo json_encode($chart_dt1);?>
                    );
        /**/
      // Optional; add a title and set the width and height of the chart
                    var options = {'title':'Tổng số lượng sản phẩm theo loại sản phẩm',
                        vAxis: {title: 'số lượng'},
                        hAxis: {title: 'Loại sản phẩm'},
                        bar: {groupWidth: "20%"}
                    };

      // Display the chart inside the <div> element with id="piechart"
                    var chart = new google.visualization.ColumnChart(document.getElementById('piechart1'));
                    chart.draw(data, options);
                }

             </script>

        </div>

        </div>
        <div class="clear"></div>
        <?php include "parts/footer.php"?>
        </div>
    </body>
</html>
