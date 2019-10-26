// IMyAidlInterface.aidl
package com.yl.test;

// Declare any non-default types here with import statements
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
    //跨进程共享一份list数据；in:
    List<Person> addPerson(in Person p);
}
