package ablack13.hierarchyadapter.bean;

import ablack13.hierarchy_adapter.HierarchyItem;

/**
 * Created by ablack13 on 25.12.16.
 */

public class Folder extends HierarchyItem {
    private String name;

    public Folder(String key, int level, String... parentalKeys) {
        super(key, level, parentalKeys);
        this.name = key;
    }

    public String getName() {
        return name;
    }
}
