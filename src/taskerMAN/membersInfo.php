<?php 
	$id = isset($_GET['id']) ? $_GET['id'] : 1;
	
	require 'includes/php/connect.php';
	
	$sql = "SELECT * FROM members WHERE id='$id'";
	$result = mysqli_query($con,$sql);
	$row = mysqli_fetch_array($result);
	
	mysql_close($con);
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
				<div id = "bodyHead"> Member: <?php echo $row['name']; ?>
					<a href = "membersEdit.php?id=<?php echo $row['id']; ?>"><div id = "edit"></div></a>
				</div>
				<div id = "membersInfoBodyMain">
					<div id = "bodyLeft">
						<div id ="porfolio"><img src="images/profilepics/pic<?php echo $row['id']; ?>.png" alt="portfolio image" height="247" width="199"></div>
					</div>
					<div id ="bodyRight">
						<div class = "nameArea"> <b>Full Name:</b> </div>
						<div class = "name"> <?php echo $row['name']; ?> </div>
						<div class = "nameArea"> <b>E-Mail:</b></div>
						<div class = "name"> <?php echo $row['email']; ?> </div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>