package com.example.go4lunch24;

import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import com.example.go4lunch24.models.Restaurant;
import com.example.go4lunch24.models.WorkMate;
import com.example.go4lunch24.repositories.RestaurantRepository;
import com.example.go4lunch24.repositories.WorkMatesRepository;
import com.example.go4lunch24.viewModel.RestaurantDetailViewModel;
import com.google.firebase.auth.FirebaseAuth;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)

public class RestaurantDetailViewModelTest {


    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Mock
    WorkMatesRepository workMatesRepository;
    @Mock
    RestaurantRepository restaurantRepository;
    @Mock
    FirebaseAuth firebaseAuth;
    @Mock
    private RestaurantDetailViewModel restaurantDetailViewModel;

    private WorkMate workMate;
    private String currentUid = "321";
    private String restaurantId = "restaurantId";
    private MutableLiveData<Restaurant> restaurantLiveData = new MutableLiveData<>();
    private MutableLiveData<Restaurant> googleRestaurantDetail = new MutableLiveData<>();
    private Restaurant restaurants;

    @Before
    public void setUp()  {
        when(workMatesRepository.getActualUser()).thenReturn(workMate);
        when(restaurantRepository.getGoogleRestaurantDetail(restaurantId)).thenReturn(googleRestaurantDetail);
    }

    @Test
    public void fetchRestaurantViewModel() throws InterruptedException {
        //Given
        Restaurant restaurantExpected = new Restaurant("1234","name",48.633331,2.33333,"address",true,100,"photoReference",5,"phoneNumber","webSite");
        //When
        restaurantDetailViewModel.getRestaurantDetail(restaurantId);
        restaurants = LiveDataTestUtil.getOrAwaitValue(restaurantDetailViewModel.getRestaurantDetail(restaurantId));
        Restaurant restaurantDetailResult = LiveDataTestUtil.getOrAwaitValue(restaurantDetailViewModel.getRestaurantDetail(restaurantId));
        restaurantDetailViewModel = new RestaurantDetailViewModel(restaurantRepository, workMatesRepository);
        //Then

        Assert.assertNotNull(restaurantDetailResult);
        Assert.assertEquals(restaurantExpected, restaurantDetailResult );
    }
}
