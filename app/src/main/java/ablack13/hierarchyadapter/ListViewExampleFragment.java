package ablack13.hierarchyadapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import ablack13.hierarchyadapter.adapter.FoldersListViewAdapter;

/**
 * Created by ablack13 on 25.12.16.
 */

public class ListViewExampleFragment extends Fragment {
    private ListView listView;
    private FoldersListViewAdapter adapter;

    public static ListViewExampleFragment newInstance() {
        ListViewExampleFragment fragment = new ListViewExampleFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_example_listview, container, false);

        listView = (ListView) view.findViewById(R.id.list_view);
        adapter = new FoldersListViewAdapter(getContext());
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter.setItems(ListDataManager.getData());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (adapter != null) {
            adapter.onDettach();
        }
    }
}
