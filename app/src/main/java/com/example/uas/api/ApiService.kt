package com.example.uas.api

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("Hipo/university-domains-list/master/world_universities_and_domains.json")
    fun getChara(): Call<List<UnivResponseItem>>
}