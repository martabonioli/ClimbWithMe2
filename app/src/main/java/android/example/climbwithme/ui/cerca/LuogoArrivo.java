package android.example.climbwithme.ui.cerca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.example.climbwithme.MyModel;
import android.example.climbwithme.R;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
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
import com.mapbox.mapboxsdk.plugins.places.autocomplete.PlaceAutocomplete;
import com.mapbox.mapboxsdk.plugins.places.autocomplete.model.PlaceOptions;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;

import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconImage;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconOffset;

public class LuogoArrivo extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {
    private static final int REQUEST_CODE_AUTOCOMPLETE = 1;
    private MapView mapView;
    private MapboxMap mapboxMap;
    private CarmenFeature home;
    private CarmenFeature work;
    private String geojsonSourceLayerId = "geojsonSourceLayerId";
    private String symbolIconId = "symbolIconId";
    private double latitudine;
    private double longitudine;
    private String posArrivo;
    Double latPartenza;
    Double lonPartenza;
    String luogoPartenza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, "pk.eyJ1IjoibHVjYWdyYW5hIiwiYSI6ImNrMzczZG45ejA3bmgzY2xpd242cnBoZzQifQ.oCQted-XquoUm5EKhjrLTQ");
        setContentView(R.layout.activity_luogo_arrivo);

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        ImageButton conferma = findViewById(R.id.conferma2);
        conferma.setOnClickListener(this);
        ImageButton indietro = findViewById(R.id.returnc);
        indietro.setOnClickListener(this);

        latPartenza = getIntent().getExtras().getDouble("latitudinepartenza");
        lonPartenza = getIntent().getExtras().getDouble("longitudinepartenza");
        luogoPartenza = getIntent().getExtras().getString("luogopartenza");
        Log.d("LongitudinePartenza2", lonPartenza.toString());

    }
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
        case R.id.conferma2:
            if (latitudine != 0) {
                Intent intent = new Intent(getApplicationContext(), CercaFinal.class);
                intent.putExtra("latitudinepartenza", latPartenza);
                intent.putExtra("longitudinepartenza", lonPartenza);
                intent.putExtra("luogopartenza", luogoPartenza);
                intent.putExtra("latitudinearrivo", latitudine);
                intent.putExtra("longitudinearrivo",longitudine);
                intent.putExtra("luogoarrivo", posArrivo);
                Log.d ("latitudineArrivo", String.valueOf(latitudine));
                Log.d ("longitudineArrivo", String.valueOf(longitudine));
                //MyModel.getInstance().cercaUscita.setLatLuogoArrivo(latitudine);
                //MyModel.getInstance().cercaUscita.setLatLuogoArrivo(longitudine);
                startActivity(intent);
            }else{
                Toast.makeText(getApplicationContext(),"Inserisci una posizione!",Toast.LENGTH_SHORT).show();
            }
        break;
        case R.id.returnc:
            Intent intent = new Intent(getApplicationContext(), LuogoPartenza.class);
            startActivity(intent);
        break;
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
                        LuogoArrivo.this.getResources(), R.mipmap.blue_marker_view));

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
                        .build(LuogoArrivo.this);
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
            posArrivo = (String) selectedCarmenFeature.placeName();
            Log.d("nonso", String.valueOf(PlaceAutocomplete.getPlace(data)));
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
                    mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(
                            new CameraPosition.Builder()
                                    .target(new LatLng(((Point) selectedCarmenFeature.geometry()).latitude(),
                                            ((Point) selectedCarmenFeature.geometry()).longitude()))
                                    .zoom(14)
                                    .build()), 4000);
                    latitudine=((Point) selectedCarmenFeature.geometry()).latitude();
                    longitudine = ((Point) selectedCarmenFeature.geometry()).longitude();
                    Log.d("lat", String.valueOf(latitudine));
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