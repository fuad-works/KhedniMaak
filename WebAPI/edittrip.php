<?php
include("config.php");

$params = $_REQUEST;
$sql = "UPDATE `trip` SET `car` = '".$params['car']."', `start_place` = '".$params['start_place']."', `end_place` = '".$params['end_place']."', `the_date` = '".$params['the_date']."', `the_time` = '".$params['the_time']."', `duration` = ".$params['duration'].", `price` = ".$params['price'].", `seats` = ".$params['seats']." WHERE id = ".$params['trip_id'];

$res = mysqli_query($connection,$sql);
if($res >= 1)
echo "1";
else echo "0";

?>