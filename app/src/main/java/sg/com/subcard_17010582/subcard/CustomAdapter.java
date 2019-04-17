package sg.com.subcard_17010582.subcard;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<card> cardList;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull ArrayList<card> objects) {
        super(context, resource, objects);
        parent_context = context;
        layout_id = resource;
        cardList = objects;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View v, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(layout_id, parent, false);
        card currentCard = cardList.get(position);
        TextView tvName = v.findViewById(R.id.tvName);
        TextView tvLimit = v.findViewById(R.id.tvLimit);
        tvName.setText(currentCard.getAppName());
        tvLimit.setText("Limit Set: $" + String.format("%.2f", currentCard.getLimit()));
        if (!currentCard.isActive()) {
            v.setBackgroundColor(Color.GRAY);
        } else {
            v.setBackgroundColor(Color.WHITE);
        }
        return v;
    }
}
