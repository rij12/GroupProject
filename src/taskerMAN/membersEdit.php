<?php 
	$id = isset($_GET['id']) ? $_GET['id'] : 1;
	
	require 'includes/php/connect.php';
	
	$sql = "SELECT * FROM members WHERE id='$id'";
	$result = mysqli_query($con,$sql);
	$row = mysqli_fetch_array($result);
	
 ?>

<!DOCTYPE html>
<html>

<head> 
	<title> Members </title>
	<link rel="stylesheet" type="text/css" href="includes/css/styles.css">
	<link rel="stylesheet" type="text/css" href="includes/css/membersStyles.css">
</head>

<body>
	<div id = "container"> 
		<?php include 'includes/php/menu.php'; ?>
		<div id ="bodyContainer">
			<div id ="body">
				<div id = "bodyHead">Edit Member: <?php echo $row['name']; ?></div>
				<div id = "membersInfoBodyMain">
					<div id = "bodyLeft">
						<div id ="porfolio"><img src="images/profilepics/pic<?php echo $row['id']; ?>.png" alt="portfolio image" height="247" width="199"></div>
					</div>
					<div id ="bodyRight">
						<form enctype="multipart/form-data" action="includes/php/updateMemberInfo.php" method = "post">
							<div class = "nameArea"> <b>Full Name:</b> </div>
							<div class = "name"> <input type="text" name="Name" maxlength="50" value="<?php echo $row['name']; ?>" required></div>
							<div class = "nameArea"> <b>E-Mail:</b></div>
							<div class = "name"> <input type="text" name="Email" maxlength="50" value="<?php echo $row['email']; ?>" required></div>
							<div class = "nameArea"><b>Password:</b></div>
							<div class = "name"><input type="password" name="password" maxlength="50"></div>
							<div class = "nameArea"><b>Profile Picture:</b></div>
							<div class = "name"><input type="file" name="picture" accept="image/*"></div>
							<div class = "nameArea"></div><div class = "name">
							<?php

								if($row['name'] != "admin"){
								echo '<input type="checkbox" name="admin" value="admin"';
								
								$sql = "SELECT * FROM members WHERE id='$id'";
								$result = mysqli_query($con,$sql);
								$row = mysqli_fetch_array($result);
								if($row['admin'] == 1){
									echo ' checked';
								}
									echo '
										>Set Admin';
									}
							?>
							<input name="id" type="hidden" value="<?php echo $row['id']; ?>"></input>
							<div class = "name"> <input type="submit" value="Submit"> </div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>