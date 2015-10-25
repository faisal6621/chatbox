package chatbox;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptSessions;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

public class Login
{
	public Login()
	{
	}

	public String doLogin( String userName )
	{
		UserDatabase userDb = UserDatabase.getInstance();
		if( !userDb.isUserLogged( userName ) )
		{
			userDb.login( userName );
			WebContext webContext = WebContextFactory.get();
			HttpServletRequest request = webContext.getHttpServletRequest();
			HttpSession session = request.getSession();
			session.setAttribute( "username", userName );
			String scriptId = webContext.getScriptSession().getId();
			session.setAttribute( "scriptSessionId", scriptId );
			updateUsersOnline();
			return "mainpage.jsp";
		}
		else
		{
			return "loginFailed.html";
		}
	}

	public void doLogout()
	{
		try
		{
			WebContext ctx = WebContextFactory.get();
			HttpSession session = ctx.getSession();
			String userName = Util.getCurrentUserName( session );
			UserDatabase.getInstance().logout( userName );
			session.removeAttribute( "username" );
			session.removeAttribute( "scriptSessionId" );
			session.invalidate();
		}
		catch ( Exception e )
		{
			System.out.println( e.toString() );
		}
		updateUsersOnline();
	}

	private void updateUsersOnline()
	{
		Browser.withAllSessions( new Runnable() {
			public void run()
			{
				ScriptSessions.addFunctionCall( "showUsersOnline" );
			}
		} );
	}

	public List<String> getUsersOnline()
	{
		UserDatabase userDb = UserDatabase.getInstance();
		return userDb.getLoggedInUsers();
	}
}
