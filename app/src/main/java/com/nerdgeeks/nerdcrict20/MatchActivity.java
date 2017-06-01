package com.nerdgeeks.nerdcrict20;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.nerdgeeks.nerdcrict20.adapters.SummaryAdapter;
import com.nerdgeeks.nerdcrict20.clients.ApiClient;
import com.nerdgeeks.nerdcrict20.clients.ApiInterface;
import com.nerdgeeks.nerdcrict20.helper.DividerItemDecoration;
import com.nerdgeeks.nerdcrict20.helper.DoubleHeaderDecoration;
import com.nerdgeeks.nerdcrict20.models.Batting;
import com.nerdgeeks.nerdcrict20.models.Score__;
import com.nerdgeeks.nerdcrict20.models.Summary;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SummaryAdapter adapter;
    private DoubleHeaderDecoration decor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);

        recyclerView = (RecyclerView) findViewById(R.id.summaryView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        String UniqueId = getIntent().getStringExtra("unique_id");
        String Url = "/api/fantasySummary?apikey=n6kNCNcVwPbDzWWvjU1q7hmsoJg1&unique_id="+UniqueId;
        getSummaryMatchesData(Url);
    }

    private void getSummaryMatchesData(String URL){
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<Summary> call = service.getSummary(URL);

        call.enqueue(new Callback<Summary>() {
            @Override
            public void onResponse(Call<Summary> call, Response<Summary> response) {
                Log.d("onResponse", response.message());

                Summary summary = response.body();


                List<Score__> bat_score = new ArrayList<>();

                for(int i=0; i<summary.getData().getBatting().size(); i++){
                    Batting bat = summary.getData().getBatting().get(i);
                    for (int j=0; j<bat.getScores().size(); j++){
                        bat_score.add(bat.getScores().get(j));
                    }
                }

                adapter = new SummaryAdapter(MatchActivity.this,bat_score,summary.getData().getBowling());
                decor = new DoubleHeaderDecoration(adapter);
                recyclerView.setAdapter(adapter);
                recyclerView.addItemDecoration(decor, 1);
            }

            @Override
            public void onFailure(Call<Summary> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_enter,R.anim.anim_leave);
    }
}
