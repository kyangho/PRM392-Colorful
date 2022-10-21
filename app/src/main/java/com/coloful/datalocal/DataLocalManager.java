package com.coloful.datalocal;

import android.content.Context;

import com.coloful.model.Account;
import com.google.gson.Gson;

public class DataLocalManager {

    private static final String JSON_ACCOUNT = "JSON_ACCOUNT";
    private static DataLocalManager instance;
    private SessionManager sessionManager;

    public static void init(Context context){
        instance = new DataLocalManager();
        instance.sessionManager = new SessionManager(context);
    }

    public static DataLocalManager getInstance(){
        if(instance == null){
            instance = new DataLocalManager();
        }
        return instance;
    }

    public static void setAccount(Account account){
        Gson gson = new Gson();
        String jsonAccount = gson.toJson(account);
        DataLocalManager.getInstance().sessionManager.putStringValue(JSON_ACCOUNT, jsonAccount);
    }

    public static Account getAccount() throws Exception{
        String jsonAccount = DataLocalManager.getInstance().sessionManager.getStringValue(JSON_ACCOUNT);
        Gson gson = new Gson();
        Account account = gson.fromJson(jsonAccount, Account.class);
        return account;
    }
}
