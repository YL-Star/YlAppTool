package com.yl.ylapp.dagger2.Test;

import javax.inject.Inject;

public class Person {

    private String name;

    @Inject
    public Person() {
        name = "person default name";
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
