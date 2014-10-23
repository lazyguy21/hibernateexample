package org.yyf.hibernateexample.domain.hql;

/**
 * Created by yeyf on 2014-5-7.
 */
public class SC {
    private Integer id; //id
    private Integer sno;//学号
    private Integer cno;//课程号
    private Integer grade;//成绩

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSno() {
        return sno;
    }

    public void setSno(Integer sno) {
        this.sno = sno;
    }

    public Integer getCno() {
        return cno;
    }

    public void setCno(Integer cno) {
        this.cno = cno;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}
