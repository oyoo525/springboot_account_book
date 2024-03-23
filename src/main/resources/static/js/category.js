$(function() {
	let incomeInputBox = $(".incomeInputBox");
	let incomeSubmitBtn = $("#incomeCategoryInputSubmitBtn");
	let incomeInput = $("#incomeCategoryInput");
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
		alert("hi")
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
				// location.href='/category';
			},
			"error" : function(err) {
				console.log("err : ", err);
			}
		})
	})




})