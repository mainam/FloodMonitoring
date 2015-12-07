package eu.fiskur.floodmonitoringapi.utilities;

import timber.log.Timber;

public class FLog {

    private static Timber.DebugTree debugTree;

    public static void d(String message){
        if(debugTree == null){
            debugTree = new Timber.DebugTree();
            Timber.plant(debugTree);
        }
        Timber.d("-->>> " + message);
    }
}
