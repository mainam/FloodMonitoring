package eu.fiskur.floodmonitoringapi;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

import eu.fiskur.floodmonitoringapi.stations.Measure;
import eu.fiskur.floodmonitoringapi.stations.Reading;
import eu.fiskur.floodmonitoringapi.stations.StationDetail;
import eu.fiskur.floodmonitoringapi.stations.StationOverview;

public class GsonStationsTests {

    @Test
    public void stationsGSONTest(){
        Gson gson = GSONProvider.getRestGson();
        List<StationOverview> stations = gson.fromJson(STATIONS_BY_LOCATION, new TypeToken<List<StationOverview>>() {
        }.getType());
        assertEquals(2, stations.size());
    }

    @Test
    public void stationsOverviewTest(){
        Gson gson = GSONProvider.getRestGson();
        List<StationOverview> stations = gson.fromJson(STATIONS_BY_LOCATION, new TypeToken<List<StationOverview>>() {
        }.getType());
        StationOverview overview = stations.get(0);
        assertEquals(2, overview.getMeasures().size());
    }



    @Test
    public void stationOverviewFieldsTest(){
        Gson gson = GSONProvider.getRestGson();
        List<StationOverview> stations = gson.fromJson(STATIONS_BY_LOCATION, new TypeToken<List<StationOverview>>() {
        }.getType());
        StationOverview overview = stations.get(0);
        assertEquals("http://environment.data.gov.uk/flood-monitoring/id/stations/690138", overview.getId());
        assertEquals("5116", overview.getRLOIid());
        assertEquals("Northern Manchester", overview.getCatchmentName());
        assertEquals("1998-05-07", overview.getDateOpened());
        assertEquals(381200, overview.getEee().intValue());
        assertEquals("Rawtenstall", overview.getLabel().toString());
    }

    @Test
    public void multipleMeasuresTest(){
        Gson gson = GSONProvider.getRestGson();
        StationDetail station = gson.fromJson(STAION_THREE_MEASURES, StationDetail.class);
        assertEquals(3, station.getMeasures().length);
    }


    @Test
    public void stationTest(){
        Gson gson = GSONProvider.getRestGson();
        StationDetail station = gson.fromJson(STATION, StationDetail.class);
        assertEquals(1, station.getMeasures().length);
    }

    @Test
    public void stationDetailFieldsTest(){
        Gson gson = GSONProvider.getRestGson();
        StationDetail station = gson.fromJson(STATION, StationDetail.class);
        assertEquals("http://environment.data.gov.uk/flood-monitoring/id/stations/690142", station.getId());
        assertEquals("5082", station.getRLOIid());
        assertEquals("Northern Manchester", station.getCatchmentName());
        assertEquals("1998-07-28", station.getDateOpened());
        assertEquals("Little Bridge", station.getLabel().toString());
    }

    @Test
    public void measureTest(){
        Gson gson = GSONProvider.getRestGson();
        StationDetail station = gson.fromJson(STATION, StationDetail.class);
        Measure measure = station.getMeasures()[0];
        assertEquals("http://environment.data.gov.uk/flood-monitoring/id/measures/690142-level-stage-i-15_min-m", measure.getId());
        assertEquals("Little Br Helmshore - level-stage-i-15_min-m", measure.getLabel());
    }

    @Test
    public void readingsTest(){
        Gson gson = GSONProvider.getRestGson();
        List<Reading> readings = gson.fromJson(STATION_READINGS, new TypeToken<List<Reading>>(){}.getType());
        assertEquals(50, readings.size());
    }

    private static final String STATION_READINGS = "{ \n" +
            "  \"@context\" : \"http://environment.data.gov.uk/flood-monitoring/meta/context.jsonld\" ,\n" +
            "  \"meta\" : { \n" +
            "    \"publisher\" : \"Environment Agency\" ,\n" +
            "    \"licence\" : \"http://www.nationalarchives.gov.uk/doc/open-government-licence/version/3/\" ,\n" +
            "    \"documentation\" : \"http://environment.data.gov.uk/flood-monitoring/doc/reference\" ,\n" +
            "    \"version\" : \"0.5\" ,\n" +
            "    \"comment\" : \"Status: Beta service\" ,\n" +
            "    \"limit\" : 50 ,\n" +
            "    \"hasFormat\" : [ \"http://environment.data.gov.uk/flood-monitoring/id/stations/690140/readings.csv?_sorted=1&_limit=50\" ]\n" +
            "  }\n" +
            "   ,\n" +
            "  \"items\" : [ { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T16-00-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T16:00:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 0.754\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T15-45-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T15:45:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 0.765\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T15-30-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T15:30:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 0.774\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T15-15-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T15:15:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 0.785\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T15-00-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T15:00:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 0.794\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T14-45-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T14:45:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 0.803\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T14-30-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T14:30:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 0.816\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T14-15-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T14:15:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 0.827\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T14-00-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T14:00:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 0.841\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T13-45-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T13:45:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 0.855\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T13-30-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T13:30:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 0.87\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T13-15-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T13:15:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 0.888\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T13-00-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T13:00:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 0.907\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T12-45-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T12:45:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 0.927\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T12-30-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T12:30:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 0.949\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T12-15-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T12:15:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 0.975\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T12-00-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T12:00:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 0.997\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T11-45-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T11:45:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 1.026\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T11-30-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T11:30:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 1.051\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T11-15-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T11:15:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 1.076\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T11-00-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T11:00:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 1.101\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T10-45-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T10:45:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 1.131\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T10-30-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T10:30:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 1.202\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T10-15-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T10:15:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 1.214\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T10-00-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T10:00:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 1.219\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T09-45-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T09:45:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 1.218\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T09-30-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T09:30:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 1.214\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T09-15-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T09:15:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 1.208\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T09-00-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T09:00:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 1.201\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T08-45-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T08:45:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 1.194\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T08-30-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T08:30:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 1.188\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T08-15-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T08:15:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 1.183\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T08-00-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T08:00:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 1.18\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T07-45-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T07:45:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 1.177\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T07-30-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T07:30:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 1.175\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T07-15-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T07:15:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 1.173\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T07-00-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T07:00:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 1.167\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T06-45-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T06:45:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 1.163\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T06-30-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T06:30:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 1.157\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T06-15-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T06:15:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 1.149\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T06-00-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T06:00:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 1.135\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T05-45-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T05:45:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 1.115\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T05-30-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T05:30:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 1.091\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T05-15-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T05:15:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 1.059\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T05-00-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T05:00:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 1.019\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T04-45-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T04:45:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 0.965\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T04-30-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T04:30:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 0.907\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T04-15-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T04:15:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 0.798\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T04-00-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T04:00:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 0.718\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690140-level-stage-i-15_min-m/2015-12-10T03-45-00Z\" ,\n" +
            "    \"dateTime\" : \"2015-12-10T03:45:00Z\" ,\n" +
            "    \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690140-level-stage-i-15_min-m\" ,\n" +
            "    \"value\" : 0.67\n" +
            "  }\n" +
            "   ]\n" +
            "}\n" +
            "\n";

    private static final String STATION = "{ \n" +
            "  \"@context\" : \"http://environment.data.gov.uk/flood-monitoring/meta/context.jsonld\" ,\n" +
            "  \"meta\" : { \n" +
            "    \"publisher\" : \"Environment Agency\" ,\n" +
            "    \"licence\" : \"http://www.nationalarchives.gov.uk/doc/open-government-licence/version/3/\" ,\n" +
            "    \"documentation\" : \"http://environment.data.gov.uk/flood-monitoring/doc/reference\" ,\n" +
            "    \"version\" : \"0.5\" ,\n" +
            "    \"comment\" : \"Status: Beta service\" ,\n" +
            "    \"hasFormat\" : [ \"http://environment.data.gov.uk/flood-monitoring/id/stations/690142.rdf\", \"http://environment.data.gov.uk/flood-monitoring/id/stations/690142.ttl\", \"http://environment.data.gov.uk/flood-monitoring/id/stations/690142.html\" ]\n" +
            "  }\n" +
            "   ,\n" +
            "  \"items\" : { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/id/stations/690142\" ,\n" +
            "    \"RLOIid\" : \"5082\" ,\n" +
            "    \"catchmentName\" : \"Northern Manchester\" ,\n" +
            "    \"dateOpened\" : \"1998-07-28\" ,\n" +
            "    \"eaAreaName\" : \"North West - South\" ,\n" +
            "    \"eaRegionName\" : \"North West\" ,\n" +
            "    \"easting\" : 378200 ,\n" +
            "    \"label\" : \"Little Bridge\" ,\n" +
            "    \"lat\" : 53.684084 ,\n" +
            "    \"long\" : -2.331538 ,\n" +
            "    \"measures\" : { \n" +
            "      \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690142-level-stage-i-15_min-m\" ,\n" +
            "      \"label\" : \"Little Br Helmshore - level-stage-i-15_min-m\" ,\n" +
            "      \"latestReading\" : { \n" +
            "        \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/690142-level-stage-i-15_min-m/2015-12-07T10-15-00Z\" ,\n" +
            "        \"date\" : \"2015-12-07\" ,\n" +
            "        \"dateTime\" : \"2015-12-07T10:15:00Z\" ,\n" +
            "        \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690142-level-stage-i-15_min-m\" ,\n" +
            "        \"value\" : 0.233\n" +
            "      }\n" +
            "       ,\n" +
            "      \"notation\" : \"690142-level-stage-i-15_min-m\" ,\n" +
            "      \"parameter\" : \"level\" ,\n" +
            "      \"parameterName\" : \"Water Level\" ,\n" +
            "      \"period\" : 900 ,\n" +
            "      \"qualifier\" : \"Stage\" ,\n" +
            "      \"station\" : \"http://environment.data.gov.uk/flood-monitoring/id/stations/690142\" ,\n" +
            "      \"stationReference\" : \"690142\" ,\n" +
            "      \"type\" : [ \"http://environment.data.gov.uk/flood-monitoring/def/core/Measure\", \"http://environment.data.gov.uk/flood-monitoring/def/core/WaterLevel\" ] ,\n" +
            "      \"unit\" : \"http://qudt.org/1.1/vocab/unit#Meter\" ,\n" +
            "      \"unitName\" : \"m\" ,\n" +
            "      \"valueType\" : \"instantaneous\"\n" +
            "    }\n" +
            "     ,\n" +
            "    \"northing\" : 420900 ,\n" +
            "    \"notation\" : \"690142\" ,\n" +
            "    \"riverName\" : \"Ogden Brook\" ,\n" +
            "    \"stageScale\" : { \n" +
            "      \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/id/stations/690142/stageScale\" ,\n" +
            "      \"datum\" : 164.27 ,\n" +
            "      \"highestRecent\" : { \n" +
            "        \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/id/stations/690142/stageScale/highestRecent\" ,\n" +
            "        \"dateTime\" : \"2012-06-22T18:15:00\" ,\n" +
            "        \"value\" : 1.262\n" +
            "      }\n" +
            "       ,\n" +
            "      \"maxOnRecord\" : { \n" +
            "        \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/id/stations/690142/stageScale/maxOnRecord\" ,\n" +
            "        \"dateTime\" : \"2002-06-14T17:15:00\" ,\n" +
            "        \"value\" : 1.737\n" +
            "      }\n" +
            "       ,\n" +
            "      \"minOnRecord\" : { \n" +
            "        \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/id/stations/690142/stageScale/minOnRecord\" ,\n" +
            "        \"dateTime\" : \"1999-09-05T17:00:00\" ,\n" +
            "        \"value\" : 0\n" +
            "      }\n" +
            "       ,\n" +
            "      \"scaleMax\" : 3 ,\n" +
            "      \"typicalRangeHigh\" : 0.339 ,\n" +
            "      \"typicalRangeLow\" : 0.014\n" +
            "    }\n" +
            "     ,\n" +
            "    \"stationReference\" : \"690142\" ,\n" +
            "    \"town\" : \"Helmshore\" ,\n" +
            "    \"type\" : [ \"http://environment.data.gov.uk/flood-monitoring/def/core/SingleLevel\", \"http://environment.data.gov.uk/flood-monitoring/def/core/StationWrapper\" ] ,\n" +
            "    \"wiskiID\" : \"690142\"\n" +
            "  }\n" +
            "}\n" +
            "\n";

    private static final String STATIONS_BY_LOCATION = "{ \n" +
            "  \"@context\" : \"http://environment.data.gov.uk/flood-monitoring/meta/context.jsonld\" ,\n" +
            "  \"meta\" : { \n" +
            "    \"publisher\" : \"Environment Agency\" ,\n" +
            "    \"licence\" : \"http://www.nationalarchives.gov.uk/doc/open-government-licence/version/3/\" ,\n" +
            "    \"documentation\" : \"http://environment.data.gov.uk/flood-monitoring/doc/reference\" ,\n" +
            "    \"version\" : \"0.5\" ,\n" +
            "    \"comment\" : \"Status: Beta service\"\n" +
            "  }\n" +
            "   ,\n" +
            "  \"items\" : [ { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/id/stations/690138\" ,\n" +
            "    \"RLOIid\" : \"5116\" ,\n" +
            "    \"catchmentName\" : \"Northern Manchester\" ,\n" +
            "    \"dateOpened\" : \"1998-05-07\" ,\n" +
            "    \"eee\" : 381200 ,\n" +
            "    \"label\" : \"Rawtenstall\" ,\n" +
            "    \"measures\" : [ { \n" +
            "      \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690138-level-stage-i-15_min----\" ,\n" +
            "      \"parameter\" : \"level\" ,\n" +
            "      \"parameterName\" : \"Water Level\" ,\n" +
            "      \"period\" : 900 ,\n" +
            "      \"qualifier\" : \"Stage\" ,\n" +
            "      \"unitName\" : \"---\"\n" +
            "    }\n" +
            "    , { \n" +
            "      \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690138-level-stage-i-15_min-m\" ,\n" +
            "      \"parameter\" : \"level\" ,\n" +
            "      \"parameterName\" : \"Water Level\" ,\n" +
            "      \"period\" : 900 ,\n" +
            "      \"qualifier\" : \"Stage\" ,\n" +
            "      \"unitName\" : \"m\"\n" +
            "    }\n" +
            "     ] ,\n" +
            "    \"nnn\" : 423300 ,\n" +
            "    \"notation\" : \"690138\" ,\n" +
            "    \"riverName\" : \"Limey Water\" ,\n" +
            "    \"stageScale\" : \"http://environment.data.gov.uk/flood-monitoring/id/stations/690138/stageScale\" ,\n" +
            "    \"stationReference\" : \"690138\" ,\n" +
            "    \"town\" : \"Rawtenstall\" ,\n" +
            "    \"wiskiID\" : \"690138\"\n" +
            "  }\n" +
            "  , { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/id/stations/690142\" ,\n" +
            "    \"RLOIid\" : \"5082\" ,\n" +
            "    \"catchmentName\" : \"Northern Manchester\" ,\n" +
            "    \"dateOpened\" : \"1998-07-28\" ,\n" +
            "    \"eee\" : 378200 ,\n" +
            "    \"label\" : \"Little Bridge\" ,\n" +
            "    \"measures\" : [ { \n" +
            "      \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/690142-level-stage-i-15_min-m\" ,\n" +
            "      \"parameter\" : \"level\" ,\n" +
            "      \"parameterName\" : \"Water Level\" ,\n" +
            "      \"period\" : 900 ,\n" +
            "      \"qualifier\" : \"Stage\" ,\n" +
            "      \"unitName\" : \"m\"\n" +
            "    }\n" +
            "     ] ,\n" +
            "    \"nnn\" : 420900 ,\n" +
            "    \"notation\" : \"690142\" ,\n" +
            "    \"riverName\" : \"Ogden Brook\" ,\n" +
            "    \"stageScale\" : \"http://environment.data.gov.uk/flood-monitoring/id/stations/690142/stageScale\" ,\n" +
            "    \"stationReference\" : \"690142\" ,\n" +
            "    \"town\" : \"Helmshore\" ,\n" +
            "    \"wiskiID\" : \"690142\"\n" +
            "  }\n" +
            "   ]\n" +
            "}\n";

    private static final String STAION_THREE_MEASURES = "{ \n" +
            "  \"@context\" : \"http://environment.data.gov.uk/flood-monitoring/meta/context.jsonld\" ,\n" +
            "  \"meta\" : { \n" +
            "    \"publisher\" : \"Environment Agency\" ,\n" +
            "    \"licence\" : \"http://www.nationalarchives.gov.uk/doc/open-government-licence/version/3/\" ,\n" +
            "    \"documentation\" : \"http://environment.data.gov.uk/flood-monitoring/doc/reference\" ,\n" +
            "    \"version\" : \"0.5\" ,\n" +
            "    \"comment\" : \"Status: Beta service\" ,\n" +
            "    \"hasFormat\" : [ \"http://environment.data.gov.uk/flood-monitoring/id/stations/L12061.rdf\", \"http://environment.data.gov.uk/flood-monitoring/id/stations/L12061.ttl\", \"http://environment.data.gov.uk/flood-monitoring/id/stations/L12061.html\" ]\n" +
            "  }\n" +
            "   ,\n" +
            "  \"items\" : { \n" +
            "    \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/id/stations/L12061\" ,\n" +
            "    \"RLOIid\" : \"8343\" ,\n" +
            "    \"catchmentName\" : \"Aire and Calder\" ,\n" +
            "    \"dateOpened\" : \"2007-03-15\" ,\n" +
            "    \"eaAreaName\" : \"North East - Yorkshire\" ,\n" +
            "    \"eaRegionName\" : \"North East\" ,\n" +
            "    \"easting\" : 397115 ,\n" +
            "    \"gridReference\" : \"SD9711526245\" ,\n" +
            "    \"label\" : \"Todmorden Callis Bridge\" ,\n" +
            "    \"lat\" : 53.732576 ,\n" +
            "    \"long\" : -2.045202 ,\n" +
            "    \"measures\" : [\n" +
            "      { \n" +
            "        \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/L12061-level-stage-i-15_min-m\" ,\n" +
            "        \"label\" : \"Todmorden Callis Bridge - level-stage-i-15_min-m\" ,\n" +
            "        \"latestReading\" : { \n" +
            "          \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/L12061-level-stage-i-15_min-m/2015-12-07T05-15-00Z\" ,\n" +
            "          \"date\" : \"2015-12-07\" ,\n" +
            "          \"dateTime\" : \"2015-12-07T05:15:00Z\" ,\n" +
            "          \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/L12061-level-stage-i-15_min-m\" ,\n" +
            "          \"value\" : 0.298\n" +
            "        }\n" +
            "         ,\n" +
            "        \"notation\" : \"L12061-level-stage-i-15_min-m\" ,\n" +
            "        \"parameter\" : \"level\" ,\n" +
            "        \"parameterName\" : \"Water Level\" ,\n" +
            "        \"period\" : 900 ,\n" +
            "        \"qualifier\" : \"Stage\" ,\n" +
            "        \"station\" : \"http://environment.data.gov.uk/flood-monitoring/id/stations/L12061\" ,\n" +
            "        \"stationReference\" : \"L12061\" ,\n" +
            "        \"type\" : [ \"http://environment.data.gov.uk/flood-monitoring/def/core/Measure\", \"http://environment.data.gov.uk/flood-monitoring/def/core/WaterLevel\" ] ,\n" +
            "        \"unit\" : \"http://qudt.org/1.1/vocab/unit#Meter\" ,\n" +
            "        \"unitName\" : \"m\" ,\n" +
            "        \"valueType\" : \"instantaneous\"\n" +
            "      },\n" +
            "      { \n" +
            "        \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/L12061-level-stage-i-15_min-m\" ,\n" +
            "        \"label\" : \"Todmorden Callis Bridge - level-stage-i-15_min-m\" ,\n" +
            "        \"latestReading\" : { \n" +
            "          \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/L12061-level-stage-i-15_min-m/2015-12-07T05-15-00Z\" ,\n" +
            "          \"date\" : \"2015-12-07\" ,\n" +
            "          \"dateTime\" : \"2015-12-07T05:15:00Z\" ,\n" +
            "          \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/L12061-level-stage-i-15_min-m\" ,\n" +
            "          \"value\" : 0.298\n" +
            "        }\n" +
            "         ,\n" +
            "        \"notation\" : \"L12061-level-stage-i-15_min-m\" ,\n" +
            "        \"parameter\" : \"level\" ,\n" +
            "        \"parameterName\" : \"Water Level\" ,\n" +
            "        \"period\" : 900 ,\n" +
            "        \"qualifier\" : \"Stage\" ,\n" +
            "        \"station\" : \"http://environment.data.gov.uk/flood-monitoring/id/stations/L12061\" ,\n" +
            "        \"stationReference\" : \"L12061\" ,\n" +
            "        \"type\" : [ \"http://environment.data.gov.uk/flood-monitoring/def/core/Measure\", \"http://environment.data.gov.uk/flood-monitoring/def/core/WaterLevel\" ] ,\n" +
            "        \"unit\" : \"http://qudt.org/1.1/vocab/unit#Meter\" ,\n" +
            "        \"unitName\" : \"m\" ,\n" +
            "        \"valueType\" : \"instantaneous\"\n" +
            "      },\n" +
            "      { \n" +
            "        \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/L12061-level-stage-i-15_min-m\" ,\n" +
            "        \"label\" : \"Todmorden Callis Bridge - level-stage-i-15_min-m\" ,\n" +
            "        \"latestReading\" : { \n" +
            "          \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/data/readings/L12061-level-stage-i-15_min-m/2015-12-07T05-15-00Z\" ,\n" +
            "          \"date\" : \"2015-12-07\" ,\n" +
            "          \"dateTime\" : \"2015-12-07T05:15:00Z\" ,\n" +
            "          \"measure\" : \"http://environment.data.gov.uk/flood-monitoring/id/measures/L12061-level-stage-i-15_min-m\" ,\n" +
            "          \"value\" : 0.298\n" +
            "        }\n" +
            "         ,\n" +
            "        \"notation\" : \"L12061-level-stage-i-15_min-m\" ,\n" +
            "        \"parameter\" : \"level\" ,\n" +
            "        \"parameterName\" : \"Water Level\" ,\n" +
            "        \"period\" : 900 ,\n" +
            "        \"qualifier\" : \"Stage\" ,\n" +
            "        \"station\" : \"http://environment.data.gov.uk/flood-monitoring/id/stations/L12061\" ,\n" +
            "        \"stationReference\" : \"L12061\" ,\n" +
            "        \"type\" : [ \"http://environment.data.gov.uk/flood-monitoring/def/core/Measure\", \"http://environment.data.gov.uk/flood-monitoring/def/core/WaterLevel\" ] ,\n" +
            "        \"unit\" : \"http://qudt.org/1.1/vocab/unit#Meter\" ,\n" +
            "        \"unitName\" : \"m\" ,\n" +
            "        \"valueType\" : \"instantaneous\"\n" +
            "      }\n" +
            "    ]\n" +
            "     ,\n" +
            "    \"northing\" : 426245 ,\n" +
            "    \"notation\" : \"L12061\" ,\n" +
            "    \"riverName\" : \"River Calder\" ,\n" +
            "    \"stageScale\" : { \n" +
            "      \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/id/stations/L12061/stageScale\" ,\n" +
            "      \"datum\" : 108.321 ,\n" +
            "      \"highestRecent\" : { \n" +
            "        \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/id/stations/L12061/stageScale/highestRecent\" ,\n" +
            "        \"dateTime\" : \"2012-06-22T21:30:00\" ,\n" +
            "        \"value\" : 2.226\n" +
            "      }\n" +
            "       ,\n" +
            "      \"maxOnRecord\" : { \n" +
            "        \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/id/stations/L12061/stageScale/maxOnRecord\" ,\n" +
            "        \"dateTime\" : \"2012-06-22T21:30:00\" ,\n" +
            "        \"value\" : 2.226\n" +
            "      }\n" +
            "       ,\n" +
            "      \"minOnRecord\" : { \n" +
            "        \"@id\" : \"http://environment.data.gov.uk/flood-monitoring/id/stations/L12061/stageScale/minOnRecord\" ,\n" +
            "        \"dateTime\" : \"2013-01-18T19:00:00\" ,\n" +
            "        \"value\" : -0.152\n" +
            "      }\n" +
            "       ,\n" +
            "      \"scaleMax\" : 3 ,\n" +
            "      \"typicalRangeLow\" : -0.01\n" +
            "    }\n" +
            "     ,\n" +
            "    \"stationReference\" : \"L12061\" ,\n" +
            "    \"town\" : \"High Green\" ,\n" +
            "    \"type\" : [ \"http://environment.data.gov.uk/flood-monitoring/def/core/SingleLevel\", \"http://environment.data.gov.uk/flood-monitoring/def/core/Station\" ] ,\n" +
            "    \"wiskiID\" : \"L12061\"\n" +
            "  }\n" +
            "}\n" +
            "\n";
}
