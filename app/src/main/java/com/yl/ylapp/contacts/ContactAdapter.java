package com.yl.ylapp.contacts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yl.module_base_utils.contacts.ContactBean;
import com.yl.ylapp.R;

import java.util.ArrayList;

/**
 * 模块类
 * Created by yanglei on 2018/7/25;
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyHolder> {

    ArrayList<ContactBean> allContact;
    Context context;


    /**
     * 初始化构造方法
     *
     * @author yanglei
     * @version [1.0, 2019.]
     */

    public ContactAdapter(Context mActivity, ArrayList<ContactBean> allContact) {
        context = mActivity;
        this.allContact = allContact;


    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.two_line_list_item, viewGroup, false);
        MyHolder viewHolder = new MyHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int i) {
       myHolder.text1.setText(allContact.get(i).getName());
       myHolder.text2.setText(allContact.get(i).getPhoneNumber());

    }

    @Override
    public int getItemCount() {
        return allContact.size();


    }

    class MyHolder extends RecyclerView.ViewHolder {

        private final TextView text1;
        private final TextView text2;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            text1 = itemView.findViewById(R.id.text1);
            text2 = itemView.findViewById(R.id.text2);
        }
    }
}
