[app](../../index.md) / [op.hachm1.travelapp](../index.md) / [MapsActivity](./index.md)

# MapsActivity

`class MapsActivity : AppCompatActivity, OnMapReadyCallback`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `MapsActivity()` |

### Functions

| Name | Summary |
|---|---|
| [onCreate](on-create.md) | `fun onCreate(savedInstanceState: `[`Bundle`](https://developer.android.com/reference/android/os/Bundle.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onMapReady](on-map-ready.md) | Manipulates the map once available. This callback is triggered when the map is ready to be used. This is where we can add markers or lines, add listeners or move the camera. In this case, we just add a marker near Sydney, Australia. If Google Play services is not installed on the device, the user will be prompted to install it inside the SupportMapFragment. This method will only be triggered once the user has installed Google Play services and returned to the app.`fun onMapReady(googleMap: GoogleMap): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
