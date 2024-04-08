$(function() {
	let incomeInputBox = $(".incomeInputBox");
	let incomeSubmitBtn = $("#incomeCategoryInputSubmitBtn");
	let incomeInput = $("#incomeCategoryInput");
	let expenseInputBox = $(".expenseInputBox");
	let expenseSubmitBtn = $("#expenseCategoryInputSubmitBtn");
	let expenseInput = $("#expenseCategoryInput");
	let checkedRadioValue = "";
	let checkedRadioIndex = -1;

	// 수입 카테고리 추가
	$("#incomeCategoryAddBtn").on("click", function() {
		incomeSubmitBtn.removeClass();
		incomeSubmitBtn.addClass("incomeCategoryAdd")
		incomeSubmitBtn.text("추가");

		incomeInputBox.removeClass("hidden")
	})

	$(document).on("click", ".incomeCategoryAdd", function() {
		let incomeCategory = incomeInput.val();
		if(incomeCategory.length <= 0) {
			alert("카테고리명을 입력해주세요.");
			return false;
		}

		$.ajax({
			"url" : "addIncomeCategory",
			"data" : {category : incomeCategory},
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


	// 지출 카테고리 추가
	$("#expenseCategoryAddBtn").on("click", function() {
		expenseSubmitBtn.removeClass();
		expenseSubmitBtn.addClass("expenseCategoryAdd")
		expenseSubmitBtn.text("추가");

		expenseInputBox.removeClass("hidden")
	})

	$(document).on("click", ".expenseCategoryAdd", function() {
		let expenseCategory = expenseInput.val();
		if(expenseCategory.length <= 0) {
			alert("카테고리명을 입력해주세요.");
			return false;
		}

		$.ajax({
			"url" : "addExpenseCategory",
			"data" : {category : expenseCategory},
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

	// 지출 카테고리 수정
	$("#expenseCategoryModifyBtn").on("click", function() {
		expenseSubmitBtn.removeClass();
		expenseSubmitBtn.addClass("expenseCategoryModify")
		expenseSubmitBtn.text("수정");

		expenseInputBox.removeClass("hidden")

		let checkedRadio = $(".expenseCategory:checked")
		checkedRadioValue = checkedRadio.val();
		console.log(checkedRadioValue);
		expenseInput.val(checkedRadioValue);
		checkedRadioIndex = checkedRadio.data("index");
	})

	$(document).on("click", ".expenseCategoryModify", function() {
		let expenseCategory = expenseInput.val();
		if(expenseCategory.length <= 0) {
			alert("카테고리명을 입력해주세요.");
			return false;
		}

		$.ajax({
			"url" : "modifyExpenseCategory",
			"data" : {
				category : expenseCategory,
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

	// 지출 카테고리 삭제
	$("#expenseCategoryDeleteBtn").on("click", function() {
		let checkedRadio = $(".expenseCategory:checked")
		checkedRadioValue = checkedRadio.val();
		expenseInput.val(checkedRadioValue);
		checkedRadioIndex = checkedRadio.data("index");

		$.ajax({
			"url" : "deleteExpenseCategory",
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