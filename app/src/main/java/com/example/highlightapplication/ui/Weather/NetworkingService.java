package com.example.highlightapplication.ui.Weather;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NetworkingService {




    String url = "http://gd.geobytes.com/AutoCompleteCity?&q=";


    public static final ExecutorService networkingExecutor = Executors.newFixedThreadPool(4);
    static Handler networkHander = new Handler(Looper.getMainLooper());

    interface NetworkingListener{
        void APINetworkListner(String jsonString);
       // void APINetworkingListerForImage(Bitmap image);
    }

    NetworkingListener listener;

    public void fetchCitiesData(String text) {
        String completeURL = url + text;
        connect(completeURL);
    }

    private void connect(String url) {
        networkingExecutor.execute(new Runnable() {
            String jsonString = "";
            @Override
            public void run() {

                HttpURLConnection httpURLConnection = null;
                try {
                    URL urlObject = new URL(url);
                    httpURLConnection = (HttpURLConnection) urlObject.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setRequestProperty("Content-Type","application/json");
                    int statues = httpURLConnection.getResponseCode();

                    if ((statues >= 200) && (statues <= 299)) {
                        InputStream in = httpURLConnection.getInputStream();
                        InputStreamReader inputStreamReader = new InputStreamReader(in);
                        int read = 0;
                        while ((read = inputStreamReader.read()) != -1) {// json integers ASCII
                            char c = (char) read;
                            jsonString += c;
                        }// jsonString = ["Torbert, LA, United States","Torch, OH, United States","Toreboda, VG, Sweden","Torino, PI, Italy","Tornado, WV, United States","Tornillo, TX, United States","Tornio, LP, Finland","Toronto, KS, United States","Toronto, OH, United States","Toronto, ON, Canada","Toronto, SD, United States","Torquay, QL, Australia","Torrance, CA, United States","Torrance, PA, United States","torre del greco, CM, Italy","Torre Pellice, PI, Italy","Torrelles de Llobregat, CT, Spain","TORRENS CREEK, QL, Australia","Torreon, CA, Mexico","Torreon, NM, United States"]
                        // dataTask in ios
                        final String finalJson = jsonString;
                        networkHander.post(new Runnable() {
                            @Override
                            public void run() {
                                //send data to main thread
                                listener.APINetworkListner(finalJson);
                            }
                        });
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    httpURLConnection.disconnect();
                }
            }
        });
    }
    }

