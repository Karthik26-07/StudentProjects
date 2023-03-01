<?php
 if (isset($_GET['command'])) {
    $command = $_GET['command'];
    if ($command == "sendSMS") {
       $timestamp=$_GET['Timestamp'];

       $deviceId=$_GET['deviceID'];
       $maskingDistance=$_GET['Maskingdistance'];
       $thresholdDistance=$_GET['Thresholddistance'];
      
       
       $data= (explode(" ",$timestamp));
        
       $time=$data[0];
       $date=$data[1];

       $Times= (explode(":",$time));
       
       $th=$Times[0];
       $tm=$Times[1];
       $ts=$Times[2];

       $dates= (explode("/",$date));
       
       $dd=$dates[0];
       $dm=$dates[1];
       $dy=$dates[2];

       $Timestamp='$*'.$th.'&'.$tm.'&'.$ts.'&'.$dd.'&'.$dm.'&'.$dy.'&';
       echo $Timestamp;
       
       header('Location:https://2factor.in/API/V1/e4447146-b756-11ed-813b-0200cd936042/SMS/VERIFY3/+918880421893/12345');
    //    curl --location --request GET 'https://2factor.in/API/V1/XXXX-XXXX-XXXX-XXXX-XXXX/SMS/VERIFY3/91XXXXXXXXXX/12345'
    }
 }