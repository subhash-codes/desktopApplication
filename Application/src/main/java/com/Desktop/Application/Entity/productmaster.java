package com.Desktop.Application.Entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "productmaster")
public class productmaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY.AUTO)
    private int productid;

    @ManyToOne
    @JoinColumn(name = "categoryid", nullable = false)
    private categorymaster category;

    private String product_name;

    private BigDecimal product_price;

    private String product_image;

    private LocalDate createdon;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<cartmaster> carts;

	public productmaster() {
		super();
		// TODO Auto-generated constructor stub
	}

	public productmaster(int productid, categorymaster category, String product_name, BigDecimal product_price,
			String product_image, LocalDate createdon, List<cartmaster> carts) {
		super();
		this.productid = productid;
		this.category = category;
		this.product_name = product_name;
		this.product_price = product_price;
		this.product_image = product_image;
		this.createdon = createdon;
		this.carts = carts;
	}

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public categorymaster getCategory() {
		return category;
	}

	public void setCategory(categorymaster category) {
		this.category = category;
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

	public LocalDate getCreatedon() {
		return createdon;
	}

	public void setCreatedon(LocalDate createdon) {
		this.createdon = createdon;
	}

	public List<cartmaster> getCarts() {
		return carts;
	}

	public void setCarts(List<cartmaster> carts) {
		this.carts = carts;
	}

	@Override
	public String toString() {
		return "productmaster [productid=" + productid + ", category=" + category + ", product_name=" + product_name
				+ ", product_price=" + product_price + ", product_image=" + product_image + ", createdon=" + createdon
				+ ", carts=" + carts + "]";
	}

    
}
