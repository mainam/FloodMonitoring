package eu.fiskur.floodmonitoringapi;

import android.app.Service;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.okhttp.ResponseBody;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FloodUtils {
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

    public static void hideKeyboard(Context context, EditText view){
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Service.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void loadImage(ResponseBody responseBody, ImageView imageView){
        byte[] imageBytes = null;
        try {
            imageBytes = responseBody.bytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (imageBytes != null) {
            Bitmap bmp = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            imageView.setImageBitmap(bmp);
        }else{
            //
        }
    }
}
