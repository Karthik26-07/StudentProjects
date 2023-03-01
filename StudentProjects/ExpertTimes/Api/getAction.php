<?php

session_start();
require_once '../Database/database.php';
$db = new DB();
if (isset($_POST['command'])) {
    $command = $_POST['command'];
    if ($command == "displaydata") {
        $id = $_POST['deviceno'];
        $_SESSION['device_id'] = $id;
        header('Location: ' . '../equipment.php');
    }elseif($command == "login") {
        $Password = $_POST['inputpassword'];
        if($Password == "Admin"){
            header('Location: ../Home/home.php');
  
        }else{
    
            echo "<script>alert('Invalid Credentials');window.location.href='../index.php'</script>";
            // header('Location: ../index.php');

 
        }
    
        
    }
}
?>