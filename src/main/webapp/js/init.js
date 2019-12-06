var init = {
	before : function(){
		$(document).snowfall({
			minSize : 5,
			maxSize : 10,
			round : true
		});
	},
	ready : function(){
		
	},
	after : function(){
		
	}
	
}

$(document).ready(function(){
	init.before();
	init.ready();
	init.after();
});
	