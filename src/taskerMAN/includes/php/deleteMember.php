<?php
	
	$id = $_POST['id'];	
	$reAlcID = $_POST['userID'];
	
	require "connect.php";
	
	$sql = "DELETE FROM members WHERE id='$id'";
	mysqli_query($con,$sql);
	
	$sql = "UPDATE tasks set MemberAllocated='$reAlcID' WHERE MemberAllocated='$id'";
	mysqli_query($con,$sql);
	
	mysqli_close($con);
	
	//removes the users profile picture
	$newname = dirname(__FILE__).'/../../images/profilepics/pic'. $id . '.png';
	if(file_exists($newname)) unlink($newname);
	
	//redirects to members page
	$url = "../../members.php";
	header( "Location: $url" );

?>