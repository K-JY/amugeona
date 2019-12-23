var init = {
	before : function(){
		if (document.location.protocol == 'http:' || (window.location.host != 'www.menupick.shop' && window.location.host != 'localhost')) {
			document.location.href = 'https://www.menupick.shop' + window.location.pathname
	    }

		common.share.init();
		$(document).snowfall({
			minSize : 5,
			maxSize : 10,
			maxSpeed : 1,
			misSpeed : 1,
			round : true
		});
		
		if($("#device").val() == "W"){
			common.alertPop("최적화 안내","해당 웹사이트는 모바일에 최적화 되어있습니다.");
		}
		this.btnEvent();
		
	},
	ready : function(){
		
	},
	after : function(){
		
	},
	btnEvent : function(){
		// 로고 선택시 메인화면
		$("#mainTitle").on("click",function(){
			location.href = "/main.do"; 
		});
		// 공유버튼 클릭시 공유화면
		$("#shareBtn").on("click",function(){
			$(".share-bag").show('blind',{},500);
			$(".share-box").show('blind',{},500);
			$("body").addClass("disable");
		});
		// 뒤로가기 클릭 이벤트
		$("#backBtn").on("click", function(){
			 if(common.backUrl == null){
				 location.href = "/main.do";
				 return;
			 }
			 
			 common.backUrl();
		});
		// 공유하기 화면 닫기 
		$(".share-bag").on("click",function(){
			$(".share-bag").hide('fold',{},500);
			$(".share-box").hide('drop',{},500);
			$("body").removeClass("disable");
		});
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
			$("body").addClass("disable");
			$(document).on("click","#popupConfirmBtn",function(){
				if(confirmFn != null){
					confirmFn();
				}
				$(".dim-layer").remove();
				$("body").removeClass("disable");
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
		share : {
			url : "https://www.menupick.shop",
			init : function(){
				if($("#shareBtn").length != 0){
					this.kakao();
					this.facebook();
					this.naver();
					this.twitter();
				}
				
			},
			kakao : function(){
				Kakao.init('8ef7de72f55e391696ebbe3bdbb83354');
			    // // 카카오링크 버튼을 생성합니다. 처음 한번만 호출하면 됩니다.
			    Kakao.Link.createDefaultButton({
			    	
			    	
			    	container: '#kakaoBtn',
			    	objectType: 'feed',
			    	content: {
			    		title: '무엇을 먹을지 고민이라면? 아무거나!',
			    		description: '#아무거나 #결정장애 #뭐먹지',
			    		imageUrl: 'https://www.menupick.shop/images/kakaoMain.png',
			    		link: {
			    			mobileWebUrl: common.share.url
			    		}
			    	},
			    	buttons: [
				        {
				        	title: '웹으로 보기',
				        	link: {
				        		mobileWebUrl: common.share.url
				        	}
				        }
			        ]
			    });
			},
			facebook : function(){
				$("#facebookBtn").on("click",function(){
		    		var url = common.share.url;
		    		var encodeUrl = encodeURIComponent(url);
		        	var facebook = 'https://www.facebook.com/sharer/sharer.php?u=';
		        	var link = facebook + encodeUrl;
		    		window.open(link);
		    	});
			},
			naver : function(){
				$("#naverBtn").on("click",function(){
		    		var url = common.share.url;
		    		var encodeUrl = encodeURIComponent(url);
		        	var naver = 'https://share.naver.com/web/shareView.nhn?url=';
		        	var link = naver + encodeUrl+'&title=아무거나';
		    		window.open(link);
		    	});
			},
			twitter : function(){
				$("#twitterBtn").on("click",function(){
		    		var url = common.share.url;
		    		var encodeUrl = encodeURIComponent(url);
		        	var twitter = 'https://twitter.com/intent/tweet?title=아무거나&url=';
		        	var link = twitter + encodeUrl;
		    		window.open(link);
		    	});
			}
		}
	}

$(document).ready(function(){
	init.before();
	init.ready();
	init.after();
});
	