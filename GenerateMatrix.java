import java.io.*;
import java.util.*;

public class GenerateMatrix {
    public static double[][] generateMatrix(List<City> cities) throws IOException{
        /**
         * Generates distances between cities and writes them into a .csv file
         * First, it calculates a city distance with all others
         * Then, it saves it as a line. After it iterated through all cities, it goes to a new line
         * If you wish to save the data on a file, uncomment code below and provide a .csv file of name specified in csvPath.
         */
        //final var csvPath = "distanceMatrix.csv";
        double[][] distanceMatrix = new double[cities.size()][cities.size()];

        //StringBuilder sb = new StringBuilder();
        //FileWriter fw = new FileWriter(csvPath, true);
        //BufferedWriter bw = new BufferedWriter(fw);
        //PrintWriter pw = new PrintWriter(bw);

        for (int i = 0; i < cities.size(); i++) {
            //sb.delete(0,sb.length());
            for (int j = 0; j < cities.size(); j++) {
                double distance = haversine_formula(cities.get(i), cities.get(j));
                //sb.append(distance+",");
                distanceMatrix[i][j] = distance >= 100 ? distance : Double.POSITIVE_INFINITY;
            }
            //sb.deleteCharAt(sb.length()-1);
            //pw.println(sb);
        }
        //pw.close();
        System.out.println("Data from .csv file loaded successfully. Proceeding to creating pheromone matrix...");
        return distanceMatrix;
    }
    public static double haversine_formula(City city1, City city2) {
        /**
         * Courtesy of vananth22 on GitHub Gists
         */
        if (city1 == city2) {
            return 0.0;
        }
        final double radius = 6378.137; // Radius of Earth in metres, https://nssdc.gsfc.nasa.gov/planetary/factsheet/earthfact.html
        double lat1 = city1.getLatitude();
        double lon1 = city1.getLongitude();
        double lat2 = city2.getLatitude();
        double lon2 = city2.getLongitude();
        double latDistance = DegToRad(lat2-lat1);
        double lonDistance = DegToRad(lon2-lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + 
        Math.cos(DegToRad(lat1)) * Math.cos(DegToRad(lat2)) * 
        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
            return radius * c;
    }
    private static double DegToRad(double value) {
        return value * Math.PI / 180;
    }
}