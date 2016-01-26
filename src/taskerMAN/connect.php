<?php
	$dbuser = "csgpadm_16";
	$dbpass = "fPeeWanK";
	$server = "db.dcs.aber.ac.uk";
	$database = "csgp_16_15_16";
	
	$con = @mysqli_connect($server,$dbuser,$dbpass);
	//@mysqli_select_db($con,$database) or die ("Unable to connect to database");
	if(!@mysqli_select_db($con,$database)){
		$url = "error.php";
		header("Location: $url");
	} 

?>