<?php 
	require 'includes/php/connect.php';
	$sql = "SELECT MIN(id) FROM members";
	$result = mysql_query($con, $sql);
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
	<!-- CSS style sheets used within the page are declared here -->
	<link rel="stylesheet" type="text/css" href="includes/css/styles.css">
	<link rel="stylesheet" type="text/css" href="includes/css/membersStyles.css">
</head>

<body>
	<div id = "container">
		<!-- PHP files used within the page are declared here --> 
		<?php include 'includes/php/menu.php'; ?>
		<div id ="bodyContainer">
			<div id ="bodyContainer">
			<div id ="body">
				<form id="selectMember" method="post" action="removeMember.php">
					<div id = "bodyHead"> Remove Member: 						<!-- Drop down list allowing user to chose which member to delete -->
								<select name="id" onchange="document.getElementById('selectMember').submit();" required>
									<option value = "" disabled selected> Select your option </option>
								    <?php
										while($row = mysqli_fetch_array($result)) {					
											echo '<option value="'. $row['id'] . '">' . $row['name'] . '</option>';
										}
										
										$sql = "SELECT * FROM members WHERE id ='$id'";
										$result = mysqli_query($con,$sql);
										$row = mysqli_fetch_array($result);
										$Rid = $row['id'];
									?>
								</select>
					</div>
				</form>
				<form id="removeMember" method="post" action="includes/php/deleteMember.php">
					<div id = "membersInfoBodyMain" style = "margin: -16px;">
						<div id = "bodyLeft">
							<div id ="porfolio"><img src="images/profilepics/pic<?php echo $row['id']; ?>.png" alt="portfolio image" height="247" width="199"></div>
						</div>
						<div id ="bodyRight">		<!-- displays information on member selected -->
							<div class = "nameArea"> <b>Full Name:</b> </div>
							<div class = "name"> <?php echo $row['name']; ?> </div>
							<div class = "nameArea"> <b>E-Mail:</b></div>
							<div class = "name"> <?php echo $row['email']; ?> </div>
							<div class = "nameArea" style = "text-align: left; margin-left: 5px; width: 165px;"> <b>Reallocate Tasks to:</b></div>
							<div class = "name"> <select name="userID">			<!-- Drop down list to reallocate all existing members tasks to new memberon deletion -->
								    <?php
										$sql = "SELECT * FROM members WHERE id <> '$id'";
										$result = mysqli_query($con,$sql);
										while($row = mysqli_fetch_array($result)) {	
											
											echo '<option value="'. $row['id'] . '">' . $row['name'] . '</option>';
											
										}
										
									?>
								</select> </div>
							<input type="hidden" name="id" value="<?php echo $Rid; ?>">
							
							<div <?php
								if(!isset($_POST['id'])){
									echo 'style="display:none;"';
								}
							?>><a href="#" onclick="document.getElementById('removeMember').submit();"><div id = "add" style = "margin-left: 100px; margin-top: 100px; padding-top: 15px; height: 38px;"> Submit </div></a></div>
							<a href="members.php"><div id = "remove" style = "margin-right: 150px; margin-top: 100px;"> Cancel </div></a>
						</div>
					</div>
				</form>
			</div>
		</div>
		</div>
	</div>
</body>
</html>