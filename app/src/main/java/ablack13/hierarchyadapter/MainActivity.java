package ablack13.hierarchyadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ablack13.hierarchyadapter.adapter.FoldersRecyclerViewAdapter;
import ablack13.hierarchyadapter.bean.Folder;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FoldersRecyclerViewAdapter adapter;
    private final String ROOT = "root";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        adapter = new FoldersRecyclerViewAdapter(MainActivity.this);
        recyclerView.setAdapter(adapter);

        adapter.setItems(fillData());
    }

    private List<Folder> fillData() {
        List<Folder> items = new ArrayList<>();
        items.add(new Folder("All notes", 0, ROOT));
        items.add(new Folder("rock", 0, ROOT));
        items.add(new Folder("fddkfg", 1, "rock"));
        items.add(new Folder("sdsddsdsdsds", 2, "fddkfg", "rock"));
        items.add(new Folder("kkkk", 1, "rock"));
        items.add(new Folder("ura_huylo", 2, "kkkk", "rock"));
        items.add(new Folder("noo", 3, "ura_huylo", "kkkk", "rock"));
        items.add(new Folder("test", 1, "rock"));
        items.add(new Folder("7878", 1, "rock"));
        items.add(new Folder("New folder", 1, "rock"));
        items.add(new Folder("play", 2, "New folder", "rock"));
        items.add(new Folder("football", 1, "rock"));
        items.add(new Folder("fff", 2, "football", "rock"));
        items.add(new Folder("sadsdsadd", 1, "rock"));
        items.add(new Folder("dffdfdfd2", 0, ROOT));
        items.add(new Folder("xcfdfdfdfd", 1, "dffdfdfd2"));
        items.add(new Folder("sassa", 1, "dffdfdfd2"));
        items.add(new Folder("dfdf", 2, "sassa", "dffdfdfd2"));
        items.add(new Folder("fgfgfg", 3, "dfdf", "sassa", "dffdfdfd2"));
        items.add(new Folder("ккук", 4, "fgfgfg", "dfdf", "sassa", "dffdfdfd2"));
        items.add(new Folder("sss", 5, "ккук", "fgfgfg", "dfdf", "sassa", "dffdfdfd2"));
        items.add(new Folder("sddssd", 6, "sss", "ккук", "fgfgfg", "dfdf", "sassa", "dffdfdfd2"));
        items.add(new Folder("lock door", 5, "ккук", "fgfgfg", "dfdf", "sassa", "dffdfdfd2"));
        items.add(new Folder("gggg", 0, ROOT));
        items.add(new Folder("foo", 0, ROOT));
        items.add(new Folder("вывывы", 1, "foo"));
        items.add(new Folder("кукук", 0, ROOT));
        items.add(new Folder("test_1", 0, ROOT));
        items.add(new Folder("dsdds", 0, ROOT));
        items.add(new Folder("asdasadasd", 0, ROOT));
        items.add(new Folder("15.16", 0, ROOT));
        items.add(new Folder("foototototto", 0, ROOT));
        items.add(new Folder("New folder2", 0, ROOT));
        items.add(new Folder("rerr", 0, ROOT));
        items.add(new Folder("FC", 0, ROOT));
        items.add(new Folder("ss", 0, ROOT));
        items.add(new Folder("y", 0, ROOT));
        items.add(new Folder("fooo", 0, ROOT));
        items.add(new Folder("cxccx", 0, ROOT));
        items.add(new Folder("33333333", 0, ROOT));
        items.add(new Folder("My Notes", 0, ROOT));
        items.add(new Folder("eeee111", 1, "My Notes"));
        items.add(new Folder("кккк", 2, "eeee111", "My Notes"));
        items.add(new Folder("ичисис", 1, "My Notes"));
        items.add(new Folder("плацебо", 1, "My Notes"));
        items.add(new Folder("cgvcb", 0, ROOT));
        items.add(new Folder("cvvfc", 0, ROOT));
        items.add(new Folder("fxxv", 0, ROOT));
        items.add(new Folder("lordy", 0, ROOT));
        items.add(new Folder("monkey", 0, ROOT));

        return items;
    }
}
