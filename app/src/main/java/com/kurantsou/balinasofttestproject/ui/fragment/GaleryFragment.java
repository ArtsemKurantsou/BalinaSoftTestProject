package com.kurantsou.balinasofttestproject.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kurantsou.balinasofttestproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GaleryFragment extends Fragment {


    public GaleryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_galery, container, false);



        return view;
    }

}
