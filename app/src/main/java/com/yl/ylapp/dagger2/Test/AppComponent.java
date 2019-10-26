package com.yl.ylapp.dagger2.Test;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by clevo on 2015/6/9.
 */
@Singleton
@Component(modules = {AppModule.class, })
//@Component(modules = {AppModule.class, ApiServiceModule.class, AppServiceModule.class})
public interface AppComponent {


    Application getApplication();

//    ApiService getService();
//
//    User getUser();
}
