<?php
include("config.php");

$params = $_REQUEST;

$sql = "SELECT trip.*,user.user_name as driver, COUNT(participation.trip_id) as regs FROM trip INNER JOIN user ON user.id = trip.driver_id LEFT OUTER JOIN participation ON participation.trip_id = trip.id WHERE driver_id <> ".$params['user_id']." AND the_date >= CURDATE() GROUP BY trip.id ORDER BY the_date DESC";

$res = mysqli_query($connection,$sql);

$rows = array();
while($r = mysqli_fetch_assoc($res)) {
    $rows[] = $r;
}
print json_encode($rows);

?>