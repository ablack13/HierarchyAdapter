package ablack13.hierarchy_adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ablack13 on 25.12.16.
 */

public abstract class ListViewHierarchyAdapter<T extends HierarchyItem, VH extends ListViewHierarchyAdapter.HierarchyViewHolder> extends BaseAdapter
        implements IHierarchyListAdapter<T, VH> {
    private ArrayList<T> items;

    private HierarchyListViewAdapterHelper<T, VH> adapterHelper;
    private DropDownViewHolderSupport dropDownViewHolder;

    public ListViewHierarchyAdapter(Context context) {
        this.items = new ArrayList<>();
        if (this instanceof DropDownViewHolderSupport) {
            this.dropDownViewHolder = (DropDownViewHolderSupport) this;
        }
        this.adapterHelper = new HierarchyListViewAdapterHelper(context, this);
    }

    @Override
    public void onDettach() {
        if (adapterHelper != null) {
            adapterHelper.onDettach();
        }
    }

    @Override
    public int getCount() {
        return items != null ? items.size() : 0;
    }

    @Override
    public T getItem(int i) {
        if (i >= items.size()) {
            return null;
        }
        return items.get(i);
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

    public List<T> getItems() {
        return items;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Deprecated
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        VH holder = null;
        if (convertView == null) {
            holder = onCreateHierarchyViewHolder(viewGroup, getItemViewType(position));
            convertView = holder.itemView;
            convertView.setTag(holder);
        } else {
            holder = (VH) convertView.getTag();
        }
        T item = getItem(position);
        if (item != null) {
            if (holder.rlContent != null && holder.margin != null && holder.getLevelingViewId() != 0) {
                adapterHelper.setLeftMargin(holder, item);
                adapterHelper.removeAllDrawables(holder);
                adapterHelper.drawTopVerticalLine(holder, item, position);
                adapterHelper.drawBottomVerticalLine(holder, item, position);
                adapterHelper.drawUnderCircleVerticalLine(holder, item, position);
                adapterHelper.drawLeftHorizontalLine(holder, item, position);
            }
            onBindHierarchyViewHolder(holder, position);
        }
        return convertView;
    }


    public abstract int getItemViewType(int position);

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (dropDownViewHolder != null) {
            DropDownHierarchyViewHolder holder = null;
            if (convertView == null) {
                holder = dropDownViewHolder.onCreateDropDownHierarchyViewHolder(parent, getItemViewType(position));
                convertView = holder.itemView;
                convertView.setTag(holder);
            } else {
                holder = (DropDownHierarchyViewHolder) convertView.getTag();
            }
            T item = getItem(position);
            if (item != null) {
                if (holder.rlContent != null && holder.margin != null && holder.getLevelingViewId() != 0) {
                    adapterHelper.setLeftMargin(holder, item);
                    adapterHelper.removeAllDrawables(holder);
                    adapterHelper.drawTopVerticalLine(holder, item, position);
                    adapterHelper.drawBottomVerticalLine(holder, item, position);
                    adapterHelper.drawUnderCircleVerticalLine(holder, item, position);
                    adapterHelper.drawLeftHorizontalLine(holder, item, position);
                }
                dropDownViewHolder.onBindDropDownHierarchyViewHolder(holder, position);
            }
            return convertView;
        } else {
            return super.getDropDownView(position, convertView, parent);
        }
    }

    public interface DropDownViewHolderSupport<T, VH extends DropDownHierarchyViewHolder> {
        void onBindDropDownHierarchyViewHolder(VH viewHolder, int position);

        VH onCreateDropDownHierarchyViewHolder(ViewGroup parent, int viewType);
    }

    public static abstract class HierarchyViewHolder implements IHierarchyViewHolder {
        public View itemView;
        View margin;
        RelativeLayout rlContent;

        public HierarchyViewHolder(View itemView) {
            this.itemView = itemView;
            this.margin = itemView.findViewById(getMarginViewId());
            this.rlContent = (RelativeLayout) itemView.findViewById(getRelativeLayoutContentViewId());
        }
    }

    public static abstract class DropDownHierarchyViewHolder extends HierarchyViewHolder implements IHierarchyViewHolder {
        public DropDownHierarchyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
