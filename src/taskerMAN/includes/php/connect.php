<?php
	$dbuser = "csgpadm_16";
	$dbpass = "fPeeWanK";
	$server = "db.dcs.aber.ac.uk";
	$database = "csgp_16_15_16";
	
	$con = @mysqli_connect($server,$dbuser,$dbpass);
	if(!@mysqli_select_db($con,$database)){
		$url = "error.php";
		header("Location: $url");
	}
	

	

?>