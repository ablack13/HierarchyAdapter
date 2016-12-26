package ablack13.hierarchyadapter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ablack13.hierarchy_adapter.ListViewHierarchyAdapter;
import ablack13.hierarchyadapter.R;
import ablack13.hierarchyadapter.bean.Folder;

/**
 * Created by ablack13 on 26.12.16.
 */

public class FoldersSpinnerAdapter extends ListViewHierarchyAdapter<Folder, FoldersSpinnerAdapter.FolderViewHolder> implements ListViewHierarchyAdapter.DropDownViewHolderSupport<Folder, FoldersSpinnerAdapter.FolderDropDownViewHolder> {

    public FoldersSpinnerAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public void onBindHierarchyViewHolder(FolderViewHolder holder, int position) {
        Folder item = getItem(position);
        if (item != null) {
            holder.tvName.setText(item.getName());
        }
    }

    @Override
    public FolderViewHolder onCreateHierarchyViewHolder(ViewGroup parent, int viewType) {
        return new FolderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_item, parent, false));
    }

    @Override
    public void onBindDropDownHierarchyViewHolder(FolderDropDownViewHolder viewHolder, int position) {
        Folder item = getItem(position);
        if (item != null) {
            viewHolder.tvName.setText(item.getName());
        }
    }

    @Override
    public FolderDropDownViewHolder onCreateDropDownHierarchyViewHolder(ViewGroup parent, int viewType) {
        return new FolderDropDownViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
    }

    public static class FolderViewHolder extends ListViewHierarchyAdapter.HierarchyViewHolder {
        TextView tvName;

        public FolderViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.text);
        }

        @Override
        public int getMarginViewId() {
            return 0;
        }

        @Override
        public int getLevelingViewId() {
            return 0;
        }

        @Override
        public int getRelativeLayoutContentViewId() {
            return 0;
        }
    }

    public static class FolderDropDownViewHolder extends ListViewHierarchyAdapter.DropDownHierarchyViewHolder {
        TextView tvName;

        public FolderDropDownViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_text);
        }

        @Override
        public int getMarginViewId() {
            return R.id.view_margin;
        }

        @Override
        public int getLevelingViewId() {
            return R.id.iv_icon;
        }

        @Override
        public int getRelativeLayoutContentViewId() {
            return R.id.rl_content;
        }
    }
}
