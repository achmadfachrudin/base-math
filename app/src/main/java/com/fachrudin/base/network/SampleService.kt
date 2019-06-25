package com.fachrudin.base.network

import com.fachrudin.base.entities.Menu
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

/**
 * @author achmad.fachrudin
 * @date 25-Jun-19
 */
interface SampleService {
    @GET("5d10dfe030000060484ca2f7")
    fun getMenuAsync(): Deferred<Response<Menu>>
}