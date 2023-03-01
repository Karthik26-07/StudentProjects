<?php

require_once '../Database/database.php';
$db = new DB();
if (isset($_GET['command'])) {
    $command = $_GET['command'];
    if ($command == "insertdata") {
//        print('Reached');
        $EquipmentId = $_GET['deviceid'];
        $Distance = $_GET['distance'];
        $time = $_GET['time'];
        $isBackup=$_GET['status'];
        $date=$_GET['date'];
        $insert = "INSERT INTO device_data(device_id,devicedate,time,distance,is_backup)";
        $values = " VALUES('$EquipmentId','$date','$time','$Distance','$isBackup')";

        $sql = $insert . $values;

        $res = $db->executeInsertAndGetId($sql);


        if ($res > 0) {
            echo json_encode($res);
        } else {
            throw new Exception("Something went wrong. Try again");
        }
    }
}
