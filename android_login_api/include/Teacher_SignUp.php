<?php include 'DB_Connect.php'; ?>
<?php 

$user_pass=$_POST["password"];
$roll_no=$_POST["tid"];
$first_name=$_POST["first_name"];
$middle_name=$_POST["middle_name"];
$last_name=$_POST["last_name"];
$email=$_POST["email"];
$dept=$_POST["dept"];
$contact=$_POST["contact"];

$mysql_query1="SELECT * FROM Teachers WHERE Email_ID='$email';";
$result1=mysqli_query($conn,$mysql_query1);

$mysql_query2="SELECT * FROM Teachers WHERE TeacherID='$roll_no';";
$result2=mysqli_query($conn,$mysql_query2);

if(mysqli_num_rows($result1)>0)
{
  echo "t20";
}
else if(mysqli_num_rows($result2)>0)
{
  echo "t21";
}
else
{
	$insert_query="INSERT INTO Teachers VALUES(null,'$roll_no','$first_name','$middle_name','$last_name','$dept','$email','$contact','$user_pass');";
	$insert=mysqli_query($conn,$insert_query);
  	if($insert)
    {
      $sql = "CREATE TABLE $roll_no (
      id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY, 
      File_name VARCHAR(30) NOT NULL,
      Path VARCHAR(100) NOT NULL,
      Creation_date TIMESTAMP
      )";
      $table=mysqli_query($conn,$sql);
      echo "t2";
    }
  	else
    {
      echo "t22";
    }
}

?>
