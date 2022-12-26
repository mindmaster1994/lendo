package com.lendo.api.lendo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.lendo.api.lendo.advice.LocaleService;
import com.lendo.api.lendo.model.User;
import com.lendo.api.lendo.security.service.UserDetailsImpl;

abstract class BaseController {

	@Autowired
	protected LocaleService localeService;

	@Autowired
	private HttpServletRequest request;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	@ModelAttribute("language")
	public String getLanguage() {
		return localeService.getLanguage(request);
	}

	@ModelAttribute("localeLanguage")
	public String getLocaleLanguage() {
		return localeService.getLocaleLanguage(request);
	}

	@ModelAttribute("locale")
	public Locale getLocale() {
		return localeService.getLocale(request);
	}

	@ModelAttribute("token")
	public String getToken() {
		return request.getHeader("token");
	}

	@ModelAttribute("name")
	public String getName() {
		return request.getHeader("name");
	}

	@ModelAttribute("logguid")
	public String getLogGuid() {
		return (String) request.getAttribute("logguid");
	}
	
	protected User getCurrentUser() {
		UserDetailsImpl principal = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return principal.getUser();
	}
}
