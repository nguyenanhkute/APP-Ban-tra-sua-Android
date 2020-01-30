
<!DOCTYPE html>
<html>
  <head>
        <link rel="icon" href="img/core-img/icon.ico">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sản phẩm bán ra</title>
        <c:set var="root" value="${pageContext.request.contextPath}"/>
        <link href="css/mos-style.css" rel='stylesheet'type='text/css' />
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  </head>
  <body>

    <?php include "parts/header.php"?>
    <div id="wrapper">
    <?php include "parts/menu.php"?>
    <div id="rightContent">

    <h2 style=" text-align: center">THỐNG KÊ SẢN PHẨM BÁN RA</h2>
    <br>
    <form action="" method="post">
        <div for="start">Từ ngày :
            <input type="date" id="start" name="start"  min="2016-01-01" max="2030-12-31">
            Tới ngày :
            <input type="date" id="end" name="end"  min="2016-01-01" max="2030-12-31">
            <button type="submit">Thống kê</button>
        </div>
    </form>
    </br></br>

    <?php 
        require_once(__DIR__."/model/thunhapsanpham.php");
        if(isset($_POST["start"])and isset($_POST["end"])) { 
        $start= $_POST["start"];
        $end= $_POST["end"];
        $start_new = date("d/m/Y", strtotime($start));
        $end_new = date("d/m/Y", strtotime($end));
         echo "<script> document.getElementById('start').value = '".$start."' </script>";
         echo "<script> document.getElementById('end').value = '".$end."' </script>";

        $list_sanpham = chart_sp($start,$end); 
        $chart_dt[]=["tensp","soluong"];
  
        $row= mysqli_num_rows($list_sanpham);
        while ($dt = mysqli_fetch_array( $list_sanpham)) {
            settype($dt["soluong"], "int");
            $chart_dt[]= [$dt["tensp"],$dt["soluong"]];
        }
    
     if ($row >0) { ?>
      <div id="piechart" style="width: 800px; height:500px;"></div>
      <script type="text/javascript">

          // Load google charts
          google.charts.load('current', {'packages':['corechart']});
          google.charts.setOnLoadCallback(drawChart);

          // Draw the chart and set the chart values
          function drawChart() {
          var data = google.visualization.arrayToDataTable(
            <?php echo json_encode($chart_dt);?>
          );

          var tit= 'Tỉ lệ sản phẩm từ ngày <?php echo $start_new ;?> đến ngày <?php echo $end_new ;?>';
          // Optional; add a title and set the width and height of the chart
          var options = {'title':tit, is3D:true};

          // Display the chart inside the <div> element with id="piechart"
          var chart = new google.visualization.PieChart(document.getElementById('piechart'));
            chart.draw(data, options);
          }
       </script>
      <?php } else {
        echo "<h2 style='text-align: center;color: black'>Không có sản phẩm nào được bán </h2> ";
      }}?>
 </div>
<div class="clear"></div>
<?php include "parts/footer.php"?>
</div>
</body>
</html>
