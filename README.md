# FloodMonitoring - in development, do not use.

Android library for the gov.uk Flood Monitoring API: http://environment.data.gov.uk/flood-monitoring/doc/reference

The FloodMonitoringHarness example application illustrates how to use the library with RxJava.

Example:

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

#Flood Warnings

`getAreaWarnings(double latitude, double longitude, int distance)` returns any flood alerts in the area as an array of FloodWarning objects.

`getFloodAreaFromUrl(String url)` returns a `Flood` object containing the `FloodArea` including latitude and longitude for plotting warning locations on a map.

#Water Level Stations

`getAreaStations(double latitude, double longitude, int distance)` returns an array of `StationOverview` objects containing some detail, but you should use the `id` to fetch the full `Station` object:

`getStation(String url)` returns the full measurement station detail.

`getReadings(String url, int count)` gets an array of `Reading` objects, normally taken at 15 minute intervals that you can use to create graphs.




