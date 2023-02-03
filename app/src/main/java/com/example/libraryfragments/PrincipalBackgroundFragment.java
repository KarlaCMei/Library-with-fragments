package com.example.libraryfragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuProvider;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.libraryfragments.adapters.CustomBookAdapter;
import com.example.libraryfragments.adapters.OnClicBookListener;
import com.example.libraryfragments.data.Book;
import com.example.libraryfragments.data.BookList;
import com.example.libraryfragments.databinding.FragmentPrincipalBackgroundBinding;

public class PrincipalBackgroundFragment extends Fragment implements OnClicBookListener {
    private FragmentPrincipalBackgroundBinding binding;
    private CustomBookAdapter adapter;

    @SuppressLint("NotifyDataSetChanged")
    ActivityResultLauncher<Intent> startActivityIntent = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent intent = result.getData();
                    if (intent != null && intent.getExtras() != null && intent.getExtras().containsKey("CREATE_BOOK")) {
                        if (intent.getExtras().getBoolean("CREATE_BOOK")) {
                            if (adapter != null) {
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }
                }
            });


    public PrincipalBackgroundFragment() {
        // Required empty public constructor
    }


    public static PrincipalBackgroundFragment newInstance() {
        return new PrincipalBackgroundFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_principal_background, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        configToolbar();

        adapter = new CustomBookAdapter(BookList.getBooks(), this);
        binding.listBooks.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        binding.listBooks.setAdapter(adapter);

        binding.btnCreateBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_principalBackgroundFragment_to_createBook);
            }
        });

        requireActivity().addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.menu_home_principal,menu);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                return false;
            }
        });

    }


    @Override
    public void onBookClicListener(Book book, View view) {
        Bundle detailActivity = new Bundle();
        detailActivity.putString(Constants.TITULO, book.getTitulo());
        detailActivity.putString(Constants.GENDER, book.getGenero());
        detailActivity.putString(Constants.DESCRIPTION, book.getDescripcion());
        detailActivity.putInt(Constants.NUM_PAGES, book.getPageNum());
        detailActivity.putInt(Constants.NUM_COPIES, book.getCopies());
        detailActivity.putString(Constants.URL_IMG, book.getUrlImg());
        detailActivity.putStringArrayList(Constants.LIST_CHARACTERS, book.getCharacters());
        Navigation.findNavController( view).navigate(R.id.action_principalBackgroundFragment_to_detailBook,detailActivity);

    }

    private void configToolbar() {
        ((AppCompatActivity) requireActivity()).setSupportActionBar(binding.toolbar2);
        if (((AppCompatActivity) requireActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) requireActivity()).getSupportActionBar().setTitle("Home");
        }
    }
}