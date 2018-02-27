package com.example.admin1.navshishu;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link videos.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link videos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class videos extends Fragment {
    TextView tv=null;
    private ListView listView;
    private videoadapter picsadapter;
    private List<pics> piclist;
    private DatabaseReference mDatabaseRef;
    ProgressDialog mProgressDialog;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public videos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Gallery_videos.
     */
    // TODO: Rename and change types and number of parameters
    public static videos newInstance(String param1, String param2) {
        videos fragment = new videos();
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
        //((Main2Activity)getActivity()).setActionBarTitle("Videos");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview;
        // Inflate the layout for this fragment

        rootview= inflater.inflate(R.layout.fragment_videos, container, false);
        Log.d("harry123", "start second Activity");
        listView = (ListView)rootview.findViewById(R.id.list_view);
        piclist = new ArrayList();

        Log.d("harry123", "Update onPreExecute start");
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage("Loading....");
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.show();

        try {

            mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Videos");
//                mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Political_Science/Semester_2/Political_Processes_In_India");
            Log.d("harry123", "Do In back 1");
            mDatabaseRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    String str = dataSnapshot.getValue().toString();

                    Log.d("harry12345","Retriving value....");
                    String str1 = str.replaceAll("\\[(.*?)\\]", "");
                    //int pos=str1.indexOf("=");
                   // str1=str1.substring(pos+1);
                    //str1 = str1.replaceAll("=", "");
                    System.out.println(str1);
                    ArrayList<String> stm = new ArrayList<>();
                    //str1 = str1.replaceAll("\\{[^{}]*\\}", "");
                    //str1 = str1.replaceAll("\\{[^{}]*\\}", "");
                    System.out.println(str1);
                    str1 = str1.substring(1, str1.length()-1);
                    System.out.println(str1);
                    String[] st = str1.split(",");
                    for(int i=0;i<st.length;i++)
                    {
                        Log.d("harry12345",st[i].trim());
                        piclist.add(new pics(st[i].trim()));
                        picsadapter = new videoadapter(getActivity(), piclist);
                        listView.setAdapter(picsadapter);
                        stm.add(st[i].trim());
                    }
                    mProgressDialog.dismiss();
                }




                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Getting Post failed, log a message
                    Log.d("harry123", "loadPost:onCancelled", databaseError.toException());
                    // ...
                }
            });
        } catch (Exception e) {
//             	b.setText("title");
            System.out.println("title2 : ");
            e.printStackTrace();
        }
        picsadapter = new videoadapter(getActivity(),piclist);
        listView.setAdapter(picsadapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                //Toast.makeText(getActivity(), "Clicked product id "+view.getTag(), Toast.LENGTH_SHORT).show();
//                Intent n = new Intent(this, Main3Activity.class);
               String t= view.getTag().toString();

                int pos=t.indexOf("=");
                t=t.substring(pos+1);
                Log.d("harry123","string is--"+t);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(t));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setPackage("com.google.android.youtube");
                startActivity(intent);
            }


        });

        return rootview;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

