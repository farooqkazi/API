package com.example.laptop.myfirstapi;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.laptop.myfirstapi.adapter.CakesAdapter;
import com.example.laptop.myfirstapi.model.CakesModel;
import com.example.laptop.myfirstapi.services.ICakesAPI;
import com.example.laptop.myfirstapi.services.InteractorService;
import com.example.laptop.myfirstapi.utilities.NetworkCheck;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    ICakesAPI iCakesAPI;
    private RecyclerView recyclerView_cakes;
    private SwipeRefreshLayout swipeLayout;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        /**
         * 1 Establish the connection
         * 2 Initialize the recycler
         * 3 Call the service
         */

        establishConnection();
        initializeRecyclerView();
        callService();

        swipeLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh_layout);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                callService();
            }
        });



    }

    public void establishConnection(){

        iCakesAPI = InteractorService.getConnection();
    }

    public void initializeRecyclerView(){

        recyclerView_cakes = (RecyclerView)findViewById(R.id.recyclerCakes);


        recyclerView_cakes.setLayoutManager(new LinearLayoutManager(this));


    }

    public void callService(){
        if (NetworkCheck.isNetworkAvailable(this)) {
            //iCakesAPI.getFlowers()
            iCakesAPI.getCakes()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<List<CakesModel>>() {
                        //.subscribe(new Observer<List<FlowersModel>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        //public void onNext(List<FlowersModel> cakesModels) {
                        public void onNext(List<CakesModel> cakesModels) {

                            //when using realm, clear Realm first then store data here

                            //recyclerView_cakes.setAdapter(new CakesAdapter(cakesModels, R.layout.row_flowers, getApplicationContext()));
                            recyclerView_cakes.setAdapter(new CakesAdapter(cakesModels, R.layout.row_cakes, getApplicationContext()));


                        }

                    });
        }
        else{

            //if using realm, read from Realm instead

            //Toast.makeText((getApplicationContext()), "No Network Available", Toast.LENGTH_LONG).show();
            alertDialog = new AlertDialog.Builder(this)
                    .setMessage("No Network")
                    .setTitle("Please Connect to the Internet")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            alertDialog.dismiss();
                            finish();
                        }
                    })
                    .show();
            }

    }


}
