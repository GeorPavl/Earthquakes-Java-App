import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class MainPartTwo {

    static Scanner sc = new Scanner(System.in).useLocale(Locale.US);

    private static List<Country> countries = new ArrayList<>();
    private static EarthquakeDB earthquakes = new EarthquakeDB();
    private static List<String> states = new ArrayList<>();

    public static void main (String [] args) {

        /* part A info */
        System.out.print("Json file with earthquake information: ");
        String eqFilename = sc.nextLine().strip();
        System.out.print("Text file with US states and territories: ");
        String usFilename = sc.nextLine().strip();
        System.out.print("CSV file with country information: ");
        String countryFilename = sc.nextLine().strip();

        /* part B additional info */
        System.out.print("Country of interest: ");
        String countryName = sc.nextLine().strip();
        System.out.print("Distance: ");
        double distance = sc.nextDouble();

//        String eqFilename = "resources/top20.geojson";
//        String countryFilename = "resources/some_countries.csv";
//        String usFilename = "resources/US_states_and_territories";
//        String countryName = "Japan";
//        double distance = 2000.0;


        readEarthquakes(eqFilename);

        readCountries(countryFilename, usFilename);

        createAvgMagJsonFile();

        createWithinDistanceFromCenterCsvFile(countryName,distance);
    }

    private static void readEarthquakes(String eqFilename){
        JsonObject json = null;

        try {
            json = JsonParser.parseReader(new FileReader(eqFilename)).getAsJsonObject();
        } catch (FileNotFoundException e){
            System.out.println("Failed to open file" + eqFilename);
        }

        JsonArray features = json.getAsJsonArray("features");

        for (JsonElement f : features){

            JsonObject feature = (JsonObject) f.getAsJsonObject();
            JsonObject properties = feature.getAsJsonObject("properties");
            JsonObject geometry = feature.getAsJsonObject("geometry");
            JsonArray coordinates = geometry.getAsJsonArray("coordinates");

            earthquakes.add(new Earthquake(properties.get("title").getAsString(),
                    properties.get("mag").getAsDouble(),
                    coordinates.get(1).getAsDouble(),
                    coordinates.get(0).getAsDouble()
            ));

        }
    }

    private static void readCountries(String countryFilename, String statesFilename){

        List<List<String>> records = new ArrayList<>();

        try(Scanner scanner = new Scanner(new File(countryFilename));) {
            while (scanner.hasNextLine()) {
                records.add(getRecordFromLine(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Failed to open file" + countryFilename);

        }

        for (List<String> record: records){
            if (record != records.get(0)){
                String countryCode = record.get(0).substring(1,record.get(0).length()-1);
                String countryName = record.get(1).substring(1,record.get(1).length()-1);
                String northTrimmed = record.get(2).substring(1,record.get(2).length()-1);
                Double north = Double.parseDouble(northTrimmed);
                String southTrimmed = record.get(3).substring(1,record.get(3).length()-1);
                Double south = Double.parseDouble(southTrimmed);
                String eastTrimmed = record.get(4).substring(1,record.get(4).length()-1);
                Double east = Double.parseDouble(eastTrimmed);
                String westTrimmed = record.get(5).substring(1,record.get(5).length()-1);
                Double west = Double.parseDouble(westTrimmed);

                countries.add(new Country(countryCode, countryName, north, south, east, west));
            }
        }

        readStates(statesFilename);

        findEarthquakeForEachCountry();
    }

    private static List<String> getRecordFromLine(String line){
        List<String> values = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }

    private static void findEarthquakeForEachCountry(){

        for (Country c : countries){

            for (Earthquake e : earthquakes.getEarthquakes()){
                if (e.getTitle().toLowerCase().contains(c.getName().toLowerCase())){
                    c.getEarthquakes().add(e);
                }
                if (c.getName().equals("United States of America")){
                    for (String s : states){
                        if (e.getTitle().toLowerCase().contains(s.toLowerCase())){
                            c.getEarthquakes().add(e);
                        }
                    }
                }
            }
        }
    }

    private static JsonObject findAverageMagnitudeForEachCountry() {
        JsonObject result = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        result.add("magnitudes", jsonArray);
        for (Country c : countries){
            Double average = c.getEarthquakes().getAverageMagnitude();
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("code", c.getCode());
            jsonObject.addProperty("avg", average);
            jsonArray.add(jsonObject);
        }

        return result;
    }

    private static void createAvgMagJsonFile(){

        JsonObject averageMagnitudes = findAverageMagnitudeForEachCountry();

        try (FileWriter file = new FileWriter("resources/top20avg.json")){
            file.write(String.valueOf(averageMagnitudes));
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readStates(String stFilename){

        try {
            File myObj = new File(stFilename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()){
                String data = myReader.nextLine();
                //System.out.println(data);
                states.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Failed to open file" + stFilename);
        }

    }

    private static void createWithinDistanceFromCenterCsvFile(String countryName, double distance) {
        for (Country c : countries) {
            if (c.getName().equals(countryName)) {
                ArrayList<Earthquake> list = c.withinDistanceFromCenter(distance);

                try {
                    FileWriter csvWriter = new FileWriter("resources/" + countryName + (int) distance + ".csv");
                    csvWriter.append("\"title\"");
                    csvWriter.append(",");
                    csvWriter.append("\"mag\"\n");

                    for (Earthquake e : list) {
                        csvWriter.append("\"" + e.getTitle() + "\"");
                        csvWriter.append(",");
                        csvWriter.append("\"" + String.valueOf(e.getMagnitude()) + "\"\n");
                    }

                    csvWriter.flush();
                    csvWriter.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
