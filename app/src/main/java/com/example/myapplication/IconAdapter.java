package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class IconAdapter extends BaseAdapter {
    Context context;
    String[] notesTittle;
    String[] notesDesc;
    int[] images;

    public IconAdapter(Context context, String[] notesTittle, String[] notesDesc, int[] images) {
        this.context = context;
        this.notesTittle = notesTittle;
        this.notesDesc = notesDesc;
        this.images = images;
    }

    @Override
    public int getCount() {
        return notesTittle.length;
    }

    @Override
    public Object getItem(int position) {
        return notesTittle[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.gridview_layout,null);
        TextView title = convertView.findViewById(R.id.tv_title);
        TextView desc = convertView.findViewById(R.id.tv_desc);
        ImageView imageView = convertView.findViewById(R.id.image_view);
        title.setText(notesTittle[position]);
        desc.setText(notesDesc[position]);
        imageView.setImageResource(images[position]);
        return convertView;
    }
}
