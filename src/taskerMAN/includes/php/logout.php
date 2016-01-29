<?php
	session_start();

$url = "../../index.php";
header("Location: $url");
session_destroy();
?>