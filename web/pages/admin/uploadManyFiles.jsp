<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() +request.getContextPath()+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath %>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>多文件上传</title>
</head>
<body>
<center>
    <form action="<%=basePath %>/admin/uploadManyFiles.do" method="post" enctype="multipart/form-data">
        <table width="600px" border="1px" cellspacing="0px">
            <tr>
                <td>大头照:</td>
                <td><input type="file" multiple="multiple" name="wenjianS"></td>
            </tr>
            <tr>
                <td><input type="submit" value="上传"></td>
                <td><input type="reset" value="重置"></td>
            </tr>
        </table>
    </form>
</center>
</body>
</html>