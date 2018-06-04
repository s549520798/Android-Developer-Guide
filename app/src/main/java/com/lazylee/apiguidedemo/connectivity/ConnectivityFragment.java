package com.lazylee.apiguidedemo.connectivity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.lazylee.apiguidedemo.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConnectivityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConnectivityFragment extends Fragment {

    public static final String TAG = "ConnectivityFragment";

    private ExpandableListView expandableListView;
    public ConnectivityFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ConnectivityFragment.
     */
    public static ConnectivityFragment newInstance() {
        ConnectivityFragment fragment = new ConnectivityFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.connectivity_fragment,container,false);
        expandableListView = rootView.findViewById(R.id.expand_list);

        return inflater.inflate(R.layout.connectivity_fragment, container, false);
    }

}
