$(function() {

	// 오늘날짜 설정하기
	let today = new Date();
	let formattedDate = today.toISOString().substr(0, 10);
	$("#date").val(formattedDate);


	$("#amount").on("keyup", function(e) {
		let input = $(e.target).val();
		let sanitizedInput = input.replace(/\D/g, '');		
		$(e.target).val(sanitizedInput);
	})


})