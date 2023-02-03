package com.example.libraryfragments.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libraryfragments.R;
import com.example.libraryfragments.data.Book;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomBookAdapter extends RecyclerView.Adapter<CustomBookAdapter.ViewHolder> {

    private ArrayList<Book> mDataSet;
    private OnClicBookListener listener;

    public CustomBookAdapter(ArrayList<Book> dataSet, OnClicBookListener listener) {
        mDataSet = dataSet;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CustomBookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_book_list_item,parent,false);

        ViewHolder vh = new ViewHolder(v);
        vh.setListener(this.listener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomBookAdapter.ViewHolder holder, int position) {
        Book myBook = mDataSet.get(position);
        holder.setBook(myBook);
        holder.getTextTitulo().setText(myBook.getTitulo());
        holder.getTextGender().setText(myBook.getGenero());
        ImageView ivBasicImage =holder.getImgUrl();
        Picasso.with(ivBasicImage.getContext()).load(myBook.getUrlImg()).into(ivBasicImage);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView textTitulo;
        private final TextView textGender;
        private final ImageView imgUrl;
        private Book book;
        private OnClicBookListener listener;

        public ViewHolder(View v) {
            super(v);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("onClick", "Element " + getBook().getTitulo() + " clicked desde adapter");
                    getListener().onBookClicListener(getBook(), v);
                }
            });
            textTitulo = v.findViewById(R.id.text_titulo);
            textGender = v.findViewById(R.id.text_genero);
            imgUrl = v.findViewById(R.id.img_book);
        }

        public TextView getTextTitulo() {
            return textTitulo;
        }

        public TextView getTextGender() {
            return textGender;
        }

        public ImageView getImgUrl() {
            return imgUrl;
        }

        public Book getBook() {
            return book;
        }

        public void setBook(Book book) {
            this.book = book;
        }

        public OnClicBookListener getListener() {
            return listener;
        }

        public void setListener(OnClicBookListener listener) {
            this.listener = listener;
        }
    }
}
