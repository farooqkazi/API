package com.example.laptop.myfirstapi.services;

import com.example.laptop.myfirstapi.model.CakesModel;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Define the form of request: Get, Post, Query
 */

public interface ICakesAPI {

    @GET(Constants.CAKELIST_API)
    //Observable<List<FlowersModel>> getFlowers();
    Observable<List<CakesModel>> getCakes();

    //if its a JSON Array (starts with [) then Observable<List<ClassName>>, if it's not an array, just do Observable<ClassName>


}
