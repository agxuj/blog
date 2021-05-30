package com.faddenyin.www.utils;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

public class WebUtils {

    public final static String TAG = "WebUtils";

    public static String get(String urlString, Map<String, String> data) throws Exception {
        return get(urlString + "?" + mapToQueryString(data, true));
    }

    public static String get(String urlString) throws Exception {
        HttpURLConnection conn = getConnectionSetting(urlString, "GET");
        return getString(conn);
    }

    public static <T> T getObjectByJson(String url, Map<String, String> map, Class<T> cls) throws Exception {
        return new Gson().fromJson(get(url, map), cls);
    }

    public static String post(String urlString, Map<String, String> data) throws Exception {
        return post(urlString, mapToQueryString(data, true));
    }

    public static String post(String urlString, String data) throws Exception {
        HttpURLConnection conn = getConnectionSetting(urlString, "POST");
        OutputStream op = conn.getOutputStream();
        op.write(data.getBytes());
        return getString(conn);
    }

    public static String postJsonForString(String urlString, String data) throws Exception {
        return post(urlString, data);
    }

    public static <T> T postJsonForObject(String urlString, String data, Class<T> dailySummaryClass) throws Exception {
        return new Gson().fromJson(post(urlString, data), dailySummaryClass);
    }

    private static HttpURLConnection getConnectionSetting(String urlString, String method) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(method);
        conn.setConnectTimeout(5000);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        return conn;
    }


    private static String getString(HttpURLConnection conn) throws Exception {
        int code = conn.getResponseCode();

        if (code == 200) {
            InputStream in = conn.getInputStream();
            StringBuffer sb = new StringBuffer();
            byte[] bytes = new byte[1024];
            int len;
            while ((len = in.read(bytes)) != -1) {
                sb.append(new String(bytes, 0, len, "utf-8"));
            }
            in.close();
            String result = sb.toString();

            return result;
        } else {
            throw new Exception("status = " + code);
        }
    }

    private static String mapToQueryString(Map<String, String> data, boolean isEncode) throws UnsupportedEncodingException {
        Set<String> keySet = data.keySet();
        StringBuilder sb = new StringBuilder();

        for (String key : keySet) {
            String value = data.get(key);
            if (value == null) value = "";
            if (isEncode) value = URLEncoder.encode(value, "UTF-8");
            sb.append(key);
            sb.append("=");
            sb.append(value);
            sb.append("&");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

}
