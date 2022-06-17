<?php
require "DataBaseConfig.php";

class DataBase
{
    public $connect;
    public $data;
    private $sql;
    protected $servername;
    protected $username;
    protected $password;
    protected $databasename;

    public function __construct()
    {
        $this->connect = null;
        $this->data = null;
        $this->sql = null;
        $dbc = new DataBaseConfig();
        $this->servername = $dbc->servername;
        $this->username = $dbc->username;
        $this->password = $dbc->password;
        $this->databasename = $dbc->databasename;
    }

    function dbConnect()
    {
        $this->connect = mysqli_connect($this->servername, $this->username, $this->password, $this->databasename);
        return $this->connect;
    }

    function prepareData($data)
    {
        return mysqli_real_escape_string($this->connect, stripslashes(htmlspecialchars($data)));
    }

    function logIn($table, $username, $password)
    {
        $username = $this->prepareData($username);
        $password = $this->prepareData($password);
        $this->sql = "select * from Users where username = '$username'";
        $result = mysqli_query($this->connect, $this->sql);
        $row = mysqli_fetch_assoc($result);
        if (mysqli_num_rows($result) != 0) {
            $dbusername = $row['username'];
            $dbpassword = $row['password'];
            $dbfullname = $row['fullname'];
            $dbemail = $row['email'];
            if ($dbusername == $username && $password == $dbpassword) {
                $login = true;
            } else $login = false;
        } else $login = false;

        return $login;
    }

    function signUp($table, $fullname, $email, $username, $password)
    {
        $fullname = $this->prepareData($fullname);
        $username = $this->prepareData($username);
        $password = $this->prepareData($password);
        $email = $this->prepareData($email);
        $this->sql =
            "INSERT INTO Users (fullname,username,password,email) values ('$fullname','$username','$password','$email')";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }
    function getNews()
    {
        $this->sql =
            "INSERT INTO Users (fullname,username,password,email) values ('$fullname','$username','$password','$email')";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }

    function addNews($table, $title, $shortdesc, $author, $image)
    {
        $date = date('Y-m-d H:i:s');
        $title = $this->prepareData($title);
        $shortdesc = $this->prepareData($shortdesc);
        $author = $this->prepareData($author);
        $image = $this->prepareData($image);
        $this->sql =
            "INSERT INTO News (title,shortdesc,author,image,date) values ('$title','$shortdesc','$author','$image','$date')";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
        
    }

    function addRV($table, $fullname, $email, $username, $password)
    {
        $fullname = $this->prepareData($fullname);
        $username = $this->prepareData($username);
        $password = $this->prepareData($password);
        $email = $this->prepareData($email);
        $this->sql =
            "INSERT INTO Users (fullname,username,password,email) values ('$fullname','$username','$password','$email')";
        if (mysqli_query($this->connect, $this->sql)) {
            return true;
        } else return false;
    }



}

?>