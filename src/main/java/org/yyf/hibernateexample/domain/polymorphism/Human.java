package org.yyf.hibernateexample.domain.polymorphism;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


/**
 *
 *@author yeyf
 *@date 2014年4月18日
 */
@Entity
@DiscriminatorValue(value="human")
public class Human extends Creature {
	@Column(name="home")
	private String home;

	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}
}

