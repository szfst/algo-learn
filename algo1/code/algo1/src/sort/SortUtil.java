package sort;

import java.util.Random;

public class SortUtil {
    public static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
    public static int randomIntBetween(int min,int max){
        Random random = new Random();
        return random.nextInt(max-min+1)+min;
    }
}
