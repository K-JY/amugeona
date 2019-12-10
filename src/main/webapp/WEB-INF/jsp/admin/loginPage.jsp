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
<script>
	init.ready = function(){
		$("#submit").on("click", function(){
			common.ajax('/admin/login.do',{id : $("#id").val(), pw : $("#pw").val()},admin.successFn, admin.errorFn);
		});
		
	}
	
	var admin = {
		successFn : function(data){
			if(data.result == true){
				location.href = "/admin/menu.do";
			}
		},
		errorFn : function(){
			
		}
	}
</script>
<body>
id : <input type="text" id="id" name="id" value=""/>
pw : <input type="password" id="pw" name="pw" value=""/>
<button id="submit">login</button>
</body>
</html>