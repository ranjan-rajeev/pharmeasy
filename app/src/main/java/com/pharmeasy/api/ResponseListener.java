package com.pharmeasy.api;

/**
 * Created by Rajeev Ranjan on 20-Jul-18.
 */

public interface ResponseListener<T> {

    void onSuccess(T response);

    void onFailure(String message);

}
