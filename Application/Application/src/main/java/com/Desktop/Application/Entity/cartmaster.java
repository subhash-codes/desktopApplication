package com.Desktop.Application.Entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "cartmaster")
public class cartmaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY.AUTO)
    private int cartid;

    @ManyToOne
    @JoinColumn(name = "productid", nullable = false)
    private productmaster product;

    private String product_name;

    private BigDecimal product_price;

    private String product_image;

    private int qty;

    private BigDecimal sub_total;

    private BigDecimal discount_percentage;

    private BigDecimal discount_amount;

    private BigDecimal net_bill;

    private String order_no;

    private String status;

    private LocalDate createdon;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private customermaster customer;

	public cartmaster() {
		super();
		// TODO Auto-generated constructor stub
	}

	public cartmaster(int cartid, productmaster product, String product_name, BigDecimal product_price,
			String product_image, int qty, BigDecimal sub_total, BigDecimal discount_percentage,
			BigDecimal discount_amount, BigDecimal net_bill, String order_no, String status, LocalDate createdon,
			customermaster customer) {
		super();
		this.cartid = cartid;
		this.product = product;
		this.product_name = product_name;
		this.product_price = product_price;
		this.product_image = product_image;
		this.qty = qty;
		this.sub_total = sub_total;
		this.discount_percentage = discount_percentage;
		this.discount_amount = discount_amount;
		this.net_bill = net_bill;
		this.order_no = order_no;
		this.status = status;
		this.createdon = createdon;
		this.customer = customer;
	}

	public int getCartid() {
		return cartid;
	}

	public void setCartid(int cartid) {
		this.cartid = cartid;
	}

	public productmaster getProduct() {
		return product;
	}

	public void setProduct(productmaster product) {
		this.product = product;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public BigDecimal getProduct_price() {
		return product_price;
	}

	public void setProduct_price(BigDecimal product_price) {
		this.product_price = product_price;
	}

	public String getProduct_image() {
		return product_image;
	}

	public void setProduct_image(String product_image) {
		this.product_image = product_image;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public BigDecimal getSub_total() {
		return sub_total;
	}

	public void setSub_total(BigDecimal sub_total) {
		this.sub_total = sub_total;
	}

	public BigDecimal getDiscount_percentage() {
		return discount_percentage;
	}

	public void setDiscount_percentage(BigDecimal discount_percentage) {
		this.discount_percentage = discount_percentage;
	}

	public BigDecimal getDiscount_amount() {
		return discount_amount;
	}

	public void setDiscount_amount(BigDecimal discount_amount) {
		this.discount_amount = discount_amount;
	}

	public BigDecimal getNet_bill() {
		return net_bill;
	}

	public void setNet_bill(BigDecimal net_bill) {
		this.net_bill = net_bill;
	}

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getCreatedon() {
		return createdon;
	}

	public void setCreatedon(LocalDate createdon) {
		this.createdon = createdon;
	}

	public customermaster getCustomer() {
		return customer;
	}

	public void setCustomer(customermaster customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "cartmaster [cartid=" + cartid + ", product=" + product + ", product_name=" + product_name
				+ ", product_price=" + product_price + ", product_image=" + product_image + ", qty=" + qty
				+ ", sub_total=" + sub_total + ", discount_percentage=" + discount_percentage + ", discount_amount="
				+ discount_amount + ", net_bill=" + net_bill + ", order_no=" + order_no + ", status=" + status
				+ ", createdon=" + createdon + ", customer=" + customer + "]";
	}

   
}

