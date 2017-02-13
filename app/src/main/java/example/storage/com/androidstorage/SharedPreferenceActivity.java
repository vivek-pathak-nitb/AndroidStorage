package example.storage.com.androidstorage;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Example class for shared preference.
 */

public class SharedPreferenceActivity extends Activity {

    private static final String MYPREF = "mypref";
    private static final String NAME = "nameKey";
    private static final String EMAIL = "emailKey";

    private SharedPreferences sharedpreferences;
    private TextView name;
    private TextView email;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharedpreference);

        name = (TextView) findViewById(R.id.etName);
        email = (TextView) findViewById(R.id.etEmail);
        sharedpreferences = getSharedPreferences(MYPREF, Context.MODE_PRIVATE);

        if (sharedpreferences.contains(NAME)) {
            name.setText(sharedpreferences.getString(NAME, ""));
        }

        if (sharedpreferences.contains(EMAIL)) {
            email.setText(sharedpreferences.getString(EMAIL, ""));

        }
    }

    public void Save(final View view) {
        final String n = name.getText().toString();
        final String e = email.getText().toString();
        final SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(NAME, n);
        editor.putString(EMAIL, e);
        editor.apply();
    }

    public void clear(View view) {
        name = (TextView) findViewById(R.id.etName);
        email = (TextView) findViewById(R.id.etEmail);
        name.setText("");
        email.setText("");

    }

    public void Get(View view) {
        name = (TextView) findViewById(R.id.etName);
        email = (TextView) findViewById(R.id.etEmail);
        sharedpreferences = getSharedPreferences(MYPREF, Context.MODE_PRIVATE);

        if (sharedpreferences.contains(NAME)) {
            name.setText(sharedpreferences.getString(NAME, ""));
        }

        if (sharedpreferences.contains(EMAIL)) {
            email.setText(sharedpreferences.getString(EMAIL, ""));

        }
    }
}
