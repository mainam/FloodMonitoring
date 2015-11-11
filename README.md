# FloodMonitoring

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
