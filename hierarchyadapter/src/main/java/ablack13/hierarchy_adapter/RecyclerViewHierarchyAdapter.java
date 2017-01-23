package ablack13.hierarchy_adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ablack13 on 24.12.16.
 */

public abstract class RecyclerViewHierarchyAdapter<T extends HierarchyItem, VH extends RecyclerViewHierarchyAdapter.HierarchyViewHolder> extends RecyclerView.Adapter<VH>
        implements IHierarchyRecyclerAdapter<T, VH> {
    @NonNull
    private ArrayList<T> items;
    private HierarchyRecyclerViewAdapterHelper adapterHelper;

    public RecyclerViewHierarchyAdapter(Context context) {
        items = new ArrayList<>();
        adapterHelper = new HierarchyRecyclerViewAdapterHelper<>(context, this);
    }

    @Override
    public List<T> getItems() {
        return items;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        onDettach();
    }

    @Override
    public void onDettach() {
        if (adapterHelper != null) {
            adapterHelper.onDettach();
        }
    }

    @Override
    public void setItems(List<T> items) {
        setItemsWithoutDataNotify(items);
        notifyDataSetChanged();
    }

    @Override
    public void setItemsWithoutDataNotify(List<T> items) {
        this.items = (ArrayList<T>) items;
    }


    @Override

    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return onCreateHierarchyViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        T item = getItems().get(position);
        if (item != null) {
            adapterHelper.setLeftMargin(holder, item);

            adapterHelper.removeAllDrawables(holder);
            adapterHelper.drawTopVerticalLine(holder, item, position);
            adapterHelper.drawBottomVerticalLine(holder, item, position);
            adapterHelper.drawUnderCircleVerticalLine(holder, item, position);
            adapterHelper.drawLeftHorizontalLine(holder, item, position);
        }
        onBindHierarchyViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public static abstract class HierarchyViewHolder extends RecyclerView.ViewHolder implements IHierarchyViewHolder {
        View margin;
        RelativeLayout rlContent;

        public HierarchyViewHolder(View itemView) {
            super(itemView);
            margin = itemView.findViewById(getMarginViewId());
            rlContent = (RelativeLayout) itemView.findViewById(getRelativeLayoutContentViewId());
        }
    }
}
