public class City {

    public final double latitude;
    public final double longitude;

    public City(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public double getLatitude () {
        return latitude;
    }
    public double getLongitude () {
        return longitude;
    }
}