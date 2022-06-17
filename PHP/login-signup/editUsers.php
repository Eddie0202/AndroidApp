<?php

include('dbConfig.php');


 if (isset($_GET['id'])&&isset($_GET['username'])&&isset($_GET['fullname'])){
	$id = $_GET['id'];
    $username = $_GET['username'];
    $fullname = $_GET['fullname'];
    $password = $_GET['password'];
    $email = $_GET['email'];
    $role = $_GET['role'];


    $query = "UPDATE users SET username='$username', fullname='$fullname', password='$password', email='$email', role='$role' WHERE id='$id' ";
    $query_run = mysqli_query($con, $query);

    if($query_run)
    {

        echo "Update Successfully";
    }
    else
    {
        echo "Not Update Yet";
    }
}
elseif (isset($_GET['id'])&&isset($_GET['title'])&&isset($_GET['date'])) {
	# code...
	$id = $_GET['id'];
    $title = $_GET['title'];
    $date = $_GET['date'];
    $shortdesc = $_GET['shortdesc'];
    $author = $_GET['author'];
    $image = $_GET['image'];


    $query = "UPDATE news SET title='$title', date='$date', shortdesc='$shortdesc', author='$author', image='$image' WHERE id='$id' ";
    $query_run = mysqli_query($con, $query);

    if($query_run)
    {

        echo "Update Successfully";
    }
    else
    {
        echo "Not Update Yet";
    }

}




?>