package org.yyf.hibernateexample.domain.hql;

/**
 * Created by yeyf on 2014-5-7.
 */
public class Course {
    private Integer id; //对象标识符
    private Integer cno;//课程号
    private String cname;//课程名
    private Integer Ccredit; //学分

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCcredit() {
        return Ccredit;
    }

    public void setCcredit(Integer ccredit) {
        Ccredit = ccredit;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Integer getCno() {
        return cno;
    }

    public void setCno(Integer cno) {
        this.cno = cno;
    }
}
