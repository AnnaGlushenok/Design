package design.design.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import design.design.R;
import design.design.User;
import design.design.UserGenerator;
import design.design.adapters.UserAdapter;

public class MusicFragment extends Fragment {
    ListView listView;
    UserAdapter userAdapter;
    User[] users = UserGenerator.generateUsers(30);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music, container, false);
        listView = view.findViewById(R.id.music_list_view);
        userAdapter = new UserAdapter(getActivity(), users, this);
        listView.setAdapter(userAdapter);
        return view;
    }
}