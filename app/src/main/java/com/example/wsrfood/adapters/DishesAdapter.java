package com.example.wsrfood.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.wsrfood.R;
import com.example.wsrfood.models.Dish;

import java.util.ArrayList;
import java.util.List;

public class DishesAdapter extends RecyclerView.Adapter<DishesAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private List<Dish> dishes = new ArrayList<>();
    private final Context context;
    private OnItemClickListener onItemClickListener;

    public DishesAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public DishesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.dish_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DishesAdapter.ViewHolder holder, int position) {
        Dish dish = dishes.get(position);
        holder.name.setText(dish.getName());
        holder.price.setText("N" + dish.getPrice());
        Glide.with(context)
                .load(dish.getIcon())
                .into(holder.logo);
        holder.itemView.setOnClickListener(view -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(dish);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dishes.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Dish dish);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name, price;
        private final ImageView logo;

        ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            price = view.findViewById(R.id.price);
            logo = view.findViewById(R.id.imageViewPhoto);
        }
    }
}
