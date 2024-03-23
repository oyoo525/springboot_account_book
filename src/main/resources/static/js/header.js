$(function() {

	$("#logoutBtn").on("click", function() {
		$.ajax({
			"url" : "logout",
			"type" : "post",
			"success" : function() {
				location.href='/login';
			},
			"error" : function(err) {
				console.log("error : ", err)
			}
		})
	})



})