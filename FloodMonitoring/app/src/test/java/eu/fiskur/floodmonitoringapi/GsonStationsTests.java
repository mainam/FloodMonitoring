package eu.fiskur.floodmonitoringapi;

import com.google.gson.Gson;

import org.junit.Test;
import static org.junit.Assert.*;

import eu.fiskur.floodmonitoringapi.stations.StationOverview;
import eu.fiskur.floodmonitoringapi.stations.Stations;

public class GsonStationsTests {

    @Test
    public void stationsGSONTest(){
        Gson gson = GSONProvider.getGSON();
        Stations stations = gson.fromJson(STATIONS_BY_LOCATION, Stations.class);
        assertEquals(2, stations.getItems().size());
    }

    @Test
    public void stationsOverviewTest(){
        Gson gson = GSONProvider.getGSON();
        Stations stations = gson.fromJson(STATIONS_BY_LOCATION, Stations.class);
        StationOverview overview = stations.getItems().get(0);
        assertEquals(2, overview.getMeasures().size());
    }

    @Test
    public void stationsFieldsTest(){
        Gson gson = GSONProvider.getGSON();
        Stations stations = gson.fromJson(STATIONS_BY_LOCATION, Stations.class);
        StationOverview overview = stations.getItems().get(0);
        assertEquals("http://environment.data.gov.uk/flood-monitoring/id/stations/690138", overview.getId());
        assertEquals("5116", overview.getRLOIid());
        assertEquals("Northern Manchester", overview.getCatchmentName());
        assertEquals("1998-05-07", overview.getDateOpened());
        assertEquals(381200, overview.getEee().intValue());
        assertEquals("Rawtenstall", overview.getLabel().toString());

    }


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
