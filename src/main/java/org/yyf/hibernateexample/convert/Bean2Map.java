package org.yyf.hibernateexample.convert;

import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.yyf.hibernateexample.domain.EnumIncludedDomain;
import org.yyf.hibernateexample.domain.EnumTest;
import org.yyf.hibernateexample.domain.onetoone.twoway.Husband;
import org.yyf.hibernateexample.domain.onetoone.twoway.Wife;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;

/**
 * Created by yeyf on 2014-5-8.
 */
public class Bean2Map {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Husband husband= new Husband();
        husband.setID(1L);
        husband.setName("yyfff");
        husband.setAge(12312);
        Wife wife= new Wife();
        wife.setName("susu");
        wife.setID(11L);
        husband.setWife(wife);
        EnumIncludedDomain enumIncludedDomain =new EnumIncludedDomain();
        enumIncludedDomain.setEnumColumn(EnumTest.Blue);
        enumIncludedDomain.setId(1L);
        Map husbandMap = BeanUtils.describe(husband);
        Map enumIncludedDomainMap = BeanUtils.describe(enumIncludedDomain);
        System.out.println(husbandMap);
        System.out.println(enumIncludedDomainMap);

        JSONObject jsonObject = JSONObject.fromObject(husband);
        System.out.println(jsonObject);
        System.out.println(JSONObject.fromObject(new Date()));
        System.out.println(new Date().toLocaleString());


    }
}
