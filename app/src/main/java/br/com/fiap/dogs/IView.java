package br.com.fiap.dogs;

public interface IView<T> {

    void onResponse(T objeto);

    void onFailure(Throwable t);

}
