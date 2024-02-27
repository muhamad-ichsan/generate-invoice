package com.ecommerce.generateinvoice.dto;

import com.ecommerce.generateinvoice.model.Product;

public class TransactionDetailDto {
	private Product product;
	private Integer qty;
	
	public TransactionDetailDto() {
		
	}
	
	public TransactionDetailDto(Product product, Integer qty) {
		super();
		this.product = product;
		this.qty = qty;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}

	
}
