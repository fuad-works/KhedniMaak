<?php
include("config.php");

$params = $_REQUEST;

$sql = "UPDATE user SET active = ".$params['active']." WHERE id = ".$params['user_id'];

$res = mysqli_query($connection,$sql);
if($res >= 1)
echo "1";
else echo "0";

?>