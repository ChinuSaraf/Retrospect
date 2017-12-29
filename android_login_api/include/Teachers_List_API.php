<?php include "DB_Connect.php"; ?>
<?php
	$id = $_POST['id'];
	$query = "SELECT Dept_ID FROM Students WHERE Email_ID = '$id'";
	$rs = mysqli_query($conn,$query);
	$dept = $rs->fetch_assoc();
	
	$d = $dept['Dept_ID'];	

	$query_main = "SELECT First_Name,Last_Name,Email_ID FROM Teachers WHERE Dept_ID='$d'";
	$rs_main = mysqli_query($conn,$query_main);
	$count = 0; 
	$str = '';

	while($row = $rs_main->fetch_assoc())
    {
      $name = $row['First_Name']." ".$row['Last_Name'];
      $email = $row['Email_ID'];
      $count = $count+1;
      $str = $str.$name.'$'.$email.'%';
    }
	
	echo $count.'%'.$str;
	
	
?>