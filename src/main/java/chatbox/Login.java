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
		if( !UserDatabase.isUserLogged( userName ) )
		{
			UserDatabase.login( userName );
			WebContext webContext = WebContextFactory.get();
			HttpServletRequest request = webContext.getHttpServletRequest();
			HttpSession session = request.getSession();
			session.setAttribute( "username", userName );
			webContext.getScriptSession().setAttribute( "user", userName );
			String scriptId = webContext.getScriptSession().getId();
			session.setAttribute( "scriptSessionId", scriptId );
			updateUsersOnline();
			return "mainpage";
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
			UserDatabase.logout( userName );
			session.removeAttribute( "userName" );
			session.removeAttribute( "scriptSessionId" );
			session.invalidate();
		}
		catch ( Exception e )
		{
			e.printStackTrace();
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
		return UserDatabase.getLoggedInUsers();
	}

	public void updateUserSession()
	{
		System.out.println( "in update user session" );
		try
		{
			WebContext context = WebContextFactory.get();
			HttpSession session = context.getSession();
			String username = Util.getCurrentUserName( session );
			context.getScriptSession().setAttribute( "user", username );
		}
		catch ( Exception ex )
		{
			ex.printStackTrace();
		}
	}
}
