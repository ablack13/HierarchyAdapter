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
 * Created by ablack13 on 25.12.16.
 */

public class FoldersListViewAdapter extends ListViewHierarchyAdapter<Folder, FoldersListViewAdapter.ViewHolder> {
    public FoldersListViewAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public void onBindHierarchyViewHolder(ViewHolder holder, int position) {
        Folder item = getItem(position);
        if (item != null) {
            holder.tvName.setText(item.getName());
        }
    }

    @Override
    public ViewHolder onCreateHierarchyViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
    }


    public static class ViewHolder extends ListViewHierarchyAdapter.HierarchyViewHolder {
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
