package com.yl.ylapp.contacts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.yl.module_base_utils.contacts.ContactBean;
import com.yl.module_base_utils.contacts.ContactUtil;
import com.yl.ylapp.R;

import java.util.ArrayList;


public class PhoneListActivity extends AppCompatActivity {

    private TextView mTextview;
    private RecyclerView mRecyleView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_list);
        initView();
        ArrayList<ContactBean> allContact = null;
        try {
            allContact = ContactUtil.getAllContact(this);
            mTextview.setText(allContact.toString());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyleView.setLayoutManager(linearLayoutManager);

        ContactAdapter contactAdapter= new ContactAdapter(this,allContact);
        mRecyleView.setAdapter(contactAdapter);

    }

    private void initView() {
        mTextview = (TextView) findViewById(R.id.textview);
        mRecyleView = (RecyclerView) findViewById(R.id.recyleView);
    }

}
