$(function() {

	let verifyNum = 0;
	let verifyCondition = $("#verifyCondition");

	$("#sendMailBtn").on("click", function() {
		verifyNum = Math.floor(100000 + Math.random() * 900000);

		let id =  $("#id").val();
		if(id.length <= 0) {
			alert("이메일을 입력해주세요.");
			return ;
		}
		if(!isValidEmail(id)) {
			alert("유효하지 않은 이메일 형식입니다.");
			return ;
		}

		$.ajax({
			"url" : "sendMail",
			"data" : {
				"id" : id,
				"verify" : verifyNum
			},
			"type" : "post",
			"dataType" : "text",
			"success" : function(resData) {
				console.log(resData);
				alert("이메일을 발송하였습니다.");
				$("#id").prop("disabled", true);
			},
			"error" : function(xhr, status, err) {
				console.log("err : ", err);
			}
		});

		$("#verifyBtn").on("click", function() {
			if($("#verify").val() != verifyNum) {
				alert("인증번호가 일치하지 않습니다.");
				verifyCondition.val(false);
			} else {
				alert("인증번호를 일치합니다.");
				verifyCondition.val(true);
			}
		});
	})





	

})