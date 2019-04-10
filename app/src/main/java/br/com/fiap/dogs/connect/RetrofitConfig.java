package br.com.fiap.dogs.connect;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {

    private final Retrofit retrofit;

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://dog.ceo/api/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public DogService getDogService() {
        return this.retrofit.create(DogService.class);
    }


}
