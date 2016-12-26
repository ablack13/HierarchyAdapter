package ablack13.hierarchyadapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import ablack13.hierarchyadapter.adapter.FoldersSpinnerAdapter;

/**
 * Created by ablack13 on 26.12.16.
 */

public class SpinnerExampleFragment extends Fragment {
    private Spinner spinner;
    private FoldersSpinnerAdapter adapter;

    public static SpinnerExampleFragment newInstance() {
        SpinnerExampleFragment fragment = new SpinnerExampleFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_example_spinner, container, false);
        spinner = (Spinner) view.findViewById(R.id.spinner);

        adapter = new FoldersSpinnerAdapter(getContext());
        spinner.setAdapter(adapter);
        spinner.setDropDownWidth((int) getResources().getDimension(R.dimen.drop_down_width));
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter.setItems(ListDataManager.getData());
    }
}
