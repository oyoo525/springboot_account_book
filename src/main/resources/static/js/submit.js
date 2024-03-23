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
		if($("#id").val().length <= 0) {
			alert("로그인이 되지 않았습니다.");
			return false;
		}
		if($("#category").val() === "0") {
			alert("카테고리를 선택해주세요.");
			return false;
		}
		if($("#amount").val() <= 0) {
			alert("금액을 입력해주세요.");
			return false;
		}
		if($("#account").val().length <= 0) {
			alert("거래처를 입력해주세요.");
			return false;
		}

		event.preventDefault();

		let data = {
			"id" : $("#id").val(),
			"type" : $(".type:checked").val(),
			"category" : $("#category").val(),
			"year" : $("#date").val().split("-")[0],
			"month" : $("#date").val().split("-")[1],
			"date" : $("#date").val().split("-")[2],
			"amount" : $("#amount").val().replace(",", ""),
			"account" :  $("#account").val(),
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