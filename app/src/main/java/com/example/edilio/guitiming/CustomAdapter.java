package com.example.edilio.guitiming;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter{
        ArrayList<String> result;
        Context context;
        int [] imageId;
        private int list;
        private Activity current;

        private static LayoutInflater inflater=null;
        public CustomAdapter(Context context, ArrayList<String> prgmNameList, int list, Activity current) {
            // TODO Auto-generated constructor stub
            result=prgmNameList;
            this.current = current;
            inflater = ( LayoutInflater )context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.list = list;
        }
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return result.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        public class Holder
        {
            TextView text;
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            Holder holder=new Holder();
            View rowView;
            rowView = inflater.inflate(R.layout.program_list, null);
            holder.text=(TextView) rowView.findViewById(R.id.textview);
            holder.text.setText(result.get(position));
            rowView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Toast.makeText(v.getContext(), "You Clicked "+result.get(position), Toast.LENGTH_LONG).show();
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("result",result.get(position));
                    current.setResult(Activity.RESULT_OK,returnIntent);
                    current.finish();
                }
            });
            return rowView;
        }

    }

