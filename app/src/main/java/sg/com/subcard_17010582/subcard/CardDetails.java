package sg.com.subcard_17010582.subcard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CardDetails extends AppCompatActivity {

    private card c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);

        Intent intent = getIntent();
        c = (card) intent.getSerializableExtra("card");
        final EditText etName = findViewById(R.id.etName);
        final EditText etLimit = findViewById(R.id.etLimit);
        etName.setText(c.getAppName());
        etLimit.setText(String.format("%.2f", c.getLimit()));


        final Button btnActive = findViewById(R.id.btnPS);
        if (c.isActive()) {
            btnActive.setText("Pause");
        } else {
            btnActive.setText("Restart");
        }
        btnActive.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                if (btnActive.getText().equals("Pause")) {
                    btnActive.setText("Restart");
                    c.setActive(false);
                } else {
                    btnActive.setText("Pause");
                    c.setActive(true);
                }

            }
        });

        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c.setLimit(Double.parseDouble(etLimit.getText().toString()));
                c.setAppName(etName.getText().toString());
                Toast.makeText(getApplicationContext(), "Edit Saved", Toast.LENGTH_SHORT).show();
                Intent i = new Intent();
                i.putExtra("card", c);
                setResult(1, i);
                finish();
            }
        });
    }
}
