import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ThreadIndex extends Thread {
    private ConcurrentHashMap<String, LinkedList<Integer>> index;
    private int start;
    private int end;
    private File ROOT;

    public ThreadIndex(ConcurrentHashMap<String, LinkedList<Integer>> index, int start, int end, final File ROOT) {
        this.index = index;
        this.start = start;
        this.end = end;
        this.ROOT = ROOT;


    }

    static private List<String> fileTermsList(File file) throws IOException {

        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line = bufferedReader.readLine();

        List<String> terms = new ArrayList<>();


        while (line != null) {

            terms.addAll(Stream.of(line.split("[^A-Za-z]+"))
                    .map(String::toLowerCase)
                    .distinct()
                    .collect(Collectors.toList()));


            line = bufferedReader.readLine();

        }
        return terms;
    }

    @Override

    public void run() {

        for (int docId = start; docId < end; docId++) {

            List<String> uniqueTerms;

            try {
                uniqueTerms = fileTermsList(new File(ROOT + "\\" + ROOT.list()[docId]));
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }


            for (int j = 0; j < uniqueTerms.size(); j++) {
                String word = uniqueTerms.get(j);
                final int doc = docId;
                index.computeIfPresent(word,(key , val)  -> val.add(doc)? val : val);
                index.computeIfAbsent(word,(key , val) -> (LinkedList<Integer>) (Arrays.asList(doc)));


            }

        }

    }
}
