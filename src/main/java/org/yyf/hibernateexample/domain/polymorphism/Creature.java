package org.yyf.hibernateexample.domain.polymorphism;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 
 * @author yeyf
 * @date 2014年4月18日
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "creature")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "creature_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Creature {
	@Id
	@GeneratedValue(generator = "sequenceGenerator", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "sequenceGenerator", sequenceName = "SEQ_DOMAIN_PRIMARYKEY")
	protected Long id;
	@Column(name = "name")
	protected String name;
	@Column(name = "age")
	protected Integer age;

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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}
