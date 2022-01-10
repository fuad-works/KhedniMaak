<?php
include("config.php");

$params = $_REQUEST;

$sql = "SELECT * FROM user WHERE id <> ".$params['user_id'];

$res = mysqli_query($connection,$sql);

$rows = array();
while($r = mysqli_fetch_assoc($res)) {
    $rows[] = $r;
}
print json_encode($rows);

?>