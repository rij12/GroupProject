<?php
session_save_path("/aber/djt/tmp");
session_start();

$url = "../../index.php";
header("Location: $url");
session_destroy();


?>