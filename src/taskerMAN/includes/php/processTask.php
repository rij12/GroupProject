<?php

  require "connect.php";
                                 
      $title = $_POST['title'];
      $comments = $_POST['comment'];

      $title = filter_var($title, FILTER_SANITIZE_STRING);
      $comments = filter_var($comments, FILTER_SANITIZE_STRING); 

      $sql="INSERT INTO tasks (StartDate,DateOfCompletion,TitleOfTask,MemberAllocated,Status,Comments) 
      VALUES ('$_POST[sDate]', '$_POST[cDate]', '$title' , '$_POST[members]', '$_POST[status]', 
      '$comments' )";
                                          
      if ($con->query($sql)) {
      echo "New record created successfully";
      } else {
      echo "Error: " . $sql . "<br>" . $con->error;
       }
      $result = mysqli_query($con,$query);
                                         
      $con->close();

      $url = "../../viewTasks.php";
      header("Location: $url");

?>
  