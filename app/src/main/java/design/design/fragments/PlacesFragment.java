package design.design.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import design.design.R;
import design.design.adapters.CardAdapter;

public class PlacesFragment extends Fragment {
    RecyclerView recyclerView;
    CardAdapter cardAdapter;
    String[] names = {"MY DOCTOR", "MY CAREMANAGER", "MY DIAGNOSIS", "THERAPY PLAN", "REMAINING PILLS", "MY ORDERS"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_places, container, false);
        recyclerView = view.findViewById(R.id.places_list_view);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        cardAdapter = new CardAdapter(getActivity(), names, getResources().getIntArray(R.array.color_array));
        recyclerView.setAdapter(cardAdapter);
        return view;
    }
}