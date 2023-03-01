<?php
require_once '../Database/database.php';
$db = new DB();
$id=$_POST['id'];
$sql="DELETE FROM device_data WHERE device_id='$id'";
    $res = $db-> executeUpdate($sql);