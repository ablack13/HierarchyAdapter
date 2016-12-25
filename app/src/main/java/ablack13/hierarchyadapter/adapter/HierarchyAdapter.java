package ablack13.hierarchyadapter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ablack13.hierarchyadapter.R;

/**
 * Created by ablack13 on 24.12.16.
 */

public class HierarchyAdapter extends RecyclerView.Adapter<HierarchyAdapter.ViewHolder> {
    private ArrayList<Item> items;
    private HierarchyAdapterHelper adapterHelper;

    public HierarchyAdapter(Context context) {
        items = new ArrayList<>();
        adapterHelper = new HierarchyAdapterHelper(context, this);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        if (adapterHelper != null) {
            adapterHelper.onDettach();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = getItem(position);
        if (item != null) {
            holder.tvText.setText(item.name);
            adapterHelper.setLeftMargin(holder, item);

            adapterHelper.removeAllDrawables(holder);
            adapterHelper.drawTopVerticalLine(holder, item, position);
            adapterHelper.drawBottomVerticalLine(holder, item, position);
            adapterHelper.drawUnderCircleVerticalLine(holder, item, position);
            adapterHelper.drawLeftHorizontalLine(holder, item, position);
        }
    }


    public Item getItem(int position) {
        if (position < getItemCount()) {
            return items.get(position);
        }
        return null;
    }

    public Item getPreviousItem(int position) {
        int previousPosition = position - 1;
        if (previousPosition >= 0 && previousPosition < getItemCount()) {
            return items.get(previousPosition);
        }
        return null;
    }

    public Item getNextItem(int position) {
        int nextPosition = position + 1;
        if (nextPosition >= 0 && nextPosition < getItemCount()) {
            return items.get(nextPosition);
        }
        return null;
    }

    public void setItems(List<Item> items) {
        this.items = (ArrayList<Item>) items;
        notifyDataSetChanged();
    }

    public List<Item> getItems() {
        return items;
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        View margin;
        ImageView ivIco;
        TextView tvText;
        RelativeLayout rlContent;

        public ViewHolder(View itemView) {
            super(itemView);

            margin = itemView.findViewById(R.id.view_margin);
            ivIco = (ImageView) itemView.findViewById(R.id.iv_icon);
            tvText = (TextView) itemView.findViewById(R.id.tv_text);
            rlContent = (RelativeLayout) itemView.findViewById(R.id.rl_content);
        }
    }
}
