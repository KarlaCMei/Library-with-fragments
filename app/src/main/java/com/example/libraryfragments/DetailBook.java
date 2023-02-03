package com.example.libraryfragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuProvider;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.libraryfragments.databinding.FragmentDetailBookBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailBook extends Fragment {
    private FragmentDetailBookBinding binding;
    int contador = 0;
    private String tituloLibro;


    public DetailBook() {
    }

    public static DetailBook newInstance() {

        return new DetailBook();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_detail_book, container, false);
        return binding.getRoot();
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        readExtra();

        contador = Integer.parseInt(binding.editNumCopies.getText().toString());
        binding.btnPrestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(contador == 0){
                    Toast.makeText(requireActivity(), "Ya no contamos con libros para prestar", Toast.LENGTH_LONG).show();
                }else{
                    contador--;
                    binding.editNumCopies.setText(String.valueOf(contador));
                }
            }
        });


       /* requireActivity().addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.menu_home,menu);

            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.share:
                        Toast.makeText(requireContext(), "Lee mi libro favorito: " + tituloLibro , Toast.LENGTH_SHORT).show();
                        return true;

                    case android.R.id.home:
                        requireActivity().onBackPressed();
                        return true;
                    default:
                        return false;
                }
            }
        });*/

    }

    private void readExtra(){


        if(getArguments()!= null && getArguments().containsKey(Constants.TITULO)){
            //binding.textDescriptionTitle.setText(("TITULO" + getArguments().getString(Constants.TITULO)));
            tituloLibro = getArguments().getString(Constants.TITULO);
            //configToolbar(tituloLibro);

            binding.toolbarBookDetail.setTitle(tituloLibro);
        }

        if(getArguments()!= null && getArguments().containsKey(Constants.GENDER)){
            binding.textGender.setText((getArguments().getString(Constants.GENDER)));
        }

        if(getArguments()!= null && getArguments().containsKey(Constants.LIST_CHARACTERS)){
            //binding.textGender.setText(("LIST_CHARACTERS" + getArguments().getString(Constants.LIST_CHARACTERS)));
            ArrayList<String> characters = getArguments().getStringArrayList(Constants.LIST_CHARACTERS);
            for(String chacrter:characters){
                binding.textCharacters.append(getString(R.string.txt_name_character_detail_activity,chacrter));
            }
        }

        if(getArguments()!= null && getArguments().containsKey(Constants.DESCRIPTION)){
            binding.textDescription.setText((getArguments().getString(Constants.DESCRIPTION)));
        }

        if(getArguments()!= null && getArguments().containsKey(Constants.NUM_PAGES)){
            binding.editNumPages.setText(String.valueOf(getArguments().getInt(Constants.NUM_PAGES)));
        }

        if(getArguments()!= null && getArguments().containsKey(Constants.NUM_COPIES)){
            binding.editNumCopies.setText(String.valueOf(getArguments().getInt(Constants.NUM_COPIES)));
        }

        if(getArguments()!= null && getArguments().containsKey(Constants.URL_IMG)){
            Picasso.with(requireContext()).load(getArguments().getString(Constants.URL_IMG)).into(binding.imgBook);

        }


    }


    /*private void configToolbar(String toolbarName) {
        ((AppCompatActivity) requireActivity()).setSupportActionBar(binding.toolbarBookDetail);
        if (((AppCompatActivity) requireActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) requireActivity()).getSupportActionBar().setTitle(toolbarName);
            ((AppCompatActivity) requireActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }
    }*/

}