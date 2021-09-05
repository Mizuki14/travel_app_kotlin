[app](../../index.md) / [op.hachm1.travelapp](../index.md) / [MapsActivity](index.md) / [onMapReady](./on-map-ready.md)

# onMapReady

`fun onMapReady(googleMap: GoogleMap): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Manipulates the map once available.
This callback is triggered when the map is ready to be used.
This is where we can add markers or lines, add listeners or move the camera. In this case,
we just add a marker near Sydney, Australia.
If Google Play services is not installed on the device, the user will be prompted to install
it inside the SupportMapFragment. This method will only be triggered once the user has
installed Google Play services and returned to the app.

