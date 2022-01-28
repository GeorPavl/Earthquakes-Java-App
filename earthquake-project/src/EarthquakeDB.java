import java.util.ArrayList;

/**
 * Represents a collection of data for earthquakes.
 * The class must have an ArrayList <Earthquake> field for earthquake storage.
 * */
public class EarthquakeDB {

    private ArrayList<Earthquake> earthquakes;

    // Constructor

    public EarthquakeDB() {
        this.earthquakes = new ArrayList<>();
    }

    // Overridden methods

    @Override
    public String toString() {
        java.util.Collections.sort(this.earthquakes, java.util.Comparator.comparingDouble(Earthquake::getMagnitude));
        int counter = 1;
        String result = "";
        for (Earthquake e : this.earthquakes) {
            result += counter + ". " + e.toString() + "\n";
            counter++;
        }
        return result;
    }

    // Other methods

    /**
     * Takes as a parameter an Earthquake object, if it does not exist
     * already in the list, adds it to it and returns true. If exists, returns false.
     * */
    public boolean add(Earthquake earthquake) {
        if (!this.earthquakes.contains(earthquake)) {
            this.earthquakes.add(earthquake);
            return true;
        }
        return false;
    }

    /**
     * Takes as parameters a Location and a radius,
     * and constructs and returns a list of all earthquakes in database, where the distance of their epicenter
     * from the location is less than the radius.
     * */
    public ArrayList<Earthquake> withinDistance(Location location, double radius) {
        ArrayList<Earthquake> earthquakesWithinDistance = new ArrayList<>();
        for (Earthquake e : this.earthquakes) {
            if (location.distanceFrom(e.getEpicenter()) < radius) {
                earthquakesWithinDistance.add(e);
            }
        }
        return earthquakesWithinDistance;
    }

    /**
     * Takes as parameters one minimum and one maximum size (double) and constructs and returns a list of all earthquakes
     * of the collection whose size is between the sizes defined by the parameters (but not equal to them)
     * */
    public ArrayList<Earthquake> withinMagnitudeRange(double minimumMagnitude, double maximumMagnitude) {
        ArrayList<Earthquake> earthquakesWithinRange = new ArrayList<>();
        for (Earthquake e : this.earthquakes) {
            if (e.getMagnitude() > minimumMagnitude && e.getMagnitude() < maximumMagnitude) {
                earthquakesWithinRange.add(e);
            }
        }
        return earthquakesWithinRange;
    }

    /**
     * Takes as a parameter a date (Date),
     * constructs and returns a list of all earthquakes in the collection that occurred
     * in the same year, month and day with what the parameter specifies
     * */
    public ArrayList<Earthquake> withinDay(Date date) {
        ArrayList<Earthquake> earthquakesWithinDay = new ArrayList<>();
        for (Earthquake e : this.earthquakes) {
            if (e.getDate().getYear() == date.getYear() &&
                e.getDate().getMonth() == date.getMonth() &&
                e.getDate().getDay() == date.getDay()) {
                    earthquakesWithinDay.add(e);
            }
        }
        return earthquakesWithinDay;
    }

    /**
     * Returns the median magnitude of its earthquakes list or 0 if the list is empty.
     * */
    public double getMedianMagnitude() {
        double medianMagnitude = 0;
        if (this.earthquakes.isEmpty()) {
            return medianMagnitude;
        }

        java.util.Collections.sort(this.earthquakes, java.util.Comparator.comparingDouble(Earthquake::getMagnitude));
        int totalEarthquakes = this.earthquakes.size();

        if (totalEarthquakes % 2 == 0) {
            int sumOfMiddleElements = (totalEarthquakes / 2) + (totalEarthquakes / 2 - 1);
            // calculate average of middle elements
            medianMagnitude = ((double) sumOfMiddleElements) / 2;
        } else {
            // get the middle element
            medianMagnitude = (double) totalEarthquakes / 2;
        }
        return medianMagnitude;
    }

    /**
     * Returns the average magnitude of earthquakes of the list or 0 if the list is empty.
     * */
    public double getAverageMagnitude() {
        double averageMagnitude = 0;
        double totalMagnitude = 0;
        if (this.earthquakes.isEmpty()) {
            return averageMagnitude;
        }
        int totalEarthquakes = this.earthquakes.size();
        for (Earthquake e : this.earthquakes) {
            totalMagnitude += e.getMagnitude();
        }
        averageMagnitude = totalMagnitude / totalEarthquakes;
        return averageMagnitude;
    }

    /**
     * Returns the maximum earthquake magnitude of the list or 0 if the list is empty.
     * */
    public double getMaxMagnitude() {
        if (this.earthquakes.isEmpty()) {
            return 0;
        }
        double maxMagnitude = -1;
        for (Earthquake e : this.earthquakes) {
            if (e.getMagnitude() > maxMagnitude) {
                maxMagnitude = e.getMagnitude();
            }
        }
        return maxMagnitude;
    }
}
