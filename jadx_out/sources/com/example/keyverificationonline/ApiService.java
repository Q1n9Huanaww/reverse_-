package com.example.keyverificationonline;

import okhttp366.ResponseBody;
import retrofit266.Call;
import retrofit266.http.GET;
import retrofit266.http.Url;

/* JADX INFO: loaded from: classes2.dex */
public interface ApiService {
    @GET
    Call<ResponseBody> checkExpire(@Url String str);

    @GET
    Call<ResponseBody> getInfo(@Url String str);

    @GET
    Call<ResponseBody> userLogin(@Url String str);
}
