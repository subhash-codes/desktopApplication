package com.Desktop.Application.Entity;
import jakarta.persistence.*;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "customermaster")
public class customermaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY.AUTO)
    private int customer_id;

    private String fullname;

    private String mobile;

    private LocalDate createdon;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<cartmaster> carts;

	public customermaster() {
		super();
		// TODO Auto-generated constructor stub
	}

	public customermaster(int customer_id, String fullname, String mobile, LocalDate createdon,
			List<cartmaster> carts) {
		super();
		this.customer_id = customer_id;
		this.fullname = fullname;
		this.mobile = mobile;
		this.createdon = createdon;
		this.carts = carts;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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
		return "customermaster [customer_id=" + customer_id + ", fullname=" + fullname + ", mobile=" + mobile
				+ ", createdon=" + createdon + ", carts=" + carts + "]";
	}

   
}


