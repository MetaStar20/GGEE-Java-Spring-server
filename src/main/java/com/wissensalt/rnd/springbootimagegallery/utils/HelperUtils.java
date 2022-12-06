package com.wissensalt.rnd.springbootimagegallery.utils;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.Date;
import java.util.HashMap;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.lang.String;
import java.util.Map;
import java.lang.Integer;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import java.util.Collections;
import org.springframework.http.HttpEntity;
import okhttp3.*;
import java.io.IOException;

public class HelperUtils {

public HelperUtils() {}

private static final String APP_URL = "http://127.0.0.1:8765/";


public static String getCurrentTimeUsingDate() {
    Date date = new Date();
    String strDateFormat = "yyyy-MM-dd-hh:mm:ss a";
    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
    String formattedDate= dateFormat.format(date);
    System.out.println("Current time of the day using Date - 12 hour format: " + formattedDate);
    return formattedDate;
}

public static void sendStartPostRequest(int id_request,int id_method,int resolution,int intervals,int extent) {

    // request url
    String url = APP_URL + "start";
	// one instance
	OkHttpClient httpClient = new OkHttpClient();
    Response response = null;
	// form parameters
    RequestBody formBody = new FormBody.Builder()
            .add("id_request", String.valueOf(id_request))
            .add("id_method", String.valueOf(id_method))
            .add("resolution", String.valueOf(resolution))
            .add("intervals", String.valueOf(intervals))
            .add("extent", String.valueOf(extent))
            .build();

    Request request = new Request.Builder()
            .url(url)
            .post(formBody)
            .build();

    try {
    	response = httpClient.newCall(request).execute();
        if (!response.isSuccessful()) {
        	System.out.println(response.body().string());
        } else {
			System.out.println("Request Failed");
        }
        // Get response body
    }
    catch (Exception e) {
      System.out.println(e.toString());
	}
    finally {
        if (response != null){
            response.close();
        }
    }
}

public static void sendStopPostRequest(int id_request,int id_method) {
	// request url
	String url = APP_URL + "stop";

	// one instance
    OkHttpClient httpClient = new OkHttpClient();
    Response response = null;
    // form parameters
    RequestBody formBody = new FormBody.Builder()
            .add("id_request", String.valueOf(id_request))
            .add("id_method", String.valueOf(id_method))
            .build();

    Request request = new Request.Builder()
            .url(url)
            .post(formBody)
            .build();

    try {
        response = httpClient.newCall(request).execute();
        if (!response.isSuccessful()) {
            System.out.println(response.body().string());
        } else {
            System.out.println("Request Failed");
        }
        // Get response body
    }
    catch (Exception e) {
      System.out.println(e.toString());
    }
    finally {
        if (response != null){
            response.close();
        }
    }

}
}
