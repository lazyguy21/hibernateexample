package org.yyf.hibernateexample.domain.cache;

/**
 * Created by yeyf on 2014-5-16.
 */

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name = "cui_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)//可读可写
public class TUser {
    private Integer id;
    private String name;

    @Id  //标识主键
    @GeneratedValue(strategy = GenerationType.AUTO)//指定主键值的产生策略由Hibernate根据数据库字段选择
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}