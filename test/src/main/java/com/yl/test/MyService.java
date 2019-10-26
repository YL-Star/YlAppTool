package com.yl.test;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.ArrayList;
import java.util.List;

public class MyService extends Service {
    public MyService() {
    }

    private static ArrayList<Person> listPerson;

    public static ArrayList<Person> getListPerson() {
        return listPerson;
    }

    public void setListPerson(ArrayList<Person> listPerson) {
        this.listPerson = listPerson;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        listPerson = new ArrayList<>();
        return iBinder;
    }


    private IBinder iBinder = new IMyAidlInterface.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean,
                               float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public int add(int num0, int num1) throws RemoteException {
            startActivity(new Intent(MyService.this, MainActivity.class));
            return num0 + num1;

        }


        @Override
        public String getTest(String str) throws RemoteException {
            if (str.equals("1")) {
                return "我是aidl返回的字符串信息";
            }
            return null;
        }

        @Override
        public List<Person> addPerson(Person p) throws RemoteException {
            listPerson.add(p);

            return listPerson;
        }

//        private int sum(final int num0, final int num1) {
//            new Thread() {
//                @Override
//                public void run() {
//                    super.run();
//                    try {
//                        sleep(2000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }.start();
//        }
    };
}
