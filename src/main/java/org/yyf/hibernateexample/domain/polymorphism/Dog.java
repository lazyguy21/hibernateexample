package org.yyf.hibernateexample.domain.polymorphism;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 
 * @author yeyf
 * @date 2014年4月18日
 */
@Entity
@DiscriminatorValue(value = "dog")
public class Dog extends Creature {
	@Column(name = "brood")
	private String brood;

	public String getBrood() {
		return brood;
	}

	public void setBrood(String brood) {
		this.brood = brood;
	}

	@Override
	public String toString() {
		return "Dog [brood=" + brood + ", id=" + id + ", name=" + name + ", age=" + age + "] \n";
	}

}
