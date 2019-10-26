package com.yl.ylapp.dagger2.components;

import com.yl.ylapp.dagger2.Dagger2Activity;

import dagger.Component;

@Component
public interface PersonComponent {

    void inject(Dagger2Activity dagger2Activity);

}
