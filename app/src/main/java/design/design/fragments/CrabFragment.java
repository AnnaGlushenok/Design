package design.design.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import design.design.MainActivity;
import design.design.R;
import design.design.Sex;
import design.design.User;
import design.design.UserGenerator;
import design.design.adapters.UserAdapter;

public class CrabFragment extends Fragment {
    private ListView listView;
    private SwipeRefreshLayout swipe;
    private Button button;
    private TextView text;
    private UserAdapter adapter;
    private ProgressBar progressBar;
    private User[] users = UserGenerator.generateUsers(10);
    private Handler handler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crab, container, false);

        progressBar = view.findViewById(R.id.progressBar);
        listView = view.findViewById(R.id.crab_list_view);
        button = view.findViewById(R.id.clear_button);
        text = view.findViewById(R.id.text);
        swipe = view.findViewById(R.id.swipe);

        progressBar.setVisibility(View.VISIBLE);

        handler.postDelayed(() -> {
            progressBar.setVisibility(View.GONE);
            button.setVisibility(View.VISIBLE);
            adapter = new UserAdapter(getActivity(), users, this);
            listView.setAdapter(adapter);
        }, 6000);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                users = new User[0];
                adapter = new UserAdapter(getActivity(), users, CrabFragment.this);
                listView.setAdapter(adapter);
                text.setVisibility(View.VISIBLE);
            }
        });

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(() -> {
                    swipe.setRefreshing(false);
                    users = UserGenerator.generateUsers(10);
                    text.setVisibility(View.INVISIBLE);
                    adapter = new UserAdapter(getActivity(), users, CrabFragment.this);
                    listView.setAdapter(adapter);
                }, 3000);
            }
        });

        return view;
    }
}