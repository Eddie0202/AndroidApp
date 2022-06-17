<?php

include('dbConfig.php');

if (isset($_GET['type'])) {
	# code...


	$stmt = $con->prepare("SELECT * FROM news order by date desc;");
	 
	 //executing the query 
	 $stmt->execute();
	 
	 //binding results to the query 
	 $stmt->bind_result($id, $title, $shortdesc, $author, $image, $date, $content);
	 
	 $products = array(); 
	 
	 //traversing through all the result 
	 while($stmt->fetch()){
	 $temp = array();
	 $temp['id'] = $id; 
	 $temp['title'] = $title; 
	 $temp['shortdesc'] = $shortdesc; 
	 $temp['author'] = $author; 
	 $temp['image'] = $image; 
	 $temp['date'] = $date; 
	 array_push($products, $temp);
	 }
	 
	 //displaying the result in json format 
	 echo json_encode($products);
}elseif (isset($_GET['id'])) {

	$iddb = $_GET['id'];

	$stmt = $con->prepare("SELECT * FROM news where id='$iddb';");
	 
	 //executing the query 
	 $stmt->execute();
	 
	 //binding results to the query 
	 $stmt->bind_result($id, $title, $shortdesc, $author, $image, $date, $content);
	 
	 $products = array(); 
	 
	 //traversing through all the result 
	 while($stmt->fetch()){
	 $temp = array();
	 $temp['id'] = $id; 
	 $temp['title'] = $title; 
	 $temp['shortdesc'] = $shortdesc; 
	 $temp['author'] = $author; 
	 $temp['image'] = $image; 
	 $temp['date'] = $date; 
	 array_push($products, $temp);
	 }
	 
	 //displaying the result in json format 
	 echo json_encode($products);
}
else{

 $stmt = $con->prepare("SELECT id, title, shortdesc, image, date FROM news
 								order by date desc;");
 
 //executing the query 
 $stmt->execute();
 
 //binding results to the query 
 $stmt->bind_result($id, $title, $shortdesc, $image, $date);
 
 $products = array(); 
 
 //traversing through all the result 
 while($stmt->fetch()){
 $temp = array();
 $temp['id'] = $id; 
 $temp['title'] = $title; 
 $temp['shortdesc'] = $shortdesc; 
 $temp['image'] = $image; 
 array_push($products, $temp);
 }
 
 //displaying the result in json format 
 echo json_encode($products);
}



?>