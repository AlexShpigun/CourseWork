import java.io.File;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

public class Index {
    final static int THREAD_AMOUNT = 20;
    static final File SOURCE_ROOT_FILE = new File("C:\\Users\\Alex Shpigun\\IdeaProjects\\untitled3");
    static final int FILES_AMOUNT = SOURCE_ROOT_FILE.list().length;
    static final int PORT = 8000;

    private static int[] startEndGenerate(int arrLength, int parts, int currentIndex) {
        int[] result = new int[2];
        result[0] = Math.round(((float)arrLength)/parts*currentIndex);
        result[1] = Math.round(((float)arrLength)/parts*(currentIndex+1));

        return result;
    }


    public static void main(String[] args) {
        ThreadIndex[] indexArr = new ThreadIndex[THREAD_AMOUNT];
        ConcurrentHashMap<String, LinkedList<Integer>> index = new ConcurrentHashMap<String, LinkedList<Integer>>(10, 0.75f, THREAD_AMOUNT);

        for (int i = 0; i < THREAD_AMOUNT; i++) {
            int[] startEndIndexes = startEndGenerate(FILES_AMOUNT, THREAD_AMOUNT, i);
            indexArr[i] = new ThreadIndex(index, startEndIndexes[0], startEndIndexes[1], SOURCE_ROOT_FILE);
            indexArr[i].start();
        }
        for (int i = 0; i < THREAD_AMOUNT; i++) {

            try {
                indexArr[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }
}




