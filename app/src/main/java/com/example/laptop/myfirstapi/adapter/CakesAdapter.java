package com.example.laptop.myfirstapi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.laptop.myfirstapi.R;
import com.example.laptop.myfirstapi.model.CakesModel;
import com.squareup.picasso.Picasso;

import java.util.List;


public class CakesAdapter extends RecyclerView.Adapter<CakesAdapter.CakesViewHolder> {

    //List<FlowersModel> cakesModel;
    List<CakesModel> cakesModel;
    int row_cakes;
    Context applicationContext;


    //public CakesAdapter(List<FlowersModel> cakesModels, int row_cakes, Context applicationContext) {
    public CakesAdapter(List<CakesModel> cakesModels, int row_cakes, Context applicationContext) {


        this.cakesModel = cakesModels;
        this. row_cakes = row_cakes;
        this.applicationContext = applicationContext;

    }

    @Override
    public CakesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(row_cakes, parent, false);

        return new CakesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CakesViewHolder holder, int position) {

        holder.tvTitle.setText(cakesModel.get(position).getTitle());
        holder.tvDesc.setText(cakesModel.get(position).getDesc());

        String url = cakesModel.get(position).getImage();

        Picasso.with(applicationContext).load(url)
                .into(holder.ivImage);

        //Double price_hold = cakesModel.get(position).getPrice();
        //String price_str = String.valueOf(price_hold);


        //holder.tvName.setText(cakesModel.get(position).getName());
        //holder.tvCat.setText(cakesModel.get(position).getCategory());
        //holder.tvPrice.setText(price_str);
        //holder.tvInstr.setText(cakesModel.get(position).getInstructions());




    }

    @Override
    public int getItemCount() {

        return cakesModel.size();
    }

    public static class CakesViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle;
        TextView tvDesc;
        ImageView ivImage;

        //TextView tvName;
        //TextView tvCat;
        //TextView tvPrice;
        //TextView tvInstr;

        public CakesViewHolder(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            tvDesc = (TextView) itemView.findViewById(R.id.txtDesc);
            ivImage = (ImageView) itemView.findViewById(R.id.imgImage);

            //tvName = (TextView) itemView.findViewById(R.id.txtName);
            //tvCat = (TextView) itemView.findViewById(R.id.txtCat);
            //tvPrice = (TextView) itemView.findViewById(R.id.txtPrice);
            //tvInstr = (TextView) itemView.findViewById(R.id.txtInstr);

            itemView.setTag(itemView);
        }
    }
}
