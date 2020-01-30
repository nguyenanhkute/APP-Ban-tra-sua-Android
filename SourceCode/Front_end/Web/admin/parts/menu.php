


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>menu</title>
        <c:set var="root" value="${pageContext.request.contextPath}"/>
        <link href="css/mos-style.css" rel='stylesheet'type='text/css' />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script >
        $(function() {
    var Accordion = function(el, multiple) {
                this.el = el || {};
                    this.multiple = multiple || false;

                    // Variables privadas
                    var links = this.el.find('.link');
                        // Evento
                    links.on('click', {el: this.el, multiple: this.multiple}, this.dropdown)
                }
    
                Accordion.prototype.dropdown = function(e) {
                    var $el = e.data.el;
                    $this = $(this),
                    $next = $this.next();

                $next.slideToggle();
                $this.parent().toggleClass('open');

                if (!e.data.multiple) {
                    $el.find('.submenu').not($next).slideUp().parent().removeClass('open');
                };
            }   

        var accordion = new Accordion($('#accordion'), false);
        });
        </script >
        
    
    <style>
    * {
    margin: 0;
    padding: 0;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    box-sizing: border-box;
}

ul {
    list-style-type: none;
}

a {
    color: #b63b4d;
    text-decoration: none;
}

/** =======================
 * Contenedor Principal
 ===========================*/
h1 {
    color: #FFF;
    font-size: 24px;
    font-weight: 400;
    text-align: center;
    margin-top: 80px;
 }

h1 a {
    color: #c12c42;
    font-size: 16px;
 }

 .accordion li{
    width: 164px;
    background: linear-gradient(120deg,#9AC0CD,#B4CDCD,#D1EEEE)repeat left center;
    padding: 10px 0 10px 10px; margin-top: 8px;margin-left: 9px;
    border: 2px solid #dcdcdc;
    -moz-border-radius-topleft: 5px;-moz-border-radius-bottomleft: 5px;
    -webkit-border-top-left-radius: 5px;-webkit-border-bottom-left-radius: 5px;
    -o-border-top-left-radius: 5px;-o-border-bottom-left-radius: 5px;
 }

.accordion .link {
    cursor: pointer;
    display: block;
    padding: 5px 10px 10px 10px;
    color: #4D4D4D;
    font-size: 14px;
    font-weight: 700;
    border-bottom: 1px solid #CCC;
    position: relative;
    -webkit-transition: all 0.4s ease;
    -o-transition: all 0.4s ease;
    transition: all 0.4s ease;
}

.accordion li:last-child .link {
    border-bottom: 0;
    
}

.accordion li i {
    position: absolute;
    top: 16px;
    left: 12px;
    font-size: 18px;
    color: #595959;
    -webkit-transition: all 0.4s ease;
    -o-transition: all 0.4s ease;
    transition: all 0.4s ease;
}

.accordion li i.fa-chevron-down {
    right: 12px;
    left: auto;
    font-size: 16px;
}

.accordion li.open .link {
    color: #000000;
}

.accordion li.open i {
    color: #000000;
}
.accordion li.open i.fa-chevron-down {
    -webkit-transform: rotate(180deg);
    -ms-transform: rotate(180deg);
    -o-transform: rotate(180deg);
    transform: rotate(180deg);
}

.accordion li.default .submenu {display: block;}
/**
 * Submenu
 -----------------------------*/
 .submenu {
    display: none;
    background: #8eb7f2;
    font-size: 14px;
 }

 .submenu li {
    border-bottom: 1px solid #4b4a5e;
 }

 .submenu a {
    display: block;
    text-decoration: none;
    color: #d9d9d9;
    padding: 12px;
    padding-left: 42px;
    -webkit-transition: all 0.25s ease;
    -o-transition: all 0.25s ease;
    transition: all 0.25s ease;
 }

 .submenu a:hover {
    background: #8eb7f2;
    color: #228B22;
 }
 </style>
    </header>
    <body>
    <div id="wrapper">
        <div id="leftBar">
    <!-- Contenedor -->
    <ul id="accordion" class="accordion">


        <li href="main.php">
            <div class="link"><i class="fa fa-paint-brush"></i><a href="main.php">Trang chủ</a><i class="fa fa-chevron-down"></i></div>
        </li>


        <li href="loaisanpham.php">
            <div class="link"><i class="fa fa-paint-brush"></i><a>Loại sản phẩm</a><i class="fa fa-chevron-down"></i></div>
            <ul class="submenu">
                <li><a href="LoaiSanPham.php">Danh sách loại sản phẩm</a></li>
                <li><a href="themLoaiSanPham.php">Thêm loại sản phẩm</a></li>
            </ul>
        </li>
        
        <li href="sanpham.php">
            <div class="link"><i class="fa fa-paint-brush"></i><a>Sản phẩm</a><i class="fa fa-chevron-down"></i></div>
            <ul class="submenu">
                <li><a href="sanpham.php?page=1">Danh sách sản phẩm</a></li>
                <li><a href="themSanPham.php">Thêm sản phẩm</a></li>
            </ul>
        </li>

        <li href="hoadon.php">
            <div class="link"><i class="fa fa-paint-brush"></i><a>Hóa đơn</a><i class="fa fa-chevron-down"></i></div>
            <ul class="submenu">
                <li><a href="hoadon.php" >Danh sách hóa đơn</a></li>
                <li><a href="chitiethoadon.php" >Danh sách chi tiết hóa đơn</a></li>
            </ul>
        </li>
        <li href="#">
            <div class="link"><i class="fa fa-globe"></i><a>Thống kê</a><i class="fa fa-chevron-down"></i></div>
            <ul class="submenu">
                <li><a href="thongke.php">Thống kê doanh thu </a></li>
                <li><a href="thunhaptheothang.php">Thống kê theo năm</a></li>
                <li><a href="thunhapsanpham.php">Thống kê theo sản phẩm</a></li>
            </ul>
        </li>
        
        <li >
            <div class="link"><i class="fa fa-paint-brush"></i><a href="quydinh.php">Quy định</a><i class="fa fa-chevron-down"></div>
        </li>

        <li href="/anatea/staff_view.php">
            <div class="link"><i class="fa fa-paint-brush"></i><a href="/anatea/staff_view.php">Vào trang web</a><i class="fa fa-chevron-down"></i></div>
        </li>

    </ul>
    </div>
    <div>
</body>
</html>