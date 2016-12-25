package ablack13.hierarchyadapter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ablack13.hierarchy_adapter.RecyclerViewHierarchyAdapter;
import ablack13.hierarchyadapter.R;
import ablack13.hierarchyadapter.bean.Folder;

/**
 * Created by ablack13 on 25.12.16.
 */

public class FoldersRecyclerViewAdapter extends RecyclerViewHierarchyAdapter<Folder, FoldersRecyclerViewAdapter.ViewHolder> {

    public FoldersRecyclerViewAdapter(Context context) {
        super(context);
    }

    @Override
    public void onBindHierarchyViewHolder(ViewHolder holder, int position) {
        Folder folder = getItems().get(position);
        if (folder != null) {
            holder.tvName.setText(folder.getName());
        }
    }

    @Override
    public ViewHolder onCreateHierarchyViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
    }

    public static class ViewHolder extends RecyclerViewHierarchyAdapter.HierarchyViewHolder {
        private TextView tvName;

        public ViewHolder(View itemView) {
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
