package android.example.climbwithme.ui.cerca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.NavigationUI;

import android.app.Activity;
import android.content.Intent;
import android.example.climbwithme.MyModel;
import android.example.climbwithme.R;
import android.example.climbwithme.ui.profile.VisualizzaUscite;
import android.example.climbwithme.ui.proponi.ProponiFragment;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.mapbox.api.geocoding.v5.models.CarmenFeature;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;
import com.mapbox.mapboxsdk.plugins.places.autocomplete.PlaceAutocomplete;
import com.mapbox.mapboxsdk.plugins.places.autocomplete.model.PlaceOptions;

import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconImage;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconOffset;


public class LuogoPartenza extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, "pk.eyJ1IjoibHVjYWdyYW5hIiwiYSI6ImNrMzczZG45ejA3bmgzY2xpd242cnBoZzQifQ.oCQted-XquoUm5EKhjrLTQ");
        setContentView(R.layout.activity_luogo_partenza);

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
                    Log.d("latitudinePartenza", String.valueOf(latitudine));
                    Log.d("longitudinePartenza", String.valueOf(longitudine));
                    Intent intent = new Intent(getApplicationContext(), LuogoArrivo.class);
                    intent.putExtra("latitudinepartenza", latitudine);
                    intent.putExtra("longitudinepartenza", longitudine);
                    intent.putExtra("luogopartenza", posPartenza);
                    //MyModel.getInstance().cercaUscita.setLatLuogoArrivo(latitudine);
                    //MyModel.getInstance().cercaUscita.setLonLuogoArrivo(longitudine);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Inserisci una posizione!", Toast.LENGTH_SHORT).show();
                }



    }

    @Override
    public void onMapReady(@NonNull final MapboxMap mapboxMap) {
        this.mapboxMap = mapboxMap;
        mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
            @Override
            public void onStyleLoaded(@NonNull Style style) {
                initSearchFab();
                addUserLocations();

// Add the symbol layer icon to map for future use
                style.addImage(symbolIconId, BitmapFactory.decodeResource(
                        LuogoPartenza.this.getResources(), R.mipmap.blue_marker_view));

// Create an empty GeoJSON source using the empty feature collection
                setUpSource(style);

// Set up a new symbol layer for displaying the searched location's feature coordinates
                setupLayer(style);
            }
        });
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
                        .build(LuogoPartenza.this);
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
                    Log.d("LuogoPartenzaLat", String.valueOf(latitudine));
                    Log.d("LuogoPartenzaLog", String.valueOf(longitudine));
                    mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(
                            new CameraPosition.Builder()
                                    .target(new LatLng(((Point) selectedCarmenFeature.geometry()).latitude(),
                                            ((Point) selectedCarmenFeature.geometry()).longitude()))
                                    .zoom(14)
                                    .build()), 4000);
                }
            }
           // MyModel.cercaUscita.setLatLuogoArrivo(((Point) selectedCarmenFeature.geometry()).latitude());
            //MyModel.cercaUscita.setLonLuogoArrivo(((Point) selectedCarmenFeature.geometry()).longitude());
        }

    }
    // Add the mapView lifecycle to the activity's lifecycle methods
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
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

