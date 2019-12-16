package com.yl.module_base_utils.obs;

/**
 * Created by yanglei on 2018/7/11;
 * 抽象被观察者接口
 * 声明了添加、删除、通知观察者方法
 */

public interface Observerable {

    public void registerObserver(Observer o);
    public void removeObserver();
    public void notifyObserver(String type, String data);



}
