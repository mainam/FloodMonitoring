package eu.fiskur.floodmonitoringapi.utilities;

import android.app.Service;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/*
    Android specific helpers
 */
public class AppUtils {

    public static final String OGL_ATTRIBUTION = "All data comes from the Environment Agency Real Time flood-monitoring API under the Open Government Licence v3";


    public static void hideKeyboard(Context context, EditText view){
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Service.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
