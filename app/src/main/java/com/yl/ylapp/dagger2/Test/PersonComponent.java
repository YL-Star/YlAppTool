package com.yl.ylapp.dagger2.Test;

import dagger.Component;

@Component
public interface PersonComponent {
    void inject(DaggerTestActivity dagger2Activity);

}
