package com.yl.ylapp.test;

import com.yl.ylapp.appinfo.Test_public;
import com.yl.ylapp.appinfo.test.Test2_public;

/**
 * @date: $date$ $time$
 * @author: yanglei
 * @description
 */
class Test2 {
    void test() {
        Test_public test1 = new Test_public();
        test1.id = "";
//        test1.age = "";
//        test1.sax = "";

//        Test_default Test2_default 类的修饰符默认不写的 在不同的包名下面不能访问。

        Test2_public test2_public = new Test2_public();
        test2_public.id="";


    }


}
