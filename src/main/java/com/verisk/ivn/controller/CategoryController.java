package com.verisk.ivn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.verisk.ivn.dao.CategoryDao;
import com.verisk.ivn.model.CategoryModel;

@Controller
@Scope("session")
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryDao cdao;

	@RequestMapping("/addCategory")
	public ModelAndView addCategory() {
		ModelAndView mv = new ModelAndView("addCategory");
		return mv;
	}

	@RequestMapping(value = "/saveCategory", method = RequestMethod.POST)
	public String saveCategory(
			@ModelAttribute("catModel") CategoryModel catModel) {
		cdao.saveCategory(catModel);
		return "redirect:displayCategory";
	}

	@RequestMapping(value = "/displayCategory")
	public ModelAndView displayCategory() {
		List<CategoryModel> clist = cdao.displayCategory();
		ModelAndView mv = new ModelAndView("displayCategory");
		mv.addObject("clist", clist);
		return mv;
	}

	@RequestMapping(value = "/editCategory/{cid}")
	public ModelAndView editCategory(@PathVariable("cid") int cid) {
		CategoryModel catModel = cdao.editCategory(cid);
		ModelAndView mv = new ModelAndView("editCategory");
		mv.addObject("catModel", catModel);
		return mv;
	}

	@RequestMapping(value = "/updateCategory", method = RequestMethod.POST)
	public String updateCategory(
			@ModelAttribute("catModel") CategoryModel catModel) {
		cdao.updateCategory(catModel);
		return "redirect:displayCategory";
	}

	@RequestMapping(value = "/deleteCategory/{cid}")
	public String deleteCategory(@PathVariable("cid") int cid) {
		cdao.deleteCategory(cid);
		return "redirect:/category/displayCategory";
	}
}
