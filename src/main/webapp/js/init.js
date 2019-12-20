var init = {
	before : function(){
/*		if (document.location.protocol == 'http:' || window.location.host != 'www.menupick.shop' || window.location.host != 'localhost') {
			document.location.href = 'https://www.menupick.shop' + window.location.pathname
	    }*/
		common.cookieCheck();
		
		$(document).snowfall({
			minSize : 5,
			maxSize : 10,
			maxSpeed : 1,
			misSpeed : 1,
			round : true
		});
		
		$(document).on("click","#mainTitle",function(){
			location.href = "/main.do"; 
		});
		
		$(document).on("click","#homeBtn",function(){
			common.kakaoShare();
		});
		
		$(document).on("click","#backBtn", function(){
			 if(common.backUrl == null){
				 location.href = "/main.do";
				 return;
			 }
			 
			 common.backUrl();
		});
	},
	ready : function(){
		
	},
	after : function(){
		
	}
	
}

var common = {
		backUrl : null,
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
		},
		alertPop : function(titls, content, confirmFn){
			var html = "";
			html += '<div class="dim-layer" style="display: block;">';
			html += '<div class="dimBg"></div>';
			html += '<div id="layer2" class="pop-layer-alert">';
			html += '<div class="pop-container pop-container-alert">';
			html += '<div class="pop-conts">';
			html += titls;
			html += '<br/>';
			html += '<p style="margin-top: 40px;margin-bottom: 0px;font-size: 15px;">'+content+'</p>';
			html += '<br/>';
			html += '</div>';
			html += '</div>';
			html += '<label class="button scrolly style2 on firstBtn" id="popupConfirmBtn" style="font-size: 15px;">확인</label>';
			html += '</div>';
			html += '</div>';
			
			$("#footer").after(html);
			
			$(document).on("click","#popupConfirmBtn",function(){
				if(confirmFn != null){
					confirmFn();
				}
				$(".dim-layer").remove();
			});
		},
		cookieCheck : function(){
			this.getCookieValue('cookieId')
			if(this.getCookieValue('cookieId') == ""){
				var random = Math.floor(Math.random() * 9999);
				var date = new Date();
			    date.setDate(date.getDate());
			    
			    var willCookie = "";
			    willCookie += "cookieId=" + date.getTime()+random;
			    
			    document.cookie = willCookie;
			}
			
		},
		getCookieValue : function(key){
		  let cookieKey = key + "="; 
		  let result = "";
		  const cookieArr = document.cookie.split(";");

		  for(let i = 0; i < cookieArr.length; i++) {
		    if(cookieArr[i][0] === " ") {
		      cookieArr[i] = cookieArr[i].substring(1);
		    }

		    if(cookieArr[i].indexOf(cookieKey) === 0) {
		      result = cookieArr[i].slice(cookieKey.length, cookieArr[i].length);
		      return result;
		    }
		  }
		  return result;
		},
		kakaoShare : function(){
			
		}
	}

$(document).ready(function(){
	init.before();
	init.ready();
	init.after();
});
	