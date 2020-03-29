package android.example.climbwithme.ui.proponi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.example.climbwithme.MyModel;
import android.example.climbwithme.R;
import android.example.climbwithme.ui.cerca.LuogoArrivo;
import android.example.climbwithme.ui.cerca.LuogoPartenza;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.api.geocoding.v5.models.CarmenFeature;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.plugins.places.autocomplete.PlaceAutocomplete;
import com.mapbox.mapboxsdk.plugins.places.autocomplete.model.PlaceOptions;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;

import java.util.List;

import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconImage;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconOffset;

public class LuogoPartenzaProponi extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener, PermissionsListener {

    private static final int REQUEST_CODE_AUTOCOMPLETE = 1;
    private MapView mapView;
    private MapboxMap mapboxMap;
    private CarmenFeature home;
    private CarmenFeature work;
    private String geojsonSourceLayerId = "geojsonSourceLayerId";
    private String symbolIconId = "symbolIconId";
    public double latitudine=0;
    public double longitudine=0;
    public String posPartenza;
    private MyModel Model = MyModel.getInstance();
    public TextView luogo;
    private PermissionsManager permissionsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, "pk.eyJ1IjoibHVjYWdyYW5hIiwiYSI6ImNrMzczZG45ejA3bmgzY2xpd242cnBoZzQifQ.oCQted-XquoUm5EKhjrLTQ");
        setContentView(R.layout.activity_luogo_partenza_proponi);

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        ImageButton conferma = findViewById(R.id.confermap);
        conferma.setOnClickListener(this);
        luogo= findViewById(R.id.luogo);

    }
    @Override
    public void onClick(View v) {
        if (latitudine != 0) {
            Log.d ("latitudine", String.valueOf(latitudine));
            Intent intent = new Intent(getApplicationContext(), LuogoArrivoProponi.class);
            intent.putExtra("latitudinepartenza", latitudine);
            intent.putExtra("longitudinepartenza", longitudine);
            intent.putExtra("luogopartenza",posPartenza);
            //MyModel.getInstance().cercaUscita.setLatLuogoArrivo(latitudine);
            //MyModel.getInstance().cercaUscita.setLonLuogoArrivo(longitudine);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(),"Inserisci una posizione!",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onMapReady(@NonNull final MapboxMap mapboxMap) {
        this.mapboxMap = mapboxMap;
        CameraPosition position = new CameraPosition.Builder()
                .target(new LatLng(MyModel.getLatposition(), MyModel.getLongposition()))
                .zoom(10)
                .tilt(20)
                .build();
        mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(position));

        mapboxMap.setStyle(new Style.Builder().fromUri("mapbox://styles/mapbox/cjerxnqt3cgvp2rmyuxbeqme7"),
                new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {
                        enableLocationComponent(style);
                        initSearchFab();
                        addUserLocations();
                        style.addImage(symbolIconId, BitmapFactory.decodeResource(
                                LuogoPartenzaProponi.this.getResources(), R.mipmap.blue_marker_view));

// Create an empty GeoJSON source using the empty feature collection
                        setUpSource(style);

// Set up a new symbol layer for displaying the searched location's feature coordinates
                        setupLayer(style);
                    }
                });
    }

    @SuppressWarnings( {"MissingPermission"})
    private void enableLocationComponent(@NonNull Style loadedMapStyle) {
// Check if permissions are enabled and if not request
        if (PermissionsManager.areLocationPermissionsGranted(this)) {

// Get an instance of the component
            LocationComponent locationComponent = mapboxMap.getLocationComponent();

// Activate with options
            locationComponent.activateLocationComponent(
                    LocationComponentActivationOptions.builder(this, loadedMapStyle).build());

// Enable to make component visible
            locationComponent.setLocationComponentEnabled(true);

// Set the component's camera mode
            locationComponent.setCameraMode(CameraMode.TRACKING);

// Set the component's render mode
            locationComponent.setRenderMode(RenderMode.COMPASS);
            locationComponent.zoomWhileTracking(14);

        } else {
            permissionsManager = new PermissionsManager(this);
            permissionsManager.requestLocationPermissions(this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {
        Toast.makeText(this, "Permessi non garantiti", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPermissionResult(boolean granted) {
        if (granted) {
            mapboxMap.getStyle(new Style.OnStyleLoaded() {
                @Override
                public void onStyleLoaded(@NonNull Style style) {
                    enableLocationComponent(style);
                }
            });
        } else {
            Toast.makeText(this, "Permessi non garantiti", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    @SuppressWarnings( {"MissingPermission"})
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    private void initSearchFab() {
        findViewById(R.id.fab_location_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new PlaceAutocomplete.IntentBuilder()
                        .accessToken(Mapbox.getAccessToken() != null ? Mapbox.getAccessToken() : "pk.eyJ1IjoibHVjYWdyYW5hIiwiYSI6ImNrMzczZG45ejA3bmgzY2xpd242cnBoZzQifQ.oCQted-XquoUm5EKhjrLTQ")
                        .placeOptions(PlaceOptions.builder()
                                .backgroundColor(Color.parseColor("#EEEEEE"))
                                .limit(10)
                                .addInjectedFeature(home)
                                //.addInjectedFeature(work)
                                .build(PlaceOptions.MODE_CARDS))
                        .build(LuogoPartenzaProponi.this);
                startActivityForResult(intent, REQUEST_CODE_AUTOCOMPLETE);
            }
        });
    }

    private void addUserLocations() {
        home = CarmenFeature.builder().text("La tua posizione")
                .geometry(Point.fromLngLat(MyModel.getLongposition(), MyModel.getLatposition()))
                //.placeName("50 Beale St, San Francisco, CA")
                .id("ultima-pos")
                .properties(new JsonObject())
                .build();

    }

    private void setUpSource(@NonNull Style loadedMapStyle) {
        loadedMapStyle.addSource(new GeoJsonSource(geojsonSourceLayerId));
    }

    private void setupLayer(@NonNull Style loadedMapStyle) {
        loadedMapStyle.addLayer(new SymbolLayer("SYMBOL_LAYER_ID", geojsonSourceLayerId).withProperties(
                iconImage(symbolIconId),
                iconOffset(new Float[] {0f, -8f})
        ));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_AUTOCOMPLETE) {
// Retrieve selected location's CarmenFeature
            CarmenFeature selectedCarmenFeature = PlaceAutocomplete.getPlace(data);
            Log.d("nonso", String.valueOf(PlaceAutocomplete.getPlace(data)));
            posPartenza = (String) selectedCarmenFeature.placeName();
            luogo.setText(posPartenza);
            Log.d("posPartenza", posPartenza);
// Create a new FeatureCollection and add a new Feature to it using selectedCarmenFeature above.
// Then retrieve and update the source designated for showing a selected location's symbol layer icon
            if (mapboxMap != null) {
                Style style = mapboxMap.getStyle();
                if (style != null) {
                    GeoJsonSource source = style.getSourceAs(geojsonSourceLayerId);
                    if (source != null) {
                        source.setGeoJson(FeatureCollection.fromFeatures(
                                new Feature[] {Feature.fromJson(selectedCarmenFeature.toJson())}));
                    }
// Move map camera to the selected location
                    latitudine=((Point) selectedCarmenFeature.geometry()).latitude();
                    longitudine = ((Point) selectedCarmenFeature.geometry()).longitude();
                    Log.d("lat", String.valueOf(latitudine));
                    mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(
                            new CameraPosition.Builder()
                                    .target(new LatLng(((Point) selectedCarmenFeature.geometry()).latitude(),
                                            ((Point) selectedCarmenFeature.geometry()).longitude()))
                                    .zoom(14)
                                    .build()), 4000);
                }
            }

        }

    }
    // Add the mapView lifecycle to the activity's lifecycle methods
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }



    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }


}
