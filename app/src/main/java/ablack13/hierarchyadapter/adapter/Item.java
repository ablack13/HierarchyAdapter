package ablack13.hierarchyadapter.adapter;

/**
 * Created by ablack13 on 24.12.16.
 */

public class Item {
    public String name;
    public int level;
    public String parent;
    String[] grandParents;

    public Item(String name, int level, String parent, String... topParents) {
        this.name = name;
        this.level = level;
        this.parent = parent;
        this.grandParents = topParents;
    }
}
