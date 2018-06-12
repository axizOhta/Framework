package jp.co.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.example.entity.Form;
import jp.co.example.entity.User_info;
import jp.co.example.service.impl.SearchServiceImpl;

@Controller
public class SearchController {

	@Autowired
	private SearchServiceImpl searchS;

	@RequestMapping("/selectIn")
	public String select (@ModelAttribute("select") Form form, Model model) {

		return "select";
	}

	@RequestMapping(value="/selectResult", method=RequestMethod.POST)
	public String selectResult (@Validated @ModelAttribute("select") Form form,
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("msg", "エラー");
			return "select";
		}

		List<User_info> list = searchS.search(form.getId(), form.getName(), form.getTel());
		if (list == null || list.size() == 0) {
			model.addAttribute("msg", "入力されたデータはありませんでした");
			return "select";
		} else {
			model.addAttribute("user_info", list);
			return "selectResult";
		}
	}



}
