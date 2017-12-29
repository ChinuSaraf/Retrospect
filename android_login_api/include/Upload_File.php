<?php include "DB_Connect.php"; ?>
<?php
  	$id = $_POST["id"];
    $file_name = $_FILES['uploaded_file']['name'];
	$query = "SELECT TeacherID FROM Teachers WHERE Email_ID='$id'";
	$sql = mysqli_query($conn,$query);
	$row = $sql->fetch_assoc();
	echo $id;
	$file_path = "uploads/";
    $file = $file_path . basename( $_FILES['uploaded_file']['name']);
    if(move_uploaded_file($_FILES['uploaded_file']['tmp_name'], $file) ){
        //echo "success";
    } else{
        //echo "fail";
    }

?>