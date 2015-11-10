package eu.fiskur.floodmonitoringapi;

public class FloodApiLogger {

    private static FloodApiLogger instance = null;
    private static ApiLogListener logListener = null;

    public static FloodApiLogger getInstance(){
        if(instance == null){
            instance = new FloodApiLogger();
        }
        instance = new FloodApiLogger();
        return instance;
    }

    private FloodApiLogger(){

    }

    public void setApiLogListener(ApiLogListener logListener){
        this.logListener = logListener;
    }

    public void log(String message){
        if(logListener != null){
            logListener.apiLog(message);
        }
    }

    public interface ApiLogListener{
        public void apiLog(String message);
    }
}

