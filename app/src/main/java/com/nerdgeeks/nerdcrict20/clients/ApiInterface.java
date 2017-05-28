package com.nerdgeeks.nerdcrict20.clients;

import com.nerdgeeks.nerdcrict20.models.Matches;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by hp on 5/7/2017.
 */

public interface ApiInterface {
    @GET
    Call<Matches> getSparksData(@Url String Url);
}
