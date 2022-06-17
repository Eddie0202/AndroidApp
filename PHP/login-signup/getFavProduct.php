<?php

include('dbConfig.php');
if (isset($_GET['username'])){
	$username = $_GET['username'];
	$sql = "select m.id, m.title, m.shortdesc, m.image
		from ((news m
		inner join fav t
		on m.id = t.id_news) 
		inner join users tl
		on t.id_user = tl.id)
        where tl.username = '$username'";
	 $stmt = $con->prepare($sql);
	 
	 //executing the query 
	 $stmt->execute();
	 
	 //binding results to the query 
	 $stmt->bind_result($id,$title, $shortdesc, $image);
	 
	 $profile = array(); 
	 
	 //traversing through all the result 
	 while($stmt->fetch()){
		 $temp = array();
		 $temp['id'] = $id; 
		 $temp['title'] = $title; 
		 $temp['shortdesc'] = $shortdesc; 
		 $temp['image'] = $image; 
		 array_push($profile, $temp);
	 }
	 
	 //displaying the result in json format 
	 echo json_encode($profile);
} else echo "All fields are required";



?>