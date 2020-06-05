package com.example.docsach;

import com.example.docsach.R;
import com.example.docsach.adapters.truyenAdapter;
import com.example.docsach.objects.truyen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    View view;
    GridView gdvDanhSachTruyen;
    ArrayList<truyen> truyenArrayList;
    //truyenAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);
        gdvDanhSachTruyen = (GridView) view.findViewById(R.id.gdvDanhSach);
        //gdvDanhSachTruyen.setAdapter(new truyenAdapter(this.getActivity())); chua truyen data
        return view;
    }
}
