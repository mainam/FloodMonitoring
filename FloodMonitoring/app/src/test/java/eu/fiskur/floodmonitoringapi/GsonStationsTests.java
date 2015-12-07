package eu.fiskur.floodmonitoringapi;

import com.google.gson.Gson;

import org.junit.Test;
import static org.junit.Assert.*;

import eu.fiskur.floodmonitoringapi.stations.Measure;
import eu.fiskur.floodmonitoringapi.stations.StationDetail;
import eu.fiskur.floodmonitoringapi.stations.StationWrapper;
import eu.fiskur.floodmonitoringapi.stations.StationOverview;
import eu.fiskur.floodmonitoringapi.stations.StationsWrapper;

public class GsonStationsTests {

    @Test
    public void stationsGSONTest(){
        Gson gson = GSONProvider.getRestGson();
        StationsWrapper stationsWrapper = gson.fromJson(STATIONS_BY_LOCATION, StationsWrapper.class);
        assertEquals(2, stationsWrapper.getItems().size());
    }

    @Test
    public void stationsOverviewTest(){
        Gson gson = GSONProvider.getRestGson();
        StationsWrapper stationsWrapper = gson.fromJson(STATIONS_BY_LOCATION, StationsWrapper.class);
        StationOverview overview = stationsWrapper.getItems().get(0);
        assertEquals(2, overview.getMeasures().size());
    }

    @Test
    public void stationOverviewFieldsTest(){
        Gson gson = GSONProvider.getRestGson();
        StationsWrapper stationsWrapper = gson.fromJson(STATIONS_BY_LOCATION, StationsWrapper.class);
        StationOverview overview = stationsWrapper.getItems().get(0);
        assertEquals("http://environment.data.gov.uk/flood-monitoring/id/stations/690138", overview.getId());
        assertEquals("5116", overview.getRLOIid());
        assertEquals("Northern Manchester", overview.getCatchmentName());
        assertEquals("1998-05-07", overview.getDateOpened());
        assertEquals(381200, overview.getEee().intValue());
        assertEquals("Rawtenstall", overview.getLabel().toString());
    }

    @Test
    public void stationTest(){
        Gson gson = GSONProvider.getRestGson();
        StationWrapper stationWrapper = gson.fromJson(STATION, StationWrapper.class);
        StationDetail station = stationWrapper.getStation();
        assertEquals(1, station.getMeasures().length);
    }

    @Test
    public void stationDetailFieldsTest(){
        Gson gson = GSONProvider.getRestGson();
        StationWrapper stationsWrapper = gson.fromJson(STATION, StationWrapper.class);
        StationDetail station = stationsWrapper.getStation();
        assertEquals("http://environment.data.gov.uk/flood-monitoring/id/stations/690142", station.getId());
        assertEquals("5082", station.getRLOIid());
        assertEquals("Northern Manchester", station.getCatchmentName());
        assertEquals("1998-07-28", station.getDateOpened());
        assertEquals("Little Bridge", station.getLabel().toString());
    }

    @Test
    public void measureTest(){
        Gson gson = GSONProvider.getRestGson();
        StationWrapper stationsWrapper = gson.fromJson(STATION, StationWrapper.class);
        StationDetail station = stationsWrapper.getStation();
        Measure measure = station.getMeasures()[0];
        assertEquals("http://environment.data.gov.uk/flood-monitoring/id/measures/690142-level-stage-i-15_min-m", measure.getId());
        assertEquals("Little Br Helmshore - level-stage-i-15_min-m", measure.getLabel());
    }

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
}
