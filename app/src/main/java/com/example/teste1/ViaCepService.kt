package com.example.teste1

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path



interface ViaCepService {
    @GET("{cep}/json/")
    suspend fun fetchCep(@Path("cep") cep: String): Response<CepResponse>

    companion object {
        fun create(): ViaCepService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://viacep.com.br/ws/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ViaCepService::class.java)
        }
    }
}
data class CepResponse(
    val cep: String,
    val logradouro: String,
    val bairro: String,
    val localidade: String,
    val ddd: String,
    val uf: String,
    val erro: Boolean
)