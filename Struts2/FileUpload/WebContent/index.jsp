<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Upload your file</title>
</head>
<body>
    <form action="upload" method="post" enctype="multipart/form-data">
    uploader:<input type="text" name="uploader">
    select file:<input type="file" name="upload">
    <input type="submit" value="Upload">  
    </form>  
</body>
</html>