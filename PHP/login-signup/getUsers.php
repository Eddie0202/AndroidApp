<?php

include('dbConfig.php');


 if (!isset($_GET['id'])){
	$stmt = $con->prepare("SELECT * FROM users;");
 
 //executing the query 
 $stmt->execute();
 
 //binding results to the query 
 $stmt->bind_result($id, $fullname, $username, $password, $email,$role);
 
 $products = array(); 
 
 //traversing through all the result 
 while($stmt->fetch()){
 $temp = array();
 $temp['id'] = $id; 
 $temp['fullname'] = $fullname; 
 $temp['username'] = $username; 
 $temp['password'] = $password; 
 $temp['email'] = $email; 
 $temp['role'] = $role; 
 array_push($products, $temp);
 }
 
 //displaying the result in json format 
 echo json_encode($products);
}
elseif (isset($_GET['id']) && !isset($_GET['username'])) {
		$id = $_GET['id'];
		$stmt = $con->prepare("SELECT * FROM users where id=$id;");
	 
	 //executing the query 
	 $stmt->execute();
	 
	 //binding results to the query 
	 $stmt->bind_result($id, $fullname, $username, $password, $email,$role);
	 
	 $products = array(); 
	 
	 //traversing through all the result 
	 while($stmt->fetch()){
	 $temp = array();
	 $temp['id'] = $id; 
	 $temp['fullname'] = $fullname; 
	 $temp['username'] = $username; 
	 $temp['password'] = $password; 
	 $temp['email'] = $email; 
	 $temp['role'] = $role; 
	 array_push($products, $temp);
	}
	echo json_encode($products);
}



?>