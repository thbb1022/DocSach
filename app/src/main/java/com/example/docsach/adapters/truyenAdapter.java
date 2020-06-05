package com.example.docsach.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.GenericLifecycleObserver;

import com.bumptech.glide.Glide;
import com.example.docsach.R;

import java.util.ArrayList;
import java.util.List;

import com.example.docsach.objects.truyen;

public class truyenAdapter extends ArrayAdapter<truyen> {
    private Context ct;
    ArrayList<truyen> arr;
    public truyenAdapter(Context context, int resource, List<truyen> objects) {
        super(context, resource, objects);
        this.ct = context;
        this.arr = new ArrayList<>(objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_truyen, null);
        }

        if (arr.size() > 0) {
            truyen truyen = this.arr.get(position);
            TextView tenTruyen = convertView.findViewById(R.id.txtTenTruyen);
            TextView tenChap = convertView.findViewById(R.id.txtTenChap);
            ImageView imgAnhTruyen = convertView.findViewById(R.id.imAnhTruuyen);

            tenTruyen.setText(truyen.getTenTruyen());
            tenChap.setText(truyen.getTenChap());
            Glide.with(this.ct).load(truyen.getAnhTruyen()).into(imgAnhTruyen);
        }
        return convertView;
    }
}
