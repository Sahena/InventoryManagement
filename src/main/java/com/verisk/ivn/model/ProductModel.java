package com.verisk.ivn.model;

import org.springframework.beans.factory.annotation.Autowired;

public class ProductModel {

	private int pid;
	private String pname;
	private int price;
	private int quantity;
	@Autowired
	private CategoryModel cmodel;

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public CategoryModel getCmodel() {
		return cmodel;
	}

	public void setCmodel(CategoryModel cmodel) {
		this.cmodel = cmodel;
	}

}
