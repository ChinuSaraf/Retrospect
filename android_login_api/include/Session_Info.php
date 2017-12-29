<?php include "DB_Connect.php"; ?>
<?php
	$category=$_POST["category"];
	$id=$_POST['id'];
	$search2=mysqli_query($conn,"SELECT TeacherID FROM Teachers WHERE Email_ID='$id'");
    $row = $search2->fetch_assoc();
    $table = $row["TeacherID"];
	
    $query = "SELECT File_name FROM $table";
    $files = mysqli_query($conn,$query);
    $count = mysqli_num_rows($files);
    while($row = $files->fetch_assoc())
    {
     $count = $count.'$'.$row["File_name"];
    }
    echo $count;
?>