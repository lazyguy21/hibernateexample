package org.yyf.hibernateexample.domain;

import javax.persistence.*;

/**
 * Created by yeyf on 2014-5-5.
 */
@Entity
@Table
public class EnumIncludedDomain {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    private EnumTest enumColumn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EnumTest getEnumColumn() {
        return enumColumn;
    }

    public void setEnumColumn(EnumTest enumColumn) {
        this.enumColumn = enumColumn;
    }

    @Override
    public String toString() {
        return "EnumIncludedDomain{" +
                "id=" + id +
                ", enumColumn=" + enumColumn +
                '}';
    }
}
