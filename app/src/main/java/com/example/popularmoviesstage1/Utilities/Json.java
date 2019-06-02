package com.example.popularmoviesstage1.Utilities;

import com.example.popularmoviesstage1.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Json {
    private static String originalTitle;
    private static String backdropPath;
    private static String image;
    private static String overView;
    private static String voteAverage;
    private static String releaseDate;
    private static String RESULTS="results";
    private static String POSTER_PATH="poster_path";
    private static JSONArray jsonArray;
    public static String[] getData(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            if(jsonObject.has(RESULTS)) {
                jsonArray = jsonObject.getJSONArray(RESULTS);
                String[] images = new String[jsonArray.length()];
                for(int i = 0;i<jsonArray.length();i++)
                {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    images[i] = jsonObject1.getString(POSTER_PATH);
                }
                return images;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void arrangeData(int position){
        JSONObject jsonObject = null;
        try {
            jsonObject=jsonArray.getJSONObject(position);
            originalTitle=jsonObject.getString(MainActivity.mainTitle);
            backdropPath=jsonObject.getString(MainActivity.backdropPath);
            image=jsonObject.getString(POSTER_PATH);
            overView=jsonObject.getString(MainActivity.overView);
            voteAverage=jsonObject.getString(MainActivity.voteCount);
            releaseDate=jsonObject.getString(MainActivity.releaseDate);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static String getOriginalTitle() {
        return originalTitle;
    }

    public static void setOriginalTitle(String originalTitle) {
        Json.originalTitle = originalTitle;
    }

    public static String getBackdropPath() {
        return backdropPath;
    }

    public static void setBackdropPath(String backdropPath) {
        Json.backdropPath = backdropPath;
    }

    public static String getImage() {
        return image;
    }

    public static void setImage(String image) {
        Json.image = image;
    }

    public static String getOverView() {
        return overView;
    }

    public static void setOverView(String overView) {
        Json.overView = overView;
    }

    public static String getVoteAverage() {
        return voteAverage;
    }

    public static void setVoteAverage(String voteAverage) {
        Json.voteAverage = voteAverage;
    }

    public static String getReleaseDate() {
        return releaseDate;
    }

    public static void setReleaseDate(String releaseDate) {
        Json.releaseDate = releaseDate;
    }

    public static String getPosterPath() {
        return POSTER_PATH;
    }

    public static void setPosterPath(String posterPath) {
        POSTER_PATH = posterPath;
    }
}
