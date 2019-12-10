var init = {
	before : function(){
		$(document).snowfall({
			minSize : 5,
			maxSize : 10,
			maxSpeed : 1,
			misSpeed : 1,
			round : true
		});
	},
	ready : function(){
		
	},
	after : function(){
		
	}
	
}

var common = {
		ajax : function(url, data, successFn, errorFn){
			$.ajax({
				method : 'POST',
				url : url,
				dataType: 'json',
			    contentType:'application/json',
				data : JSON.stringify(data),
				error : function(){
					if(errorFn){
						errorFn(data);
					}
				},
				success : function(data){
					if(successFn){
						successFn(data);
					}
				}
				
			})
		},
		ajaxFile : function(url, data, successFn, errorFn){
			$.ajax({
				method : 'POST',
				url : url,
				dataType: 'json',
			    processData : false,
		        contentType : false,
				data : data,
				error : function(){
					if(errorFn){
						errorFn(data);
					}
				},
				success : function(data){
					if(successFn){
						successFn(data);
					}
				}
				
			})
		}
	}

$(document).ready(function(){
	init.before();
	init.ready();
	init.after();
});
	