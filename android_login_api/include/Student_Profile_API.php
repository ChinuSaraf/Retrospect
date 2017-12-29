<?php include "DB_Connect.php"; ?>
<?php
	$id = $_POST["id"];
	$query = "SELECT * FROM Students WHERE Email_ID='$id'";
	$rs = mysqli_query($conn,$query);
	$row = $rs->fetch_assoc();
	$arr = array('Roll_no'=>null,'First_Name'=>null,'Middle_Name'=>null,'Last_Name'=>null,'Dept_ID'=>null,'Email_ID'=>null,'Contact'=>null,'Year'=>null);
	$arr['Roll_no'] = $row['Roll_no'];
	$arr['First_Name'] = $row['First_Name'];
	$arr['Middle_Name'] = $row['Middle_Name'];
	$arr['Last_Name'] = $row['Last_Name'];
	$arr['Dept_ID'] = $row['Dept_ID'];
	$arr['Email_ID'] = $row['Email_ID'];
	$arr['Contact'] = $row['Contact'];
	$arr['Year'] = $row['Year'];
	echo json_encode($arr);
?>
