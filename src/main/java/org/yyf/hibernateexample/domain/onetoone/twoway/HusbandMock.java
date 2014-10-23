package org.yyf.hibernateexample.domain.onetoone.twoway;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * Created by yeyf on 2014-5-6.
 */
public class HusbandMock {
    private Long ID;
    private String name;
    private Integer age;
    private Long wifeId;

    @Override
    public String toString() {
        return "HusbandMock{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", wifeId=" + wifeId +
                '}';
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
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

    public Long getWifeId() {
        return wifeId;
    }

    public void setWifeId(Long wifeId) {
        this.wifeId = wifeId;
    }

    public HusbandMock(Long ID, String name, Integer age, Long wifeId) {
        this.ID = ID;
        this.name = name;
        this.age = age;
        this.wifeId = wifeId;
    }

    public HusbandMock(Long ID, String name, Integer age) {
        this.ID = ID;
        this.name = name;
        this.age = age;
    }
}
