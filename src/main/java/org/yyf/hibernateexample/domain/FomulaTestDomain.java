package org.yyf.hibernateexample.domain;

import java.lang.reflect.Method;

/**
 * Created by yeyf on 2014-5-9.
 */
public class FomulaTestDomain {
    public static void main(String[] args) throws NoSuchMethodException {
        System.out.println(EnumTest.class);
        System.out.println((EnumTest.Blue  instanceof Enum)+"1");
        System.out.println(EnumTest.class.isEnum()+"df");
        EnumTest blue = EnumTest.valueOf("0");
        EnumTest[] values = EnumTest.values();
        System.out.println(blue);
        Class cls = EnumTest.class;
        Method valuesMethod = cls.getMethod("values");
        cls.getEnumConstants();

    }
}
