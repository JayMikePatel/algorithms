import java.util.*;

public class TravelingSalespersonGeneticAlgorithm {
    // Define cities and flight costs
    static final String[] cities = {"Seattle", "LA", "Chicago", "Atlanta", "Dallas"};
    static final int[][] flightCosts = {
            {0, 70, 145, 140, 120},
            {70, 0, 100, 170, 150},
            {145, 100, 0, 75, 165},
            {140, 170, 75, 0, 85},
            {120, 150, 165, 85, 0}
    };

    static final int POPULATION_SIZE = 100;
    static final double MUTATION_RATE = 0.01;
    static final int NUM_GENERATIONS = 1000;

    static Random random = new Random();

    public static void main(String[] args) {
        int[] bestRoute = findBestRoute();
        System.out.print("Best Route: " + cities[0] + " -> ");
        for(int i = 0; i < bestRoute.length; i++) {
            System.out.print(cities[bestRoute[i]] + " -> ");
        }
        System.out.println(cities[0]);
        System.out.println("Total Cost: " + calculateCost(bestRoute));
    }

    static int[] findBestRoute() {
        // Initialize population
        int[][] population = new int[POPULATION_SIZE][cities.length - 1];
        for (int i = 0; i < POPULATION_SIZE; i++) {
            population[i] = getRandomRoute();
        }

        // Evolution loop
        for (int generation = 0; generation < NUM_GENERATIONS; generation++) {
            population = evolvePopulation(population);
        }

        // Find the best route in the final population
        int[] bestRoute = population[0];
        int bestCost = calculateCost(bestRoute);
        for (int i = 1; i < population.length; i++) {
            int cost = calculateCost(population[i]);
            if (cost < bestCost) {
                bestRoute = population[i];
                bestCost = cost;
            }
        }
        return bestRoute;
    }

    static int[][] evolvePopulation(int[][] population) {
        int[][] newPopulation = new int[population.length][cities.length - 1];

        // Keep the best individual
        int[] bestRoute = getBestRoute(population);
        newPopulation[0] = bestRoute;

        // Crossover and mutation
        for (int i = 1; i < population.length; i++) {
            int[] parent1 = selectParent(population);
            int[] parent2 = selectParent(population);
            int[] child = crossover(parent1, parent2);
            mutate(child);
            newPopulation[i] = child;
        }

        return newPopulation;
    }

    static int[] getRandomRoute() {
        List<Integer> citiesList = new ArrayList<>();
        for (int i = 1; i < cities.length; i++) {
            citiesList.add(i);
        }
        Collections.shuffle(citiesList);
        int[] route = new int[cities.length - 1];
        for (int i = 0; i < route.length; i++) {
            route[i] = citiesList.get(i);
        }
        return route;
    }

    static int[] getBestRoute(int[][] population) {
        int[] bestRoute = population[0];
        int bestCost = calculateCost(bestRoute);
        for (int i = 1; i < population.length; i++) {
            int cost = calculateCost(population[i]);
            if (cost < bestCost) {
                bestRoute = population[i];
                bestCost = cost;
            }
        }
        return bestRoute;
    }

    static int[] crossover(int[] parent1, int[] parent2) {
        int[] child = new int[parent1.length];
        int startPos = random.nextInt(parent1.length);
        int endPos = random.nextInt(parent1.length);

        for (int i = 0; i < child.length; i++) {
            if (startPos < endPos && i > startPos && i < endPos) {
                child[i] = parent1[i];
            } else if (startPos > endPos) {
                if (!(i < startPos && i > endPos)) {
                    child[i] = parent1[i];
                }
            }
        }

        for (int i = 0; i < parent2.length; i++) {
            if (!contains(child, parent2[i])) {
                for (int j = 0; j < child.length; j++) {
                    if (child[j] == 0) {
                        child[j] = parent2[i];
                        break;
                    }
                }
            }
        }
        return child;
    }

    static void mutate(int[] route) {
        for (int i = 0; i < route.length; i++) {
            if (random.nextDouble() < MUTATION_RATE) {
                int swapWith = random.nextInt(route.length);
                int temp = route[i];
                route[i] = route[swapWith];
                route[swapWith] = temp;
            }
        }
    }

    static int calculateCost(int[] route) {
        int cost = 0;
        int currentCity = 0;
        for (int nextCity : route) {
            cost += flightCosts[currentCity][nextCity];
            currentCity = nextCity;
        }
        cost += flightCosts[currentCity][0]; // Return to starting city
        return cost;
    }

    static boolean contains(int[] array, int key) {
        for (int i : array) {
            if (i == key) {
                return true;
            }
        }
        return false;
    }

    static int[] selectParent(int[][] population) {
        return population[random.nextInt(population.length)];
    }
}
