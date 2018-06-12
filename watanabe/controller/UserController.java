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
import jp.co.example.service.AdminService;
import jp.co.example.service.User_infoService;

@Controller
public class UserController {

	@Autowired
	private AdminService adminService;
	@Autowired
	private User_infoService userService;
	@Autowired
	HttpSession session;

	@RequestMapping("/start")
	public String start(Model model) {
		return "index";
	}


	@RequestMapping(value="/index",method = RequestMethod.POST)
	public String index(Model model) {
		//		List<User> list = userService.findAll();
		//		model.addAttribute("userlist", list);
		return "login";		//ログインページへの遷移
	}

	@RequestMapping(value="/login",method = RequestMethod.POST)
	public String login(@ModelAttribute("login") Form form,Model model) {

		if((form.getId()==null || form.getPass()==null)||("".equals(form.getId()))||("".equals(form.getPass()))) {
			model.addAttribute("msg", "IDまたはPASSが未入力です");
			return "login";
		}
		Admin loginUser= adminService.authentication(form.getId(),form.getPass());
		if(loginUser==null) {
			model.addAttribute("msg", "IDまたはPASSが間違っています");
			return "login";
		}
		session.setAttribute("loginUser", loginUser);
		return "menu";
	}

	@RequestMapping("/insertIn")
	public String insertIn(Model model) {		//メニューで登録押したらそのページに飛ばすよ
		return "insert";
	}

	@RequestMapping("/insertBack")
	public String insertBack(Model model) {		//メニューで登録押したらそのページに飛ばすよ
		return "insert";
	}

	@RequestMapping(value="/insert",method = RequestMethod.POST)
	public String insert(@ModelAttribute("insert") Form form,Model model) {		//登録画面で入力後の処理だよ
		if((form.getName()==null || form.getTel()==null ||form.getPass()==null)||"".equals(form.getName())||"".equals(form.getTel())||"".equals(form.getPass())){
			model.addAttribute("msg", "必須項目が未入力です！！");
			return "insert";
		}
		session.setAttribute("insertname", form.getName());
		session.setAttribute("inserttel", form.getTel());
		session.setAttribute("insertpass", form.getPass());		//確認画面で使うからセッションいれるよ
		return "insertConfirm";
	}

	@RequestMapping(value="/insertConfirm",method=RequestMethod.POST)
	public String insertResult(@ModelAttribute("insertConfirm") Form form,Model model) {		//確認したよ
		if(form.getRePass().equals(session.getAttribute("insertpass"))) {//パスあってたら
			userService.register((String)session.getAttribute("insertname"),(String)session.getAttribute("inserttel"),(String)session.getAttribute("insertpass"));	//登録するよ
			int maxId=userService.findMaxId();
			session.setAttribute("defo_id", maxId);		//検索時に初期表示させるために入れるよ
			return "insertResult";
		}else {
			model.addAttribute("msg", "前画面で入力したパスワードと一致しません");
			return "insert";
		}
	}







}