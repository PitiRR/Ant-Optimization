import java.util.*;
import java.io.File;
// import City;
import java.io.FileNotFoundException;

public class LoadCities {
    public static List<City> loadCities(String address) throws IllegalArgumentException, FileNotFoundException {
        var cities = new ArrayList<City>();
        Scanner sc = new Scanner(new File("equipment.csv"));

        while (sc.hasNextLine()) {
            String cell = sc.nextLine();
            String[] line = cell.split(",");
            if (line.length == 2) { //if a line has both latitude and longitude.
                var city = new City(Double.parseDouble(line[0]), Double.parseDouble(line[1]));
                cities.add(city);
            } else if (line.length > 0) {
                throw new IllegalArgumentException ("Invalid input.");
            }
        }
        return cities;
    }
}