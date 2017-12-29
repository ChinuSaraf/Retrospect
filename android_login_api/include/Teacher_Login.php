<?php include 'DB_Connect.php'; ?>
<?php 
$user_name=$_POST["user_name"];
$user_pass=$_POST["password"];
$mysql_query="SELECT * FROM Teachers WHERE Email_ID='$user_name' AND Password='$user_pass';";
$result=mysqli_query($conn,$mysql_query);
if(mysqli_num_rows($result)>0)
{
  echo "t1";
}
else
{
  echo "t10";
}
?>