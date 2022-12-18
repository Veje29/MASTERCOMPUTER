package umn.ac.id.uas.project;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import umn.ac.id.uas.project.adapter.IntelAdapter;
import umn.ac.id.uas.project.model.ProcessorIntelModel;
public class ProcessorRecyclerViewFragment extends Fragment {
    Context context;
    ArrayList<ProcessorIntelModel> intelI9;
    ArrayList<ProcessorIntelModel> intelI7;

    public ProcessorRecyclerViewFragment(Context context, ArrayList<ProcessorIntelModel> intelI9, ArrayList<ProcessorIntelModel> intelI7) {
        this.context = context;
        this.intelI9 = intelI9;
        this.intelI7 = intelI7;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_processor_recycler_view, container, false);

        RecyclerView intelI9RecyclerView = view.findViewById(R.id.recycler_view_intel_i9);
        IntelAdapter intelI9Adapter = new IntelAdapter(context, intelI9);
        intelI9RecyclerView.setAdapter(intelI9Adapter);
        intelI9RecyclerView.setLayoutManager(new LinearLayoutManager(context));

        RecyclerView intelI7RecyclerView = view.findViewById(R.id.recycler_view_intel_i7);
        IntelAdapter intelAdapter = new IntelAdapter(context, intelI7);
        intelI7RecyclerView.setAdapter(intelAdapter);
        intelI7RecyclerView.setLayoutManager(new LinearLayoutManager(context));

        return view;
    }
}