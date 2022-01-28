import java.util.Objects;

/**
 * Represents an earthquake.
 * An earthquake characterized by the date on which it arose (Date),
 * its size, the focal depth and focus (Location)
 * */
public class Earthquake {

    private String title;

    private Date date;

    private double magnitude;

    private double focalDepth;

    private Location epicenter;

    // Constructor

    public Earthquake(Date date, double magnitude, double focalDepth, Location epicenter) {
        this.date = date;
        this.magnitude = magnitude;
        this.focalDepth = focalDepth;
        this.epicenter = epicenter;
    }

    public Earthquake(Date date, double magnitude, double focalDepth, double longitude, double latitude) {
        this.date = date;
        this.magnitude = magnitude;
        this.focalDepth = focalDepth;
        this.epicenter = new Location(longitude, latitude);
    }

    public Earthquake(String title, Double magnitude, Location epicenter){
        this.title = title;
        this.magnitude = magnitude;
        this.epicenter = epicenter;
    }

    public Earthquake(String title, Double magnitude, Double lon, Double lat){
        this.title = title;
        this.magnitude = magnitude;
        this.epicenter = new Location(lon, lat);
    }

    // Getters

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public double getFocalDepth() {
        return focalDepth;
    }

    public Location getEpicenter() {
        return epicenter;
    }

    // Overridden methods

    @Override
    public String toString() {
        return getDate().toString() + ", " + getEpicenter().toString() + ", " + getFocalDepth() + "km, " + getMagnitude() + "μ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Earthquake)) return false;
        Earthquake that = (Earthquake) o;
        return Double.compare(that.getMagnitude(), getMagnitude()) == 0 &&
                Double.compare(that.getFocalDepth(), getFocalDepth()) == 0 &&
                Objects.equals(getDate(), that.getDate()) &&
                Objects.equals(getEpicenter(), that.getEpicenter());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDate(), getMagnitude(), getFocalDepth(), getEpicenter());
    }
    // Other methods

    public void updateMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }
}
