package com.faisal.chatbox.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import chatbox.UserDatabase;

@Controller
@RequestMapping( value = "/" )
public class IndexController
{
	@RequestMapping( method = RequestMethod.GET )
	public String index()
	{
		return "index";
	}

	@RequestMapping( value = "/login", method = RequestMethod.POST )
	public String login( HttpServletRequest request, HttpSession session )
	{
		String userName = request.getParameter( "userName" );
		System.out.println( "in login >> " + userName );
		session.setAttribute( "userName", userName );
		UserDatabase.login( userName );
		return "redirect:mainpage";
	}

	@RequestMapping( value = "/mainpage", method = RequestMethod.GET )
	public String mainpage( HttpSession session )
	{
		System.out.println( "in mainpage" );
		String userName = (String) session.getAttribute( "userName" );
		System.out.println( "user :: " + userName );
		if( userName == null ) { return "redirect:."; }
		return "mainpage";
	}

	@RequestMapping( value = "/logout", method = RequestMethod.GET )
	public String logout( HttpServletRequest request, HttpSession session )
	{
		String userName = (String) session.getAttribute( "userName" );
		System.out.println( "in logout >> " + userName );
		UserDatabase.logout( userName );
		try
		{
			request.logout();
		}
		catch ( ServletException ex )
		{
			ex.printStackTrace();
			session.invalidate();
		}
		return "index";
	}
}
