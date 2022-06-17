<?php

include('dbConfig.php');
if (isset($_GET['id'])){
	$id = $_GET['id'];
	$sql = "SELECT id, title, content FROM news Where id = $id";
	 $stmt = $con->prepare($sql);
	 
	 //executing the query 
	 $stmt->execute();
	 
	 //binding results to the query 
	 $stmt->bind_result($id, $title, $content);
	 
	 $profile = array(); 
	 
	 //traversing through all the result 
	 while($stmt->fetch()){
		 $temp = array();
		 $temp['id'] = $id; 
		 $temp['title'] = $title; 
		 $temp['content'] = $content;  
		 array_push($profile, $temp);
	 }
	 
	 //displaying the result in json format 
	 echo json_encode($profile);
} else echo "All fields are required";



?>