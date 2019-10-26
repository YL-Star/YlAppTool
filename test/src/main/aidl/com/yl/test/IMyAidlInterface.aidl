// IMyAidlInterface.aidl
package com.yl.test;

import com.yl.test.Person;

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
    int add(int num0,int num1);
    String getTest(String str);
    List<Person> addPerson(in Person p);
}
