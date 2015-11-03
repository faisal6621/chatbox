package chatbox;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptSessions;

public class ChatRoomDatabase
{
	private static List<String>	chatContent	= new Vector<String>();

	public ChatRoomDatabase()
	{
	}

	public void postMessage( String message )
	{
		String user = Util.getCurrentUserName();
		if( user != null )
		{
			Date time = new Date();
			StringBuffer sb = new StringBuffer();
			sb.append( time.toString() );
			sb.append( " <b><i>" );
			sb.append( user );
			sb.append( "</i></b>:  " );
			sb.append( message );
			sb.append( "<br/>" );
			String newMessage = sb.toString();
			chatContent.add( newMessage );
			postNewMessage( newMessage );
		}
	}

	public List<String> getChatContent()
	{
		return chatContent;
	}

	public void postNewMessage( final String newMessage )
	{
		//		Browser.withAllSessions( new Runnable() {
		//			public void run()
		//			{
		//				ScriptSessions.addFunctionCall( "newMessage", newMessage );
		//			}
		//		} );
		UserScriptSessionFilter filter = new UserScriptSessionFilter( "user", "faisal" );
		Browser.withAllSessionsFiltered( filter, new Runnable() {
			public void run()
			{
				System.out.println( "found scriptSession user >> faisal" );
				ScriptSessions.addFunctionCall( "newMessage", newMessage );
			}
		} );
	}
}
