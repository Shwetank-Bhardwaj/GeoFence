package com.shwetank.geofence;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.List;

public class GeofenceBroadcastReceiver extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        if (geofencingEvent.hasError()) {

            return;
        }

        // Get the transition type.
        int geofenceTransition = geofencingEvent.getGeofenceTransition();

        // Test that the reported transition was of interest.
        if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER ||
                geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT) {

            // Get the geofences that were triggered. A single event can trigger
            // multiple geofences.
            List<Geofence> triggeringGeofences = geofencingEvent.getTriggeringGeofences();

            // Get the transition details as a String.

//            String geofenceTransitionDetails = getGeofenceTransitionDetails(
//                    this,
//                    geofenceTransition,
//                    triggeringGeofences
//            );
            Toast.makeText(context, triggeringGeofences.get(0) + "", Toast.LENGTH_SHORT).show();

            // Send notification and log the transition details.
//            sendNotification(geofenceTransitionDetails);
//            Log.i("Log", geofenceTransitionDetails);
        } else {
            Toast.makeText(context, "Invalid Type", Toast.LENGTH_SHORT).show();
            // Log the error.
//            Log.e("Log", getString(R.string.geofence_transition_invalid_type,
//                    geofenceTransition));
        }
    }
}