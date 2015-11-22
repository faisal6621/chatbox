package com.faisal.chatbox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping( value = "/" )
public class IndexController
{
	@RequestMapping( method = RequestMethod.GET )
	public String index()
	{
		return "index";
	}

	@RequestMapping( value = "/mainpage", method = RequestMethod.GET )
	public String mainpage()
	{
		return "mainpage";
	}
}
