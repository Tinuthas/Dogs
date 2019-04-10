package br.com.fiap.dogs.repository;

import org.json.JSONException;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface DogRespositoryI {

    void getDog(String dog) throws IOException, ExecutionException, InterruptedException;

    void getDogAll() throws IOException, JSONException, ExecutionException, InterruptedException;
}
