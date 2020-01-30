<?php

        function resize_image($file, $w, $h, $crop=FALSE) {
    	list($width, $height) = getimagesize($file);

        $extension = strtolower(substr($file,strrpos($file,'.')));
    if (in_array($extension,array('.jpg','.jpeg'))) { $img = @imagecreatefromjpeg($file); }
    elseif ($extension == '.png') { $img = @imagecreatefrompng($file); }
    elseif ($extension == '.gif') { $img = @imagecreatefromgif($file); }
    elseif ($extension == '.bmp') { $img = @imagecreatefromwbmp($file); }

    	$dst = imagecreatetruecolor($w, $h);
    	imagecopyresampled($dst, $img, 0, 0, 0, 0, $w, $h, $width, $height);

   	if (in_array($extension,array('.jpg','.jpeg'))) { imagejpeg($dst, $file); }
    elseif ($extension == '.png') { imagepng($dst, $file); }
    elseif ($extension == '.gif') { imagegif($dst, $file); }
    elseif ($extension == '.bmp') { imagebmp($dst, $file); }

 	@imagedestroy($file);
 	list($wi, $he) = getimagesize($file);

    	return $file;
}

//upload.php
if($_FILES["file"]["name"] != '')
{
	$test = explode('.', $_FILES["file"]["name"]);
	$ext = end($test);
	$name = rand(100, 999) . '.' . $ext;
	$location = 'img/product-img/'. $name;
	move_uploaded_file($_FILES["file"]["tmp_name"], $location);
	$tt = resize_image($location,250,250);

	echo '<img src="'.$tt.'" height="190" width="225" class="img-thumbnail"  id="img1" />';
 	
}
?>