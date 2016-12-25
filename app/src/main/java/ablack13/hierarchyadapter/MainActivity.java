package ablack13.hierarchyadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadRecyclerViewFragment(null);
    }

    public void loadRecyclerViewFragment(View view) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, RecyclerViewExampleFragment.newInstance())
                .commitAllowingStateLoss();
    }

    public void loadListViewFragment(View view) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_container, ListViewExampleFragment.newInstance())
                .commitAllowingStateLoss();
    }

}
