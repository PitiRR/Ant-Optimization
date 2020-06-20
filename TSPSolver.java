import java.io.FileNotFoundException;
import java.io.IOException;

public class TSPSolver {
    public static void main(String args[]) throws FileNotFoundException, IOException {
        // determine constants
        final var speedKph = 800;
        final var airportTimeHours = 0.5;
        final var fileCoordinateAddress = "equipment.csv";
        // load csv
        var cities = LoadCities.loadCities(fileCoordinateAddress);
        // create city objects with coordinates
        var distanceMatrix = GenerateMatrix.generateMatrix(cities);
        var pheromoneMatrix = GeneratePheromoneMatrix.generatePheromoneMatrix(distanceMatrix);
        // create distance matrix
        // solve the problem
        var solution = Solver.solve(distanceMatrix, pheromoneMatrix, cities);
        solution /= speedKph;
        solution += airportTimeHours * cities.size();
        System.out.println("Distance traveled in hours: "+solution+" hour");
    }
}