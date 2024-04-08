$(function() {
	let accountInputBox = $(".accountInputBox");
	let accountSubmitBtn = $("#accountInputSubmitBtn");
	let accountInput = $("#accountInput");
	let expenseInputBox = $(".expenseInputBox");
	let expenseSubmitBtn = $("#expenseCategoryInputSubmitBtn");
	let expenseInput = $("#expenseCategoryInput");
	let checkedRadioValue = "";
	let checkedRadioIndex = -1;


	// 수입 카테고리 추가
	$("#accountAddBtn").on("click", function() {
		accountSubmitBtn.removeClass();
		accountSubmitBtn.addClass("accountAdd")
		accountSubmitBtn.text("추가");

		accountInputBox.removeClass("hidden")
	})

	$(document).on("click", ".accountAdd", function() {
		let account = accountInput.val();
		if(account.length <= 0) {
			alert("카테고리명을 입력해주세요.");
			return false;
		}

		$.ajax({
			"url" : "addAccount",
			"data" : {account : incomeCategory},
			"type" : "post",
			"dataType" : "text",
			"success" : function(resData) {
				console.log(resData);
				location.href='/category';
			},
			"error" : function(err) {
				console.log("err : ", err);
			}
		})
	})

	// 수입 카테고리 수정
	$("#incomeCategoryModifyBtn").on("click", function() {
		incomeSubmitBtn.removeClass();
		incomeSubmitBtn.addClass("incomeCategoryModify")
		incomeSubmitBtn.text("수정");

		incomeInputBox.removeClass("hidden")

		let checkedRadio = $(".incomeCategory:checked")
		checkedRadioValue = checkedRadio.val();
		console.log(checkedRadioValue);
		incomeInput.val(checkedRadioValue);
		checkedRadioIndex = checkedRadio.data("index");
	})

	$(document).on("click", ".incomeCategoryModify", function() {
		let incomeCategory = incomeInput.val();
		if(incomeCategory.length <= 0) {
			alert("카테고리명을 입력해주세요.");
			return false;
		}

		$.ajax({
			"url" : "modifyIncomeCategory",
			"data" : {
				category : incomeCategory,
				index : checkedRadioIndex
			},
			"type" : "post",
			"dataType" : "text",
			"success" : function(resData) {
				console.log(resData);
				location.href='/category';
			},
			"error" : function(err) {
				console.log("err : ", err);
			}
		})
	})


	// 수입 카테고리 삭제
	$("#incomeCategoryDeleteBtn").on("click", function() {
		let checkedRadio = $(".incomeCategory:checked")
		checkedRadioValue = checkedRadio.val();
		incomeInput.val(checkedRadioValue);
		checkedRadioIndex = checkedRadio.data("index");

		$.ajax({
			"url" : "deleteIncomeCategory",
			"data" : {
				category : checkedRadioValue,
				index : checkedRadioIndex
			},
			"type" : "post",
			"dataType" : "text",
			"success" : function(resData) {
				console.log(resData);
				location.href='/category';
			},
			"error" : function(err) {
				console.log("err : ", err);
			}
		})
	})






})