package op.hachm1.travelapp

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomnavigation.BottomNavigationView

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }
    private lateinit var mMap: GoogleMap
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        //Bottom Navigation
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.back -> { val intent = Intent(baseContext, MainActivity::class.java)
                    startActivity(intent)
                    finish()}
            }
            true
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

    //Make markers and place it in the map
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setPadding(0,0,0,100)
        mMap.uiSettings.isZoomControlsEnabled = true
        setUpMap()


        // Add a marker in Sydney and move the camera
        //http://techslides.com/list-of-countries-and-capitals
        val pretoria = LatLng(-33.924870,18.424055)
        val abuja = LatLng(9.076479,7.398574)
        val tokyo = LatLng(35.689487,139.691711)
        val seoul = LatLng(37.55,126.983333)
        val canberra = LatLng(-35.26666667,149.133333)
        val wellington = LatLng(-41.3,174.783333)
        val berlin = LatLng(52.51666667,13.4)
        val paris = LatLng(48.86666667,2.333333)
        val ottawa = LatLng(45.41666667,-75.7)
        val washington = LatLng(38.883333,-77.0)
        val buenosAires = LatLng(-34.58333333,-58.666667)
        val Brasilia = LatLng(-15.78333333,-47.916667)

        mMap.addMarker(MarkerOptions().position(pretoria).title("South Africa, Pretoria").icon(
            BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)))
        mMap.addMarker(MarkerOptions().position(abuja).title("Nigeria, Abuja").icon(
            BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)))
        mMap.addMarker(MarkerOptions().position(tokyo).title("Japan, Tokyo").icon(
            BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)))
        mMap.addMarker(MarkerOptions().position(seoul).title("South Korea, Seoul").icon(
            BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)))
        mMap.addMarker(MarkerOptions().position(canberra).title("Australia, Canberra").icon(
            BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)))
        mMap.addMarker(MarkerOptions().position(wellington).title("New Zealand, Wellington").icon(
            BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)))
        mMap.addMarker(MarkerOptions().position(berlin).title("Germany, Berlin").icon(
            BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)))
        mMap.addMarker(MarkerOptions().position(paris).title("France, Paris").icon(
            BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)))
        mMap.addMarker(MarkerOptions().position(ottawa).title("Canada, Ottawa").icon(
            BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)))
        mMap.addMarker(MarkerOptions().position(washington).title("United States of America, Washington, D.C.").icon(
            BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)))
        mMap.addMarker(MarkerOptions().position(buenosAires).title("Argentina, Buenos Aires").icon(
            BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)))
        mMap.addMarker(MarkerOptions().position(Brasilia).title("Brazil, Brasilia").icon(
            BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)))
    }

    //set up map
    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            return
        }

        mMap.isMyLocationEnabled = true

        //get current location and place it on the map
        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
            // Got last known location. In some rare situations this can be null.
            // 3
            if (location != null) {
                lastLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 5f))
            }
        }
    }
}
