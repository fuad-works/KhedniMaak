<?php
include("config.php");

$params = $_REQUEST;

$sql = "DELETE FROM `participation` WHERE trip_id = ".$params['trip_id'];

$res = mysqli_query($connection,$sql);

$sql = "DELETE FROM `trip` WHERE id = ".$params['trip_id'];

$res = mysqli_query($connection,$sql);
if($res >= 1)
echo "1";
else echo "0";

?>