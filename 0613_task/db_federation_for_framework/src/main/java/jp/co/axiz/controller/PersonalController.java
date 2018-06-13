package jp.co.axiz.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.axiz.entity.Admin;
import jp.co.axiz.entity.AdminForm;
import jp.co.axiz.entity.UserInfo;
import jp.co.axiz.entity.UserInfoForm;
import jp.co.axiz.service.AdminService;
import jp.co.axiz.service.UserInfoService;

@Controller
public class PersonalController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private UserInfoService uIS;

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
		return "login";
	}//ログインページへの遷移


	@RequestMapping("/login")
	public String list(@Validated @ModelAttribute("login") AdminForm fm, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "login";
		}
		String id = fm.getAdminId();
		String pass = fm.getPassword();
		Admin admin = adminService.findById(id,pass);
		if(admin==null) {
			model.addAttribute("error", "IDまたはPASSが間違っています");
			return "login";
		}else if(id.equals("") || pass.equals("")) {
			model.addAttribute("error", "いずれかの値が未入力です");
			return "login";
		}
		session.setAttribute("controller", admin.getAdminName());

		return "menu";
	}

//	@RequestMapping(value="/login", method=RequestMethod.POST)
//	public String login(@ModelAttribute("login") AdminForm form, Model model) {
//		Admin success = adminService.findById(form.getAdminId(),form.getPassword());
//		boolean isSuccess=success!=null;
//		if(isSuccess) {
//			session.setAttribute("loginUser", success);
//			return "menu";
//		}else {
//			model.addAttribute("msg", "IDかPASSが間違っています。");
//			return "login";
//		}
//	}

//	@RequestMapping(value="/login",method = RequestMethod.POST)
//	public String login(@ModelAttribute("login") Form form,Model model) {
//
//		if((form.getId()==null || form.getPass()==null)||("".equals(form.getId()))||("".equals(form.getPass()))) {
//			model.addAttribute("msg", "IDまたはPASSが未入力です");
//			return "login";
//		}
//		Admin loginUser= adminService.authentication(form.getId(),form.getPass());
//		if(loginUser==null) {
//			model.addAttribute("msg", "IDまたはPASSが間違っています");
//			return "login";
//		}
//		session.setAttribute("loginUser", loginUser);
//		return "menu";
//	}

	@RequestMapping("/index")
	public String index(@ModelAttribute("login") AdminForm form, Model model) {
		return "login";
	}

	@RequestMapping({ "/menu"})
	public String returnMenu(UserInfoForm form, Model model) {
		return "menu";
	}

	//SELECT用コントローラー
	@RequestMapping("/selectIn")
	public String select (@ModelAttribute("command") UserInfoForm form, Model model) {

		return "select";
	}

	@RequestMapping(value="/selectResult", method=RequestMethod.POST)
	public String selectResult (@Validated @ModelAttribute("command") UserInfoForm form,
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("msg", "エラー");
			return "select";
		}

		List<UserInfo> list = uIS.search(form.getId(), form.getName(), form.getTel());
		if (list == null || list.size() == 0) {
			model.addAttribute("msg", "入力されたデータはありませんでした");
			return "select";
		} else {
			model.addAttribute("user_info", list);
			return "selectResult";
		}
	}

	//INSERT用コントローラー
	@RequestMapping("/insertIn")
	public String insertIn(@ModelAttribute("command") UserInfoForm form,Model model) {		//メニューで登録押したらそのページに飛ばすよ
		return "insert";
	}

	@RequestMapping("/insertBack")
	public String insertBack(@ModelAttribute("command") UserInfoForm form,Model model) {		//メニューで登録押したらそのページに飛ばすよ
		return "insert";
	}

	@RequestMapping(value="/insert",method = RequestMethod.POST)
	public String insert(@ModelAttribute("command") UserInfoForm form,Model model) {		//登録画面で入力後の処理だよ
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
	public String insertResult(@ModelAttribute("command") UserInfoForm form,Model model) {		//確認したよ
		if(form.getRePass().equals(session.getAttribute("insertpass"))) {//パスあってたら
			uIS.register((String)session.getAttribute("insertname"),(String)session.getAttribute("inserttel"),(String)session.getAttribute("insertpass"));	//登録するよ
			List<UserInfo> user = uIS.findMaxId();
			UserInfo uI = user.get(0);
			Integer maxId = uI.getUserId();

			session.setAttribute("defo_id", maxId);		//検索時に初期表示させるために入れるよ
			return "insertResult";
		}else {
			model.addAttribute("msg", "前画面で入力したパスワードと一致しません");
			return "insertConfirm";
		}
	}


	//UPDATE用コントローラー
	@RequestMapping({ "/updateIn"})
	public String returnUpdate(@ModelAttribute("command") UserInfoForm form, Model model) {
		return "update";
	}

	@RequestMapping({ "/updateinput"})
	public String returnUpdateInput(@ModelAttribute("command") UserInfoForm form, Model model) {
		return "updateInput";
	}


	@RequestMapping("/updateInput")
	public String update(@Validated @ModelAttribute("command") UserInfoForm fm, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "update";
		}
		Integer id  = Integer.parseInt(fm.getId());
		UserInfo user = uIS.findById(id);
		session.setAttribute("beforeUser", user);

		return "updateInput";
	}

	@RequestMapping("/updateConfirm")
	public String updateConfirm(@Validated @ModelAttribute("command") UserInfoForm fm, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "updateInput";
		}
		String newName = fm.getNewName();
		String newTel = fm.getNewTel();
		String newPass = fm.getNewPass();

		UserInfo beforeUser = (UserInfo)session.getAttribute("beforeUser");

		Integer id = beforeUser.getUserId();

		UserInfo newUser = new UserInfo(id, newName, newTel, newPass);

		session.setAttribute("NewUser", newUser);
		session.setAttribute("defo_id", id);

		if(beforeUser.getPassword().equals(newUser.getPassword())) {
			session.setAttribute("Pass", beforeUser.getPassword());

		}else {
			session.setAttribute("Pass", "");
		}

		if(newName.equals(beforeUser.getUserName()) && newTel.equals(beforeUser.getTelephone()) && newPass.equals(beforeUser.getPassword())) {
			model.addAttribute("error", "1項目以上変更してください。");

			return "updateInput";
		}

		return "updateConfirm";
	}

	@RequestMapping("/updateResult")
	public String updateResult(@ModelAttribute("command") UserInfoForm fm, Model model) {
		String rePass = fm.getRePass();
		UserInfo beforeUser = (UserInfo)session.getAttribute("beforeUser");
		UserInfo newUser = (UserInfo)session.getAttribute("NewUser");

		String newName =newUser.getUserName();
		String newTel = newUser.getTelephone();
		String newPass = newUser.getPassword();

		if(newName != null && newTel != null && newPass != null){
			if(!(newName.equals(beforeUser.getUserName())) && !(newName.equals(""))) {
				uIS.updateName(newUser);
			}

			if(!(newTel.equals(beforeUser.getTelephone())) && !(newTel.equals(""))){
				uIS.updateTel(newUser);
			}

			if(!(newPass.equals(beforeUser.getPassword())) && !(newPass.equals(""))){
				if(newPass.equals(rePass)) {
					uIS.updatePass(newUser);
				}else {
					model.addAttribute("error", "前画面で入力したパスワードと一致しません。");

					return "updateConfirm";
				}
			}
		}

		return "updateResult";
	}

	@RequestMapping("/delete")
	public String delete(@ModelAttribute("command") UserInfoForm form, Model model) {
		try {
			Integer id = Integer.parseInt(form.getId());
			UserInfo success = uIS.findById(id);
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
	public String deleteConfirm(@ModelAttribute("command") UserInfoForm form, Model model) {
		try {
			Integer id = Integer.parseInt(form.getId());
			System.out.println(id);
			uIS.deleteById(id);
			session.removeAttribute("defo_id");
			return "deleteResult";
		}catch(Exception e){
			return "delete";
		}
	}

	@RequestMapping("/deleteIn")
	public String deleteIn(@ModelAttribute("command") UserInfoForm form, Model model) {
		return "delete";
	}

	@RequestMapping("/logout")
	public String logout (HttpSession session, Model model) {

		session.invalidate();

		return "logout";
	}

}
