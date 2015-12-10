# FloodMonitoring

Android library for the gov.uk Flood Monitoring API: http://environment.data.gov.uk/flood-monitoring/doc/reference

The FloodMonitoringHarness example application illustrates how to use the library with RxJava. The API is RESTful and follows [HATEOAS](https://en.wikipedia.org/wiki/HATEOAS), this means where the Android library requires a url you can use the id of an object: `getId()`

A couple of apps using this library:  
Flood Alerts: https://play.google.com/store/apps/details?id=eu.fiskur.floodmonitor  
River Levels: https://play.google.com/store/apps/details?id=eu.fiskur.riverlevels

Example usage:

```java
FloodMonitoring.getInstance().getThreeDayForecast()
	.observeOn(AndroidSchedulers.mainThread())
	.subscribeOn(Schedulers.newThread())
	.subscribe(new Observer<ThreeDayForecast>() {
	    @Override
	    public void onCompleted() {
	    }

	    @Override
	    public void onError(Throwable e) {
	        //...
	    }

	    @Override
	    public void onNext(ThreeDayForecast threeDayForecast) {
	        outputForecast(threeDayForecast);
	    }
	});
```

#Logging

You can turn on logging to see the network response:

`FloodMonitoring.getInstance().logOutput(true);`

Then register a log listener:

```java        
FloodApiLogger.getInstance().setApiLogListener(new FloodApiLogger.ApiLogListener() {
    @Override
    public void apiLog(String message) {
        Log.d(TAG, message);
    }
});
```

#Flood Warnings

`getAllWarnings()` returns `List<FloodWarning>` of all current alerts (including any that have been removed in the last 24 hours).

`getAreaWarnings(double latitude, double longitude, int distance)` returns `List<FloodWarning>` of any flood alerts in the area as an array of FloodWarning objects.

`getFloodAreaFromUrl(String url)` returns a `Flood` object containing the `FloodArea` including latitude and longitude for plotting warning locations on a map.

#Water Level Stations

`getAreaStations(double latitude, double longitude, int distance)` returns `List<StationOverview>` containing some detail, but you should use the `id` to fetch the full `Station` object:

`getStation(String url)` returns the full `StationDetail`.

`getReadings(String url, int count)` gets the last `count` `Reading` objects, normally taken at 15 minute intervals that you can use to create graphs, you can also use `getReadingsToday(String url)` and `getReadingsDays(String url, int days)` which will ge the last x days worth of readings (although the API docs state data is held for a month it only seems to return up to two weeks worth of readings).




