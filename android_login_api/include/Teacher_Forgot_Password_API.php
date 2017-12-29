<?php include "DB_Connect.php"; ?>
<?php
	$id = $_POST['id'];
	$select = "SELECT Password FROM Teachers WHERE Email_ID = '$id'";
	$query = mysqli_query($conn,$select);
	$count = mysqli_num_rows($query);
	if($count > 0)
    {
      $row = $query->fetch_assoc();
      $msg = "Greetings from XYZ Team. We hav received your request of forgot password from your account. Your password is: ".$row['Password'];
      $msg = wordwrap($msg,70);
      $sorf = mail($id,"Forgot Password",$msg);
      if($sorf)
      {
        echo "ms";
      }
      else
      {
        echo "mns";
      }
    }
?>
	