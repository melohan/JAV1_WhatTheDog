package com.jav1.whatthedog.marker

import android.view.MotionEvent
import android.widget.TextView
import com.jav1.whatthedog.distance.DistanceManager
import com.jav1.whatthedog.polyline.PolylineManager
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Overlay

/**
 * Created by Mathieu RABOT
 * 21.06.2022
 * This class is used to handle the events on the map that use markers
 * using the GeoPoint class, the Overlay class and the MapView class from OSMdroid
 * For the project what-the-dog JAV1
 */
class MarkerCreatorOverlay(private val startPoint: GeoPoint, private val textView: TextView) : Overlay() {

    /**
     * Handle the long press event on the map that deal with the creation of a marker
     * @param {MapView} mapView the mapView that is used to display the map
     * @param {MotionEvent} e the event that is triggered
     * @return {Boolean} true if the event is handled
     */
    override fun onLongPress(e: MotionEvent?, mapView: MapView?): Boolean {
        val title = "My chosen location"
        val snippet = "This is where I am"
        val endPoint = mapView!!.projection.fromPixels(
            e!!.x.toInt(),
            e.y.toInt(), null
        ) as GeoPoint
        if (MarkerManager.hasMarker(mapView, startPoint)) {
            MarkerManager.updateMarker(mapView, startPoint, endPoint)
            PolylineManager.updatePolyline(mapView, startPoint, endPoint)
        } else {
            MarkerManager.addMarker(mapView, endPoint, title, snippet)
            PolylineManager.addPolyline(mapView, startPoint, endPoint)
        }
            DistanceManager.getDistance(startPoint, endPoint)
        return true
    }
}
