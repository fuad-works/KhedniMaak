<?php 
$host = "localhost";
$db = "takemedrive";
$user = "root";
$pass = "";

$connection = mysqli_connect($host,$user,$pass,$db);

mysqli_set_charset($connection, "utf8")
?>