package eu.fiskur.floodmonitoringapi.utilities;

/*
 * Copyright (C) 2014 Jared Rummler <jared@jrummyapps.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.graphics.Point;

import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/*
    Quick Hull method taken from: http://jaredrummler.com/2014/11/13/android-bitmaps-convex-hull/

    More details: http://fiskurgit.github.io/convex_hull_from_geojson/

    To use in an Android app translate your LatLngs using convertToPoint and the GoogleMap projection: map.getProjection()
    Translate the returned Point array using convertToLatLng to add Markers to your map.
 */
public class QuickHull {

    public ArrayList<Point> convertToPoint(List<LatLng> coords, Projection projection){
        ArrayList<Point> points = new ArrayList<Point>();

        for(LatLng coord : coords){
            points.add(projection.toScreenLocation(coord));
        }

        return points;
    }

    public ArrayList<LatLng> convertToLatLng(List<Point> points, Projection projection){
        ArrayList<LatLng> coords = new ArrayList<LatLng>();

        for(Point point : points){
            coords.add(projection.fromScreenLocation(point));
        }

        return coords;
    }


    public ArrayList<Point> quickHull(ArrayList<Point> points) {
        final ArrayList<Point> convexHull = new ArrayList<Point>();
        if (points.size() < 3) {
            return (ArrayList<Point>) points.clone();
        }

        int minPoint = -1, maxPoint = -1;
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        for (int i = 0; i < points.size(); i++) {
            if (points.get(i).x < minX) {
                minX = points.get(i).x;
                minPoint = i;
            }
            if (points.get(i).x > maxX) {
                maxX = points.get(i).x;
                maxPoint = i;
            }
        }
        final Point a = points.get(minPoint);
        final Point b = points.get(maxPoint);
        convexHull.add(a);
        convexHull.add(b);
        points.remove(a);
        points.remove(b);

        ArrayList<Point> leftSet = new ArrayList<Point>();
        ArrayList<Point> rightSet = new ArrayList<Point>();

        for (int i = 0; i < points.size(); i++) {
            Point p = points.get(i);
            if (pointLocation(a, b, p) == -1) leftSet.add(p);
            else rightSet.add(p);
        }
        hullSet(a, b, rightSet, convexHull);
        hullSet(b, a, leftSet, convexHull);

        return convexHull;
    }

    private static int distance(Point a, Point b, Point c) {
        final int ABx = b.x - a.x;
        final int ABy = b.y - a.y;
        int num = ABx * (a.y - c.y) - ABy * (a.x - c.x);
        if (num < 0) num = -num;
        return num;
    }

    private static void hullSet(Point a, Point b, ArrayList<Point> set, ArrayList<Point> hull) {
        final int insertPosition = hull.indexOf(b);
        if (set.size() == 0) return;
        if (set.size() == 1) {
            final Point p = set.get(0);
            set.remove(p);
            hull.add(insertPosition, p);
            return;
        }
        int dist = Integer.MIN_VALUE;
        int furthestPoint = -1;
        for (int i = 0; i < set.size(); i++) {
            Point p = set.get(i);
            int distance = distance(a, b, p);
            if (distance > dist) {
                dist = distance;
                furthestPoint = i;
            }
        }
        final Point p = set.get(furthestPoint);
        set.remove(furthestPoint);
        hull.add(insertPosition, p);

        // Determine who's to the left of AP
        final ArrayList<Point> leftSetAP = new ArrayList<Point>();
        for (int i = 0; i < set.size(); i++) {
            final Point m = set.get(i);
            if (pointLocation(a, p, m) == 1) {
                leftSetAP.add(m);
            }
        }

        // Determine who's to the left of PB
        final ArrayList<Point> leftSetPB = new ArrayList<Point>();
        for (int i = 0; i < set.size(); i++) {
            final Point m = set.get(i);
            if (pointLocation(p, b, m) == 1) {
                leftSetPB.add(m);
            }
        }
        hullSet(a, p, leftSetAP, hull);
        hullSet(p, b, leftSetPB, hull);
    }

    private static int pointLocation(Point a, Point b, Point p) {
        int cp1 = (b.x - a.x) * (p.y - a.y) - (b.y - a.y) * (p.x - a.x);
        return (cp1 > 0) ? 1 : -1;
    }
}

