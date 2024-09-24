public class Knapsack {
    public static void main(String[] args) {
        // Initialize the item values and weights.
        int P[] = {0, 2, 3, 1, 4};
        int wt[] = {0, 3, 4, 6, 5};
        
        // Set the maximum weight capacity and number of items.
        int m = 8, n = 4;
        
        // Create a 2D array to store the values of the items for each weight capacity.
        int[][] k = new int[n + 1][m + 1];
        
        // Initialize the weight index to 0.
        int w = 0;
        
        // Iterate through each item.
        for (int i = 0; i <= n; i++) {
            // Iterate through each weight capacity.
            for (w = 0; w <= m; w++) {
                // If there are no items or no weight capacity, the value is 0.
                if(i==0|| w==0)
                    k[i][w] = 0;
                // If the weight of the current item is less than or equal to the current weight capacity,
                // then consider the maximum of either including the current item or not including it.
                else if (wt[i] <= w) {
                    k[i][w] = Math.max(P[i] + k[i - 1][w - wt[i]], k[i - 1][w]);
                }
                // If the weight of the current item is greater than the current weight capacity,
                // then exclude the current item and use the value of the previous item at the same weight capacity.
                else {
                    k[i][w] = k[i - 1][w];
                }
            }
        }
        
        int[] selectedItems = new int[n + 1];
        boolean[] x = new boolean[n + 1];
        int availableWeight = m;
        for(int j = n; j > 0; j--) {
            if(k[j][availableWeight] != k[j - 1][availableWeight]) {
                selectedItems[j] = 1;
                x[j] = true;
                availableWeight -= wt[j];
            }
        }
        
        // Print the maximum value that can be obtained using the given weight capacity and items.
        System.out.println("Maximum Profit: " + k[n][m]);
        
        System.out.print("Profit Array [");
        for(int j = 0; j < P.length - 1; j++) {
            System.out.print(P[j] + ", ");
        }
        System.out.println(P[P.length - 1] + "]");
        
        System.out.print("Weight Array [");
        for(int l = 0; l < wt.length - 1; l++) {
            System.out.print(wt[l] + ", ");
        }
        System.out.println(wt[wt.length - 1] + "]");
        
        System.out.print("Selected items (0 for not selected, 1 for selected):\n[");
        for(int o = 1; o < selectedItems.length - 1; o++) {
            System.out.print(selectedItems[o] + ", ");
        }
        System.out.println(selectedItems[selectedItems.length - 1] + "]");
    }
}
