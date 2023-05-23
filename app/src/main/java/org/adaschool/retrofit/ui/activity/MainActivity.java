package org.adaschool.retrofit.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.bumptech.glide.Glide;

import org.adaschool.retrofit.databinding.ActivityMainBinding;
import org.adaschool.retrofit.network.RetrofitInstance;
import org.adaschool.retrofit.network.dto.BreedImagesDto;
import org.adaschool.retrofit.network.dto.DogBreed;
import org.adaschool.retrofit.network.dto.PlaceDto;
import org.adaschool.retrofit.network.service.DogApiService;
import org.adaschool.retrofit.ui.adapter.DogsListAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ActivityMainBinding binding;

    private SharedPreferences sharedPreferences;

    private DogsListAdapter dogsListAdapter = new DogsListAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedPreferences = getSharedPreferences("org.adaschool.retrofit", MODE_PRIVATE);
        configureRecyclerView();
        loadDogBreeds();

    }

    private void loadDogBreeds() {

        DogApiService dogApiService = RetrofitInstance.getRetrofitInstance(sharedPreferences)
                .create(DogApiService.class);
        Call<List<PlaceDto>> call = dogApiService.getAllBreeds();
        call.enqueue(new Callback<List<PlaceDto>>() {
            @Override
            public void onResponse(Call<List<PlaceDto>> call, Response<List<PlaceDto>> response) {
                if (response.isSuccessful()) {
                    List<PlaceDto> breedsMap = response.body();
                    dogsListAdapter.update(breedsMap);
//                    Set<String> breeds = breedsMap.keySet();
                    Log.d("Developer", "Breed: " + breedsMap);
//                    String breed = (String) breedsMap.toArray()[0];
//                    Log.d("Developer", "Breed: " + breed);
//                    loadDogBreedImages(breed);

                } else {
                    Log.e(TAG, "Error en la respuesta de la API");
                }
            }

//            private void loadDogBreedImages(String breed) {
//                Call<BreedImagesDto> breedImages = dogApiService.getBreedImages(breed);
//                breedImages.enqueue(new Callback<BreedImagesDto>() {
//                    @Override
//                    public void onResponse(Call<BreedImagesDto> call, Response<BreedImagesDto> response) {
//                        if (response.isSuccessful()) {
//                            BreedImagesDto body = response.body();
//                            List<DogBreed> dogs = new ArrayList<>();
//                            for (String image : body.getMessage()) {
//                                dogs.add(new DogBreed(breed, image));
//                            }
//                            dogsListAdapter.update(dogs);
//                        } else {
//                            Log.e(TAG, "Error en la respuesta de la API");
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<BreedImagesDto> call, Throwable t) {
//                        Log.e(TAG, "Error al llamar a la API", t);
//                    }
//                });
//                Log.d("Developer", "Breed: " + breed);
//            }

            @Override
            public void onFailure(Call<List<PlaceDto>> call, Throwable t) {
                Log.e(TAG, "Error al llamar a la API", t);
            }
        });
    }

    private void configureRecyclerView() {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(dogsListAdapter);
    }


    private void logout() {
        sharedPreferences.edit().remove("token").apply();
    }

    private void saveToken() {
        sharedPreferences.edit().putString("token", "token").apply();
    }

}