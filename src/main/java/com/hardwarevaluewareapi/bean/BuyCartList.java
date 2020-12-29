package com.hardwarevaluewareapi.bean;

import java.util.List;

public class BuyCartList {
	private List<Cart> list;

	public List<Cart> getList() {
		return list;
	}

	public void setList(List<Cart> list) {
		this.list = list;
	}

	public BuyCartList(List<Cart> list) {
		super();
		this.list = list;
	}

	public BuyCartList() {
		
	}
}
