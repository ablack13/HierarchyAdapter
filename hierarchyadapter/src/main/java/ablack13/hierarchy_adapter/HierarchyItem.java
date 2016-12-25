package ablack13.hierarchy_adapter;

/**
 * Created by ablack13 on 24.12.16.
 */

public abstract class HierarchyItem {
    private String key;
    private String parentKey;
    private int level;
    private String[] grandParentalKeys;

    public HierarchyItem(String key, int level, String parentKey, String... grandParentalKeys) {
        this.key = key;
        this.level = level;
        this.parentKey = parentKey;
        this.grandParentalKeys = grandParentalKeys;
    }

    public String getParentKey() {
        return parentKey;
    }

    public void setParentKey(String parentKey) {
        this.parentKey = parentKey;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String[] getGrandParentalKeys() {
        return grandParentalKeys;
    }

    public void setGrandParentalKeys(String[] grandParentalKeys) {
        this.grandParentalKeys = grandParentalKeys;
    }
}
