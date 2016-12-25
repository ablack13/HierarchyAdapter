package ablack13.hierarchyadapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ablack13.hierarchyadapter.adapter.FoldersRecyclerViewAdapter;

/**
 * Created by ablack13 on 25.12.16.
 */

public class RecyclerViewExampleFragment extends Fragment {
    private RecyclerView recyclerView;
    private FoldersRecyclerViewAdapter adapter;

    public static RecyclerViewExampleFragment newInstance() {
        RecyclerViewExampleFragment fragment = new RecyclerViewExampleFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_example_recyclerview, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new FoldersRecyclerViewAdapter(getContext());
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter.setItems(ListDataManager.getData());
    }
}
