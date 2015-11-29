package chatbox;

import java.util.List;
import java.util.Vector;

public class UserDatabase
{
	private static List<String>	loggedInUsers	= new Vector<String>();

	public static List<String> getLoggedInUsers()
	{
		return loggedInUsers;
	}

	public static boolean isUserLogged( String userName )
	{
		return loggedInUsers.contains( userName );
	}

	public static void login( String userName )
	{
		loggedInUsers.add( userName );
	}

	public static void logout( String userName )
	{
		loggedInUsers.remove( userName );
	}
}
