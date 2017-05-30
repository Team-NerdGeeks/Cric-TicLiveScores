package com.nerdgeeks.nerdcrict20.clients;

import com.nerdgeeks.nerdcrict20.models.LiveMatch;
import com.nerdgeeks.nerdcrict20.models.Matches;
import com.nerdgeeks.nerdcrict20.models.Summary;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by hp on 5/7/2017.
 */

public interface ApiInterface {
    @GET
    Call<Matches> getMatchData(@Url String mUrl);

    @GET
    Call<LiveMatch> getLiveMatchData(@Url String nUrl);

    @GET
    Call<Summary> getSummary(@Url String nUrl);
}
