<?php

  require "connect.php";
                                    
   
                                    

    $sql="INSERT INTO tasks (StartDate,DateOfCompletion,TitleOfTask,MemberAllocated,Status,Comments) 
    VALUES ('$_POST[sDate]', '$_POST[cDate]', '$_POST[title]' , '$_POST[members]', '$_POST[status]', 
    '$_POST[comment]' )";
                                        
    if ($con->query($sql)) {
    echo "New record created successfully";
    } else {
    echo "Error: " . $sql . "<br>" . $con->error;
     }
    $result = mysqli_query($con,$query);
                                       
    $con->close();

      $url = "viewTasks.php";
      header("Location: $url");



			?>
  