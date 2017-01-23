package ablack13.hierarchy_adapter;

import android.view.ViewGroup;

import java.util.List;

/**
 * Created by ablack13 on 25.12.16.
 */

interface IHierarchyRecyclerAdapter<T extends HierarchyItem, VH extends RecyclerViewHierarchyAdapter.HierarchyViewHolder> {
    void onDettach();

    void onBindHierarchyViewHolder(VH holder, int position);

    VH onCreateHierarchyViewHolder(ViewGroup parent, int viewType);

    void setItems(List<T> items);
    void setItemsWithoutDataNotify(List<T> items);

    List<T> getItems();
}
