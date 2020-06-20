public class GeneratePheromoneMatrix {
    public static double[][] generatePheromoneMatrix(double[][] distanceMatrix) {
        double[][] pheromoneMatrix = new double[distanceMatrix.length][distanceMatrix.length];

        for (int i = 0; i < distanceMatrix.length; i++) {
            for (int j = 0; j < distanceMatrix.length; j++) {
                if (i != j) {
                    double pheromone = 1 / distanceMatrix[i][j];
                    pheromoneMatrix[i][j] = pheromone;
                }
            }
        }
        System.out.println("Pheromone Matrix saved successfully.");
        return pheromoneMatrix;
    }
}