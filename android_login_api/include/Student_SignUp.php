<?php include 'DB_Connect.php'; ?>
<?php 

$user_pass=$_POST["password"];
$roll_no=$_POST["rollno"];
$first_name=$_POST["first_name"];
$middle_name=$_POST["middle_name"];
$last_name=$_POST["last_name"];
$email=$_POST["email"];
$dept=$_POST["dept"];
$year=$_POST["year"];
$contact=$_POST["contact"];

$mysql_query1="SELECT * FROM Students WHERE Email_ID='$email';";
$result1=mysqli_query($conn,$mysql_query1);

$mysql_query2="SELECT * FROM Students WHERE Roll_no='$roll_no';";
$result2=mysqli_query($conn,$mysql_query2);

if(mysqli_num_rows($result1)>0)
{
  echo "s20";
}
else if(mysqli_num_rows($result2)>0)
{
  echo "s21";
}
else
{
	$insert_query="INSERT INTO Students VALUES(null,'$roll_no','$first_name','$middle_name','$last_name','$user_pass','$year','$email','$dept','$contact');";
	$insert=mysqli_query($conn,$insert_query);
  	if($insert)
    {
      echo "s2";
    }
  	else
    {
      echo "s22";
    }
}
?>