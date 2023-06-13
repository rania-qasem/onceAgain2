package com.example.onceagain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Car_Adapter extends ArrayAdapter<Car> {

    private List<Car> carItemList;
    private Context context;

    public Car_Adapter(@NonNull Context context, List<Car> carItemList) {
        super(context, R.layout.car_item, carItemList);

        this.context = context;
        this.carItemList = carItemList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.car_item, parent, false);
        }


        Car car = carItemList.get(position);

//        ImageView imageView = convertView.findViewById(R.id.item_image);
        TextView nameTextView = convertView.findViewById(R.id.item_name);
        TextView priceTextView = convertView.findViewById(R.id.item_price);

//        imageView.setImageResource(carItem.getImgID());
        nameTextView.setText(car.getCarType());
        priceTextView.setText(car.getPrice());
        return convertView;
    }

    @Override
    public int getCount() {
        return carItemList.size();
    }

    @Override
    public Car getItem(int position) {
        return carItemList.get(position);
    }

}
