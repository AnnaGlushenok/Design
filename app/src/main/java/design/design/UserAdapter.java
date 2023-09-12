package design.design;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

public class UserAdapter extends ArrayAdapter<User> {
    private Context context;
    private User[] users;
    private Fragment selectedFragment;

    public UserAdapter(@NonNull Context context, User[] users, Fragment selectedFragment) {
        super(context, R.layout.element1, users);
        this.context = context;
        this.users = users;
        this.selectedFragment = selectedFragment;
    }

    @SuppressLint("MissingInflatedId")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.element1, parent, false);

        User user = this.users[position];
        if (selectedFragment instanceof FavoritesFragment) {
            ((ImageView) view.findViewById(R.id.sex)).setImageResource(user.getSex().equals(Sex.MALE) ? R.drawable.man : R.drawable.woman);
            ((TextView) view.findViewById(R.id.name)).setText(String.format("%s %s", user.getLastName(), user.getFirstName()));
            ((TextView) view.findViewById(R.id.age)).setText(String.valueOf(user.getAge()));
        } else if (selectedFragment instanceof MusicFragment) {
            view = inflater.inflate(R.layout.element2, parent, false);
            ((TextView) view.findViewById(R.id.name)).setText(String.format("%s %s", user.getLastName(), user.getFirstName()));
            ((TextView) view.findViewById(R.id.age)).setText(String.valueOf(user.getAge()));
            ((TextView) view.findViewById(R.id.description)).setText(user.getDescription());

            Picasso.get()
                    .load(user.getSquareAvatarUrl())
                    .into((ShapeableImageView) view.findViewById(R.id.avatar));
        }
        return view;
    }
}
