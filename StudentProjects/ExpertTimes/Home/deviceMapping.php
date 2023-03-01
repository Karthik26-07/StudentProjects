<?php
require_once '../Database/database.php';
$db = new DB();
$sql = "select  distinct device_id,id from device_data";
$Equipment = $db->executeSelect($sql);

$devicemap = "SELECT * FROM devicemapping
JOIN device_data ON
device_data.id=devicemapping.deviceId";
$DeviceMapping = $db->executeSelect($devicemap);
?>

<div class="container">
<div class="text-right">
    <button type="button" style=" background-color: #000066; width: 110px;"
            class="btn btn-primary mt-1 " data-toggle="modal" data-target="#exampleModal" data-whatever="@getbootstrap">
        <i class="bi bi-plus"></i>  <i class="bi bi-plus" hidden></i> Add</button>
</div>
<form method="POST" action="../Api/HomeAction.php">
<div class="col-md-8">
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h3 class="modal-title center" id="exampleModalLabel"><b class="">Device Mapping</b></h3>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <input type="hidden" id="command" name="command" value="deviceMapping"/>

                    <div class="form-group">
                        <label for="trainnumber"><b>Device Id</b></label>
                        <select id="deviceid" name="deviceid" class="form-control" required>
                            <option value="">--SELECT Device Id--</option>
                            <?php foreach ($Equipment as $deviceno) { ?>
                                <option  value="<?php echo $deviceno["id"]; ?>"><?php echo $deviceno["device_id"]; ?></option>
                            <?php } ?>

                        </select>
                    </div>



                    <div class="form-group">
                        <label for="recipient-name" class="col-form-label"><b>Equipment Id:</b></label>
                        <input type="text" class="form-control" id="equipmentid" name="equipmentid"placeholder="Equipment Id" required>
                    </div>
                    <div class="form-group">
                        <label for="message-text" class="col-form-label"><b>Description:<b></label>
                                    <textarea class="form-control" id="message-text" name="description" id="description"required></textarea>
                                    </div>

                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                        <button type="submit" class="btn text-white" style=" background-color: #000066; width: 110px;">Save</button>
                                    </div>
                                    </div>
                                    </div>
                                    </div>
                            </div>
                                    </form>







                                    <table class="table table-bordered my-3">
                                        <thead>
                                            <tr>
                                                <th scope="col">S.No</th>
                                                <th scope="col">Device Id</th>
                                                <th scope="col">Equipment Id</th>
                                                <th scope="col">Date</th>
                                            </tr>
                                        </thead>

                                        <?php if (count($DeviceMapping) === 0) { ?>
                                            <tr>
                                                <td colspan="5" class="text-center text-danger">
                                                    No Record Found
                                                </td>
                                            </tr>

                                            <?php
                                        } else {
                                            $count = 1;
                                            foreach ($DeviceMapping as $mapdata) {
                                                ?>
                                                <tr>
                                                    <td><?php echo $count++ ?></td>
                                                    <td><?php echo $mapdata["device_id"]; ?></td>
                                                    <td><?php echo $mapdata["equipmentId"]; ?></td>
                                                    <td><?php echo $mapdata["date"]; ?></td>
                                                </tr>



                                            <?php
                                            }
                                        }
                                        ?>

                                    </table>  
                                    </div>