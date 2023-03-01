<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


        <link rel="icon" href="../Images/logoicon.png" type="image/icon">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">

        <title>Expert Time</title>
        <link href="../css/customStyle.css"  rel="stylesheet" type="text/css"/>
    </head>
    <body>

        <?php require_once './Navbar.php' ?>
<form method="GET" action="../Api/sendSMS.php">
<input type="text" name="command" id="command" value="sendSMS" hidden>

        <div class="row text-right ml mt-3 ml-3"> 
                     <div class="col-12 col-md-3  ">
                      <input class="form-control text-center" type="text" placeholder="Timestamp" name="Timestamp" id="Timestamp">
                      <br>
                    </div>
                    <div class="col-12 col-md ">
                      <input class="form-control text-center" type="text" placeholder="Device ID" name="deviceID" id="deviceID">
                      <br>
                    </div>  <div class="col-12 col-md ">
                      <input class="form-control text-center" type="text" placeholder="Masking distance" name="Maskingdistance" id="Maskingdistance">
                      <br>
                    </div>  <div class="col-12 col-md  ">
                      <input class="form-control text-center" type="text" placeholder="Max Threshold distance" name="Thresholddistance" id="Thresholddistance">
                      <br>
                    </div>
                    <div class="col-12 col-md mr-3 ">
                    <button class="btn btns " type="submit" name="save" id="save">Send</button>

                    </div></div> 
</form>










        <div class="container">
        <div class="row px-5 my-5" >
            <div class="col-12 col-md-4 "  >
                <div class="list-group text-center" id="list-tab" role="tablist"  >
                    <a style=" background-color:#000066;" class="list-group-item list-group-item-action active" id="list-deviceMapping-list" data-toggle="list" href="#list-deviceMapping" role="tab" aria-controls="deviceMapping"><b>Device Mapping</b></a>
                    <a style=" background-color:#000066;"class="list-group-item list-group-item-action" id="list-findDevice-list" data-toggle="list" href="#list-findDevice" role="tab" aria-controls="findDevice"><b>Find Device</b></a>
                    <a style=" background-color:#000066;"class="list-group-item list-group-item-action" id="list-equipmets-list" data-toggle="list" href="#list-equipmets" role="tab" aria-controls="equipmets"><b>Equipments</b></a>
                    <a style=" background-color:#000066;"class="list-group-item list-group-item-action" id="list-deviceHistory-list" data-toggle="list" href="#list-deviceHistory" role="tab" aria-controls="deviceHistory"><b>Device History</b></a>
                    <a style=" background-color:#000066;"class="list-group-item list-group-item-action" id="list-deviceData-list" data-toggle="list" href="#list-deviceData" role="tab" aria-controls="deviceData"><b>Device Data</b></a>
                    <button style=" background-color:#000066;"class="list-group-item list-group-item-action" id="logout" data-toggle="list"  onclick="location.href = '../index.php';"><b>Logout</b></button>
                    <!-- <a class="list-group-item list-group-item-action" id="list-logout-list" data-toggle="list" href="#list-logout" role="tab" aria-controls="logout"><b>Logout</b></a> -->

                </div>
            </div>
            
            <div class="col-12 col-md-8">
                <div class="tab-content " id="nav-tabContent">
                    <div class="tab-pane fade show active" id="list-deviceMapping" role="tabpanel" aria-labelledby="list-deviceMapping-list">
                     
                    
                    
                    <?php require_once './deviceMapping.php' ?>
                    </div>
                    <div class="tab-pane fade" id="list-findDevice" role="tabpanel" aria-labelledby="list-findDevice-list"></div>
                    <div class="tab-pane fade" id="list-equipmets" role="tabpanel" aria-labelledby="list-equipmets-list"></div>
                    <div class="tab-pane fade" id="list-deviceHistory" role="tabpanel" aria-labelledby="list-deviceHistory-list"></div>

                    <div class="tab-pane fade " id="list-deviceData" role="tabpanel" aria-labelledby="list-deviceData-list">

                       <?php
                        require_once '../Database/database.php';
                        $db = new DB();
                        $sql = "select distinct device_id from device_data";
                        $Equipment = $db->executeSelect($sql);
                        ?>



                        <div class="" style="float: right;">
                            <div class="form-group ">
                                <!-- <label for="trainnumber"><b>Device Id</b></label> -->
                                <select id="deviceid" name="deviceid" class="form-control mt-2" onchange="myfunction(this.value)" style="border-radius: 50px;" required>
                                    <option value="">--SELECT DEVICE ID--</option>
                                    <?php foreach ($Equipment as $deviceno) { ?>
                                        <option  value="<?php echo $deviceno["device_id"]; ?>"><?php echo $deviceno["device_id"]; ?></option>
                                    <?php } ?>

                                </select>
                            </div>

                        </div>
                        <div id="dispalydata">

                        </div>
                    </div>
                   
                </div>


            </div>
          
        </div>
                                    </div>
                                    
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script>
            $( document ).ready(function() {
            $('#list-tab').on('click', 'a', function(e) {
                
  e.preventDefault();
  $('#nav-tabContent > div').hide();
$('#list-deviceData').hide();
  var target = $(this).attr('href');

  $(target).show();

});
});
            </script>

        <script type="text/javascript">

                                    function myfunction(str) {
                                        if (str === "") {
                                            $("#dispalydata").html("");  // print the empty result
                                            return;
                                        } else {

                                            $.ajax({
                                                type: "GET",
                                                url: "./displayData.php",
                                                datatype: "json",
                                                data: {id: str},
                                                success: function (result) {
                                                    $("#dispalydata").html(result);  // print the result
                                                    return;

                                                }
                                            });
                                        }

                                    }
        
                                   
        
        
        </script>
     <!-- <script>
        jQuery(function(){
  jQuery('.list-group-item list-group-item-action active').click(function(){
       var target = jQuery(this).attr('href');
       var targetDiv = jQuery("#div"+target);
       jQuery(this).parents('.list-group').find('.tab-pane').hide(); 
       jQuery(this).parents('.list-group').find(targetDiv).show();
   });
});
        </script> -->
    </body>
</html>





