package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class IconAdapter extends BaseAdapter {
    Context context;
//    String[] notesTittle;
//    String[] notesDesc;
//    int[] images;
   public int position;

    List<CategoryModel> categoryModelList;


    public IconAdapter(Context context, List<CategoryModel> categoryModelList) {
        this.context = context;
        this.categoryModelList = categoryModelList;
    }

    @Override
    public int getCount() {
        return categoryModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return categoryModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.gridview_layout,null);



        TextView title = convertView.findViewById(R.id.tv_title);
        TextView desc = convertView.findViewById(R.id.tv_desc);
        TextView date = convertView.findViewById(R.id.tv_date);
//        Button locationbtn = convertView.findViewById(R.id.btn_location);

        title.setText(categoryModelList.get(position).getTitle());
        desc.setText(categoryModelList.get(position).getDescription());
        date.setText(categoryModelList.get(position).getDate());

//        locationbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//        Intent intent = new Intent(context,MapActivity.class);
//        intent.putExtra("id", position);
//        context.startActivity(intent);
//
//            }
//        });


//        ImageView imageView = convertView.findViewById(R.id.image_view);

//        title.setText(notesTittle[position]);
//        desc.setText(notesDesc[position]);
//        imageView.setImageResource(images[position]);

        return convertView;
    }
}
