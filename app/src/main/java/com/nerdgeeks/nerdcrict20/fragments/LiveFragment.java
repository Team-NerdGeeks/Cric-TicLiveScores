package com.nerdgeeks.nerdcrict20.fragments;


import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.nerdgeeks.nerdcrict20.R;
import com.nerdgeeks.nerdcrict20.adapters.ScoreAdapter;
import com.nerdgeeks.nerdcrict20.clients.ApiClient;
import com.nerdgeeks.nerdcrict20.clients.ApiInterface;
import com.nerdgeeks.nerdcrict20.models.Match;
import com.nerdgeeks.nerdcrict20.models.Matches;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LiveFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LiveFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ScoreAdapter liveAdapter;
    private RecyclerView recyclerView;
    private List<Match> currentMatches = new ArrayList<>();
    private CoordinatorLayout coordinatorLayout;
    private ProgressBar circular_progress;
    private ImageView imgCloud;


    public LiveFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LiveFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LiveFragment newInstance(String param1, String param2) {
        LiveFragment fragment = new LiveFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_live, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.liveView);
        coordinatorLayout = (CoordinatorLayout) rootView.findViewById(R.id.fragment_live);
        circular_progress = (ProgressBar) rootView.findViewById(R.id.circular_progress);
        imgCloud = (ImageView) rootView.findViewById(R.id.cloud);
        final SwipeRefreshLayout refreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.refresh);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        final String Url = "/api/matches/?apikey=n6kNCNcVwPbDzWWvjU1q7hmsoJg1&v=3";
        getLiveMatchesData(Url,rootView);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (currentMatches!=null){
                    currentMatches.clear();
                    getLiveMatchesData(Url,rootView);
                    refreshLayout.setRefreshing(false);
                }else {
                    getLiveMatchesData(Url,rootView);
                    refreshLayout.setRefreshing(false);
                }
            }
        });
        return rootView;
    }

    private void getLiveMatchesData(final String url, final View rootView) {
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<Matches> call = service.getMatchData(url);

        call.enqueue(new Callback<Matches>() {
            @Override
            public void onResponse(Call<Matches> call, Response<Matches> response) {
                Log.d("Live onResponse", response.message());

                Matches matches = response.body();

                for(int i=0; i<matches.getMatches().size();i++){
                    if(matches.getMatches().get(i).getMatchStarted()){
                        currentMatches.add(matches.getMatches().get(i));
                    }
                }
                
                liveAdapter = new ScoreAdapter(getActivity(),currentMatches);
                recyclerView.setAdapter(liveAdapter);
                circular_progress.setVisibility(View.INVISIBLE);
                imgCloud.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<Matches> call, Throwable t) {
                Log.d("Error Live", t.getMessage());
                Snackbar.make(coordinatorLayout, "Unable to resolve host, check your internet connection", Snackbar.LENGTH_INDEFINITE).setAction("RETRY", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getLiveMatchesData(url,rootView);
                    }
                }).show();
                circular_progress.setVisibility(View.INVISIBLE);
                imgCloud.setVisibility(View.VISIBLE);
            }
        });
    }
}
