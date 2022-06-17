<?php

include('security.php');

// code xử lí dữ liệu phía tài khoản người dùng và quản trị viên

if(isset($_POST['registerbtn']))
{
    $username = $_POST['username'];
    $email = $_POST['email'];
    $password = $_POST['password'];
    $cpassword = $_POST['confirmpassword'];
    $role = "admin";

    $email_query = "SELECT * FROM user WHERE email='$email' ";
    $email_query_run = mysqli_query($connection, $email_query);
    if(mysqli_num_rows($email_query_run) > 0)
    {
        $_SESSION['status'] = "Email đã tồn tại hãy nhập email khác.";
        $_SESSION['status_code'] = "error";
        header('Location: register.php');  
    }
    else
    {
        if($password === $cpassword)
        {
            $query = "INSERT INTO user (username,password,email,role) VALUES ('$username','$password','$email', '$role')";
            $query_run = mysqli_query($connection, $query);
            
            if($query_run)
            {
                // echo "Saved";
                $_SESSION['status'] = "Admin Profile Added";
                $_SESSION['status_code'] = "success";
                header('Location: register.php');
            }
            else 
            {
                $_SESSION['status'] = "Admin Profile Not Added";
                $_SESSION['status_code'] = "error";
                header('Location: register.php');  
            }
        }
        else 
        {
            $_SESSION['status'] = "Password and Confirm Password Does Not Match";
            $_SESSION['status_code'] = "warning";
            header('Location: register.php');  
        }
    }

}



if(isset($_POST['updatebtn']))
{
    $id = $_POST['edit_id'];
    $username = $_POST['edit_username'];
    $name = $_POST['name'];
    $email = $_POST['edit_email'];
    $password = $_POST['edit_password'];
    $role = "admin";

    $query = "UPDATE user SET username='$username', name='$name', email='$email', password='$password' WHERE id='$id' ";
    $query_run = mysqli_query($connection, $query);

    if($query_run)
    {
        $_SESSION['status'] = "Your Data is Updated";
        $_SESSION['status_code'] = "success";
        header('Location: register.php'); 
    }
    else
    {
        $_SESSION['status'] = "Your Data is NOT Updated";
        $_SESSION['status_code'] = "error";
        header('Location: register.php'); 
    }
}

if(isset($_POST['updatebtn1']))
{
    $id = $_POST['edit_id'];
    $username = $_POST['edit_username'];
    $name = $_POST['edit_name'];
    $password = $_POST['edit_password'];

    $query = "UPDATE user SET username='$username', name='$name', password='$password' WHERE id='$id' ";
    $query_run = mysqli_query($connection, $query);

    if($query_run)
    {

        $_SESSION['status'] = "Your Data is Updated";
        $_SESSION['status_code'] = "success";
        header('Location: register1.php'); 
    }
    else
    {
        $_SESSION['status'] = "Your Data is NOT Updated";
        $_SESSION['status_code'] = "error";
        header('Location: register1.php'); 
    }
}

if(isset($_POST['delete_btn']))
{
    $id = $_POST['delete_id'];

    $query = "DELETE FROM user WHERE id='$id' ";
    $query_run = mysqli_query($connection, $query);

    if($query_run)
    {
        $_SESSION['status'] = "Your Data is Deleted";
        $_SESSION['status_code'] = "success";
        header('Location: register.php'); 
    }
    else
    {
        $_SESSION['status'] = "Your Data is NOT DELETED";       
        $_SESSION['status_code'] = "error";
        header('Location: register.php'); 
    }    
}

if(isset($_POST['delete1_btn']))
{
    $id = $_POST['delete_id'];

    $query = "DELETE FROM user WHERE id='$id' ";
    $query_run = mysqli_query($connection, $query);

    if($query_run)
    {
        $_SESSION['status'] = "Your Data is Deleted";
        $_SESSION['status_code'] = "success";
        header('Location: register1.php'); 
    }
    else
    {
        $_SESSION['status'] = "Your Data is NOT DELETED";       
        $_SESSION['status_code'] = "error";
        header('Location: register1.php'); 
    }    
}






// code Xử lí Dữ liệu của phim


if(isset($_POST['updateMovie_btn']))
{
    $edit_id = $_POST['edit_id'];
    $edit_title = $_POST['edit_title'];
    $edit_director = $_POST['edit_director'];
    $edit_length = $_POST['edit_length'];
    $edit_rating = $_POST['edit_rating'];
    $edit_description = $_POST['edit_description'];

    $edit_image = $_FILES['edit_image']['name'];
    $file_tmp = $_FILES['edit_image']['tmp_name'];
    $path = "../images/".$edit_image;

    if (isset($_POST['edit_tag'])) {
        # code...
        $qr1 = "DELETE FROM moviess_tags WHERE movies_id = '$edit_id'";
        mysqli_query($connection, $qr1);
        foreach ($_POST['edit_tag'] as $edit_tag) {
            # code...
            $qr2 = "INSERT INTO movies_tags (movies_id,tags_id) VALUES ('$edit_id','$edit_tag')";
            mysqli_query($connection, $qr2);
        }
    }

    $query = "UPDATE movies SET title='$edit_title', description='$edit_description', director='$edit_director', 
                rating='$edit_rating', length='$edit_length', imageLink='images/$edit_image'
                WHERE id_movies='$edit_id' ";
    $query_run = mysqli_query($connection, $query);

    if($query_run)
    {
        move_uploaded_file($file_tmp, $path);

        $_SESSION['status'] = "Your Data is Updated";
        $_SESSION['status_code'] = "success";
        header('Location: bookList.php'); 
    }
    else
    {
        $_SESSION['status'] = "Your Data is NOT Updated";
        $_SESSION['status_code'] = "error";
        header('Location: bookList.php'); 
    }
}

if(isset($_POST['deleteMovie_btn']))
{
    $id = $_POST['delete_id'];

    $query = "DELETE FROM movies WHERE id_movies='$id' ";
    $query_run = mysqli_query($connection, $query);

    if($query_run)
    {
        $_SESSION['status'] = "Your Data is Updated";
        $_SESSION['status_code'] = "success";
        header('Location: bookList.php'); 
    }
    else
    {
        $_SESSION['status'] = "Your Data is NOT Updated";
        $_SESSION['status_code'] = "error";
        header('Location: bookList.php'); 
    }
}

if(isset($_POST['addMovie_btn']))
{
    
    $title = $_POST['title'];
    $description = $_POST['description'];
    $director = $_POST['director'];
    $rating = $_POST['rating'];
    $image = $_FILES['image']['name'];
    $file_tmp = $_FILES['image']['tmp_name'];
    $path = "../images/".$image;
    $length = $_POST['length'];
    $date = date('Y-m-d H:i:s');
    $movielink = $_POST['movielink'];

    $query = "SELECT * FROM movies WHERE title='$title' ";
    $query_run = mysqli_query($connection, $query);
    if(mysqli_num_rows($query_run) > 0)
    {
        $_SESSION['status'] = "Phim này đã tồn tại";
        $_SESSION['status_code'] = "error";
        header('Location: bookList.php');  
    }
    else
    {
            $query1 = "INSERT INTO movies (title,description,director,rating,imagelink,length,date,movielink) VALUES ('$title','$description','$director','$rating','images/$image','$length','$date','$movielink')";
            $query1_run = mysqli_query($connection, $query1);
            
            if($query1_run)
            {
                // echo "Saved";
                move_uploaded_file($file_tmp, $path);
                if (isset($_POST['tag'])) {
                    # code...
                    $query_r1 = mysqli_query($connection, "SELECT id_movies FROM movies WHERE title Like '$title'");
                    $row = mysqli_fetch_assoc($query_r1);
                    $id = $row['id_movies'];
                    foreach ($_POST['tag'] as $tag) {
                        # code...
                        $qr2 = "INSERT INTO movies_tags (movies_id,tags_id) VALUES ('$id','$tag')";
                        mysqli_query($connection, $qr2);
                    }
                }

                $_SESSION['status'] = "Thêm phim thành công";
                $_SESSION['status_code'] = "success";
                header('Location: bookList.php');
            }
            else 
            {
                $_SESSION['status'] = "Phim không được thêm";
                $_SESSION['status_code'] = "error";
                header('Location: bookList.php');  
            }
        
    }
}

//Code Xử lý bài review
if(isset($_POST['addReview_btn']))
{
    $id_user = $_SESSION['id'];
    $content = $_POST['editor'];
    $author = $_SESSION['user_name'];
    $date =  date('Y-m-d H:i:s');
    $title = $_POST['review_title'];
    $image = $_FILES['image']['name'];
    $file_tmp = $_FILES['image']['tmp_name'];
    $path = "../img/".$image;
    $query_run = $connection->query("INSERT INTO news (title,content,author,image,date) VALUES ('$title','$content','$author','img/$image','$date')");
    if($query_run)
    {
        move_uploaded_file($file_tmp, $path);
        $_SESSION['status'] = "Your Data is Updated";
        $_SESSION['status_code'] = "success";
        header('Location: addReview.php'); 
    }
    else
    {
        $_SESSION['status'] = "Your Data is NOT Updated";
        $_SESSION['status_code'] = "error";
        header('Location: addReview.php'); 
    }
}

if(isset($_POST['updateReview_btn1']))
{
    $id_review = $_POST['edit_id'];
    $title = $_POST['edit_review_title'];
    $image_link = $_POST['edit_image_link'];
    $content = $_POST['edit_editor'];

    $query = "UPDATE review SET title='$title', image_link='$image_link', content='$content'
                WHERE id_review='$id_review' ";
    $query_run = mysqli_query($connection, $query);

    if($query_run)
    {
        $_SESSION['status'] = "Your Data is Updated";
        $_SESSION['status_code'] = "success";
        header('Location: review_list.php'); 
    }
    else
    {
        $_SESSION['status'] = "Your Data is NOT Updated";
        $_SESSION['status_code'] = "error";
        header('Location: review_list.php'); 
    }
}

if(isset($_POST['deleteReview_btn']))
{
    $id_review = $_POST['deleteReview_id'];

    $query = "DELETE FROM review WHERE id_review='$id_review' ";
    $query_run = mysqli_query($connection, $query);

    if($query_run)
    {
        $_SESSION['status'] = "Your Data is Updated";
        $_SESSION['status_code'] = "success";
        header('Location: review_list.php'); 
    }
    else
    {
        $_SESSION['status'] = "Your Data is NOT Updated";
        $_SESSION['status_code'] = "error";
        header('Location: review_list.php'); 
    }
}


?>