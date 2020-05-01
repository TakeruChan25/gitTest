package com.example.ssbbmm5.api

//import com.example.ssbbmm5.BuildConfig.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Interface {

    val BASE_URL = "https://api.github.com/"

    fun create() : Service {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(Service::class.java)
    }
//    var retrofit: Retrofit? = null
//
//    fun create(): Service? {
//        if (retrofit == null) {
//            retrofit = Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//        }
//        return retrofit
//    }

//    fun create(): Service {
//        val retrofit = Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create())
////            .baseUrl(BASE_URL)
//            .build()
//        return retrofit.create(Service::class.java)
//    }
}