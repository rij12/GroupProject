function validateForm() {
	var start = document.forms["process"]["sDate"].value;
	var end = document.forms["process"]["cDate"].value;
	if (start > end) {
		alert("The start date must begin before the end date");
		return false;
	}
}