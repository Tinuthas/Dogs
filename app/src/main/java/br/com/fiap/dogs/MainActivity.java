package br.com.fiap.dogs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import br.com.fiap.dogs.model.Dog;
import br.com.fiap.dogs.repository.DogRepository;
import br.com.fiap.dogs.repository.DogRespositoryI;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private TextView textView;
    private ImageView dogImageView;
    private DogRespositoryI respository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        textView = findViewById(R.id.dogNameTextView);
        dogImageView = findViewById(R.id.dogImageView);


        try{

            final List<String> dogs = new ArrayList<String>();
            dogs.add("Selecione");

            new DogRepository(new IView() {
                @Override
                public void onResponse(Object objeto) {

                    Map<String, List<String>> map = (Map<String, List<String>>) objeto;
                    Set<String> chaves = map.keySet();

                    for (Iterator<String> iterator = chaves.iterator(); iterator.hasNext(); ) {
                        String chave = iterator.next();
                        List<String> subs = new ArrayList<>();
                        if (chave != null) {
                            if (map.get(chave) != null) {
                                subs = map.get(chave);
                            }
                        }
                        if (subs.size() > 0) {
                            for (String subName : subs) {
                                dogs.add(subName + " " + chave);
                            }continue;
                        }
                        dogs.add(chave);

                    }

                }

                @Override
                public void onFailure(Throwable t) {

                }
            }).getDogAll();

            //List<String> dogs = respository.getDogAll();


            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                    dogs);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);


            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String nsvNome = parent.getItemAtPosition(position).toString();
                    try{
                        if (!nsvNome.equals("Selecione")) {
                            setImageDog(nsvNome);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

    public void setImageDog(String name) throws InterruptedException, ExecutionException, IOException {
        //affenpinscher
        name = checkName(name);
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        new DogRepository(new IView() {
            @Override
            public void onResponse(Object objeto) {
                Dog dog = (Dog) objeto;
                Picasso.with(MainActivity.this).load(dog.getMessage()).into(dogImageView);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        }).getDog(name);


    }

    public String checkName(String name) {
        if (!name.contains(" ")) return name;

        String[] a = name.split(" ");
        name = a[1] + "-" + a[0];

        return name;
    }

}
