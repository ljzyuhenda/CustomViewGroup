package com.yuhenda.customviewgroup;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.yuhenda.customviewgroup.UI.CustomView;

public class MainActivity extends AppCompatActivity {
    private String[][] mDataList = {{"1,2", "3,6", "4", "5"}, {"1,2,3", "4,5", "6"},
            {"1,2,3", "4,5,6"}, {"1", "2,3", "4,5", "6"},
            {"1,2", "3,6", "4", "5"}, {"1", "2,3", "4,5", "6"}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(new AdapterListView());
    }

    public static int getScreenWidth(Activity context) {
        DisplayMetrics metrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        return metrics.widthPixels;
    }

    private class AdapterListView extends BaseAdapter {

        @Override
        public int getCount() {
            if (mDataList == null) {
                return 0;
            }

            return mDataList.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_listview, parent, false);
                ((CustomView) convertView).setDatas(mDataList[position]);
            }

            return convertView;
        }
    }
}
