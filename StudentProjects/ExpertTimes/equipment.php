<!doctype html>
<?php
$page = $_SERVER['PHP_SELF'];
$sec = "10";
require_once 'Database/database.php';
$db = new DB();
session_start();
$device_id = $_SESSION['device_id'];
$sql = "select * from device_data where device_id = '$device_id'";
$device_data = $db->executeSelect($sql);
?>

<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="refresh" content="5; url="<?php echo $_SERVER['PHP_SELF']; ?>">


        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="icon" href="Images/logoicon.png" type="image/icon">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
        <title>Expert Time</title>
        <link href="css/customStyle.css"  rel="stylesheet" type="text/css"/>

    </head>
    <body>
    <?php
require_once './Database/database.php';
$db = new DB();
$sql = "select distinct device_id from device_data";
$Equipment = $db->executeSelect($sql);
?>

<div id="navbar">
    <nav class="navbar navbar-expand-lg navbar-light " style="background-color: #ffffff">
        <span class="navbar-brand">
            <img src="Images/logo.png" width="300px" class="d-inline-block" alt="">
        </span>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="nav nav-pills ml-auto">
                <li class="nav-item dropdown px-1">
                    <button class="btn btns" onclick="document.location.href = 'index.php'">Home</button>
                </li>
                <li class="nav-item dropdown px-1">
                    <form method="POST" action="Api/getAction.php">
                        <input type="hidden" id="command" name="command" value="displaydata"/>
                        <div class="selectWrapper">
                            <select class="selectBox" style="border-radius: 50px;"
                                    name="deviceno" id="deviceno" onchange="this.form.submit();">
                                <option value="" selected="selected"  >Select Device Number</option>
                                <?php foreach ($Equipment as $DeviceNo) { ?>
                                    <option value="<?php echo $DeviceNo['device_id']; ?> "><?php echo $DeviceNo["device_id"]; ?></option>
                                <?php } ?>
                            </select>
                        </div>
                    </form>
                </li>
                <!-- Button trigger modal -->

                
            </ul>
        </div>
    </nav>
</div>
        <div class="container">
            <div class="row">
                <div class="col-lg-12 col-xs-12">
                    <p class="text-center display-4"><b  style="color:#000066">Equipment Number :  <?php echo $device_id; ?></b></p>
                </div>
            </div>

            <div class="w-50 center my-4">
                <table class="table table-bordered  text-center">
                    <thead>
                        <tr>
                            <th scope="col">Date</th>
                            <th scope="col">Time</th>
                            <th scope="col">Distance(m)</th>
                        </tr>
                    </thead>
                    <tbody>
                        <?php if (count($device_data) === 0) { ?>
                            <tr>
                                <td colspan="8" class="text-center text-danger">
                                    No Record Found
                                </td>
                            </tr>
                            <?php
                        } else {

                            foreach ($device_data as $row) {
                                ?> <tr class="font-weight-bold">
                                    <td><?php echo $row["devicedate"]; ?> </td>
                                    <td><?php echo $row["time"]; ?></td>
                                    <td><?php if ($row["is_backup"]==1){
                    echo $row["distance"]."".'*';
                }else{
                    echo $row["distance"];
                }
                
                ?></td>
                                </tr>
                                <?php
                            }
                        }
                        ?>
                    </tbody>
                </table>
            </div>
            <div class="row text-left">
                <div class="col-lg-6">

                </div>
                <div class="col-lg-6 btn-toolbar justify-content-center">
                <?php  echo "<a class='btns text-white text-center' type='button'  href='./download.php?id=".$device_id."'>Download</a>";?>

                    <!-- <button type="submit" class="btn  btns text-white text-center">Download</button><br> -->
                    <!-- <button type="submit" class="btn  btns text-white">Clear</button> -->
                </div>


            </div>


        </div>

        <?php require_once 'footer.php' ?>