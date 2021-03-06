package br.com.fiap.model;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "TB_SETUP")
@SequenceGenerator(name = "setup", sequenceName = "SQ_TB_SETUP", allocationSize = 1)
public class Setup {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "setup")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private User user;
	
	private String name;
	private String description;
	private BigDecimal price = new BigDecimal(1000);

	public Setup() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Setup [name=" + name + ", description=" + description + ", price=" + price + "]";
	}
}