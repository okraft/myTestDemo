package com.flows.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.flows.security.IncorrectCaptchaException;
import com.flows.service.IUserService;

@Controller
public class LoginController {

	@Autowired
	private IUserService userService;

	@RequestMapping("login")
	public String login(HttpServletRequest req, Model model,@RequestParam(value = "username",required =false) String userName) {
		String exceptionClassName = (String) req.getAttribute("shiroLoginFailure");
		if(StringUtils.isNotBlank(exceptionClassName)){
			String error = null;
			if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
				error = "用户名或密码错误";
			} else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
				error = "用户名或密码错误";
			} else if (IncorrectCaptchaException.class.getName().equals(exceptionClassName)){
				error = "验证码错误";
			} else if(ExcessiveAttemptsException.class.getName().equals(exceptionClassName)){
				error = "登录次数过频繁";
			} else if (exceptionClassName != null) {
				error = "其他错误：" + exceptionClassName;
			}
			model.addAttribute("error", error);
			model.addAttribute("userName", userName);
		}
		return "login";
	}
	
	@RequestMapping("logout")
	public String logout(){
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "redirect:/login";
	}

}
