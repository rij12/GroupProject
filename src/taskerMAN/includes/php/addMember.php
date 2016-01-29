<?php
	
	$memberName = $_POST['name'];
	$memberEmail = $_POST['email'];
	$password = $_POST['password'];
	require "connect.php";
	$sql = "SELECT * FROM members WHERE email='$memberEmail'";
	$result = mysqli_query($con, $sql);
	$rowcount=mysqli_num_rows($result);
	mysqli_close($con);
		
	if($rowcount == 0){
	
	$memberName = filter_var($memberName, FILTER_SANITIZE_STRING);
	$memberEmail = filter_var($memberEmail, FILTER_SANITIZE_EMAIL);
	$password = filter_var($password, FILTER_SANITIZE_STRING);
	
	
	$hashPass = hash('sha256',$password);
	
	
	require "connect.php";
	
	
	
	$sql = "INSERT INTO members (id, name, email, password) VALUES (NULL, '$memberName', '$memberEmail', '$hashPass')";
	mysqli_query($con,$sql);
	
	$sql = "SELECT id FROM members WHERE name = '$memberName'";
	$result = mysqli_query($con,$sql);
	$row = mysqli_fetch_array($result);
	$id = $row['id'];
	
	mysqli_close($con);
	
	if((!empty($_FILES['picture']['name'])) && ($_FILES['picture']['error'] == 0)) {
     //Defines the path which to save the file to
      $newname = dirname(__FILE__).'/../../images/profilepics/pic'. $id . '.png';
     // if the file already exists on the server, remove it
	  if(file_exists($newname)) unlink($newname);
        //Tries to move the uploaded file to its new location
        if (!(move_uploaded_file($_FILES['picture']['tmp_name'],$newname))) {
            echo "Error: A problem occurred during file upload!";
        } 
	} 
	else {
		copy('../../images/portfolio.png', '../../images/profilepics/pic'. $id . '.png');
	}
	
	//redirects to members page
	$url = "../../members.php";
	header( "Location: $url" );
	}else{
		$url = "../../createMember.php?failed=1";
		header("Location: $url");
	}
?>