package org.yyf.hibernateexample.domain.onetoone.twoway;

import org.hibernate.annotations.Formula;

import javax.persistence.*;

/**
 * 
 * @author yeyf
 * @date 2013年12月12日
 */
@Entity
@Table(name="twoway_husband")
public class Husband {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long ID;
	@Column(name="name")//如果属性名就是列名就省略了
	private String name;
	private Integer age;
	@OneToOne(fetch = FetchType.LAZY)
//	@OneToOne
	@JoinColumn(name="wife_id")
	private Wife wife;

    private Integer countryId;

    @Formula("(select d.name from test_dictionary d where d.code=countryId)")
    private String countryName;

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        return "Husband{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", wife="  +wife+
                ", countryId=" + countryId +
                ", countryName='" + countryName + '\'' +
                '}';
    }

    public Wife getWife() {
		return wife;
	}

	public void setWife(Wife wife) {
		this.wife = wife;
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
