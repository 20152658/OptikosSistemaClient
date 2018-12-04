$(document).ready(function(){
	
	$("#loginForm").validate({
		rules:
		{	
			email: {
				required: true,
				email: true
			},
			password: {
				required: true
			}
		},
			messages:{
				
				email: {
					required: ' Please enter email',
					email: ' Please enter valid email'
				},
				password: {
					required: " Please enter password",
				}
			}
			
	});
});