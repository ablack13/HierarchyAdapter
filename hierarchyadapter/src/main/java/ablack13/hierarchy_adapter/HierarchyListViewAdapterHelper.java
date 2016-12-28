package ablack13.hierarchy_adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ablack13 on 25.12.16.
 */

class HierarchyListViewAdapterHelper<T extends HierarchyItem, VH extends ListViewHierarchyAdapter.HierarchyViewHolder> {
    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);
    private static final String ROOT = "root";

    private int leftMargin;
    private int underCircleLineWidth;
    private int underCircleLineHeight;
    private int underCircleLineRightMargin;
    private int topVerticalLineWidth;
    private int topVerticalLineHeight;
    private int topVerticalLineRightMargin;
    private int bottomVerticalLineWidth;
    private int bottomVerticalLineHeight;
    private int bottomVerticalLineRightMargin;
    private int leftHorizontalLineWidth;
    private int leftHorizontalLineHeight;
    private int leftHorizontalLineRightMargin;

    private int topVerticalLineViewId;
    private int bottomVerticalLineViewId;
    private int underCircleVerticalLineViewId;
    private int leftHorizontalLineViewId;
    private float density;
    private List<Integer> linesViewIds;
    private WeakReference<ListViewHierarchyAdapter<T, VH>> adapter;

    private int lineColor;

    public HierarchyListViewAdapterHelper(Context context, ListViewHierarchyAdapter<T, VH> adapter) {
        this.adapter = new WeakReference<>(adapter);
        this.density = context.getResources().getDisplayMetrics().density;
        this.leftMargin = (int) context.getResources().getDimension(R.dimen.hierarchy_leftMargin);
        this.underCircleLineWidth = (int) context.getResources().getDimension(R.dimen.hierarchy_underCircleLineWidth);
        this.underCircleLineHeight = (int) context.getResources().getDimension(R.dimen.hierarchy_underCircleLineHeight);
        this.underCircleLineRightMargin = (int) context.getResources().getDimension(R.dimen.hierarchy_underCircleLineRightMargin);
        this.topVerticalLineWidth = (int) context.getResources().getDimension(R.dimen.hierarchy_topVerticalLineWidth);
        this.topVerticalLineHeight = (int) context.getResources().getDimension(R.dimen.hierarchy_topVerticalLineHeight);
        this.topVerticalLineRightMargin = (int) context.getResources().getDimension(R.dimen.hierarchy_topVerticalLineRightMargin);
        this.bottomVerticalLineWidth = (int) context.getResources().getDimension(R.dimen.hierarchy_bottomVerticalLineWidth);
        this.bottomVerticalLineHeight = (int) context.getResources().getDimension(R.dimen.hierarchy_bottomVerticalLineHeight);
        this.bottomVerticalLineRightMargin = (int) context.getResources().getDimension(R.dimen.hierarchy_bottomVerticalLineRightMargin);
        this.leftHorizontalLineWidth = (int) context.getResources().getDimension(R.dimen.hierarchy_leftHorizontalLineWidth);
        this.leftHorizontalLineHeight = (int) context.getResources().getDimension(R.dimen.hierarchy_leftHorizontalLineHeight);
        this.leftHorizontalLineRightMargin = (int) context.getResources().getDimension(R.dimen.hierarchy_leftHorizontalLineRightMargin);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(new int[]{R.attr.isLightTheme});
        boolean isLightTheme = typedArray.getBoolean(0, true);
        typedArray.recycle();
        this.lineColor = context.getResources().getColor(isLightTheme ? R.color.hierarchy_lineColor_light : R.color.hierarchy_lineColor_dark);

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

    void onDettach() {
        if (adapter != null) {
            adapter.clear();
            adapter = null;
        }
        if (linesViewIds != null && linesViewIds.size() > 0) {
            linesViewIds.clear();
            linesViewIds = null;
        }
    }

    private ListViewHierarchyAdapter getAdapter() {
        return adapter != null ? adapter.get() : null;
    }

    void setLeftMargin(ListViewHierarchyAdapter.HierarchyViewHolder holder, HierarchyItem item) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) holder.margin.getLayoutParams();
        params.leftMargin = leftMargin * item.getLevel();
        holder.margin.setLayoutParams(params);
    }

    void drawTopVerticalLine(ListViewHierarchyAdapter.HierarchyViewHolder holder, HierarchyItem item, int position) {
        int locLevel = item.getLevel();
        HierarchyItem previousItem = getPreviousItem(position);
        if (item.getLevel() > 0
                && previousItem != null
                && (previousItem.getKey().equals(item.getParentKey())
                || (previousItem.getParentKey().equals(item.getParentKey()))
                || hasPreviousLevelItem(item, position))) {

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(topVerticalLineWidth, topVerticalLineHeight);
            params.addRule(RelativeLayout.LEFT_OF, holder.getLevelingViewId());
            params.rightMargin = topVerticalLineRightMargin;
            View view = new View(holder.rlContent.getContext());
            view.setId(topVerticalLineViewId + locLevel);
            view.setBackgroundColor(lineColor);
            holder.rlContent.addView(view, topVerticalLineWidth, topVerticalLineHeight);
            view.setLayoutParams(params);
            linesViewIds.add(topVerticalLineViewId + locLevel);

            drawAddditionalTopVerticalLine(holder, item, position);
        }
    }

    void drawBottomVerticalLine(ListViewHierarchyAdapter.HierarchyViewHolder holder, HierarchyItem item, int position) {
        int locLevel = item.getLevel();
        HierarchyItem nextItem = getNextItem(position);
        if (!item.getParentKey().equals(ROOT)
                && (nextItem != null
                && nextItem.getParentKey().equals(item.getParentKey())
                || hasNextLevelItem(item, position))) {

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(bottomVerticalLineWidth, bottomVerticalLineHeight);
            params.addRule(RelativeLayout.LEFT_OF, holder.getLevelingViewId());
            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            params.rightMargin = bottomVerticalLineRightMargin;
            View view = new View(holder.rlContent.getContext());
            view.setId(bottomVerticalLineViewId + locLevel);
            view.setBackgroundColor(lineColor);
            holder.rlContent.addView(view, bottomVerticalLineWidth, bottomVerticalLineHeight);
            view.setLayoutParams(params);
            linesViewIds.add(bottomVerticalLineViewId + locLevel);
        }
        drawAdditionalBottomVerticalLine(holder, item, position);
    }

    void drawLeftHorizontalLine(ListViewHierarchyAdapter.HierarchyViewHolder holder, HierarchyItem item, int position) {
        HierarchyItem previousItem = getPreviousItem(position);
        if (item.getLevel() > 0
                && previousItem != null
                && (previousItem.getKey().equals(item.getParentKey())
                || previousItem.getParentKey().equals(item.getParentKey())
                || hasPreviousLevelItem(item, position))) {

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(leftHorizontalLineWidth, leftHorizontalLineHeight);
            params.addRule(RelativeLayout.LEFT_OF, holder.getLevelingViewId());
            params.addRule(RelativeLayout.CENTER_VERTICAL);
            params.rightMargin = leftHorizontalLineRightMargin;
            View view = new View(holder.rlContent.getContext());
            view.setId(leftHorizontalLineViewId);
            view.findViewById(leftHorizontalLineViewId);
            view.setBackgroundColor(lineColor);
            holder.rlContent.addView(view, bottomVerticalLineWidth, bottomVerticalLineHeight);
            view.setLayoutParams(params);
            linesViewIds.add(leftHorizontalLineViewId);
        }
    }

    void drawUnderCircleVerticalLine(ListViewHierarchyAdapter.HierarchyViewHolder holder, HierarchyItem item, int position) {
        HierarchyItem nextItem = getNextItem(position);
        if (nextItem != null && nextItem.getParentKey().equals(item.getKey())) {
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(underCircleLineWidth, underCircleLineHeight);
            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            params.addRule(RelativeLayout.RIGHT_OF, holder.getLevelingViewId());
            params.leftMargin = underCircleLineRightMargin;
            View view = new View(holder.rlContent.getContext());
            view.setId(underCircleVerticalLineViewId);
            view.setBackgroundColor(lineColor);
            holder.rlContent.addView(view, underCircleLineWidth, underCircleLineHeight);
            view.setLayoutParams(params);
            linesViewIds.add(underCircleVerticalLineViewId);
        }
    }

    void removeAllDrawables(ListViewHierarchyAdapter.HierarchyViewHolder holder) {
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


    void drawAddditionalTopVerticalLine(ListViewHierarchyAdapter.HierarchyViewHolder holder, HierarchyItem item, int position) {
        int locLevel = item.getLevel();
        String[] grandParents = item.getGrandParentalKeys();
        int locMargin = leftMargin;
        if (item.getLevel() > 0 && grandParents != null && grandParents.length > 0) {
            for (String grandParent : grandParents) {
                if (hasPreviousParent(grandParent, position) && hasNextChild(grandParent, position)) {
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(topVerticalLineWidth, topVerticalLineHeight);
                    params.addRule(RelativeLayout.LEFT_OF, holder.getLevelingViewId());
                    params.rightMargin = topVerticalLineRightMargin + locMargin;
                    View view = new View(holder.rlContent.getContext());
                    view.setId(topVerticalLineViewId + locLevel);
                    view.setBackgroundColor(lineColor);
                    holder.rlContent.addView(view, topVerticalLineWidth, topVerticalLineHeight);
                    view.setLayoutParams(params);
                    linesViewIds.add(topVerticalLineViewId + locLevel);
                }
                --locLevel;
                locMargin += leftMargin;
            }
        }
    }

    void drawAdditionalBottomVerticalLine(ListViewHierarchyAdapter.HierarchyViewHolder holder, HierarchyItem item, int position) {
        int locLevel = item.getLevel();
        String[] grandParents = item.getGrandParentalKeys();
        int locMargin = leftMargin;
        if (item.getLevel() > 0 && grandParents != null && grandParents.length > 0) {
            for (String grandParent : grandParents) {
                if (hasPreviousParent(grandParent, position) && hasNextChild(grandParent, position)) {
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(bottomVerticalLineWidth, bottomVerticalLineHeight);
                    params.addRule(RelativeLayout.LEFT_OF, holder.getLevelingViewId());
                    params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                    params.rightMargin = bottomVerticalLineRightMargin + locMargin;
                    View view = new View(holder.rlContent.getContext());
                    view.setId(bottomVerticalLineViewId + locLevel);
                    view.setBackgroundColor(lineColor);
                    holder.rlContent.addView(view, bottomVerticalLineWidth, bottomVerticalLineHeight);
                    view.setLayoutParams(params);
                    linesViewIds.add(bottomVerticalLineViewId + locLevel);
                }
                --locLevel;
                locMargin += leftMargin;
            }
        }
    }

    private boolean hasPreviousLevelItem(HierarchyItem item, int position) {
        boolean hasPreviousLevelItem = false;
        for (int i = 0; i < position; i++) {
            HierarchyItem tempItem = getItem(i);
            if (tempItem != null && tempItem.getParentKey().equals(item.getParentKey())) {
                hasPreviousLevelItem = true;
                break;
            }
        }
        return hasPreviousLevelItem;
    }

    private boolean hasNextLevelItem(HierarchyItem item, int position) {
        boolean hasNextLevelItem = false;
        for (int i = position + 1; i < getAdapter().getItems().size(); i++) {
            HierarchyItem tempItem = getItem(i);
            if (tempItem != null && tempItem.getParentKey().equals(item.getParentKey())) {
                hasNextLevelItem = true;
                break;
            }
        }
        return hasNextLevelItem;
    }

    private boolean hasPreviousParent(String parent, int position) {
        boolean hasPreviousParent = false;
        for (int i = 0; i < position; i++) {
            HierarchyItem item = getItem(i);
            if (item != null && item.getKey().equals(parent)) {
                hasPreviousParent = true;
                break;
            }
        }
        return hasPreviousParent;
    }

    private boolean hasNextChild(String parent, int position) {
        boolean hasNextChild = false;
        for (int i = position; i < getAdapter().getItems().size(); i++) {
            HierarchyItem item = getItem(i);
            if (item != null && item.getParentKey().equals(parent)) {
                hasNextChild = true;
                break;
            }
        }
        return hasNextChild;
    }

    private T getPreviousItem(int position) {
        int previousPosition = position - 1;
        if (previousPosition >= 0 && previousPosition < getAdapter().getCount()) {
            return (T) getAdapter().getItems().get(previousPosition);
        }
        return null;
    }

    private T getNextItem(int position) {
        int nextPosition = position + 1;
        if (nextPosition >= 0 && nextPosition < getAdapter().getCount()) {
            return (T) getAdapter().getItems().get(nextPosition);
        }
        return null;
    }

    private T getItem(int position) {
        if (position < getAdapter().getCount()) {
            return (T) getAdapter().getItems().get(position);
        }
        return null;
    }
}
