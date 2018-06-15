package com.fenghaha.mvpdemo.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by FengHaHa on2018/5/25 0025 12:11
 */
public class HttpUtil {
    private static final String TAG = "HttpUtil";

    private static Handler handler = new Handler();


    public static void post(String url, String param, HttpCallBack callBack) {


        //把开启新线程的操作封装在网络请求里
        new Thread(() -> {
            HttpURLConnection connection = null;
            //Log.d(TAG, "网络请求");
            try {
                URL mUrl = new URL(url);
                connection = (HttpURLConnection) mUrl.openConnection();
                connection.setRequestMethod("POST");
                connection.setReadTimeout(5000);
                connection.setConnectTimeout(8000);
                connection.setDoOutput(true);
                OutputStream os = connection.getOutputStream();
                os.write(param.getBytes());
                os.flush();
                os.close();
                int responseCode = connection.getResponseCode();
                Log.d("ResponseCode", "ResponseCode = " + responseCode);
                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    Response response = new Response(getByteArrayFromIS(connection.getInputStream()));
                    // Log.d(TAG, "info = " + response.getInfo() + "status = " + response.getStatus());
                    handler.post(() -> callBack.onResponse(response));
                } else {
                    handler.post(() -> {
                        callBack.onFail("网络");
                    });
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static byte[] getByteArrayFromIS(InputStream is) throws IOException {
        byte buff[] = new byte[1024];
        int len;
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        while ((len = is.read(buff)) != -1) {
            os.write(buff, 0, len);
        }
        byte[] bytes = os.toByteArray();
        is.close();
        os.close();
        return bytes;
    }

    public static class Response {
        private int status;
        private String data;
        private byte[] bytes;
        private String info;

        public Response(byte[] content) {
            bytes = content;
            String contentString = new String(content);

            if (JsonParser.has(contentString, "status")) {
                status = Integer.parseInt(JsonParser.getElement(contentString, "status"));
            } else {
                status = 200;
            }
            if (JsonParser.has(contentString, "info"))
                info = JsonParser.getElement(contentString, "info");
            data = JsonParser.getElement(contentString, "data");
        }

        public boolean isOk() {
            return status == 200;
        }

        public String getInfo() {
            return info;
        }

        public String getData() {
            return data;
        }

        public byte[] getBytes() {
            return bytes;
        }
    }

    public interface HttpCallBack {
        void onResponse(Response response);

        void onFail(String reason);
    }

    public interface ImageCallback {
        void onResponse(Bitmap bitmap, String info);
    }
}