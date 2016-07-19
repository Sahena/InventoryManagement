package com.verisk.ivn.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.verisk.ivn.dao.UserDao;
import com.verisk.ivn.model.UserModel;

@Controller
@Scope("session")
@RequestMapping(value = "/home")
public class LoginController {

	@Autowired
	private UserDao userDao;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}

	@ModelAttribute
	public void addingCommonObject(Model model) {
		model.addAttribute("headerMessage", "Inventory Management");
	}

	@RequestMapping(value = "/dashboard")
	public ModelAndView validateLogin(
			@ModelAttribute("userModel") UserModel userModel,
			HttpSession session) {
		if (session.getAttribute("username") != null) {
			ModelAndView mv = new ModelAndView("dashboard");
			return mv;
		} else {
			boolean valid = userDao.validateAuthentication(userModel);
			if (valid) {
				session.setAttribute("username", userModel.getUserName());
				ModelAndView mv = new ModelAndView("dashboard");
				return mv;
			} else {
				ModelAndView mv = new ModelAndView("login");
				return mv;
			}
		}
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	public ModelAndView addUser() {
		ModelAndView mv = new ModelAndView("addUser");
		return mv;
	}

	@RequestMapping(value = "/displayUser")
	public ModelAndView displayUser() {
		List<UserModel> uList = userDao.getUserDetail();
		ModelAndView mv = new ModelAndView("displayUser");
		mv.addObject("userList", uList);
		return mv;

	}

	@RequestMapping(value = "/saveUser")
	public String saveUser(@ModelAttribute("userModel") UserModel userModel) {
		boolean saved = userDao.saveUserDetail(userModel);
		if (saved)
			return "redirect:/home/displayUser";
		else
			return "redirect:/home/addUser";
	}

	@RequestMapping(value = "/deleteUser/{id}")
	public String deleteUser(@PathVariable("id") int id) {

		userDao.deleteUserDetail(id);
		return "redirect:/home/displayUser";
	}

	@RequestMapping(value = "/editUser/{id}")
	public ModelAndView editUser(@PathVariable("id") int id) {
		UserModel userModel = userDao.editUserDetail(id);
		ModelAndView mv = new ModelAndView("editUser");
		mv.addObject("umodel", userModel);
		return mv;
	}

	@RequestMapping(value = "/updateUser")
	public String updateUser(@ModelAttribute("userModel") UserModel userModel) {
		userDao.updateUserDetail(userModel);
		return "redirect:/home/displayUser";
	}

	@RequestMapping(value = "/logOut")
	public ModelAndView logOut(HttpSession session) {
		session.invalidate();
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}
}