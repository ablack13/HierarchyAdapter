package ablack13.hierarchyadapter.adapter;

import android.content.Context;
import android.graphics.Color;
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
    private int leftMargin = 16;
    private int underCircleLineWidth = 2;
    private int underCircleLineHeight = 22;
    private int underCircleLineRightMargin = -18;
    private int topVerticalLineWidth = 2;
    private int topVerticalLineHeight = 32;
    private int topVerticalLineRightMargin = 8;
    private int bottomVerticalLineWidth = 2;
    private int bottomVerticalLineHeight = 32;
    private int bottomVerticalLineRightMargin = 8;
    private int leftHorizontalLineWidth = 8;
    private int leftHorizontalLineHeight = 2;
    private int leftHorizontalLineRightMargin = 2;


    public HierarchyAdapter(Context context) {
        items = new ArrayList<>();
        float density = context.getResources().getDisplayMetrics().density;
        this.leftMargin *= density;
        this.underCircleLineWidth *= density;
        this.underCircleLineHeight *= density;
        this.underCircleLineRightMargin *= density;
        this.topVerticalLineWidth *= density;
        this.topVerticalLineHeight *= density;
        this.topVerticalLineRightMargin *= density;
        this.bottomVerticalLineWidth *= density;
        this.bottomVerticalLineHeight *= density;
        this.bottomVerticalLineRightMargin *= density;
        this.leftHorizontalLineWidth *= density;
        this.leftHorizontalLineHeight *= density;
        this.leftHorizontalLineRightMargin *= density;
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
            setLeftMargin(holder, item);
            drawTopVerticalLine(holder, item);
            drawUnderCircleVerticalLine(holder, item);
            drawBottomVerticalLine(holder, item);
            drawLeftHorizontalLine(holder, item);
        }
    }

    private void setLeftMargin(ViewHolder holder, Item item) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) holder.margin.getLayoutParams();
        params.leftMargin = leftMargin * item.level;
        holder.margin.setLayoutParams(params);
    }

    public Item getItem(int position) {
        if (position < getItemCount()) {
            return items.get(position);
        }
        return null;
    }

    public Item getNextItem(int position) {
        if ((position + 1) < getItemCount()) {
            return items.get(position + 1);
        }
        return null;
    }

    public void setItems(List<Item> items) {
        this.items = (ArrayList<Item>) items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private View margin;
        private ImageView ivIco;
        private TextView tvText;
        private RelativeLayout rlContent;

        public ViewHolder(View itemView) {
            super(itemView);

            margin = itemView.findViewById(R.id.view_margin);
            ivIco = (ImageView) itemView.findViewById(R.id.iv_icon);
            tvText = (TextView) itemView.findViewById(R.id.tv_text);
            rlContent = (RelativeLayout) itemView.findViewById(R.id.rl_content);
        }
    }

    private void drawTopVerticalLine(ViewHolder holder, Item item) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(topVerticalLineWidth, topVerticalLineHeight);
        params.addRule(RelativeLayout.LEFT_OF, R.id.iv_icon);
        params.rightMargin = topVerticalLineRightMargin;
        View view = new View(holder.rlContent.getContext());
        view.setBackgroundColor(Color.GREEN);
        holder.rlContent.addView(view, topVerticalLineWidth, topVerticalLineHeight);
        view.setLayoutParams(params);
    }

    private void drawBottomVerticalLine(ViewHolder holder, Item item) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(bottomVerticalLineWidth, bottomVerticalLineHeight);
        params.addRule(RelativeLayout.LEFT_OF, R.id.iv_icon);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.rightMargin = bottomVerticalLineRightMargin;
        View view = new View(holder.rlContent.getContext());
        view.setBackgroundColor(Color.BLUE);
        holder.rlContent.addView(view, bottomVerticalLineWidth, bottomVerticalLineHeight);
        view.setLayoutParams(params);
    }

    private void drawLeftHorizontalLine(ViewHolder holder, Item item) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(leftHorizontalLineWidth, leftHorizontalLineHeight);
        params.addRule(RelativeLayout.LEFT_OF, R.id.iv_icon);
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        params.rightMargin = leftHorizontalLineRightMargin;
        View view = new View(holder.rlContent.getContext());
        view.setBackgroundColor(Color.RED);
        holder.rlContent.addView(view, bottomVerticalLineWidth, bottomVerticalLineHeight);
        view.setLayoutParams(params);
    }

    private void drawUnderCircleVerticalLine(ViewHolder holder, Item item) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(underCircleLineWidth, underCircleLineHeight);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.RIGHT_OF, R.id.iv_icon);
        params.leftMargin = underCircleLineRightMargin;
        View view = new View(holder.rlContent.getContext());
        view.setBackgroundColor(Color.RED);
        holder.rlContent.addView(view, underCircleLineWidth, underCircleLineHeight);
        view.setLayoutParams(params);
    }
}
