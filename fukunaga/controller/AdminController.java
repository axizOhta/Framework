package jp.co.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.example.entity.Admin;
import jp.co.example.entity.Form;
import jp.co.example.entity.UserInfo;
import jp.co.example.service.AdminService;
import jp.co.example.service.UserInfoService;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	HttpSession session;

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@ModelAttribute("login") Form form, Model model) {
		Admin success = adminService.findByID(form.getId(),form.getPass());
		boolean isSuccess=success!=null;
		if(isSuccess) {
			session.setAttribute("loginUser", success);
			return "menu";
		}else {
			model.addAttribute("msg", "IDかPASSが間違っています。");
			return "login";
		}
	}

	@RequestMapping("/index")
	public String index(@ModelAttribute("login") Form form, Model model) {
		return "login";
	}

	@RequestMapping("/delete")
	public String delete(@ModelAttribute("delete") Form form, Model model) {
		try {
			Integer id = Integer.parseInt(form.getId());
			UserInfo success = userInfoService.findById(id);
			boolean isSuccess=success!=null;
			if(isSuccess) {
				session.setAttribute("deleteUser", success);
				return "deleteConfirm";
			}else {
				model.addAttribute("msg", "指定したIDは存在しません。");
				return "delete";
			}
		}catch(Exception e){
			model.addAttribute("msg", "指定したIDは存在しません。");
			return "delete";

		}
	}

	@RequestMapping("/deleteConfirm")
	public String deleteConfirm(@ModelAttribute("delete") Form form, Model model) {
		try {
			Integer id = Integer.parseInt(form.getId());
			System.out.println(id);
			userInfoService.deleteById(id);
			session.removeAttribute("defo_id");
			return "deleteResult";
		}catch(Exception e){
			return "delete";
		}
	}

	@RequestMapping("/deleteIn")
	public String deleteIn(@ModelAttribute("delete") Form form, Model model) {
		return "delete";
	}

}
