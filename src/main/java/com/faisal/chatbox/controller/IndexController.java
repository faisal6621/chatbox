package com.faisal.chatbox.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptSessions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import chatbox.UserDatabase;

@Controller
@RequestMapping( value = "/" )
public class IndexController
{
	@RequestMapping( method = RequestMethod.GET )
	public String index( HttpSession session )
	{
		//		String userName = Util.getCurrentUserName( session );
		//		if( userName != null ) { return "redirect:mainpage"; }
		return "index";
	}

	@RequestMapping( value = "/login", method = RequestMethod.GET ,params="error")
	public String login( HttpServletRequest request, HttpSession session )
	{
//		String userName = request.getParameter( "userName" );
//		if( userName == null || userName.trim().equals( "" ) ) { return "redirect:."; }
//		if( UserDatabase.isUserLogged( userName ) ) { return "redirect:."; }
//		session.setAttribute( "userName", userName );
//		UserDatabase.login( userName );
//		return "redirect:mainpage";
	    System.out.println( "in login error");
	    Exception exception = (Exception) session.getAttribute( "SPRING_SECURITY_LAST_EXCEPTION" );
	    System.out.println( exception.getMessage() );
	    return "index";
	}

	@RequestMapping( value = "/mainpage", method = RequestMethod.GET )
	public String mainpage( HttpServletRequest request )// HttpSession session
	{
		System.out.println( "in mainpage" );
		String userName = request.getUserPrincipal().getName();
		System.out.println( "user :: " + userName );
		//		if( userName == null )
		//		{
		//			System.out.println( "redirecting to login" );
		//			return "redirect:.";
		//		}
		if( !UserDatabase.isUserLogged( userName ) )
		{
			System.out.println( "new login" );
			UserDatabase.login( userName );
		}
		//		else
		//		{
		//			return "redirect:.";
		//		}
		updateOnlineUsers();
		return "mainpage";
	}

	@RequestMapping( value = "/logout", method = RequestMethod.GET )
	public String logout( HttpServletRequest request, HttpSession session )
	{
	    System.out.println( "in logout" );
		try
		{
			String userName = request.getUserPrincipal().getName();
			UserDatabase.logout( userName );
			session.invalidate();
			request.logout();
		}
		catch ( ServletException ex )
		{
			ex.printStackTrace();
		}
		finally
		{
			updateOnlineUsers();
		}
		return "index";
	}

	private void updateOnlineUsers()
	{
		Browser.withAllSessions( new Runnable() {
			public void run()
			{
				ScriptSessions.addFunctionCall( "showUsersOnline" );
			}
		} );
	}
}
