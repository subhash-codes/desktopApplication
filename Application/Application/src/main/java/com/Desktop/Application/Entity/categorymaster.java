package com.Desktop.Application.Entity;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "categorymaster")
public class categorymaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY.AUTO)
    private int categoryid;

    private String categoryname;

    private String category_image;

    private LocalDate createdon;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<productmaster> products;

	public categorymaster() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public categorymaster(int categoryid, String categoryname, String category_image) {
		super();
		this.categoryid = categoryid;
		this.categoryname = categoryname;
		this.category_image = category_image;
	}


	public categorymaster(String categoryname, String category_image, LocalDate createdon) {
		super();
		this.categoryname = categoryname;
		this.category_image = category_image;
		this.createdon = createdon;
	}
	
	public categorymaster(int categoryid, String categoryname, String category_image, LocalDate createdon,
			List<productmaster> products) {
		super();
		this.categoryid = categoryid;
		this.categoryname = categoryname;
		this.category_image = category_image;
		this.createdon = createdon;
		this.products = products;
	}

	public int getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public String getCategory_image() {
		return category_image;
	}

	public void setCategory_image(String category_image) {
		this.category_image = category_image;
	}

	public LocalDate getCreatedon() {
		return createdon;
	}

	public void setCreatedon(LocalDate createdon) {
		this.createdon = createdon;
	}

	public List<productmaster> getProducts() {
		return products;
	}

	public void setProducts(List<productmaster> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "categorymaster [categoryid=" + categoryid + ", categoryname=" + categoryname + ", category_image="
				+ category_image + ", createdon=" + createdon + ", products=" + products + "]";
	}


}
