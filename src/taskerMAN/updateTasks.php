 

 <?php

  require "connect.php";
                                    
                                      echo $_POST['member']  . "<br>";

                                    
                                       $nTitle = $_POST['title'];
                                       $nMember= $_POST['member'];
                                       $nsDate = $_POST['sDate'];
                                       $ncDate= $_POST['cDate'];
                                       $nComment = $_POST['comments'];
                                       $nStatus = $_POST['status'];
                                       $ID = $_POST['id'];
                                        


                                        echo $nTitle  . "<br>";
                                        echo $nMember  . "<br>";
                                        echo $nsDate .  "<br>";
                                        echo $ncDate  . "<br>";
                                        echo $nComment . "<br>";
                                        echo $ID  . "<br>";
                                        echo $nStatus . "<br>";

                                       $query="UPDATE tasks SET TaskID = '$ID',StartDate = '$nsDate', DateOfCompletion = '$ncDate', TitleOfTask = '$nTitle', MemberAllocated = '$nMember',   Status = '$nStatus', Comments = '$nComment' WHERE TaskID= '$ID' ";
                                        
                                       $result = mysqli_query($con,$query);
                                       
                                      $con->close();
                                      
                                      $URL = '';
                                      header('Location:editTask.php');      

                                       


                                 
			?>


