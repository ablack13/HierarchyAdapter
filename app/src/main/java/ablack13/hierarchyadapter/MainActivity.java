package ablack13.hierarchyadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ablack13.hierarchyadapter.adapter.HierarchyAdapter;
import ablack13.hierarchyadapter.adapter.Item;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private HierarchyAdapter adapter;
    private final String ROOT = "root";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        adapter = new HierarchyAdapter(MainActivity.this);
        recyclerView.setAdapter(adapter);


        adapter.setItems(fillData());
    }

    private List<Item> fillData() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("All notes", 0, ROOT));
        items.add(new Item("rock", 0, ROOT));
        items.add(new Item("fddkfg", 1, "rock"));
        items.add(new Item("sdsddsdsdsds", 2, "fddkfg", "rock"));
        items.add(new Item("kkkk", 1, "rock"));
        items.add(new Item("ura_huylo", 2, "kkkk", "rock"));
        items.add(new Item("noo", 3, "ura_huylo", "kkkk", "rock"));
        items.add(new Item("test", 1, "rock"));
        items.add(new Item("7878", 1, "rock"));
        items.add(new Item("New folder", 1, "rock"));
        items.add(new Item("play", 2, "New folder", "rock"));
        items.add(new Item("football", 1, "rock"));
        items.add(new Item("fff", 2, "football", "rock"));
        items.add(new Item("sadsdsadd", 1, "rock"));
        items.add(new Item("dffdfdfd2", 0, ROOT));
        items.add(new Item("xcfdfdfdfd", 1, "dffdfdfd2"));
        items.add(new Item("sassa", 1, "dffdfdfd2"));
        items.add(new Item("dfdf", 2, "sassa", "dffdfdfd2"));
        items.add(new Item("fgfgfg", 3, "dfdf", "sassa", "dffdfdfd2"));
        items.add(new Item("ккук", 4, "fgfgfg", "dfdf", "sassa", "dffdfdfd2"));
        items.add(new Item("sss", 5, "ккук", "fgfgfg", "dfdf", "sassa", "dffdfdfd2"));
        items.add(new Item("sddssd", 6, "sss", "ккук", "fgfgfg", "dfdf", "sassa", "dffdfdfd2"));
        items.add(new Item("lock door", 5, "ккук", "fgfgfg", "dfdf", "sassa", "dffdfdfd2"));
        items.add(new Item("gggg", 0, ROOT));
        items.add(new Item("foo", 0, ROOT));
        items.add(new Item("вывывы", 1, "foo"));
        items.add(new Item("кукук", 0, ROOT));
        items.add(new Item("test_1", 0, ROOT));
        items.add(new Item("dsdds", 0, ROOT));
        items.add(new Item("asdasadasd", 0, ROOT));
        items.add(new Item("15.16", 0, ROOT));
        items.add(new Item("foototototto", 0, ROOT));
        items.add(new Item("New folder2", 0, ROOT));
        items.add(new Item("rerr", 0, ROOT));
        items.add(new Item("FC", 0, ROOT));
        items.add(new Item("ss", 0, ROOT));
        items.add(new Item("y", 0, ROOT));
        items.add(new Item("fooo", 0, ROOT));
        items.add(new Item("cxccx", 0, ROOT));
        items.add(new Item("33333333", 0, ROOT));
        items.add(new Item("My Notes", 0, ROOT));
        items.add(new Item("eeee111", 1, "My Notes"));
        items.add(new Item("кккк", 2, "eeee111", "My Notes"));
        items.add(new Item("ичисис", 1, "My Notes"));
        items.add(new Item("плацебо", 1, "My Notes"));
        items.add(new Item("cgvcb", 0, ROOT));
        items.add(new Item("cvvfc", 0, ROOT));
        items.add(new Item("fxxv", 0, ROOT));
        items.add(new Item("lordy", 0, ROOT));
        items.add(new Item("monkey", 0, ROOT));

        return items;
    }
}
