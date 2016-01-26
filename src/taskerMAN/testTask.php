<?php

 
                                    
   
	for($i = 0; $i < 50; $i++){							
 require "connect.php";
    $sql="INSERT INTO tasks (StartDate,DateOfCompletion,TitleOfTask,MemberAllocated,Status,Comments) 
    VALUES ('26/08/2015', '26/08/2016', 'Test' , '3', 'Allocated', 
    'This is a comment' )";
                                        
    if ($con->query($sql)) {
    echo "New record created successfully";
    } else {
    echo "Error: " . $sql . "<br>" . $con->error;
     }
    $result = mysqli_query($con,$query);
                                       
    $con->close();
	}
	
	

      $url = "viewTasks.php";
      header("Location: $url");



			?>
  