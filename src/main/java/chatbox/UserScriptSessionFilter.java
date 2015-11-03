package chatbox;

import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;

public class UserScriptSessionFilter implements ScriptSessionFilter
{
	private String	key;
	private String	value;

	public UserScriptSessionFilter( String inKey, String inValue )
	{
		this.key = inKey;
		this.value = inValue;
	}

	public boolean match( ScriptSession inScriptSession )
	{
		String user = (String) inScriptSession.getAttribute( key );
		if( value != null && value.equals( user ) ) { return true; }
		return false;
	}
}
