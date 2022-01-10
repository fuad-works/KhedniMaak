<?php
include("config.php");

$params = $_REQUEST;



$sql = "SELECT * FROM participation WHERE user_id =  ".$params['user_id']." AND trip_id = ".$params['trip_id'];

$res = mysqli_query($connection,$sql);

$rows = array();
while($r = mysqli_fetch_assoc($res)) {
    $rows[] = $r;
}

if(sizeof($rows) > 0)
{
    $sql = "DELETE FROM participation WHERE user_id = ".$params['user_id']." AND trip_id = ".$params['trip_id'];   
}
else {
$sql = "INSERT INTO participation(user_id, trip_id) VALUES (";
$sql .= $params['user_id'];
$sql .= ",".$params['trip_id'];
$sql .= ")";
}

$res = mysqli_query($connection,$sql);
if($res >= 1)
echo "1";
else echo "0";

?>