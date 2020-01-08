init.ready = function(){
	common.share.url="https://www.menupick.shop/amu/typeSelect.do";
	amugeona.btnEvent();
};

var amugeona = {
	nullList: ['TY026','TY027','TY028','TY029','TY030','TY031','TY032'],
	typeList: new Array(),
	btnEvent: function(){
		$(document).on('click','label[name=typeBtn]',function(e){
			$('label[name=typeBtn]').removeClass('on');
			$(this).addClass('on');
			$("#"+$(this).attr("for")).attr("checked",true);

			var data = $('input[name=typeRadioBox]:checked').val();
			var paramData = {
				typeCd:data.split('_')[0],
				category:data.split('_')[1],
				step:data.split('_')[2],
			}
			
			amugeona.typeList.push(paramData);
			
			if(paramData.category == 'CT009'){// 마지막 타입일 때
				var typeData = '';
				var categoryData = '';
				var stepData = '';
				for(var i = 0;i<amugeona.typeList.length;i++){
					if(amugeona.nullList.indexOf(amugeona.typeList[i].typeCd) == -1){
						typeData += amugeona.typeList[i].typeCd+'_';
						categoryData += amugeona.typeList[i].category+'_';
						stepData += amugeona.typeList[i].step+'_';
					}
					
				}
				$("#typeData").val(typeData);
				$("#categoryData").val(categoryData);
				$("#stepData").val(stepData);
				common.loading.start();
				$("#frm").submit();
				
				return;
			} 

			common.ajax('/amu/ajaxTypeList.do',paramData,amugeona.typeSuccessFn, amugeona.typeErrorFn);
		});
		
		$(document).on('click','#nextBtn',function(e){
			if(!$('input[name=typeRadioBox]').is(':checked')){
				common.alertPop("","메뉴를 선택해 주세요.");
				return;
			}
			var data = $('input[name=typeRadioBox]:checked').val();
			var paramData = {
				typeCd:data.split('_')[0],
				category:data.split('_')[1],
				step:data.split('_')[2],
			}
			
			amugeona.typeList.push(paramData);
			
			if(paramData.category == 'CT009'){// 마지막 타입일 때
				var typeData = '';
				var categoryData = '';
				var stepData = '';
				for(var i = 0;i<amugeona.typeList.length;i++){
					if(amugeona.nullList.indexOf(amugeona.typeList[i].typeCd) == -1){
						typeData += amugeona.typeList[i].typeCd+'_';
						categoryData += amugeona.typeList[i].category+'_';
						stepData += amugeona.typeList[i].step+'_';
					}
					
				}
				$("#typeData").val(typeData);
				$("#categoryData").val(categoryData);
				$("#stepData").val(stepData);
				common.loading.start();
				$("#frm").submit();
				
				return;
			} 

			common.ajax('/amu/ajaxTypeList.do',paramData,amugeona.typeSuccessFn, amugeona.typeErrorFn);
		})
		
		$(document).on('click','#preBtn',function(e){

			var data = $('input[name=typeRadioBox]:checked').val();

			amugeona.typeList.pop();
			if(amugeona.typeList.length == 0){
				common.ajax('/amu/ajaxTypeList.do',{category:'CT001'},amugeona.typeSuccessFn, amugeona.typeErrorFn);
			}else{
				var paramData = amugeona.typeList[amugeona.typeList.length-1];

				common.ajax('/amu/ajaxTypeList.do',paramData,amugeona.typeSuccessFn, amugeona.typeErrorFn);
			}
			
		})
	},
	typeSuccessFn : function(data){ // type 데이터 전송 완료 후 새로운 type 데이터 받음
		var list = data.list;
		
		$('.type-contents').empty();
		$("#btn-list").empty();

		var html1 = '';
		var html2 = '';
		for(var i = 0;i<list.length;i++){
			html1 += html.typeList(i,list[i].TYPE_CATEGORY,list[i].PARENT_CATEGORY,list[i].TYPE_CD,list[i].CODE_NAME,list[i].GROUP_ID);
		}
		if(list.length%2 == 1) html1 += html.nullBtn();
		
		if(amugeona.typeList.length != 0){
			html2 += html.preBtn();
		}
		$("#typeTitle").html(list[0].TITLE_NAME);
		$('.type-contents').append(html1);
		$(".type-contents").hide();
		$(".type-contents").show('drop',{},500);
		$("#btn-list").append(html2);
	},
	typeErrorFn : function(data){
		
	},
	foodSuccessFn : function(data){
		
	},
	foodErrorFn : function(data){
		
	}

}

var html = {
	typeList : function(idx, typeCategory, parentCategory, typeCd, codeName, step){
		var html = '';
		html += '<label class="button style2 scrolly typeBtn" name="typeBtn" for="'+parentCategory+idx+'">'+codeName+'</label>';
		html += '<input type="radio" class="hidden" name="typeRadioBox" id="'+parentCategory+idx+'" value="'+typeCd+'_'+typeCategory+'_'+step+'">';
		return html;
	},
	nullBtn : function(){
		var html = '<label class="button scrolly " id="noneTypeBtn">null</label>';
		return html;
	},
	nextBtn : function(){
		var html = '<label class="button scrolly rightBtn" id="nextBtn">다음 ></label>';
		return html;
	},
	preBtn : function(){
		var html = '<label class="button scrolly leftBtn" id="preBtn">< 이전</label>';
		return html;
	},
	data : function(id, value){
		var html = '';
		return html;
	}
}


	