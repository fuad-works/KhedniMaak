<?php
include("config.php");

$params = $_REQUEST;

$sql = "SELECT * FROM user WHERE user_name LIKE '".$params['user_name']."' AND password LIKE '".sha1($params['password'])."'";

$res = mysqli_query($connection,$sql);

$r = mysqli_fetch_assoc($res);
while($r1 = mysqli_fetch_assoc($res)) {
    $r = $r1;
}
print json_encode($r);

?>