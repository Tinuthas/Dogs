package br.com.fiap.dogs.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import br.com.fiap.dogs.model.Dog;
import br.com.fiap.dogs.IView;
import br.com.fiap.dogs.connect.DogService;
import br.com.fiap.dogs.connect.RetrofitConfig;
import br.com.fiap.dogs.model.ListAllResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DogRepository implements DogRespositoryI{

    private final DogService dogService;
    private final IView view;

    public DogRepository(IView view) {
        this.view = view;
        this.dogService = new RetrofitConfig().getDogService();
    }

    @Override
    public void getDog(String dog) throws ExecutionException, InterruptedException {
        Call<Dog> call = dogService.buscarDog(dog);
        call.enqueue(new Callback<Dog>() {
            @Override
            public void onResponse(Call<Dog> call, Response<Dog> response) {
                view.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<Dog> call, Throwable t) {
                view.onFailure(t);
            }
        });
    }

    @Override
    public void getDogAll() {
        //ResponseBody responseBody = new GetDogAll().execute().get();
        final List<String> listaDogs = new ArrayList<>();
        Call<ListAllResponse> call = dogService.buscarDogAll();
        call.enqueue(new Callback<ListAllResponse>() {
            @Override
            public void onResponse(Call<ListAllResponse> call, Response<ListAllResponse> response) {

                ListAllResponse listAllResponse = response.body();

                Map<String, List<String>> messages = listAllResponse.getBreeds();

                view.onResponse(messages);

            }

            @Override
            public void onFailure(Call<ListAllResponse> call, Throwable t) {
                view.onFailure(t);
            }
        });

    }






}
