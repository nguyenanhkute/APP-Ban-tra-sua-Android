
<?php
include_once('C:/xampp/htdocs/admin/conn.php');
function lay_sampham() {
	$conn = connect();
    $strSQL = "select * from sanpham, loaisanpham where sanpham.maloaisp= loaisanpham.maloaisp ORDER BY masp ASC";
    $result = mysqli_query($conn, $strSQL) Or die('<p>Không thể thực thi câu truy vấn.</p>');
    return $result;
}
function lay_sampham_phantrang($begin,$end) {
    $conn = connect();
    $strSQL = "select sp.MaSP,lsp.TenLoaiSP,sp.TenSP,sp.HinhAnh,sp.Gia,sp.MoTa
				from sanpham as sp, loaisanpham lsp
				where sp.maloaisp= lsp.maloaisp  LIMIT ".$begin.",".$end ;
    $result = mysqli_query($conn, $strSQL) Or die('<p>Không thể thực thi câu truy vấn.</p>');
    return $result;
}
function lay_sampham_masp($masp) {
	$conn = connect();
    $strSQL = "select sp.MaSP,lsp.TenLoaiSP,sp.TenSP,sp.HinhAnh,sp.Gia,sp.MoTa
				from sanpham as sp, loaisanpham lsp
				where sp.masp='".$masp."' and sp.maloaisp=lsp.maloaisp";
    $result = mysqli_query($conn, $strSQL) Or die('<p>Không thể thực thi câu truy vấn.</p>');
    return $result;
}

function lay_sp_lsp() {
    $conn = connect();
    $strSQL = "select lsp.MaLoaiSP,lsp.TenLoaiSP,COUNT(sp.MaSP) as sl from sanpham sp, loaisanpham lsp WHERE sp.MaLoaiSP=lsp.MaLoaiSP GROUP by lsp.MaLoaiSP";
    $result = mysqli_query($conn, $strSQL) Or die('<p>Không thể thực thi câu truy vấn.</p>');
    return $result;
}

function update_sanpham($masp,$tensp,$gia,$mota,$anh) {
    $conn = connect();
    if ($anh == ''){
        $sql = "update sanpham set tensp='".$tensp."', gia='".$gia."', mota= '".$mota."' where masp='".$masp."'";
        mysqli_query($conn,$sql) or die('<p>Không thể thực thi câu truy vấn.</p>');

    } else {
        $sql = "update sanpham set tensp= '".$tensp."', gia='".$gia."', mota= '".$mota."', HinhAnh='".$anh."' where masp='".$masp."'";
        mysqli_query($conn,$sql) or die('<p>Không thể thực thi câu truy vấn.</p>');

    }
    
}

    function insert_sanpham($malsp,$tensp,$anh,$gia,$mota) {
        $conn = connect();
        if ($anh == ''){
            $sql1 = "insert into sanpham VALUES (AUTO_MASP(),'".$malsp."','".$tensp."','','".$gia."','".$mota."')";
            mysqli_query($conn,$sql1) or die('<p>Không thể thực thi câu truy vấn.</p>');
            } else {
                 $sql = "insert into sanpham VALUES (AUTO_MASP(),'".$malsp."','".$tensp."','".$anh."','".$gia."','".$mota."')";
				 echo $sql;
            mysqli_query($conn,$sql) or die('<p>Không thể thực thi câu truy vấn.</p>');
        }
    }

    function search_sanpham($search) {
        $conn = connect();
        $sql1 = "select * from sanpham  as sp, loaisanpham as lsp where sp.MaLoaiSP= lsp.MaLoaiSP and ( masp like '%".$search."%' or tensp like '%".$search."%' or mota like '%".$search."%' or gia like '%".$search."%' or TenLoaiSP like '%".$search."%')";
        $result = mysqli_query($conn,$sql1) or die('<p>Không thể thực thi câu truy vấn.</p>');
        return $result;
    }
?>