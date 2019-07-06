package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.ProductViewHolder> {
    private Context mtx;
    private List<Pizza> productList;
    private OnItemClickListner mListner;

    public interface OnItemClickListner{
        void  onItemClick(int position);
    }

    public void setOnItemCliclListener(OnItemClickListner listener){
        mListner = listener;
    }

    public PizzaAdapter(Context mtx, List<Pizza> productList) {
        this.mtx = mtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mtx);
        View view = inflater.inflate(R.layout.list_item, null);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        holder.itemView.setTag(productList.get(position));
        Pizza product = productList.get(position);

        String PizzaName = product.getName();
        Double PizzaPrice = product.getPrice();
        String PizzaDescription = product.getDescription();
        String PizzaImage = product.getImgurl();

        holder.textViewname.setText(PizzaName);
        holder.textViewprice.setText("Rs." + PizzaPrice);
        holder.textViewdescription.setText(PizzaDescription);

        Log.e("Pizza Adapter",PizzaImage);
         Picasso.get().load(PizzaImage).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewname, textViewdescription, textViewprice;
        public ImageView imageView;

        public ProductViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textViewname = itemView.findViewById(R.id.name);
            textViewdescription = itemView.findViewById(R.id.description);
            textViewprice = itemView.findViewById(R.id.price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListner != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListner.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

}
