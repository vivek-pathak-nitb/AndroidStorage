package example.storage.com.androidstorage;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Activity to view list of countries.
 */

public class CountryListActivity extends AppCompatActivity {

    final String[] from = new String[]{DbHelper._ID, DbHelper.SUBJECT, DbHelper.DESC};

    final int[] to = new int[]{R.id.id, R.id.title, R.id.desc};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_emp_list);

        DbManager dbManager = new DbManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        final SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.activity_view_record,
                cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        // OnClickListner For List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
               final  TextView idTextView = (TextView) view.findViewById(R.id.id);
                final TextView titleTextView = (TextView) view.findViewById(R.id.title);
                final TextView descTextView = (TextView) view.findViewById(R.id.desc);

                final String id = idTextView.getText().toString();
                final String title = titleTextView.getText().toString();
                final String desc = descTextView.getText().toString();

                final Intent modify_intent = new Intent(getApplicationContext(), ModifyCountryActivity.class);
                modify_intent.putExtra("title", title);
                modify_intent.putExtra("desc", desc);
                modify_intent.putExtra("id", id);

                startActivity(modify_intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.add_record) {
            final Intent add_mem = new Intent(this, AddCountryActivity.class);
            startActivity(add_mem);
        }
        return super.onOptionsItemSelected(item);
    }
}
