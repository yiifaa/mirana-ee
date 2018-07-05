package com.mirana;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mirana.domain.Account;

/**
 * 
 * 
 * @author <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version 1.0
 * 开发日期：2017年6月19日 ： 上午9:43:10 
 */
@RestController
public class IndexController {
	
	@RequestMapping("/")
	public Map<String, String> index(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getHeader(" Proxy-Client-IP ");
		}
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getHeader(" WL-Proxy-Client-IP ");
		}
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return null;
	}
	
	@RequestMapping(value="/login", consumes="application/json", method=RequestMethod.POST)
	public Account getAccount(@RequestBody Account account, HttpServletRequest request) {
		System.out.println(request.getParameter("username"));
		//	request.getParameter("host")
		account.setVersion(new Date());
		return account;
	}

}
