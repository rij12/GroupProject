<?php 
	require 'connect.php';
	$sql = "SELECT MIN(id) FROM members";
	$result = mysqli_query($con,$sql);
	$row = mysql_fetch_array($result);
	
	if(isset($_POST['id'])){
		$id = $_POST['id'];
	}else{
		$id = $row['MIN(id)'];
	}
	
	
	$sql = "SELECT * FROM members WHERE name <> 'admin'";
	$result = mysqli_query($con,$sql);
	
	
 ?>
<!DOCTYPE html>
<html>
<head> 
<title> Members </title>
<link rel="stylesheet" type="text/css" href="styles.css">
<link rel="stylesheet" type="text/css" href="membersStyles.css">
</head>
<body>
	<div id = "container"> 
		<?php include 'menu.php'; ?>
		<div id ="bodyContainer">
			<div id ="bodyContainer">
			<div id ="body">
			<form id="selectMember" method="post" action="removeMember.php">
				<div id = "bodyHead"> Remove Member: 							
							<select name="id" onchange="document.getElementById('selectMember').submit();" required>
								<option value="" disabled selected>Select your option</option>
							    <?php
									while($row = mysqli_fetch_array($result)) {					
										echo '<option value="'. $row['id'] . '"';
										if($id == $row['id']){
											echo ' selected ';
										}
										echo'>' . $row['name'] . '</option>';
									}
									
									$sql = "SELECT * FROM members WHERE id ='$id'";
									$result = mysqli_query($con,$sql);
									$row = mysqli_fetch_array($result);
									$Rid = $row['id'];
								?>
							</select>
							
					<a href = "#"><div id = "edit"></div></a>
				</div></form>
				<form id="removeMember" method="post" action="deleteMember.php">
				<div id = "membersInfoBodyMain">
					<div id = "bodyLeft">
						<div id ="porfolio"><img src="images/profilepics/pic<?php echo $row['id']; ?>.png" alt="portfolio image" height="247" width="199"></div>
					</div>
					<div id ="bodyRight">
						<div id = "nameArea"> <b>Full Name:</b> </div>
						<div id = "name"> <?php echo $row['name']; ?> </div>
						<div id = "emailArea"> <b>E-Mail:</b></div>
						<div id = "email"> <?php echo $row['email']; ?> </div>
						<div> <b>Reallocate Tasks to:</b></div>
						<div id = "email"> <select name="userID">
							    <?php
									$sql = "SELECT * FROM members WHERE id <> '$id'";
									$result = mysqli_query($con,$sql);
									while($row = mysqli_fetch_array($result)) {	
										
										echo '<option value="'. $row['id'] . '">' . $row['name'] . '</option>';
										
									}
									
								?>
							</select> </div>
						<input type="hidden" name="id" value="<?php echo $Rid; ?>">
						
					</div>
				</div>
				<div id = "bodyFooter">
							<a href="#" onclick="document.getElementById('removeMember').submit();"><div id = "add"> Submit </div></a>
							<a href="members.php"><div id = "remove"> Cancel </div></a>
				</div>
				</form>
			</div>
		</div>
		</div>
	</div>
</body>
</html>