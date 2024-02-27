package com.ecommerce.generateinvoice.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ecommerce.generateinvoice.model.TransactionDetail;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionDto {

    private Long id;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Jakarta")
    private Date date;

    private List<TransactionDetail> detail;
    
    private BigDecimal amount;

    @JsonCreator
    public TransactionDto(@JsonProperty("id") Long id,
                          @JsonProperty("date") Date date,
                          @JsonProperty("detail") List<TransactionDetail> detail,
                          @JsonProperty("amount") BigDecimal amount) {
        this.id = id;
        this.date = date;
        this.detail = detail;
        this.amount = amount;
    }

	public TransactionDto() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<TransactionDetail> getDetail() {
		return detail;
	}

	public void setDetail(List<TransactionDetail> detail) {
		this.detail = detail;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
