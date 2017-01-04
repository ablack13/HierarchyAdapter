package ablack13.hierarchy_adapter;

/**
 * Created by ablack13 on 24.12.16.
 */

public abstract class HierarchyItem {
    private String key;
    private int level;
    private String[] parentalKeys;

    public HierarchyItem(String key, int level, String... parentalKeys) {
        this.key = key;
        this.level = level;
        this.parentalKeys = parentalKeys;
    }

    public String getParentKey() {
        return parentalKeys[0];
    }

    public int getLevel() {
        return level;
    }

    public String getKey() {
        return key;
    }

    public String[] getParentalKeys() {
        return parentalKeys;
    }
}
