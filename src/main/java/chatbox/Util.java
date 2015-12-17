package chatbox;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

public class Util
{
	public Util()
	{
	}

	public static String getCurrentUserName()
	{
		//get user name from session
		WebContext ctx = WebContextFactory.get();
		HttpServletRequest request = ctx.getHttpServletRequest();
		HttpSession session = request.getSession();
		return getCurrentUserName( session );
	}

	public static String getCurrentUserName( HttpSession session )
	{
		String userName = (String) session.getAttribute( "userName" );
		return userName;
	}
}
