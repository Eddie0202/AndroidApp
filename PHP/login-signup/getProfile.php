<?php

include('dbConfig.php');
if (isset($_GET['username'])){
	$username = $_GET['username'];
	$sql = "select * from users where username = '$username'";
	 $stmt = $con->prepare($sql);
	 
	 //executing the query 
	 $stmt->execute();
	 
	 //binding results to the query 
	 $stmt->bind_result($id,$fullname, $username, $password, $email,$role);
	 
	 $profile = array(); 
	 
	 //traversing through all the result 
	 while($stmt->fetch()){
		 $temp = array();
		 $temp['id'] = $id;
		 $temp['fullname'] = $fullname; 
		 $temp['username'] = $username; 
		 $temp['password'] = $password; 
		 $temp['email'] = $email; 
		 $temp['role'] = $role; 
		 array_push($profile, $temp);
	 }
	 
	 //displaying the result in json format 
	 echo json_encode($profile);
} else echo "All fields are required";



?>