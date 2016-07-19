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
import com.verisk.ivn.dao.ProductDao;
import com.verisk.ivn.model.CategoryModel;
import com.verisk.ivn.model.ProductModel;

@Controller
@Scope("session")
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductDao pdao;
	@Autowired
	private CategoryDao cdao;

	@RequestMapping(value = "/addProduct")
	public ModelAndView addProduct() {
		List<CategoryModel> clist = cdao.displayCategory();
		ModelAndView mv = new ModelAndView("addProduct");
		mv.addObject("clist", clist);
		return mv;
	}

	@RequestMapping(value = "/saveProduct", method = RequestMethod.POST)
	public String saveProduct(
			@ModelAttribute("prodModel") ProductModel prodModel) {
		pdao.saveProduct(prodModel);
		return "redirect:displayProduct";
	}

	@RequestMapping(value = "/displayProduct")
	public ModelAndView displayProduct() {
		List<ProductModel> plist = pdao.displayProduct();
		ModelAndView mv = new ModelAndView("displayProduct");
		mv.addObject("plist", plist);
		return mv;
	}

	@RequestMapping(value = "/deleteProduct/{pid}")
	public String deleteProduct(@PathVariable("pid") int pid) {
		pdao.deleteProduct(pid);
		return "redirect:/product/displayProduct";
	}

	@RequestMapping(value = "/editProduct/{pid}")
	public ModelAndView editProduct(@PathVariable("pid") int pid) {
		ProductModel pmodel = pdao.getProductFromId(pid);
		List<CategoryModel> clist = cdao.displayCategory();
		ModelAndView mv = new ModelAndView("editProduct");
		mv.addObject("pmodel", pmodel);
		mv.addObject("clist", clist);
		return mv;
	}

	@RequestMapping(value = "/updateProduct")
	public String updateProduct(@ModelAttribute("pmodel") ProductModel pmodel) {
		pdao.updateProduct(pmodel);
		return "redirect:/product/displayProduct";
	}
}