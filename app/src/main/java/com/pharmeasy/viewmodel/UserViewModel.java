package com.pharmeasy.viewmodel;

import android.app.ProgressDialog;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.widget.Toast;

import com.pharmeasy.api.ResponseListener;
import com.pharmeasy.api.user.UserAPIService;
import com.pharmeasy.model.UserEntity;
import com.pharmeasy.ui.MainActivity;

/**
 * Created by Rajeev Ranjan on 20-Jul-18.
 */

public class UserViewModel extends ViewModel implements ResponseListener<MutableLiveData<UserEntity>> {

    ProgressDialog progressDialog;
    Context context;
    UserEntity userEntity;
    MutableLiveData<UserEntity> userEntityLiveData;
    static UserAPIService userAPIService;

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }


    public UserViewModel() {
        this.userAPIService = new UserAPIService();
    }

    public void init(Context context) {
        this.context = context;
    }


    public void getUser(int page) {
        ((MainActivity)context).showProgressView();
        userAPIService.getUser(page, this);
        // userEntity = new UserEntity();
    }


    public LiveData<UserEntity> getUserViewModel() {

        if (userEntityLiveData == null) {
            userEntityLiveData = new MutableLiveData<UserEntity>();
            getUser(1);
            //userAPIService.getUser(1, this);
        }

        return userEntityLiveData;
    }

    @Override
    public void onSuccess(MutableLiveData<UserEntity> response) {
        ((MainActivity)context).hideProgressView();

        if (userEntity == null) {
            userEntity = response.getValue();
            userEntityLiveData.setValue(userEntity);
        } else {
            userEntity.setPage(response.getValue().getPage());
            userEntity.getData().addAll(response.getValue().getData());
            userEntityLiveData.setValue(userEntity);
        }
        int count = userEntity.getPage() * userEntity.getPer_page();
        //Toast.makeText(context, count + "/" + userEntity.getTotal(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(String message) {
        ((MainActivity)context).hideProgressView();
        Toast.makeText(context, "" + message, Toast.LENGTH_SHORT).show();
    }

    public MutableLiveData<UserEntity> getUserEntityLiveData() {
        return userEntityLiveData;
    }

    public void setUserEntityLiveData(MutableLiveData<UserEntity> userEntityLiveData) {
        this.userEntityLiveData = userEntityLiveData;
    }


    protected void cancelDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    protected void showDialog() {
        showDialog("Loading...");
    }

    protected void showDialog(String msg) {
        if (progressDialog == null)
            progressDialog = ProgressDialog.show(context, "", msg, true);
        else {
            if (!progressDialog.isShowing())
                progressDialog = ProgressDialog.show(context, "", msg, true);
        }
    }

}
