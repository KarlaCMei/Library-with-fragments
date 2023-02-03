package com.example.libraryfragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.libraryfragments.data.Book;
import com.example.libraryfragments.data.BookList;
import com.example.libraryfragments.databinding.FragmentCreateBookBinding;

public class CreateBook extends Fragment {
    private FragmentCreateBookBinding binding;


    public CreateBook() {
        // Required empty public constructor
    }

    public static CreateBook newInstance() {
        return new CreateBook();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_create_book, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnCreateBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createBook();
            }
        });

    }

    public void createBook() {
        Book newBook = new Book();
        newBook.setTitulo(binding.editTitle.getText().toString());
        newBook.setGenero(binding.editGender.getText().toString());
        newBook.setDescripcion(binding.editDescription.getText().toString());
        newBook.setPageNum(Integer.parseInt(binding.editNumPages.getText().toString()));
        newBook.setCopies(Integer.parseInt(binding.editNumCopies.getText().toString()));
        newBook.setUrlImg(binding.editUrlImg.getText().toString());
        BookList.addBook(newBook);

        Bundle finishBundle = new Bundle();
        finishBundle.putBoolean("CREATE_BOOK", true);

    }

    /*@Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/
}