<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Chatbox</title>
<link href="css/chat.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/interface/Login.js'></script>
<script type='text/javascript' src='dwr/interface/ChatRoomDatabase.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>
<script type="text/javascript">
	dwr.engine.setActiveReverseAjax(true);

	function logout() {
		Login.doLogout(showLoginScreen);
	}

	function showLoginScreen() {
		window.location.href = 'logout';
	}

	function showUsersOnline() {
		var cellFuncs = [ function(user) {

			return '<i>' + user + '</i>';
		} ];
		Login.getUsersOnline({
			callback : function(users) {
				dwr.util.removeAllRows('usersOnline');
				dwr.util.addRows("usersOnline", users, cellFuncs, {
					escapeHtml : false
				});
			}
		});
	}

	function getPreviousMessages() {
		ChatRoomDatabase.getChatContent({
			callback : function(messages) {
				var chatArea = dwr.util.byId('chatArea');
				var html = "";
				for (index in messages) {
					var msg = messages[index];
					html += msg;
				}
				chatArea.innerHTML = html;
				var chatAreaHeight = chatArea.scrollHeight;
				chatArea.scrollTop = chatAreaHeight;
			}
		});

	}

	function newMessage(message) {
		var chatArea = dwr.util.byId('chatArea');
		var oldMessages = chatArea.innerHTML;
		chatArea.innerHTML = oldMessages + message;
		var chatAreaHeight = chatArea.scrollHeight;
		chatArea.scrollTop = chatAreaHeight;
	}

	function sendMessageIfEnter(event) {
		if (event.keyCode == 13) {
			sendMessage();
		}
	}

	function sendMessage() {
		var message = dwr.util.byId('messageText');
		var messageText = message.value;
		ChatRoomDatabase.postMessage(messageText);
		message.value = '';
	}
</script>
</head>
<body onload="showUsersOnline();">
  <div id="maincontainer">
    <div id="topsection">
      <div class="innertube">
        <h1>Chatbox</h1>
        <h4>
          Welcome <i><%=(String) session.getAttribute("userName")%></i>
        </h4>
      </div>
    </div>
    <div id="contentwrapper">
      <div id="contentcolumn">
        <div id="chatArea" style="width: 600px; height: 300px; overflow: auto"></div>
        <div id="inputArea">
          <h4>Send message</h4>
          <input id="messageText" type="text" size="50" onkeyup="sendMessageIfEnter(event);"><input type="button" value="Send msg"
            onclick="sendMessage();">
        </div>
      </div>
    </div>
    <div id="leftcolumn">
      <div class="innertube">
        <table style="border-collapse: collapse;">
          <thead>
            <tr>
              <td><b>Users online</b></td>
            </tr>
          </thead>
          <tbody id="usersOnline">
          </tbody>
        </table>
        <input id="logoutButton" type="button" value="Logout" onclick="location.href='logout';"><%-- logout();return false; --%>
      </div>
    </div>
    <div id="footer"></div>
  </div>
  <script type="text/javascript">
			getPreviousMessages();
		</script>
</body>
</html>
