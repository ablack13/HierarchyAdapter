package ablack13.hierarchyadapter.adapter;

/**
 * Created by ablack13 on 24.12.16.
 */

public class Item {
    public String name;
    public int level;
    public String parent;

    public Item(String name, int level, String parent) {
        this.name = name;
        this.level = level;
        this.parent = parent;
    }
}
