<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>타이틀</title>
</head>
<body>
      <form method="post" action="/gb/delete">
      <input type='hidden' name="no" value="${param.no }"><!-- name하고 vo의 이름하고 같아야 한다. -->
      <table width=410 border=1>
            <tr>
                 <td >비밀번호</td>
                 <td ><input type="password" name="password" ></td> <!-- name하고 vo의 이름하고 같아야 한다. -->
                 <td colspan=4 align=right><input type="submit" value="확인. "></td>
                 <td><a href="/gb/list">메인으로 돌아가기</a></td>
           </tr>
      </table>
      </form>

</body>
</html>