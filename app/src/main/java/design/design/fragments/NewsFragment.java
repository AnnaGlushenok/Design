package design.design.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import design.design.R;
import design.design.adapters.ListAdapter;

public class NewsFragment extends Fragment {
    ListView listView;
    ListAdapter adapter;
    String[] names = {"Title", "Name", "Name1", "Name2"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        listView = view.findViewById(R.id.news_list_view);
        adapter = new ListAdapter(getActivity(), names, this);
        listView.setAdapter(adapter);
        return view;
    }
}