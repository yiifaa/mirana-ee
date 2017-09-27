package com.mirana;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mirana.domain.Account;

/**
 * 测试SessionAttributes的可用性
 * 
 * @author  <a href="mailto:ganhuanxp@163.com">甘焕</a>
 * @version  1.0
 * 开发日期：2017年8月25日 ： 上午10:28:11 
 */
@RestController
@SessionAttributes("account")
@Scope("prototype")
public class SessionAttributeController {

	private Account account;

	/**
	 * @param account
	 */
	@Inject
	public SessionAttributeController(Account account) {
		super();
		this.account = account;
	}
	
	@RequestMapping("/account")
	public Account account() {
		return this.account;
	}
	
	@RequestMapping("/info")
	public Account info(@ModelAttribute("account") Account account, @SessionAttribute("account") Account acc, HttpSession session) {
		return account;
	}
	
}
