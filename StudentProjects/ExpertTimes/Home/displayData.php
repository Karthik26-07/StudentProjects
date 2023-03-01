<?php
                        require_once '../Database/database.php';
                        $db = new DB();
                        $Id=$_GET['id'];
                        $sql = "select *  from device_data where device_id='$Id'";
                        // join devicemapping on
                        // devicemapping.deviceId=device_data.id
                         
                        $Equipment = $db->executeSelect($sql);
                        ?>
      
    <form class="form-group"> 
    
    <input type="hidden" id="data" name="data" value="<?php echo $Id;?>"/>
                  
<table class="table table-bordered my-3">
    <thead>
        <tr>
            <th scope="col">S.No</th>
            <th scope="col">Date</th>
            <th scope="col">Time</th>
            <th scope="col">Distance [m]</th>
            
            
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
<div class="col-lg-8 btn-toolbar my-4 center">
<?php  echo "<a class='btns text-white text-center mr-2 mt-1' type='button'  href='./customDownload.php?id=".$Id."'>Download</a>";?>
                

<button type="button"  onclick="Delete('<?php echo $Id; ?>');"class="btns text-white text-center mt-1" >Clear</button>


                    <!-- <button  name="download" id="download">Download</button><br> -->
                    <!-- <button type="submit" class="btn  btns text-white">Clear</button> -->
                </div>
                </div>
     
</form>
<script type="text/javascript"  >

function Delete(id) {

    if (confirm('Are you sure?')) {
        $.ajax({
            type: "POST",
            url: "../Api/deleteAction.php",

            data: {id: id
        },

            timeout: 10000,
            success: function () {
                document.location.reload()
            }
        });
    } else {
        // Do nothing!

    }
}


</script>