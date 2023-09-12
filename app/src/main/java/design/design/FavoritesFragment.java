package design.design;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

public class FavoritesFragment extends Fragment {
    ListView listView;
    UserAdapter userAdapter;
    User[] users = UserGenerator.generateUsers(30);
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        listView = view.findViewById(R.id.favorites_list_view);
        userAdapter = new UserAdapter(getActivity(), users, this);
        listView.setAdapter(userAdapter);
        return view;
    }
}