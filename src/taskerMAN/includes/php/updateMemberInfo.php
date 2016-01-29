<?php
	if(isset($_POST['admin'])){
		$admin = "1";
	}else{
		$admin = "0";
	}

	$memberName = $_POST['Name'];
	$memberEmail = $_POST['Email'];
	$memberID = $_POST['id'];
	$password = $_POST['password'];
	
	require "connect.php";
	$sql = "SELECT * FROM members WHERE email='$memberEmail'";
	$result = mysqli_query($con, $sql);
	$row = mysqli_fetch_array($result);
	
	$rowcount=mysqli_num_rows($result);
	mysqli_close($con);
		
	if($rowcount == 0 || $rowcount == 1){

	$memberName = filter_var($memberName, FILTER_SANITIZE_STRING);
	$memberEmail = filter_var($memberEmail, FILTER_SANITIZE_EMAIL);
	$password = filter_var($password, FILTER_SANITIZE_STRING);
	
	if($memberName == "admin"){
		$admin = "1";
	}

	$hashPass = hash('sha256',$password);
	require "connect.php";
	
	if((!isset($_POST['password'])) || ($_POST['password'] == "")){		
		$sql = "UPDATE members SET name='$memberName', email='$memberEmail' , admin='$admin' WHERE id='$memberID'";
	}else{
		$sql = "UPDATE members SET name='$memberName', email='$memberEmail' , password='$hashPass' , admin='$admin' WHERE id='$memberID'";
	}

	
	mysqli_query($con,$sql);
	mysqli_close($con);
	
	if((!empty($_FILES['picture']['name'])) && ($_FILES['picture']['error'] == 0)) {
     //Defines the path which to save the file to
      $newname = dirname(__FILE__).'/../../images/profilepics/pic'. $memberID . '.png';
     // if the file already exists on the server, remove it
	  if(file_exists($newname)) unlink($newname);
        //Tries to move the uploaded file to its new location
        if (!(move_uploaded_file($_FILES['picture']['tmp_name'],$newname))) {
            echo "Error: A problem occurred during file upload!";
        } 
	}

	//redirects to members page
	$url = "../../membersInfo.php?id=" . $memberID;
	header( "Location: $url" );
	}else{
		$url = "../../membersEdit.php?id=" . $memberID . "&failed=1";
		header("Location: $url");
	}

?>