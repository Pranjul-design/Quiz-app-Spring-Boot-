function validateForm(){
	var status = true ; 
	var checkForSpecialCharacters = /^[a-zA-Z_ ]*$/;
	
	$('.alert-danger').hide();
	
	/******* subjectName Validation******/
    var subjectName = $.trim($('#subjectName').val());
    if(subjectName=="") {
		$('#subjectName').focus();
		status = 'Blank_mandatory_fields';
		$('.'+status).show(500);
		status = false;
		window.scrollTo(0,0);
		return status;
	} else if(!checkForSpecialCharacters.test(subjectName)) {
		$('#subjectName').focus();
		status = 'special_character';
		$('.'+status).show(500);
		status = false;
		window.scrollTo(0,0);
		return status;
	}
	
}
