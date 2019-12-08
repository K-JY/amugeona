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
		var stepId = '';
		$("select").on("change",function(){
			stepId = 'step0'+$(this).attr("id").replace("step0","");
			common.ajax('/admin/foodSelect.do',{
				foodCd : $(this).val()
			}, function(data){
				if(data.result){
					var html = '';
					for(var i = 0;i<data.list.length;i++){
						html += '<option value="'+data.list[i].CODE_ID+'">'+data.list[i].CODE_NAME+'</option>';
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
				step01:$("#step01 option:selected").val(),
				step02:$("#step02 option:selected").val(),
				step03:$("#step03 option:selected").val(),
				step04:$("#step04 option:selected").val(),
				step05:$("#step05 option:selected").val(),
				step06:$("#step06 option:selected").val()
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
		successFn : function(data){
			
		},
		errorFn : function(){
			
		}
	}
</script>
<h2>음식관리</h2>
음식 이름 : <input type="text" id="foodName" name="foodName"/>
step01 : 
<select id="step01">
</select>
step02 : 
<select id="step02">
</select>
step03 : 
<select id="step03">
</select>
step04 : 
<select id="step04">
</select>
step05 : 
<select id="step05">
</select>
step06 : 
<select id="step06">
</select>
<button id="insertBtn" value="등록"></button>

</body>
</html>