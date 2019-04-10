package br.com.fiap.dogs.connect;


import br.com.fiap.dogs.model.Dog;
import br.com.fiap.dogs.model.ListAllResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DogService {
    @GET("breed/{dog}/images/random")
    Call<Dog> buscarDog(@Path("dog") String dog);

    @GET("breeds/list/all")
    Call<ListAllResponse> buscarDogAll();


}
