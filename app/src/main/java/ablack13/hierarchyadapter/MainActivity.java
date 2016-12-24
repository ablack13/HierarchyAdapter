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
        items.add(new Item("All notes", 0));
        items.add(new Item("rock", 0));
        items.add(new Item("fddkfg", 1));
        items.add(new Item("sdsddsdsdsds", 2));
        items.add(new Item("kkkk", 1));
        items.add(new Item("ura_huylo", 2));
        items.add(new Item("noo", 3));
        items.add(new Item("test", 2));
        items.add(new Item("7878", 2));
        items.add(new Item("New folder", 2));
        items.add(new Item("play", 3));
        items.add(new Item("footbal", 2));
        items.add(new Item("fff", 3));
        items.add(new Item("sadsdsadd",2));
        items.add(new Item("dffdfdfd2", 1));
        items.add(new Item("xcfdfdfdfd", 2));
        items.add(new Item("sassa", 2));
        items.add(new Item("dfdf", 3));
        items.add(new Item("fgfgfg", 4));
        items.add(new Item("ккук", 5));
        items.add(new Item("sss", 6));
        items.add(new Item("sddssd", 7));
        items.add(new Item("lock door", 6));
        items.add(new Item("gggg", 0));
        items.add(new Item("foo", 0));
        items.add(new Item("вывывы", 1));
        items.add(new Item("кукук", 0));
        items.add(new Item("test_1", 0));
        items.add(new Item("dsdds", 0));
        items.add(new Item("asdasadasd", 0));
        items.add(new Item("15.16", 0));
        items.add(new Item("foototototto", 0));
        items.add(new Item("New folder2", 0));
        items.add(new Item("rerr", 0));
        items.add(new Item("FC", 0));
        items.add(new Item("ss", 0));
        items.add(new Item("y", 0));
        items.add(new Item("fooo", 0));
        items.add(new Item("cxccx", 0));
        items.add(new Item("33333333", 0));
        items.add(new Item("My Notes", 0));
        items.add(new Item("eeee111", 1));
        items.add(new Item("кккк", 2));
        items.add(new Item("ичисис", 1));
        items.add(new Item("плацебо", 1));
        items.add(new Item("cgvcb", 0));
        items.add(new Item("cvvfc", 0));
        items.add(new Item("fxxv", 0));
        items.add(new Item("lordy", 0));
        items.add(new Item("monkey", 0));

        return items;
    }
}
