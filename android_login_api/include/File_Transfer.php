<?php include "DB_Connect.php"; ?>
<?php
	$id = $_POST["id"];
	$filename = $_POST["fname"];
	$path = "uploads/".$filename;
	$query = "SELECT TeacherID FROM Teachers WHERE Email_ID='$id'";
	$sql = mysqli_query($conn,$query);
	$row = $sql->fetch_assoc();
	$today = date('Y/m/d H:i:s');
	$dir = $row["TeacherID"];
	$insert = "INSERT INTO $dir VALUES(null,'$filename','$path','$today')";
	if(mysqli_query($conn,$insert))
    {
      echo "Success";
    }
	else
    {
      echo "Fail";
    }
?>