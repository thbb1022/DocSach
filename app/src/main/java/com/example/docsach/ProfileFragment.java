package com.example.docsach;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
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
    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
    public static final String TAG = MainActivity.class.getSimpleName(); //debug log
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final View view = inflater.inflate(R.layout.fragment_profile, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById((R.id.recycleview_id));
        Button btnLogOut = (Button)view.findViewById(R.id.btnLogout);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase database =FirebaseDatabase.getInstance();
        DatabaseReference myData = database.getReference("User");

        if (user != null) {
            // Name, email address, and profile photo Url
            String id = user.getUid();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();
            TextView EmailInfo = (TextView) view.findViewById(R.id.emailInfo);
            final TextView UserNameInfo = (TextView) view.findViewById(R.id.usernameInfo);
            EmailInfo.setText(email);


            myData.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String name1 = dataSnapshot.child("name").getValue().toString();
                    UserNameInfo.setText(name1);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }


            });

        }
        //view =  inflater.inflate(R.layout.fragment_profile, container, false);
        final Context context = container.getContext();

        fv_list = new ArrayList<>();
        lstBook = new ArrayList<>();
        myData m = new myData();
        lstRoot = m.myList();

        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            uID = user.getUid();
            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
            DatabaseReference moviesRef = rootRef.child("Recently");
            ValueEventListener eventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        Favorite_item item = ds.getValue(Favorite_item.class);
                        String id = item.uId;
                        if (id.equals(uID)) {
                            Log.d("aaa", uID);
                            String stt = item.bookId;
                            for (Book b : lstRoot) {
                                 if (stt.equals(b.getStt().toString())) {
                                    lstBook.add(b);
                            }
                        }
                        //display
                        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(context, lstBook);
                        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycleview_favorite_id);
                        recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
                        recyclerView.setAdapter(myAdapter);

                    }
                }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {}
            };
            moviesRef.addListenerForSingleValueEvent(eventListener);
        }


        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity().getApplicationContext(), MainActivity.class));
            }
        });



        return view;

    }
}
