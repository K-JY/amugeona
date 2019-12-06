var amugeona = {
	typeList: new Array(),
	btnEvent: function(){
		$(document).on('click','label[name=typeBtn]',function(e){
			$('label[name=typeBtn]').removeClass('on');
			$(this).addClass('on');
		});
		
		$(document).on('click','#nextBtn',function(e){
			if(!$('input[name=typeRadioBox]').is(':checked')){
				alert('메뉴를 선택해 주세요.');
				return;
			}
			var data = $('input[name=typeRadioBox]:checked').val();
			var paramData = {
				typeCd:data.split('|')[0],
				category:data.split('|')[1],
				step:data.split('|')[2],
			}
			
			amugeona.typeList.push(paramData);
			
			if(paramData.category == 'CT009'){// 마지막 타입일 때
				var typeData = '';
				var categoryData = '';
				var stepData = '';
				for(var i = 0;i<amugeona.typeList.length;i++){
					typeData += amugeona.typeList[i].typeCd+'|';
					categoryData += amugeona.typeList[i].category+'|';
					stepData += amugeona.typeList[i].step+'|';
				}
				$("#typeData").val(typeData);
				$("#categoryData").val(categoryData);
				$("#stepData").val(stepData);
				$("#frm").submit();
				
				return;
			} 

			common.ajax('/amu/ajaxTypeList.do',paramData,amugeona.typeSuccessFn, amugeona.typeErrorFn);
		})
	},
	typeSuccessFn : function(data){ // type 데이터 전송 완료 후 새로운 type 데이터 받음
		var list = data.list;
		
		$('#type-contents').empty();
		$("#btn-list").empty();

		var html1 = '';
		var html2 = '';
		for(var i = 0;i<list.length;i++){
			html1 += html.typeList(i,list[i].TYPE_CATEGORY,list[i].PARENT_CATEGORY,list[i].TYPE_CD,list[i].CODE_NAME,list[i].GROUP_ID);
		}
		if(list.length%2 == 1) html1 += html.nullBtn();
		
		html2 = html.nextBtn();
		$('#type-contents').append(html1);
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
		html += '<input type="radio" class="hidden" name="typeRadioBox" id="'+parentCategory+idx+'" value="'+typeCd+'|'+typeCategory+'|'+step+'">';
		return html;
	},
	nullBtn : function(){
		var html = '<label class="button scrolly " id="noneTypeBtn">null</label>';
		return html;
	},
	nextBtn : function(){
		var html = '<label class="button scrolly" id="nextBtn" name="typeBtn">다음 ></label>';
		return html;
	},
	preBtn : function(){
		var html = '';
		return html;
	},
	data : function(id, value){
		var html = '';
		return html;
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
	}
}

$(document).ready(function(){
	amugeona.btnEvent();
})