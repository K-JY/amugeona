<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>admin</title>
<script type="text/javascript" src="/js/lib/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="/js/lib/snowfall.jquery.js"></script>
<script type="text/javascript" src="/js/init.js"></script>
</head>
<body>
<script>
	init.ready = function(){
		admin.init();
		var stepId = '';
		$("select").on("change",function(){
			stepId = 'step0'+(($(this).attr("id").replace("step0","")*1)+1);
			common.ajax('/admin/ajaxFoodSelect.do',{
				foodCd : $("#"+$(this).attr("id")+" option:selected").val().split("|")[1]
			}, function(data){
				if(data.result){
					var html = '<option value="">선택</option>';
					for(var i = 0;i<data.list.length;i++){
						var value = data.list[i].TYPE_CD+'|'+data.list[i].TYPE_CATEGORY+'|'+data.list[i].PARENT_CATEGORY;
						html += '<option value="'+value+'">'+data.list[i].CODE_NAME+'</option>';
					}
					$("#"+stepId).empty();
					$("#"+stepId).append(html);
				}
				
			}, function(data){
				
			});
		});
		
		$("#insertBtn").on("click",function(){
			common.ajax('/admin/foodInsert.do',{
				foodName : $(this).val(), 
				list : [
					{typeCd : $("#step01 option:selected").val().split("|")[0]},
					{typeCd : $("#step02 option:selected").val().split("|")[0]},
					{typeCd : $("#step03 option:selected").val().split("|")[0]},
					{typeCd : $("#step04 option:selected").val().split("|")[0]},
					{typeCd : $("#step05 option:selected").val().split("|")[0]},
					{typeCd : $("#step06 option:selected").val().split("|")[0]}
				]
			},function(data){
				if(data.result){
					alert("성공");
				}else{
					alert("실패");
				}
			}, function(data){
				
			});
		})
	}
	
	var admin = {
		init : function(){
			var stepId = 
			stepId = 'step01';
			common.ajax('/admin/ajaxFoodSelect.do',{
				foodCd : 'CT001'
			}, function(data){
				if(data.result){
					var html = '<option value="">선택</option>';
					for(var i = 0;i<data.list.length;i++){
						var value = data.list[i].TYPE_CD+'|'+data.list[i].TYPE_CATEGORY+'|'+data.list[i].PARENT_CATEGORY;
						html += '<option value="'+value+'">'+data.list[i].CODE_NAME+'</option>';
					}
					$("#"+stepId).empty();
					$("#"+stepId).append(html);
				}
				
			}, function(data){
				
			});
		}
	}
</script>
<h2>음식관리</h2>
<form action="/admin/foodInsert.do", method="post" enctype="multipart/form-data">
    
음식 이름 : <input type="text" id="foodName" name="foodName"/>
<br/>
step01 : 
<select id="step01" name="step01">
</select>
<br/>
step02 : 
<select id="step02" name="step02">
</select>
<br/>
step03 : 
<select id="step03" name="step03">
</select>
<br/>
step04 : 
<select id="step04" name="step04">
</select>
<br/>
step05 : 
<select id="step05" name="step05">
</select>
<br/>
step06 : 
<select id="step06" name="step06">
</select>
<input type="file", name="uploadfile" placeholder="파일 선택" /><br/>
<input type="submit" value="업로드">
</form>
</body>
</html>