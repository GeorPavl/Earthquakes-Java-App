import java.util.ArrayList;

public class Country {

    /** fields */

    private String code;
    private String name;
    private Double north;
    private Double south;
    private Double east;
    private Double west;
    private Location center;
    private EarthquakeDB earthquakes;

    public Country(String code, String name, Double north, Double south, Double east, Double west) {
        this.code = code;
        this.name = name;
        this.north = north;
        this.south = south;
        this.east = east;
        this.west = west;
        this.earthquakes = new EarthquakeDB();

        Double centerLongitude = (north + south) / 2;
        Double centerLatitude = (east + west) / 2;
        Location center = new Location(centerLongitude,centerLatitude);
        this.center = center;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Double getNorth() {
        return north;
    }

    public Double getSouth() {
        return south;
    }

    public Double getEast() {
        return east;
    }

    public Double getWest() {
        return west;
    }

    public EarthquakeDB getEarthquakes() {
        return earthquakes;
    }

    public Location getCenter() {
        return center;
    }

    @Override
    public String toString() {
        return "Country{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", north=" + north +
                ", south=" + south +
                ", east=" + east +
                ", west=" + west +
                ", center=" + center +
                ", earthquakes=" + earthquakes +
                '}';
    }

    public ArrayList<Earthquake> withinDistanceFromCenter(double distance){
        return earthquakes.withinDistance(this.center, distance);
    }
}
