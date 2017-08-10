<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<form:form action="edit" method="post" modelAttribute="person">
	Id:<form:input path="id" />
	<br>
	<br>
	name:<form:input path="name" />
  	<br>
  	<br>
  	sex:<form:input path="sex" />
  	<br>
  	email:<form:input path="email"/>
  	<br>
  	<br>
  	password:<form:input path="password" />
  	<br>
  	<br>
  	phone:<form:input path="phone" />
  	<br>
  	<br>
  	<input type="submit" name="sumbit" />
</form:form>
</body>
</html>