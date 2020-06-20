import java.util.Random;
import java.util.List;

public class Solver {
    static int iterations = 750;
    static double totalPheromones;
    static int routeCount = 0;
    static Random rd = new Random();
    static double randomSum;
    static int index;
    static int bestDistance = Integer.MAX_VALUE;

    public static int solve(double[][] distanceMatrix, double[][] pheromoneMatrix, List<City> cities) {
        /**
         * there are two parts to this solver
         * First, have an ant walk through cities
         * Two, update pheromone values for next ants
         */
        int j = 0;
        int[] route = new int[cities.size()+1];
        for (int i = 0; i < iterations; i++) {
            int totalDistance = 0;
            boolean[] visitedCities = new boolean[cities.size()];
            visitedCities[0] = true;
            double[] probability = new double[cities.size()];
            routeCount = 0;
            while (routeCount < cities.size() - 1) {
                totalPheromones = 0;
                for (int k = 0; k < cities.size(); k++) {
                    if (!visitedCities[k])
                        totalPheromones += Math.pow(pheromoneMatrix[j][k], 4) / Math.pow(distanceMatrix[j][k], 5);
                }
                for (int k = 0; k < cities.size(); k++) {
                    if (!visitedCities[k]) {
                        probability[k] = (Math.pow(pheromoneMatrix[j][k], 4) / Math.pow(distanceMatrix[j][k], 5)) / totalPheromones;
                        //System.out.println(pheromoneMatrix[j][k]+" "+totalPheromones);
                    }
                }
                
                double target = rd.nextDouble();
                index = 0;
                randomSum = 0;
                // System.out.println("Total Pheromones: "+totalPheromones);
                while (randomSum <= target) {
                    if (!visitedCities[index]) {
                        randomSum += probability[index];
                    }
                    index++;
                }
                index--;
                visitedCities[index] = true;
                totalDistance += distanceMatrix[j][index];
                j = index;
                routeCount++;
                route[routeCount] = index;
                // recompute probabilities
                // randomize
            }
            routeCount++;
            route[routeCount] = 0;
            // return to starting city
            totalDistance += distanceMatrix[index][0];
            for (int l = 0; l < cities.size(); l++) {
                for (int k = 0; k < cities.size(); k++)
                    pheromoneMatrix[l][k] *= .95;
            }
            for (int l = 0; l < route.length - 1; l++)
                pheromoneMatrix[l][route[l]] += Math.pow(totalDistance, -3);
            //for (int l = 0; l < cities.size(); l++)
            //    System.out.print(route[l]+", ");
            if (totalDistance <= bestDistance) { 
                bestDistance = totalDistance;
                System.out.println("New best distance! It is: "+bestDistance);
            }
            //System.out.println("Distance traveled: "+totalDistance);
        }
        for (int l = 0; l < cities.size(); l++)
            System.out.print(route[l]+",");
        return bestDistance;
    }
}