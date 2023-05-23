package org.adaschool.retrofit.network.service;

import org.adaschool.retrofit.network.dto.BreedImagesDto;
import org.adaschool.retrofit.network.dto.PlaceDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DogApiService {
    @GET("v1/places")
    Call<List<PlaceDto>> getAllBreeds();

//    @GET("v1/places")
//    Call<BreedImagesDto> getBreedImages(String breed);

}