import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;


public class ThreadIndex extends Thread  {
    private ConcurrentHashMap<String, LinkedList<Integer>> index;
    private int start;
    private int end;
    private File ROOT;

    public  ThreadIndex(ConcurrentHashMap<String, LinkedList<Integer>> index, int start, int end, final File ROOT) {
        this.index = index;
        this.start = start;
        this.end = end;
        this.ROOT = ROOT;


    }@Override
    public void run(){

     }

}
