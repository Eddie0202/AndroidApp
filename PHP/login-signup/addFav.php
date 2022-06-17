<?php

include('dbConfig.php');

if (isset($_POST['username'])&&isset($_POST['idmovies'])){
	$username = $_POST['username'];
	$idmovies = $_POST['idmovies'];
	$sql = "select id from users where username = '$username'";
	$stmt = $con->prepare($sql);
	$stmt->execute();
	$stmt->bind_result($id);
	while($stmt->fetch()){
		 $temp = array();
		 $temp['id'] = $id;
	 }
	 $iduser = $temp['id'];



	 $sql1 = "INSERT INTO fav (id_user,id_news) values ('$iduser','$idmovies')";
        if (mysqli_query($con, $sql1)) {
            echo "Add Favorite Success";
        } else{
        	$sql3 = "DELETE FROM fav WHERE (id_user='$iduser') AND (id_news = '$idmovies')";
        	mysqli_query($con, $sql3);
        	echo "Unfavorite Success";
        }

     // $sql2 = "INSERT INTO fav (id_user,id_news) values ('$iduser',17)";
     //    if (mysqli_query($con, $sql2)) {
     //        echo "Add Favorite Success";
     //    } else echo "Add Favorite Failed";
	 
} else echo "All fields are required";



?>