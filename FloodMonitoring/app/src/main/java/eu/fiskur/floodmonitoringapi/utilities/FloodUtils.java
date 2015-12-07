package eu.fiskur.floodmonitoringapi.utilities;

import android.app.Service;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.squareup.okhttp.ResponseBody;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FloodUtils {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static String[] counties = new String[]{
            "Avon",
            "Bedfordshire",
            "Berkshire",
            "Borders",
            "Buckinghamshire",
            "Cambridgeshire",
            "Central",
            "Cheshire",
            "Cleveland",
            "Clwyd",
            "Cornwall",
            "County Antrim",
            "County Armagh",
            "County Down",
            "County Fermanagh",
            "County Londonderry",
            "County Tyrone",
            "Cumbria",
            "Derbyshire",
            "Devon",
            "Dorset",
            "Dumfries and Galloway",
            "Durham",
            "Dyfed",
            "East Sussex",
            "Essex",
            "Fife",
            "Gloucestershire",
            "Grampian",
            "Greater Manchester",
            "Gwent",
            "Gwynedd County",
            "Hampshire",
            "Herefordshire",
            "Hertfordshire",
            "Highlands and Islands",
            "Humberside",
            "Isle of Wight",
            "Kent",
            "Lancashire",
            "Leicestershire",
            "Lincolnshire",
            "Lothian",
            "Merseyside",
            "Mid Glamorgan",
            "Norfolk",
            "North Yorkshire",
            "Northamptonshire",
            "Northumberland",
            "Nottinghamshire",
            "Oxfordshire",
            "Powys",
            "Rutland",
            "Shropshire",
            "Somerset",
            "South Glamorgan",
            "South Yorkshire",
            "Staffordshire",
            "Strathclyde",
            "Suffolk",
            "Surrey",
            "Tayside",
            "Tyne and Wear",
            "Warwickshire",
            "West Glamorgan",
            "West Midlands",
            "West Sussex",
            "West Yorkshire",
            "Wiltshire",
            "Worcestershire"
    };

    /*
        Although Retrofit isn't designed for downloading binary data it can be used to fetch images
        This convenience method builds the bitmap and populates an ImageView
     */
    public boolean loadImage(ResponseBody responseBody, ImageView imageView){
        byte[] imageBytes = null;
        try {
            imageBytes = responseBody.bytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (imageBytes != null) {
            Bitmap bmp = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            imageView.setImageBitmap(bmp);
            return true;
        }else{
            return false;
        }
    }

    //If for some reason you want to avoid GSON serialisation:
    public JSONObject getJSONObject(ResponseBody responseBody){
        try{
            String decoded = new String(responseBody.bytes(), "UTF-8");
            return new JSONObject(decoded);
        }catch(JSONException | IOException e){
            e.printStackTrace();
            return new JSONObject();
        }
    }

    //Today in the format yyyy-MM-dd
    public static String getTodaysDate(){
        return sdf.format(new Date());
    }

    //Today minus x days in the format yyyy-MM-dd
    public static String getPreviousDate(int days){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -days);
        return sdf.format(cal.getTime());
    }

    /*
        From: http://stackoverflow.com/a/31791765

        Rather than use expensive JSONObject or GSON + Pojos just use a regular expression to extract
        latitude and longitude pairs from JSON

     */
    public List<LatLng> getPoints(String jsonStr){
        List<LatLng> points = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\[([^\\[\\],]*),([^\\[\\],]*)]");
        Matcher latLonMatcher = pattern.matcher(jsonStr);
        while (latLonMatcher.find()) {
            double v1 = Double.parseDouble(latLonMatcher.group(1));
            double v2 = Double.parseDouble(latLonMatcher.group(2));

            if(v1 > v2){
                points.add(new LatLng(v1, v2));
            }else{
                points.add(new LatLng(v2, v1));
            }
        }

        return points;
    }

    /*
           Get the center of an array of LatLng points, more accurate than using bounds as outliers
           won't influence the result as much
     */
    public static LatLng getCenter(List<LatLng> points){
        double totalLatitude = 0;
        double totalLongitude = 0;
        for(LatLng point : points){
            totalLatitude += point.latitude;
            totalLongitude += point.longitude;
        }

        return new LatLng(totalLatitude/points.size(), totalLongitude/points.size());
    }

    /*
        Untested.
     */
    public static LatLngBounds getBounds(List<LatLng> points){
        LatLng sw;
        LatLng ne;

        double highestLat = 0;
        double highestLon = 0;
        double lowestLat = Double.MAX_VALUE;
        double lowestLon = Double.MAX_VALUE;

        for(LatLng point : points){
            double thisLat = point.latitude;
            double thisLon = point.longitude;

            if(thisLat > highestLat){
                highestLat = thisLat;
            }
            if(thisLon > highestLon){
                highestLon = thisLon;
            }
            if(thisLat < lowestLat){
                lowestLat = thisLat;
            }
            if(thisLon < lowestLon){
                lowestLon = thisLon;
            }
        }

        sw = new LatLng(lowestLat, lowestLon);
        ne = new LatLng(highestLat, highestLon);

        return new LatLngBounds(sw, ne);
    }

}
