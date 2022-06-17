package com.example.mynewsapps;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import com.example.mynewsapps.Adapter.HomeFragmentAdapter;


public class HomeFragment extends Fragment {

    TextView tv_for_greeting;
    RecyclerView recyclerView1;
    HomeFragmentAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View homeView = inflater.inflate(R.layout.fragment_home, container, false);

        Bundle bundle = getArguments();
        String username = bundle.getString("username");
//        tv_for_greeting.setText(username);

        recyclerView1 = homeView.findViewById(R.id.recyclerView_for_home_page);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new GridLayoutManager(getActivity(),2));
        adapter = new HomeFragmentAdapter(getActivity(), username);
        recyclerView1.setAdapter(adapter);


        return homeView;
    }



}