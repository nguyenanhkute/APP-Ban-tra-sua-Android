
<!DOCTYPE html>
<html>
  <head>
        <link rel="icon" href="img/core-img/icon.ico">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sản phẩm bán theo tháng</title>
        <c:set var="root" value="${pageContext.request.contextPath}"/>
        <link href="css/mos-style.css" rel='stylesheet'type='text/css' />
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  </head>
  <body>

    <?php include "parts/header.php"?>
    <div id="wrapper">
    <?php include "parts/menu.php"?>
    <div id="rightContent">
      <h2 style=" text-align: center">THỐNG KÊ THU NHẬP THÁNG CỦA NĂM</h2>

      <?php 
      require_once(__DIR__."/model/thunhaptheothang.php");
      $list_nam = lay_nam();
      ?>
    </br>
      <form action="" method="post">
          <select id="chonnam" name="chonnam">
              <option selected >--CHỌN NĂM--</option>
              <?php while ($nam= mysqli_fetch_array($list_nam)){ ?>
                <option ><?php echo $nam['nam'] ?></option>
              <?php } ?>
          </select>
          <button type="submit">Thống kê</button>
      <form>

    <?php 
        if(isset($_POST["chonnam"])) { 
        $chonnam= $_POST["chonnam"];

        echo "<script> document.getElementById('chonnam').value = '".$chonnam."' </script>";
        $list= lay_doanhthu_thang($chonnam); 
        $chart_dt[]=["thang","tổng tiền"];
        $list_thang;
        $list_tongtien;
        $row= mysqli_num_rows($list);
        $in =1;
        while ($dt = mysqli_fetch_array( $list)){
          $list_thang[$in]=$dt["thang"];
          $list_tongtien[$dt["thang"]]=$dt['tongtien'];
          $in++;
        }
        for ($i =1 ; $i<=12;$i++) {
          if (in_array($i, $list_thang)){
            $chart_dt[]= [$i,$list_tongtien[$i]];
          }
          else 
            $chart_dt[]= [$i,0];
        }
    

    if ($row >0) { ?>
    <div id="piechart" style="width: 600px; height:500px;"></div>

    <script type="text/javascript">
    // Load google charts
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);

      // Draw the chart and set the chart values
      function drawChart() {
      var data = google.visualization.arrayToDataTable(/*[
        ['sanpham', 'soluong'],
        ['Work', 8],
        ['Eat', 2],
        ['E', 0],
        ]*/
        <?php echo json_encode($chart_dt);?>
        );
        /**/
        var tit= 'Tổng doanh thu theo tháng của năm <?php echo $chonnam ;?>';
      // Optional; add a title and set the width and height of the chart
      var options = {'title':tit, width: 740,
        height: 500,
        vAxis: {title: 'Thu nhập'},
        hAxis: {title: 'Tháng'},
        bar: {groupWidth: "90%"}
      };

      // Display the chart inside the <div> element with id="piechart"
      var chart = new google.visualization.ColumnChart(document.getElementById('piechart'));
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
