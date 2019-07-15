package com.example.collabolab.com.example.collabolab.service;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;


public class Service {
    private JSONArray resultData = null;

    final String BASE_URL = "http://172.16.5.132:5000/";

    public int population(int i){
        return -1;
    }

    public int population(){
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.accumulate("url", BASE_URL+"numofpeople");
            // 네트워크 처리 비동기화
            resultData = new NetworkProcessor().execute(jsonObject).get();

            // 결과 처리
            if(resultData == null)
                // error
                return -1;
            jsonObject = resultData.getJSONObject(0);
            return Integer.parseInt(jsonObject.getString("num"));

        }catch (JSONException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return -1;
    }
/*
    public JSONObject time(){
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.accumulate("url", BASE_URL+"");

            // 네트워크 처리 비동기화
            resultData = new NetworkProcessor().execute(jsonObject).get();

            // 결과 처리
            if(resultData == null)
                // error
                return -1;

            jsonObject = resultData.getJSONObject(0);
            return Integer.parseInt(jsonObject.getString("num"));

        }catch (JSONException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return -1;
    }
*/

    public int[] week(){
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.accumulate("url", BASE_URL+"avgofpeople");

            // 네트워크 처리 비동기화
            resultData = new NetworkProcessor().execute(jsonObject).get();

            // 결과 처리
            if(resultData == null)
                // error
                return null;

            jsonObject = resultData.getJSONObject(0);
            return new int[]{
                    (int)jsonObject.getDouble("mon_avg"),
                    (int)jsonObject.getDouble("tue_avg"),
                    (int)jsonObject.getDouble("wed_avg"),
                    (int)jsonObject.getDouble("thr_avg"),
                    (int)jsonObject.getDouble("fri_avg"),
            };

        }catch (JSONException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int[] tablePCnt(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.accumulate("url", BASE_URL+"tablestatus");

            // 네트워크 처리 비동기화
            resultData = new NetworkProcessor().execute(jsonObject).get();
            Log.i("PCNT",resultData.toString());
            // 결과 처리
            if(resultData == null)
                // error
                return null;

            jsonObject = resultData.getJSONObject(0);
            int arr[] = {
                    Integer.parseInt(jsonObject.getString("t1")),
                    Integer.parseInt(jsonObject.getString("t2")),
                    Integer.parseInt(jsonObject.getString("t3")),
                    Integer.parseInt(jsonObject.getString("t4")),
            };
            return arr;
        }catch (JSONException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int[] tableStatus(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.accumulate("url", BASE_URL+"tablestatus");

            // 네트워크 처리 비동기화
            resultData = new NetworkProcessor().execute(jsonObject).get();

            // 결과 처리
            if(resultData == null)
                // error
                return null;

            jsonObject = resultData.getJSONObject(0);
            int arr[] = {
                    Integer.parseInt(jsonObject.getString("s1")),
                    Integer.parseInt(jsonObject.getString("s2")),
                    Integer.parseInt(jsonObject.getString("s3")),
                    Integer.parseInt(jsonObject.getString("s4")),
            };
            return arr;
        }catch (JSONException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }




}
