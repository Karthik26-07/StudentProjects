package com.example.cattledisease.ui.NearByLocation;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cattledisease.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.cattledisease.ui.NearByLocation.LocConfig.GEOMETRY;
import static com.example.cattledisease.ui.NearByLocation.LocConfig.GOOGLE_BROWSER_API_KEY;
import static com.example.cattledisease.ui.NearByLocation.LocConfig.ICON;
import static com.example.cattledisease.ui.NearByLocation.LocConfig.LATITUDE;
import static com.example.cattledisease.ui.NearByLocation.LocConfig.LOCATION;
import static com.example.cattledisease.ui.NearByLocation.LocConfig.LONGITUDE;
import static com.example.cattledisease.ui.NearByLocation.LocConfig.NAME;
import static com.example.cattledisease.ui.NearByLocation.LocConfig.OK;
import static com.example.cattledisease.ui.NearByLocation.LocConfig.PLACE_ID;
import static com.example.cattledisease.ui.NearByLocation.LocConfig.PLAY_SERVICES_RESOLUTION_REQUEST;
import static com.example.cattledisease.ui.NearByLocation.LocConfig.PROXIMITY_RADIUS;
import static com.example.cattledisease.ui.NearByLocation.LocConfig.REFERENCE;
import static com.example.cattledisease.ui.NearByLocation.LocConfig.STATUS;
import static com.example.cattledisease.ui.NearByLocation.LocConfig.SUPERMARKET_ID;
import static com.example.cattledisease.ui.NearByLocation.LocConfig.TAG;
import static com.example.cattledisease.ui.NearByLocation.LocConfig.VICINITY;
import static com.example.cattledisease.ui.NearByLocation.LocConfig.ZERO_RESULTS;


public class NearByLocation extends AppCompatActivity implements OnMapReadyCallback, LocationListener {

    ProgressBar progress_bar;
    private GoogleMap mMap;

    LocationManager locationManager;
    boolean loc_enabled = true;
    int flag = 0;
    private FusedLocationProviderClient mFusedLocationProvderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isGooglePlayServicesAvailable()) {
            return;
        }
        setContentView(R.layout.activity_near_by_location);
        progress_bar = findViewById(R.id.progress_bar);
        progress_bar.setVisibility(View.VISIBLE);
        if (loc_enabled) {
            isLocationEnabled();
        }
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            showCurrentLocation();

            return true;

        } else {
            showLocationSettings();
            return false;
        }

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);


    }

    private void showCurrentLocation() {

        if (ContextCompat.checkSelfPermission(NearByLocation.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(NearByLocation.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 100);
        } else {
            if (loc_enabled) {
                LocationRequest locationRequest = new LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 5)
                        .setWaitForAccurateLocation(false)
                        .setMinUpdateIntervalMillis(0)
                        .setMaxUpdateDelayMillis(100)
                        .build();
                mFusedLocationProvderClient = LocationServices.getFusedLocationProviderClient(NearByLocation.this);
                mFusedLocationProvderClient.requestLocationUpdates(locationRequest, mLocationCallback, Looper.myLooper());
            } else {
                loc_enabled = isLocationEnabled();
            }
        }

        //
//
//        Criteria criteria = new Criteria();
//        String bestProvider = locationManager.getBestProvider(criteria, true);
//        if (ActivityCompat.checkSelfPermission(this,
//                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
//                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
//                        != PackageManager.PERMISSION_GRANTED) {
//            return;
//        }
//        assert bestProvider != null;
//        Location location = locationManager.getLastKnownLocation(bestProvider);
//
//        if (location != null) {
//            onLocationChanged(location);
//        }
//
//        locationManager.requestLocationUpdates(bestProvider, MIN_TIME_BW_UPDATES,
//                MIN_DISTANCE_CHANGE_FOR_UPDATES, (LocationListener) this);
    }

    private LocationCallback mLocationCallback = new LocationCallback() {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
        @Override
        public void onLocationResult(@NonNull LocationResult locationResult) {
            Location location = locationResult.getLastLocation();
            if (location.equals("")) {

                Toast.makeText(NearByLocation.this, "Waiting for location", Toast.LENGTH_SHORT).show();
            } else {
                if (flag == 0) {
                    onLocationChanged(location);
                    flag = 1;
                } else {
//                Toast.makeText(getContext(), " location", Toast.LENGTH_SHORT).show();

                }

            }



        }
    };

    @Override
    public void onLocationChanged(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        LatLng latLng = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(latLng).title("My Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));

        loadNearByPlaces(latitude, longitude);
    }

    public void loadNearByPlaces(double latitude, double longitude) {
        //YOU Can change this type at your own will, e.g hospital, cafe, restaurant.... and see how it all works
        String type = "near by veterinary hospital";
        String googlePlacesUrl = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?" + "location=" + latitude + "," + longitude +
                "&radius=" + PROXIMITY_RADIUS +
                "&types=" + type +
                "&sensor=true" +
                "&key=" + GOOGLE_BROWSER_API_KEY;
        JsonObjectRequest request = new JsonObjectRequest(googlePlacesUrl,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject result) {

                        Log.i(TAG, "onResponse: Result= " + result.toString());
                        Toast.makeText(NearByLocation.this, "onResponse: Result= " + result.toString(), Toast.LENGTH_SHORT).show();
                        parseLocationResult(result);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(NearByLocation.this, "onErrorResponse: Error= " + error, Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "onErrorResponse: Error= " + error);
                        Toast.makeText(NearByLocation.this, "onErrorResponse: Error= " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "onErrorResponse: Error= " + error.getMessage());
                    }
                });

        LocController.getInstance().addToRequestQueue(request);

    }

    private void parseLocationResult(JSONObject result) {
        progress_bar.setVisibility(View.INVISIBLE);
        String Id, place_id, placeName = null, reference, icon, vicinity = null;
        double latitude, longitude;
        try {
            JSONArray jsonArray = result.getJSONArray("results");
            if (result.getString(STATUS).equalsIgnoreCase(OK)) {
                mMap.clear();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject place = jsonArray.getJSONObject(i);

                    Id = place.getString(SUPERMARKET_ID);
                    place_id = place.getString(PLACE_ID);
                    if (!place.isNull(NAME)) {
                        placeName = place.getString(NAME);
                    }
                    if (!place.isNull(VICINITY)) {
                        vicinity = place.getString(VICINITY);
                    }
                    latitude = place.getJSONObject(GEOMETRY).getJSONObject(LOCATION)
                            .getDouble(LATITUDE);
                    longitude = place.getJSONObject(GEOMETRY).getJSONObject(LOCATION)
                            .getDouble(LONGITUDE);
                    reference = place.getString(REFERENCE);
                    icon = place.getString(ICON);

                    MarkerOptions markerOptions = new MarkerOptions();
                    LatLng latLng = new LatLng(latitude, longitude);
                    markerOptions.position(latLng);
                    markerOptions.title(placeName + " : " + vicinity);

                    mMap.addMarker(markerOptions);

                }
                Toast.makeText(getBaseContext(), jsonArray.length() + " veterinary found!",
                        Toast.LENGTH_LONG).show();

            } else if (result.getString(STATUS).equalsIgnoreCase(ZERO_RESULTS)) {
                Toast.makeText(getBaseContext(), "No veterinary hospital found in 5KM radius!!!",
                        Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
    }

    @Override
    public void onProviderEnabled(String s) {
    }

    @Override
    public void onProviderDisabled(String s) {
    }


    private void showLocationSettings() {
        AlertDialog.Builder builder = new AlertDialog.Builder(NearByLocation.this);
        builder.setMessage("Please turn on your device location");
        builder.setTitle("Alert !");
        builder.setCancelable(false);
        builder.setPositiveButton("SETTING", (DialogInterface.OnClickListener) (dialog, which) -> {
            startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
        });
        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });

        builder.show();

    }

    private boolean isGooglePlayServicesAvailable() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i(TAG, "This device is not supported.");
                Toast.makeText(NearByLocation.this, "This device is not supported.", Toast.LENGTH_SHORT).show();
                finish();
            }
            return false;
        }
        return true;
    }

}
























