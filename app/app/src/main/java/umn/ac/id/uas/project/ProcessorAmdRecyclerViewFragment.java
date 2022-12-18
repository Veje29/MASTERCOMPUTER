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

public class ProcessorAmdRecyclerViewFragment extends Fragment {
    Context context;
    ArrayList<ProcessorIntelModel> amd4;
    ArrayList<ProcessorIntelModel> amd5;

    public ProcessorAmdRecyclerViewFragment(Context context, ArrayList<ProcessorIntelModel> amd4, ArrayList<ProcessorIntelModel> amd5) {
        this.context = context;
        this.amd4 = amd4;
        this.amd5 = amd5;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_processor_amd_recycler_view, container, false);

        RecyclerView amd4RecyclerView = view.findViewById(R.id.recycler_view_amd4);
        IntelAdapter amd4Adapter = new IntelAdapter(context, amd4);
        amd4RecyclerView.setAdapter(amd4Adapter);
        amd4RecyclerView.setLayoutManager(new LinearLayoutManager(context));

        RecyclerView amd5RecyclerView = view.findViewById(R.id.recycler_view_amd5);
        IntelAdapter intelAdapter = new IntelAdapter(context, amd5);
        amd5RecyclerView.setAdapter(intelAdapter);
        amd5RecyclerView.setLayoutManager(new LinearLayoutManager(context));

        return view;
    }
}