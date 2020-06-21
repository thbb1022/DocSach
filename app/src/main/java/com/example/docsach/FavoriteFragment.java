package com.example.docsach;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoriteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoriteFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    List<String> bookId_list;
    List<Book> lstRoot;
    List<Book> lstBook;
    List<Favorite_item> fv_list;
    FirebaseUser user;
    String uID;
    View view;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavoriteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavoriteFragment newInstance(String param1, String param2) {
        FavoriteFragment fragment = new FavoriteFragment();
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
        view =  inflater.inflate(R.layout.fragment_favorite, container, false);
        final Context context = container.getContext();

        fv_list = new ArrayList<>();
        lstBook = new ArrayList<>();
        myData m = new myData();
        lstRoot = m.myList();

        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            uID = user.getUid().toString();
            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
            DatabaseReference bookRef = rootRef.child("Favorite");
            ValueEventListener eventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot ds : dataSnapshot.getChildren()) {
                        Favorite_item item = ds.getValue(Favorite_item.class);
                        String id = item.uId;
                        if(id.equals(uID) ){
                            Log.d("aaa", uID);
                            String stt = item.bookId;
                            for(Book b: lstRoot){
                                int x =Integer.parseInt(stt) - 1;
                                String s = String.valueOf(x);
                                if(s.equals(b.getStt().toString()) ){
                                    lstBook.add(b);
                                }
                            }
                        }
                    }
                    //display
                    RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(context, lstBook);
                    RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycleview_favorite_id);
                    recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
                    recyclerView.setAdapter(myAdapter);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {}
            };
            bookRef.addListenerForSingleValueEvent(eventListener);
        }
        return view;
    }
}

