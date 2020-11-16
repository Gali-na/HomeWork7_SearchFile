import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the file to be searched.. ");
        String name = scan.next();
        System.out.println("Enter the directory where to search ");
        String directory = scan.next();
        ExecutorService es = Executors.newFixedThreadPool(3);
        ArrayList<Future<String>> result = new ArrayList<>();
        File file = new File(directory);
        File[] fileArray = file.listFiles();
        for (File fileElement : fileArray) {

            result.add(es.submit(new FindFile(name, new File(String.valueOf(fileElement)))));
        }
        for (Future<String> future : result) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        es.shutdown();
    }
}
