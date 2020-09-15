package com.example.pnrinquiry.ApiClient

import com.example.pnrinquiry.Model.BaseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiInterface {
    @GET("rail/{pnr}")
    fun getPnrDetail(@Header("x-rapidapi-key") xrapidapikey : String,@Path("pnr") pnr : String) : Call<BaseResponse>
}