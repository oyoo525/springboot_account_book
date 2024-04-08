$(function() {

	// 오늘날짜 설정하기
	let today = new Date();
	let formattedDate = today.toISOString().substr(0, 10);
	$("#date").val(formattedDate);

	// 타입별로 select 보이기
	$(".type").on("change", function() {
		let type = $(".type:checked").val();

		if(type == "income") {
			$("#incomeType").removeClass("hidden");
			$('input[value="cash"].incomeType').prop('checked', true);
			$("#expenseType").addClass("hidden");
			$("#incomeCategory").removeClass("hidden");
			$("#expenseCategory").addClass("hidden");
			$("#bankBox").addClass("hidden")

		} else if (type == "expense") {
			$("#incomeType").addClass("hidden");
			$("#expenseType").removeClass("hidden");
			$('input[value="cash"].expenseType').prop('checked', true);
			$("#incomeCategory").addClass("hidden");
			$("#expenseCategory").removeClass("hidden");
			$("#bankBox").addClass("hidden")
		}
	})

	// 캐쉬유형별로 select 보이기
	$(".incomeType").on("change", function() {
		let type = $(".incomeType:checked").val();

		if(type == "cash") {
			$("#bankBox").addClass("hidden")
			$("#accountSelect").addClass("hidden");
			$("#cardSelect").addClass("hidden");
		} else if (type == "account") {
			$("#bankBox").removeClass("hidden")
			$("#accountSelect").removeClass("hidden");
			$("#cardSelect").addClass("hidden");
		}
	})
	$(".expenseType").on("change", function() {
		let type = $(".expenseType:checked").val();

		if(type == "cash") {
			$("#bankBox").addClass("hidden")
			$("#accountSelect").addClass("hidden");
			$("#cardSelect").addClass("hidden");
		} else if (type == "account") {
			$("#bankBox").removeClass("hidden")
			$("#accountSelect").removeClass("hidden");
			$("#cardSelect").addClass("hidden");
		} else if(type == "card") {
			$("#bankBox").removeClass("hidden")
			$("#accountSelect").addClass("hidden");
			$("#cardSelect").removeClass("hidden");
		}
	})






	// 금액에 숫자만 입력하기
	$("#amount").on("keyup", function(e) {
		let input = $(e.target).val();
		let sanitizedInput = input.replace(/\D/g, '');		
		$(e.target).val(sanitizedInput);
	})


})