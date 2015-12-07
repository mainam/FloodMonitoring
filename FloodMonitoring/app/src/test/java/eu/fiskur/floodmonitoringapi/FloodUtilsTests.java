package eu.fiskur.floodmonitoringapi;

import com.google.android.gms.maps.model.LatLng;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

import eu.fiskur.floodmonitoringapi.utilities.FloodUtils;

public class FloodUtilsTests {

    @Test
    public void getPointsTest(){
        String json = "[[[0.093493,51.6037],[0.092077,51.6134],[0.075051,51.6179],[-0.247248,51.5166],[-0.259754,51.5235],[-0.28098,51.518],[-0.301457,51.515]]]";
        List<LatLng> points = FloodUtils.getPoints(json);
        assertEquals(7, points.size());
    }
}
