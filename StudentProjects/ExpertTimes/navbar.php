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

                <li class="nav-item dropdown px-1">
                    <button class="btn btns" data-toggle="modal" data-target="#loginModal">Login</button>
                </li>
            </ul>
        </div>
    </nav>
</div>

<!-- ###################################--Modal--###################################### -->
<form method="POST" action="Api/getAction.php">
<div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header  ">
      <div class="center">
        <h3 ><b>Login</b></h3>
                                </div>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      <input type="hidden" id="command" name="command" value="login"/>
      
    <label for="exampleInputPassword1">Password</label>
    <input type="password" class="form-control" id="inputpassword" name="inputpassword" placeholder="Password">
  
        
      </div>
      <div class="modal-footer">
        <div class="center">
      <button class="btn btns" type="submit">Login</button>
                                </div>
        
      </div>
    </div>
  </div>
</div>
                                </form>