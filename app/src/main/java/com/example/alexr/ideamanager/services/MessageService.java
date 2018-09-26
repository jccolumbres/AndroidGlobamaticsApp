package com.example.alexr.ideamanager.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface MessageService {

    @GET("messages")
    Call<String> getMessages();

    @GET
    Call<String> getMessagesFromDiffUrl(@Url String altUrl);

}
