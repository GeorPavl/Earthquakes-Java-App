import java.util.Objects;

/**
 * Represents a location.
 * A location characterized by its latitude and longitude.
 * */
public class Location {

    private double longitude;

    private double latitude;

    // Constructor

    public Location(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    // Getters & Setters

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    // Overridden methods

    @Override
    public String toString() {
        return "[" + getLatitude() + ", " + getLongitude() + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location location = (Location) o;
        return Double.compare(location.getLongitude(), getLongitude()) == 0 &&
                Double.compare(location.getLatitude(), getLatitude()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLongitude(), getLatitude());
    }

    // Other methods

    public double distanceFrom(Location location) {
        double f1 = Math.toRadians(this.latitude);
        double f2 = Math.toRadians(location.latitude);
        double l1 = Math.toRadians(this.longitude);
        double l2 = Math.toRadians(location.longitude);
        double r = 6371; // η ακτίνα της γης σε χιλιόμετρα
        double distance = 2 * r *
                Math.asin(Math.sqrt(
                        Math.pow(Math.sin((f2-f1)/2),2) +
                                Math.cos(f1) * Math.cos(f2) * Math.pow(Math.sin((l2-l1)/2),2)
                ));
        return distance;
    }
}
