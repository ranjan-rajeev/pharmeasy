package com.pharmeasy.api.user;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.pharmeasy.api.APIRequestBuilder;
import com.pharmeasy.api.ResponseListener;
import com.pharmeasy.model.UserEntity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rajeev Ranjan on 20-Jul-18.
 */

public class UserAPIService extends APIRequestBuilder {

    //get the exhisting instance of retrofit
    IUserServices iUserServices = super.getRetrofit().create(IUserServices.class);


    public void getUser(int page, final ResponseListener<MutableLiveData<UserEntity>> userEntityResponseListener) {
        final MutableLiveData<UserEntity> userEntityMutableLiveData = new MutableLiveData<>();
        iUserServices.getUser(page).enqueue(new Callback<UserEntity>() {
            @Override
            public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {
                if (response.body() != null) {
                    UserEntity userEntity = response.body();
                    userEntityMutableLiveData.setValue(userEntity);
                    userEntityResponseListener.onSuccess(userEntityMutableLiveData);
                } else {
                    userEntityResponseListener.onFailure("Unable to fetch response from server");
                }
            }

            @Override
            public void onFailure(Call<UserEntity> call, Throwable t) {
                userEntityResponseListener.onFailure(t.getMessage());
            }
        });
        // return userEntityMutableLiveData;
    }
}
