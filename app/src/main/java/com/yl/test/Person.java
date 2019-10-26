package com.yl.test;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @method $methodName$
 * @description 描述一下方法的作用
 * @date: $date$ $time$
 * @author: yanglei
 * @return $return$
 */
public class Person implements Parcelable {
    private int age;
    private String name;

    protected Person(Parcel in) {
        age = in.readInt();
        name = in.readString();
    }

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(age);
        parcel.writeString(name);
    }

}
