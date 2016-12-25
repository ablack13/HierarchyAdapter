package ablack13.hierarchyadapter.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import ablack13.hierarchyadapter.R;

/**
 * Created by ablack13 on 25.12.16.
 */

public class HierarchyAdapterHelper {
    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);
    private static final String ROOT = "root";

    int leftMargin = 16;
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

    private int topVerticalLineViewId;
    private int bottomVerticalLineViewId;
    private int underCircleVerticalLineViewId;
    private int leftHorizontalLineViewId;

    private List<Integer> linesViewIds;
    private WeakReference<HierarchyAdapter> adapter;

    public HierarchyAdapterHelper(Context context, HierarchyAdapter adapter) {
        this.adapter = new WeakReference<>(adapter);
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

        topVerticalLineViewId = generateViewId();
        bottomVerticalLineViewId = generateViewId();
        underCircleVerticalLineViewId = generateViewId();
        leftHorizontalLineViewId = generateViewId();

        linesViewIds = new ArrayList<>();
    }

    private int generateViewId() {
        for (; ; ) {
            final int result = sNextGeneratedId.get();
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1;
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }

    private HierarchyAdapter getAdapter() {
        return adapter != null ? adapter.get() : null;
    }

    void setLeftMargin(HierarchyAdapter.ViewHolder holder, Item item) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) holder.margin.getLayoutParams();
        params.leftMargin = leftMargin * item.level;
        holder.margin.setLayoutParams(params);
    }

    void drawTopVerticalLine(HierarchyAdapter.ViewHolder holder, Item item, int position) {
        int locLevel = item.level;
        Item previousItem = getAdapter().getPreviousItem(position);
        if (item.level > 0 && previousItem != null && (previousItem.name.equals(item.parent) || (previousItem.parent.equals(item.parent)))) {
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(topVerticalLineWidth, topVerticalLineHeight);
            params.addRule(RelativeLayout.LEFT_OF, R.id.iv_icon);
            params.rightMargin = topVerticalLineRightMargin;
            View view = new View(holder.rlContent.getContext());
            view.setId(topVerticalLineViewId + locLevel);
            view.setBackgroundColor(Color.BLUE);
            holder.rlContent.addView(view, topVerticalLineWidth, topVerticalLineHeight);
            view.setLayoutParams(params);
            linesViewIds.add(topVerticalLineViewId + locLevel);
        }
    }

    void drawBottomVerticalLine(HierarchyAdapter.ViewHolder holder, Item item, int position) {
        int locLevel = item.level;
        Item nextItem = getAdapter().getNextItem(position);
        if (!item.parent.equals(ROOT) && nextItem != null && nextItem.parent.equals(item.parent)) {
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(bottomVerticalLineWidth, bottomVerticalLineHeight);
            params.addRule(RelativeLayout.LEFT_OF, R.id.iv_icon);
            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            params.rightMargin = bottomVerticalLineRightMargin;// + locMargin;
            View view = new View(holder.rlContent.getContext());
            view.setId(bottomVerticalLineViewId + locLevel);
            view.setBackgroundColor(Color.RED);
            holder.rlContent.addView(view, bottomVerticalLineWidth, bottomVerticalLineHeight);
            view.setLayoutParams(params);
            linesViewIds.add(bottomVerticalLineViewId + locLevel);
        }
    }

    void drawLeftHorizontalLine(HierarchyAdapter.ViewHolder holder, Item item, int position) {
        Item previousItem = getAdapter().getPreviousItem(position);
        if (item.level > 0 && previousItem != null && (previousItem.name.equals(item.parent) || previousItem.parent.equals(item.parent))) {
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(leftHorizontalLineWidth, leftHorizontalLineHeight);
            params.addRule(RelativeLayout.LEFT_OF, R.id.iv_icon);
            params.addRule(RelativeLayout.CENTER_VERTICAL);
            params.rightMargin = leftHorizontalLineRightMargin;
            View view = new View(holder.rlContent.getContext());
            view.setId(leftHorizontalLineViewId);
            view.findViewById(leftHorizontalLineViewId);
            view.setBackgroundColor(Color.RED);
            holder.rlContent.addView(view, bottomVerticalLineWidth, bottomVerticalLineHeight);
            view.setLayoutParams(params);
            linesViewIds.add(leftHorizontalLineViewId);
        }
    }

    void drawUnderCircleVerticalLine(HierarchyAdapter.ViewHolder holder, Item item, int position) {
        Item nextItem = getAdapter().getNextItem(position);
        if (nextItem != null && nextItem.parent.equals(item.name)) {
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(underCircleLineWidth, underCircleLineHeight);
            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            params.addRule(RelativeLayout.RIGHT_OF, R.id.iv_icon);
            params.leftMargin = underCircleLineRightMargin;
            View view = new View(holder.rlContent.getContext());
            view.setId(underCircleVerticalLineViewId);
            view.setBackgroundColor(Color.RED);
            holder.rlContent.addView(view, underCircleLineWidth, underCircleLineHeight);
            view.setLayoutParams(params);
            linesViewIds.add(underCircleVerticalLineViewId);
        }
    }

    void removeAllDrawables(HierarchyAdapter.ViewHolder holder) {
        if (linesViewIds.size() > 0) {
            View viewById;
            for (Integer viewId : linesViewIds) {
                viewById = holder.rlContent.findViewById(viewId);
                if (viewById != null) {
                    holder.rlContent.removeView(viewById);
                }
            }
        }
    }
}
