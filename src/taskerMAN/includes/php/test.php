<?php
require "connect.php";
		
	
	
	
	$sql = "CREATE TABLE IF NOT EXISTS `membersTests` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  `email` text NOT NULL,
  `password` text NOT NULL,
  `admin` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
)";

mysqli_query($con,$sql);

$sql = "INSERT INTO `membersTests` (`id`, `name`, `email`, `password`, `admin`) VALUES
(4, 'Max Limbu', 'mal30@aber.ac.uk', 'e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855', 0),
(5, 'Rhodri Pearce', 'rhp14@aber.ac.uk', '', 0),
(6, 'Robert Mouncer', 'rdm10@aber.ac.uk', 'e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855', 0),
(7, 'Archie Strange', 'ars14@aber.ac.uk', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', 1),
(8, 'Greg Sharpe', 'gds2@aber.ac.uk', 'e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855', 1),
(16, 'admin', 'admin', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', 1),
(43, 'Emil', 'emr18@aber.ac.uk', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', 0),
(50, 'Richard Jones', 'rij12@aber.ac.uk', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', 0),
(52, 'Test Picture User', 'p@d.co.uk', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', 0)";

mysqli_query($con,$sql);

	$sql = "CREATE TABLE IF NOT EXISTS `tasksTests` (
  `TaskID` int(11) NOT NULL AUTO_INCREMENT,
  `StartDate` varchar(10) NOT NULL,
  `DateOfCompletion` varchar(100) NOT NULL,
  `TitleOfTask` varchar(100) NOT NULL,
  `MemberAllocated` int(40) NOT NULL,
  `Status` varchar(10) NOT NULL,
  `Comments` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`TaskID`)
)";

mysqli_query($con,$sql);

$sql = "INSERT INTO `tasksTests` (`TaskID`, `StartDate`, `DateOfCompletion`, `TitleOfTask`, `MemberAllocated`, `Status`, `Comments`) VALUES
(2, '2222-11-11', '2016-02-21', 'ADFASDFASDFASDFAWEF 2.0', 6, 'Abandoned', ' ffadfasdf asdfasd fadsj fnasdlkj fndsalf jkasndflkja sdnflkjasdnflkjas dnflksajdnflksjd naslkjd nas'),
(3, '2015-12-21', '2015-12-22', 'Sweep', 4, 'Allocated', 'No comment'),
(591, '', '', '', 7, '', ''),
(592, '', '', '', 5, '', ''),
(594, '2015-11-25', '2015-11-26', 'User id test', 4, 'Complete', 'Does this work with user id?drtfghuijnokmp,l jar'),
(2239, '26/08/2015', '26/08/2016', 'Test', 4, 'Complete', 'This is a comment, a very long comment'),
(2240, '26/08/2015', '26/08/2016', 'Test', 4, 'Complete', 'This is a comment'),
(2241, '26/08/2015', '26/08/2016', 'Test', 4, 'Complete', 'This is a comment ff sd'),
(2242, '26/08/2015', '26/08/2016', 'Test', 4, 'Complete', 'This is a comment'),
(2243, '26/08/2015', '26/08/2016', 'Test', 4, 'Complete', 'This is a comment testing 123'),
(2244, '26/08/2015', '26/08/2016', 'Test', 4, 'Complete', 'This is a comment'),
(2245, '26/08/2015', '26/08/2016', 'Test', 4, 'Complete', 'This is a comment testing'),
(2246, '26/08/2015', '26/08/2016', 'Test', 4, 'Complete', 'This is a comment'),
(2247, '26/08/2015', '26/08/2016', 'Test', 4, 'Complete', 'This is a comment COMPLETED'),
(2248, '26/08/2015', '26/08/2016', 'Test', 4, 'Complete', 'This is a commentwdvvdvdvdvdvd'),
(2249, '26/08/2015', '26/08/2016', 'Test', 4, 'Complete', 'This is a comment comments, comments'),
(2250, '26/08/2015', '26/08/2016', 'Test', 4, 'Complete', 'This is a comment, Completed'),
(2251, '26/08/2015', '26/08/2016', 'Test', 4, 'Complete', 'This is a comment comments everywhere drittspill!! skdan kj dkjandsjksa'),
(2252, '26/08/2015', '26/08/2016', 'Test', 4, 'Complete', 'This is a comment'),
(2253, '26/08/2015', '26/08/2016', 'Test', 4, 'Complete', 'This is a commentdsfkm fsdlkfsmdsdsdsdsdsdsdsdsdsd'),
(2254, '26/08/2015', '26/08/2016', 'Test', 4, 'Complete', 'This is a comment comments everywhere'),
(2255, '26/08/2015', '26/08/2016', 'Test', 4, 'Complete', 'This is a comment'),
(2256, '26/08/2015', '26/08/2016', 'Test', 4, 'Complete', 'This is a comment1111111111111111111'),
(2257, '26/08/2015', '26/08/2016', 'Test', 4, 'Complete', 'This is a comment'),
(2258, '26/08/2015', '26/08/2016', 'Test', 4, 'Complete', 'This is a comment'),
(2259, '26/08/2015', '26/08/2016', 'Test', 4, 'Complete', 'This is a comment'),
(2260, '26/08/2015', '26/08/2016', 'Test', 4, 'Complete', 'This is a comment')";

	mysqli_query($con,$sql);
	
	?>