<?php
include("config.php");

$params = $_REQUEST;
$sql = "INSERT INTO `trip` (`driver_id`, `car`, `start_place`, `end_place`, `the_date`, `the_time`, `duration`, `price`, `seats`) VALUES (";
$sql .= $params['driver_id'];
$sql .= ",'".$params['car']."'";
$sql .= ",'".$params['start_place']."'";
$sql .= ",'".$params['end_place']."'";
$sql .= ",'".$params['the_date']."'";
$sql .= ",'".$params['the_time']."'";
$sql .= ",".$params['duration'];
$sql .= ",".$params['price'];
$sql .= ",".$params['seats'];
$sql .= ")";


$res = mysqli_query($connection,$sql);
if($res >= 1)
echo "1";
else echo "0";

?>