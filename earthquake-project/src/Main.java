import java.util.ArrayList;

public class Main {

    private static EarthquakeDB earthquakeDB = new EarthquakeDB();

    private static EarthquakeDB earthquakeDBAfterCalculations = new EarthquakeDB();

    public static void main(String[] args) {

        initializeDatabase();

        System.out.println(earthquakeDB.toString());

        System.out.println("##");

        initializeNewDatabase();

        System.out.println(earthquakeDBAfterCalculations.toString());
    }

    private static void initializeDatabase() {
        Date date = new Date(2021,2, 10, 13, 45, 57);
        earthquakeDB.add(new Earthquake(date, 2.6, 2, 22.1, 39.7));

        Date date1 = new Date(2021,2,10,14,50,13);
        earthquakeDB.add(new Earthquake(date1, 4.2, 6, 20.5, 38.7));

        Date date2 = new Date(2021,2,11,18,19,13);
        earthquakeDB.add(new Earthquake(date2, 3.2, 6, 21.5, 39.1));

        Date date3 = new Date(2021,2,10,14,0,0);
        earthquakeDB.add(new Earthquake(date3, 3,2, 21.1, 38.5));

        Date date4 = new Date(2021,2,12,5,30,0);
        earthquakeDB.add(new Earthquake(date4, 4,8,20.8,38.5));
    }

    private static void initializeNewDatabase() {
        ArrayList<Earthquake> withingDay = earthquakeDB.withinDay(new Date(2021,2,10));
        System.out.println(withingDay.toString());
        System.out.println("#");
        ArrayList<Earthquake> withinMagnitudeRange = earthquakeDB.withinMagnitudeRange(2.9,4.5);
        System.out.println(withinMagnitudeRange.toString());
        System.out.println("#");
        ArrayList<Earthquake> withingDistance = earthquakeDB.withinDistance(new Location(21.1, 38.5), 60);
        System.out.println(withingDistance.toString());

        for (Earthquake e : withingDay) {
            if (withingDistance.contains(e) && withinMagnitudeRange.contains(e)) {
                earthquakeDBAfterCalculations.add(e);
            }
        }
    }
}
