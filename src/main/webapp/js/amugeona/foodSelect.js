init.ready = function(){
	food.click();
}

var food = {
	click : function(){
		$("#nextBtn").on("click",function(){
			$("#frm").submit();
		});
	}
}