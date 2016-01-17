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
		//get user name from UserPrincipal
		WebContext ctx = WebContextFactory.get();
		HttpServletRequest request = ctx.getHttpServletRequest();
		String user = null;
		if( request.getUserPrincipal() != null )
		{
			user = request.getUserPrincipal().getName();
		}
		return user;
	}

	@Deprecated
	public static String getCurrentUserName( HttpSession session )
	{
		String userName = (String) session.getAttribute( "userName" );
		return userName;
	}
}
