package com.example.docsach;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LibraryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LibraryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    List<Book> lstBookRoot;
    List<Book> lstBook1;
    List<Book> lstBook2;
    List<Book> lstBook3;


    public LibraryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LibraryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LibraryFragment newInstance(String param1, String param2) {
        LibraryFragment fragment = new LibraryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_library, container, false);
        RecyclerView recyclerView1 = (RecyclerView) view.findViewById(R.id.tienHiep);
        RecyclerView recyclerView2 = (RecyclerView) view.findViewById(R.id.huyenHuyen);
        RecyclerView recyclerView3 = (RecyclerView) view.findViewById(R.id.diGioi);
        //
        init();
        chiaTheLoai();
        //
        Context context = container.getContext();

        RecyclerViewAdapter myAdapter1 = new RecyclerViewAdapter(context, lstBook1);
        RecyclerViewAdapter myAdapter2 = new RecyclerViewAdapter(context, lstBook2);
        RecyclerViewAdapter myAdapter3 = new RecyclerViewAdapter(context, lstBook3);

        recyclerView1.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        recyclerView1.setAdapter(myAdapter1);
        recyclerView2.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        recyclerView2.setAdapter(myAdapter2);
        recyclerView3.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        recyclerView3.setAdapter(myAdapter3);
        return view;
    }

    public void init(){
        myData m = new myData();
        lstBookRoot = m.myList();

    }

    public void chiaTheLoai(){
        lstBook1 = new ArrayList<>();
        lstBook2 = new ArrayList<>();
        lstBook3 = new ArrayList<>();
        for(Book item: lstBookRoot)
        {
            Log.d("ten the loai: ", item.getCategory().toString());

            if(item.getCategory().toString() == "Tiên hiệp"){
                lstBook1.add(item);
            }else if(item.getCategory().toString() == "Huyền huyễn"){
                lstBook2.add(item);
            }else if(item.getCategory().toString() == "Dị giới") {
                lstBook3.add(item);
            }
        }
    }
}