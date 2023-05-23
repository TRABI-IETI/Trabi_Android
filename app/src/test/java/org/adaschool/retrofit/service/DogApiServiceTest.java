package org.adaschool.retrofit.service;


import org.adaschool.retrofit.network.dto.BreedImagesDto;
import org.adaschool.retrofit.network.dto.PlaceDto;
import org.adaschool.retrofit.network.service.DogApiService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.mock.Calls;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DogApiServiceTest {

    @Mock
    DogApiService dogApiService;

    PlaceDto testPlaceDto;
    BreedImagesDto testBreedImagesDto;

    @Before
    public void setUp() {
        // Set up test data for BreedsResponse
        Map<String, String[]> testBreeds = new HashMap<>();
        testBreeds.put("affenpinscher", new String[]{});
        testBreeds.put("akita", new String[]{});
        testBreeds.put("bulldog", new String[]{"boston", "english", "french"});

        testPlaceDto = new PlaceDto();
        testPlaceDto.setStatus("success");
        testPlaceDto.setMessage(testBreeds);

        // Set up test data for BreedImagesDto
        List<String> testImages = Arrays.asList(
                "https://images.dog.ceo/breeds/bulldog-boston/n02096585_10380.jpg",
                "https://images.dog.ceo/breeds/bulldog-boston/n02096585_10473.jpg"
        );

        testBreedImagesDto = new BreedImagesDto();
        testBreedImagesDto.setStatus("success");
        testBreedImagesDto.setMessage(testImages);
    }

    @Test
    public void getAllBreedsTest() throws IOException {
        Call<PlaceDto> testCall = Calls.response(testPlaceDto);
        when(dogApiService.getAllBreeds()).thenReturn(testCall);

        Call<PlaceDto> call = dogApiService.getAllBreeds();
        Response<PlaceDto> response = call.execute();

        assertEquals(200, response.code());
        assertEquals(testPlaceDto.getStatus(), response.body().getStatus());
        assertEquals(testPlaceDto.getMessage(), response.body().getMessage());

        verify(dogApiService, times(1)).getAllBreeds();
    }

    @Test
    public void getBreedImagesTest() throws IOException {
        Call<BreedImagesDto> testCall = Calls.response(testBreedImagesDto);
        when(dogApiService.getBreedImages("bulldog")).thenReturn(testCall);

        Call<BreedImagesDto> call = dogApiService.getBreedImages("bulldog");
        Response<BreedImagesDto> response = call.execute();

        assertEquals(200, response.code());
        assertEquals(testBreedImagesDto.getStatus(), response.body().getStatus());
        assertEquals(testBreedImagesDto.getMessage(), response.body().getMessage());

        verify(dogApiService, times(1)).getBreedImages("bulldog");
    }
}