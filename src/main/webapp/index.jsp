<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book Authoring</title>
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
  <h1>Book Authoring Sample</h1>
  <table style="border-collapse: collapse;">
    <tr>
      <td>User name:</td>
      <td><input id="userName" type="text" size="30"></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><input type="button" value="Login" onclick="login();return false;"></td>
    </tr>
  </table>
</body>
</html>