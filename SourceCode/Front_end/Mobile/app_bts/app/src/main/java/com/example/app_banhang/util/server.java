package com.example.app_banhang.util;

public class server {
    public static String localhost="192.168.56.1:8008";
    public static String DuongdanloaiSP = "http://"+ localhost +"/server/getloaisp.php";
    public static String URL_READ = "http://"+ localhost +"/server/read_detail_user.php?id=";
    public static String URL_EDIT = "http://"+ localhost +"/server/edit_detail_user.php";
    public static String URL_CHANGEPASSWORD = "http://"+ localhost +"/server/change_password.php";
    public static String URL_SIGNUP = "http://"+ localhost +"/server/register.php";
    public static String URL_SEARCH = "http://"+ localhost +"/server/";
    public static String URL_LOGIN = "http://"+ localhost +"/server/login.php";
    public static String URL_DATHANG = "http://"+ localhost +"/server/dathang.php";
    public static String URL_FORGETPASSWORD = "http://"+ localhost +"/server/forgetpassword.php";
    public static String URL_PhanHoi = "http://"+ localhost +"/server/insertcomment.php";

    public static String URL_GETLSMH = "http://"+ localhost +"/server/getlichsumuahang.php?makhachhang=";

    public static String URL_GETPhanHoi = "http://"+ localhost +"/server/getphanhoi.php?tensp=";
    public static String Duongdansanpham = "http://"+localhost+"/server/getsanpham.php";
    public static String DuongdanLSPSP = "http://"+localhost+"/server/getloaispsp.php?tenlsp=";
    public static String DuongdansanphamMaSP = "http://"+localhost+"/server/getsanphamMSP.php?masp=";

}
