<?php
include("config.php");

$params = $_REQUEST;

$sql = "INSERT INTO user(user_name, password, user_type) VALUES (";
$sql .= "'".$params['user_name']."'";
$sql .= ",'".sha1($params['password'])."'";
$sql .= ",1)";

$res = mysqli_query($connection,$sql);
if($res >= 1)
echo "1";
else echo "0";

?>