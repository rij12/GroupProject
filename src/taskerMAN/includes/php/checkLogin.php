<?php
	
	@$username = $_POST['username'];
	@$password = $_POST['password'];
	
	// checks whether the past values are empty
	((isset($_POST['username'])) && (isset($_POST['password']))) or die("Login Failed: missing login details");
	
	// check whether sha256 can be implemented on java 
	$user = filter_var($username, FILTER_SANITIZE_EMAIL);
	$password = filter_var($password, FILTER_SANITIZE_STRING);
	$pass = hash('sha256', $password);
	//$pass = $password;
	
	require "connect.php";

			
		$sql = "SELECT * FROM members WHERE email='$user' AND password='$pass'";
		$result = mysqli_query($con, $sql);
		$row = mysqli_fetch_array($result);
		
		// if it finds a match with the correct password, and the number of rows retuned is greater than zero
		if(mysqli_num_rows($result) != 0){
			$admin = $row['admin'];
			if($admin == 1){
				// starts the session
			session_start();
			$_SESSION['login'] = $user;
			
			$url = "../../home.php";
			header("Location: $url");
			}else{
				$url = "../../index.php?denied=2";
			header("Location: $url");
			}
			
			
		}else{
			$url = "../../index.php?denied=1";
			header("Location: $url");
		}
		mysqli_close($con);
	
		
	
?>