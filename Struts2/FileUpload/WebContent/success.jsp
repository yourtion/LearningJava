<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>File Uploaded</title>
</head>
<body>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>

upload succeed!<br/>
uploader:<s:property value=" + uploader"/><br/>
file name:<s:property value="+ uploadFileName"/><br/>
file type:<s:property value="+ uploadContentType"/><br/>
file address:<p> <%=basePath %><s:property value="'uploadFiles/' + uploadFileName"/></p><br/>  
</body>
</html>