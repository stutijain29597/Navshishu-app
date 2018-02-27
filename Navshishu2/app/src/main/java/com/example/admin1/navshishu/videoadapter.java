package com.example.admin1.navshishu;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.admin1.navshishu.R;
import com.example.admin1.navshishu.pics;

import java.util.List;

/**
 * Created by admin1 on 04-06-2017.
 */
public class videoadapter extends BaseAdapter {
    private Context iContext;
    private List<pics> Pics;
    public videoadapter(Context iContext, List<pics> Pics)
    { super();
        this.iContext=iContext;
        this.Pics=Pics;}
    @Override
    public int getCount() {
        return Pics.size();
    }

    @Override
    public Object getItem(int i) {
        return Pics.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v=View.inflate(iContext,R.layout.video_list,null);
        TextView l_Name=(TextView)v.findViewById(R.id.pic_category);
        String str=Pics.get(i).getName(),str1;
        int pos=str.indexOf("=");
        str1=str.substring(0,pos);
        l_Name.setText(str1 );
       Log.d("Harry12345","name--"+str1);
        v.setTag(Pics.get(i).getName());
        return v;
    }
}


