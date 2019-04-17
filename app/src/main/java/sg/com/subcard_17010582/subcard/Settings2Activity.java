package sg.com.subcard_17010582.subcard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Settings2Activity extends AppCompatActivity {

    ArrayList<String> settings;
    ListView lvSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings2);

        settings = new ArrayList<String>();
        settings.add("Background Themes");
        settings.add("Credit & Debit Cards");
        settings.add("Notifications");
        settings.add("Payment Methods");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, settings);
        lvSettings = findViewById(R.id.lvSettings);
        lvSettings.setAdapter(adapter);
    }
}
