package com.matheuscordeiro.sendpromotionemailjob.domain;

public class ProductCustomerInterest {
	private Customer customer;
	private Product product;
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
}
