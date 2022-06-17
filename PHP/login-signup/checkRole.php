<?php

include('dbConfig.php');
if (isset($_GET['username'])){
	$username = $_GET['username'];
	$sql = "select role from Users where username = '$username'";
	$stmt = $con->prepare($sql);
	 
	 //executing the query 
	 $stmt->execute();
	 
	 //binding results to the query 
	 $stmt->bind_result($role);
	 
	 $profile = array(); 
	 
	 //traversing through all the result 
	 $stmt->fetch();
		 $temp = array();
		 $temp['role'] = $role; 
		 if ($temp['role'] == "admin") {
		 	# code...
		 	echo $temp['role'];
		 } else echo "nothing";
		 
		 
} else echo "All fields are required";



?>