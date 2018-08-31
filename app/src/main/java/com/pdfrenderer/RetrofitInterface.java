package com.pdfrenderer;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;

public interface RetrofitInterface {

    @GET("XSLsample/pdf/sample-link_1.pdf")
    @Streaming
    Call<ResponseBody> downloadFile();
}
