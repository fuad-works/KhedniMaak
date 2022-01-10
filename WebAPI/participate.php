<?php
include("config.php");

$params = $_REQUEST;

$sql = "SELECT * FROM participation WHERE user_id =  ".$params['user_id']." AND trip_id = ".$params['trip_id'];

$res = mysqli_query($connection,$sql);

$rows = array();
while($r = mysqli_fetch_assoc($res)) {
    $rows[] = $r;
}
print json_encode($rows);

?>