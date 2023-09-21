package design.design.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import design.design.R;

public class ListAdapter extends ArrayAdapter<String> {
    private Context context;
    private String[] names;
    Fragment selectedFragment;

    @Override
    public int getCount() {
        return 20;
    }

    public ListAdapter(@NonNull Context context, String[] names, Fragment selectedFragment) {
        super(context, R.layout.element4, names);
        this.context = context;
        this.names = names;
        this.selectedFragment = selectedFragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.element4, parent, false);
        String name = names[position % names.length];
        ((TextView) view.findViewById(R.id.text1)).setText(name);
        ((TextView) view.findViewById(R.id.text2)).setText(name);
        ((TextView) view.findViewById(R.id.text3)).setText(name);
        return view;
    }
}
