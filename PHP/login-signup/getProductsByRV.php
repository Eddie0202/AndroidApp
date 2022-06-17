<?php

include('dbConfig.php');

$ip = '192.168.1.3';

 $stmt = $con->prepare("select  m.id, m.title, m.shortdesc, m.image, group_concat(tl.tag, '') as tags
		from ((news m
		inner join news_tags t
		on m.id = t.news_id) 
		inner join tags tl
		on t.tags_id = tl.id)
        where t.tags_id = '10'
		group by m.id;");
 
 //executing the query 
 $stmt->execute();
 
 //binding results to the query 
 $stmt->bind_result($id, $title, $shortdesc, $image, $tags);
 
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



?>