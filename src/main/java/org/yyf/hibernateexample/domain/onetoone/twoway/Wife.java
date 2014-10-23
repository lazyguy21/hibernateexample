package org.yyf.hibernateexample.domain.onetoone.twoway;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 *
 *@author yeyf
 *@date 2013年12月12日
 */
@Entity
@Table(name="twoway_wife")
public class Wife {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long ID;
	private String name;
	private Integer age;
	@OneToOne(cascade=CascadeType.PERSIST,mappedBy="wife",fetch = FetchType.LAZY)
	private Husband husband;

    @Override
    public String toString() {
        return "Wife{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", husband只打印ID=" + husband.getID() +
                '}';
    }

    public Husband getHusband() {
		return husband;
	}
	public void setHusband(Husband husband) {
		this.husband = husband;
	}
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
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

