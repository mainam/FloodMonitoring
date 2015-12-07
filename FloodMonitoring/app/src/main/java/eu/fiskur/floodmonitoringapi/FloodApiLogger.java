package eu.fiskur.floodmonitoringapi;

/*
    Simple class for use with Retrofit/OkHttp request interception, if 'logOutput(true)' is set on the FloodMonitoring API
    implementation can set their own listener here, eg:

    FloodApiLogger.getInstance().setApiLogListener(new ApiLogListener(){
        void apiLog(String message){
            Log.d("SomeTag", message);
        }
    });
 */
public class FloodApiLogger {

    private static FloodApiLogger instance = null;
    private static ApiLogListener logListener = null;

    public static FloodApiLogger getInstance(){
        if(instance == null){
            instance = new FloodApiLogger();
        }
        return instance;
    }

    private FloodApiLogger(){

    }

    public void setApiLogListener(ApiLogListener logListener){
        FloodApiLogger.logListener = logListener;
    }

    public void log(String message){
        if(logListener != null){
            logListener.apiLog(message);
        }
    }

    public interface ApiLogListener{
        void apiLog(String message);
    }
}

