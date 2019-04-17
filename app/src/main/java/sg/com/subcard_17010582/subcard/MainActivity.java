package sg.com.subcard_17010582.subcard;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvCard;
    ArrayList<card> alCardList;
    CustomAdapter caCard;
    final Context context = this;
    private FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fabAdd = findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.custom_add_dialog);

                final EditText etName = dialog.findViewById(R.id.etName);
                final EditText etLimit = dialog.findViewById(R.id.etLimit);
                Button btnAdd = dialog.findViewById(R.id.btnAdd);
                Button btnCancel = dialog.findViewById(R.id.btnCancel);

                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int id = alCardList.size() + 1;
                        String appName = etName.getText().toString();
                        Double limit = Double.parseDouble(etLimit.getText().toString());
                        card newCard = new card(id, appName, limit);
                        alCardList.add(newCard);
                        caCard.notifyDataSetChanged();
                        etName.setText("");
                        etLimit.setText("");
                        dialog.dismiss();
                    }
                });
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        etName.setText("");
                        etLimit.setText("");
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        lvCard = findViewById(R.id.lvCard);
        alCardList = new ArrayList<>();
        card c1 = new card(1, "Netflix", 13.90);
        card c2 = new card(2, "Spotify", 10.00);
        card c3 = new card(3, "Carousell", 100.00);
        card c4 = new card(4, "Lazada", 55.00);
        alCardList.add(c1);
        alCardList.add(c2);
        alCardList.add(c3);
        alCardList.add(c4);

        caCard = new CustomAdapter(this, R.layout.card_item, alCardList);
        lvCard.setAdapter(caCard);

        lvCard.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getBaseContext(), CardDetails.class);
                card currentCard = alCardList.get(position);
                intent.putExtra("card", currentCard);
                startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.settings) {
            Intent intent = new Intent(getApplicationContext(), Settings2Activity.class);
            startActivity(intent);
        } else if (id == R.id.reset) {
            for (card c: alCardList) {
                c.setLimit(0);
            }
            caCard.notifyDataSetChanged();
        } else {
            Intent intent = new Intent(getApplicationContext(), TNCActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        // The returned result data is identified by requestCode.
        // The request code is specified in startActivityForResult(intent, REQUEST_CODE_1); method.
        switch (requestCode)
        {
            // This request code is set by startActivityForResult(intent, REQUEST_CODE_1) method.
            case 1:

                if(resultCode == 1)
                {
                    card c = (card) intent.getSerializableExtra("card");
                    for (int i = 0; i < alCardList.size(); i++) {
                        card card = alCardList.get(i);
                        if (c.getId() == card.getId()) {
                            alCardList.set(i,c);
                            break;
                        }
                    }
                    caCard.notifyDataSetChanged();
                }
        }
    }
}

