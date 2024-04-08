$(function() {

	// 회원가입
	$("#join_form").on("submit", function(event) {
		if($("#verifyCondition").val() == "false") {
			alert("인증이 왼료되지 않았습니다.");
			return false;	
		}
		if($("#pass").val().length <= 0) {
			alert("비밀번호가 입력되지 않았습니다.");
			return false;
		}
		event.preventDefault();
		
		let data = {
			id : $("#id").val(),
			pass : $("#pass").val()
		}

		$.ajax({
			"url" : "join",
			"data" : data,
			"type" : "post",
			"dataType" : "json",
			"success" : function(resData) {
				console.log(resData);
				location.href='/';
			},
			"error" : function(xhr, status, err) {
				console.log(xhr, "-", status, ", err :", err);
			}
		})
	})

	// 로그인
	$("#login_form").on("submit", function(event) {
		let id = $("#id").val();
		let pass = $("#pass").val();
		
		if(id.length <= 0) {
			alert("아이디가 입력되지 않았습니다.");
			return false;
		}
		if(pass.length <= 0) {
			alert("비밀번호가 입력되지 않았습니다.");
			return false;
		}
		
		event.preventDefault();

		$.ajax({
			"url" : "login",
			"data" : {
				id : id,
				pass : pass
			},
			"type" : "post", 
			"dataType" : "text",
			"success" : function(resData) {
				console.log("result : ", resData);

				if(resData.secretKey == "") {
					alert("회원정보가 일치하지 않습니다.");
					return false;
				} else {
					location.href='/';
				}

			},
			"error" : function(xhr, status, err) {
				console.log(xhr, '-', status, '-', err);
			}
		})
	})

	// 가계부 추가하기
	$("#account_form").on("submit", function(event) {
		if($("#amount").val() <= 0) {
			alert("금액을 입력해주세요.");
			return false;
		}

		event.preventDefault();

		let type = $(".type:checked").val();
		let type2 = "";
		let category = "";
		let bank = "";
		
		if(type == "income") {
			type2 = $(".incomeType:checked").val();
			category = $("#incomeCategory").val();
		} else if(type == "expense") {
			type2 = $(".expenseType:checked").val();
			category = $("#expenseCategory").val();
		}

		if(type2 == "cash") {
			bank = "";
		} else if(type2 == "account") {
			bank = $("#accountSelect").val();
		} else if(type2 == "card") {
			bank = $("#cardSelect").val();
		}
		
		console.log(type2, ":", bank);

		let data = {
			"date" : $("#date").val(),
			"type" : type,
			"type2" : type2,
			"bankName" : bank,
			"category" : category,
			"account" :  $("#account").val(),
			"amount" : $("#amount").val().replace(",", ""),
			"memo" : $("#memo").val()
		}

		$.ajax({
			"url" : "account",
			"data" : JSON.stringify(data),
			"type" : "post",
			"dataType" : "JSON",
			"contentType" : "application/json; charset=UTF=8",
			"success" : function(resData) {
				console.log(resData);
				location.href='/';
			},
			"error" : function(xhr, status, err) {
				console.log(xhr, '-', status, '-', err);
			}
		})
	})













})