import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

class MyTimer {
    private long start;

    public MyTimer() {
        start = System.currentTimeMillis();
    }

    public long getElapsed() {
        return System.currentTimeMillis() - start;
    }

    public void createNewPoint() {
        System.out.println(start);
    }
}

public class Main {
    public static int n;
    public static int []arr;
    public static boolean check(int index, int target) {
        return arr[index] <= target;
    }
    public static void generateRandomArr(int n) {
        Random random = new Random();
        int []randomArray = new int[n];
        for (int i = 0; i < n; i++) {
            randomArray[i] = random.nextInt(Integer.MAX_VALUE);
        }
        Arrays.sort(randomArray);
        try {
            FileWriter writer = new FileWriter("arr.txt");
            writer.write(Integer.toString(n) + " ");
            for (int i = 0; i < n; i++) {
                writer.write(Integer.toString(randomArray[i]) + " ");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл");
            e.printStackTrace();
        }
    }

    public static void readArr() throws IOException {
        FileReader fr = new FileReader("arr.txt");
        Scanner scanner = new Scanner(fr);
        String str = scanner.next();
        n = Integer.parseInt(str);
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            str = scanner.next();
            arr[i] = Integer.parseInt(str);
        }
        fr.close();
    }

    public static int findBinarySearch(int target) {
        int l = 0, r = n;
        while (r - l > 1) {
            int mid = (l + r) / 2;
            if (check(mid, target)) {
                l = mid;
            } else {
                r = mid;
            }
        }
        return arr[l];
    }

    public static int findStupid(int target) {
        int index = 0;
        for (int i = 0; i < n && arr[i] <= target; i++, index = i) {}
        return arr[index - 1];
    }

    public static void printArr() {
        for (int i = 0; i < n; i++) {
            if (i % 15 == 0) System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        generateRandomArr(10000000);
        System.exit(0);
        readArr();
//        printArr();
        MyTimer myTimer = new MyTimer();
        int resultBinary = findBinarySearch(1923424);
        long currentTime = myTimer.getElapsed();
        System.out.println("Result: " + resultBinary + " time mill: " + currentTime);
        int resultStupid = findStupid(1923424);
        System.out.println("Result: " + resultStupid + " time mill: " + (myTimer.getElapsed() - currentTime));
    }
}