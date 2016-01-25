<?php
	if(isset($_POST['guest'])){
		$guest = $_POST['guest'];
			session_start();
			$_SESSION['login'] = 'guest';
			
			$url = "home.php";
			header("Location: $url");
		
	}else{
	//double check what the @ symbol means with variables
	@$username = $_POST['username'];
	@$password = $_POST['password'];
	
	// checks whether the past values are empty
	((isset($_POST['username'])) && (isset($_POST['password']))) or die("Login Failed: missing login details");
	
	// check whether sha256 can be implemented on java 
	$user = $username;
	$pass = hash('sha256', $password);
	//$pass = $password;
	
	require "connect.php";

	// find out what ctype_alnum does again *** login fails at this point at the moment
	//if (ctype_alnum(str_replace('@','',$user)) && ctype_alnum($pass)) {
			
		$sql = "SELECT * FROM members WHERE email='$user' AND password='$pass'";
		$result = mysqli_query($con, $sql);
		
		// if it finds a match with the correct password, and the number of rows retuned is greater than zero
		if(mysqli_num_rows($result) != 0){
			// starts the session
			session_start();
			$_SESSION['login'] = $user;
			
			$url = "home.php";
			header("Location: $url");
			
		}else{
			echo 'Login Failed: no matches found';
		}
		mysqli_close($con);
	}
		
	
?>