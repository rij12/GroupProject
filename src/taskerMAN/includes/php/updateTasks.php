<?php
  require "connect.php";

    $nTitle = $_POST['title'];
    $nMember= $_POST['member'];
    $nsDate = $_POST['sDate'];
    $ncDate= $_POST['cDate'];
    $nComment = $_POST['comments'];
    $nStatus = $_POST['status'];     
    $ID = $_POST['id'];

    $nTitle = filter_var($nTitle, FILTER_SANITIZE_STRING);
    $nComment = filter_var($nComment, FILTER_SANITIZE_STRING);

    $query="UPDATE tasks SET TaskID = '$ID',StartDate = '$nsDate', DateOfCompletion = '$ncDate', TitleOfTask = '$nTitle', MemberAllocated = '$nMember',   Status = '$nStatus', Comments = '$nComment' WHERE TaskID= '$ID' ";
      
    $result = mysqli_query($con,$query);
     
    $con->close();
    
    $URL = 'Location:../../viewTasks.php';
    header($URL);      

?>


