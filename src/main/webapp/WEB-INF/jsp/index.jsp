<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Chatbox - Login</title>
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/interface/Login.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>
<script type="text/javascript">
	function login() {
		var userNameInput = dwr.util.byId('userName');
		var userName = userNameInput.value;
		Login.doLogin(userName, loginResult);
	}

	function loginResult(newPage) {
		window.location.href = newPage;
	}
</script>
</head>
<body>
  <h1>Chatbox - Login</h1>
  <sf:form servletRelativeAction="login" htmlEscape="true">
    <table style="border-collapse: collapse;">
      <tr>
        <td>User name:</td>
        <td><input type="text" name="userName" size="30" maxlength="30"></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td><input type="submit" value="Login"> <%-- onclick="login();return false;" --%></td>
      </tr>
    </table>
  </sf:form>
</body>
</html>