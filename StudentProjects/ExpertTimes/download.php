<?php
ob_start();
$timer = date_default_timezone_set("Asia/Kolkata");
$date = new DateTime();
$current_date = $date->format('Y-m-d');
$current_time = $date->format('H:i:s');


                        require_once 'Database/database.php';
                        $db = new DB();
                        $Id=$_GET['id'];
                        $sql = " select * from device_data where device_id = '$Id' ";
                        $Equipment = $db->executeSelect($sql);
                        $newsql="SELECT equipmentId FROM devicemapping ORDER BY id DESC  LIMIT 1";
                        $queryresult= $db->executeSelect($newsql);
                        foreach ($queryresult as $result){
                            $equipmentIDpdf=$result["equipmentId"];

                        }
                       




                        ?>
                        
     <!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous"> -->
        <link rel="icon" href="Images/logoicon.png" type="image/icon">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
        <title>Expert Time</title>
        <link href="css/customStyle.css"  rel="stylesheet" />
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.9.2/html2pdf.bundle.min.js"></script>


    </head>
    <body>
    <nav class="navbar navbar-expand-lg navbar-light " style="background-color: #ffffff">
        <span class="navbar-brand">
            <img src="Images/logo.png" width="300px" class="d-inline-block" alt="">
        </span>
</nav>
     <div class="container">
            <div class="row">
                <div class="col-lg-12 col-xs-12">
                   <h1> <p class=""><b  style="color:#000066;  text-align: center;">Device Id :  <?php echo $Id; ?></b></p></h1>
                   <!-- <h2> <p class=""><b  style="color:#000066;  text-align: center;  ">Equipment ID:   <?php echo $equipmentIDpdf; ?></b></p></h2> -->
                   <h3> <p class=""><b  style="color:#000066;  text-align: center;  ">Data downloaded on:   <?php echo $current_time; ?></b></p></h3>

                </div>
            </div>
           

<table class="center">
    <thead>
        <tr>
            <th >SnNo</th>
            <th >Date</th>

            <th>Time</th>
            <th>Distance</th>
        </tr>
    </thead>





    <?php if (count($Equipment) === 0) { ?>
        <tr>
            <td colspan="5" class="text-center text-danger">
                No Record Found
            </td>
        </tr>

        <?php
    } else {
        $count = 1;
        foreach ($Equipment as $devicedata) {
            
            ?>
            
            <tr>
           
                <td><?php echo $count++ ?></td>
                <td><?php echo $devicedata["devicedate"]; ?></td>

                <td><?php echo $devicedata["time"]; ?></td>
                <td><?php if ($devicedata["is_backup"]==1){
                    echo $devicedata["distance"]."".'*';
                }else{
                    echo $devicedata["distance"];
                }
                
                ?></td>
                

            </tr>



        <?php
        }
    }

       
    ?>

</table>  

</body>
</html>
<?php

require_once   'mpdf/vendor/autoload.php';

$mpdf = new \Mpdf\Mpdf();

$mpdf->SetDisplayMode('fullpage'); 

$mpdf->setFooter('{PAGENO}');

$template = ob_get_contents();
$ss = file_get_contents('css/customStyle.css');

ob_end_clean();
$mpdf->WriteHTML($ss, 1);
$mpdf->WriteHTML($template, 2);
$mpdf->defaultPagebreakType=TRUE;

error_reporting(E_ERROR | E_PARSE);

// $mpdf->Output(); 

$mpdf->Output('expert_times.pdf','D'); 
?>