<?php

require_once '../Database/database.php';
$db = new DB();
if (isset($_POST['command'])) {
    $command = $_POST['command'];
    if ($command == "deviceMapping") {
   $deviceId=$_POST['deviceid'];
   $equipmentId=$_POST['equipmentid'];
   $description=$_POST['description'];
   $insert = "INSERT INTO devicemapping(deviceId,equipmentId,description,date)";
   $values = " VALUES('$deviceId',' $equipmentId',' $description',NOW())";

   $sql = $insert . $values;

   $res = $db->executeInsertAndGetId($sql);


   if ($res > 0) {
                header('Location: ../Home/home.php');

   } else {
       throw new Exception("Something went wrong. Try again");
   }

   
    }
}