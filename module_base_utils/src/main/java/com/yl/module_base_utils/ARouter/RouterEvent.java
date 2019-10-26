package com.yl.module_base_utils.ARouter;

import java.io.Serializable;

public class RouterEvent implements Serializable {
    private String eventName;
    private String value;
    public RouterEvent(String eventName, String value){
        this.eventName = eventName;
        this.value = value;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
