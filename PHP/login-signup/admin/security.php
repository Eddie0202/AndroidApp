<?php
session_start();
include('database/dbconfig.php');
date_default_timezone_set('Asia/Ho_Chi_Minh');

if($connection)
{
    // echo "Database Connected";
}
else
{
    header("Location: database/dbconfig.php");
}
?>