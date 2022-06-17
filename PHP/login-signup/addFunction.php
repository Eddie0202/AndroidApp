<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['title']) && isset($_POST['shortdesc']) && isset($_POST['author']) && isset($_POST['image'])) {
    if ($db->dbConnect()) {
        if ($db->addNews("news", $_POST['title'], $_POST['shortdesc'], $_POST['author'], $_POST['image'])) {
            echo "Add News Success";
        } else echo "Add News Failed";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>