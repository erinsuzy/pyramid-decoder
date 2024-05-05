import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PyramidDecoder {

    public static void main(String[] args) {
        try{
            Map<Integer, String> numWordMap = createNumWordMap();

            List<List<Integer>>pyrNum = readNum();

            sortNumInPyr(pyrNum);

            createPyr(pyrNum);

            String output = createPhrase(pyrNum, numWordMap);

            System.out.println(output);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<Integer, String> createNumWordMap() throws IOException {
        Map<Integer, String> numWordMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("pyramidStuff/pyramidInfo.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                if (parts.length == 2) {
                    int number = Integer.parseInt(parts[0]);
                    String word = parts[1];
                    numWordMap.put(number, word);
                } else {
                    System.err.println("Invalid line: " + line);
                }
            }
            reader.close();
        }
        return numWordMap;
    }



    private static List<List<Integer>> readNum() throws IOException {
        List<List<Integer>> pyrNum = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("pyramidStuff/pyramidInfo.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] numbers = line.trim().split("\\s+");
                List<Integer> pyr = new ArrayList<>();
                for (String number : numbers) {
                    pyr.add(Integer.parseInt(number));
                }
                pyrNum.add(pyr);
            }
            br.close();
        }
        return pyrNum;
    }

    private static void sortNumInPyr(List<List<Integer>> pyrNum) {
        for (List<Integer> number : pyrNum) {
            Collections.sort(number);
        }
    }

    private static void createPyr(List<List<Integer>> pyrNum) {
        for (List<Integer> integers : pyrNum) {
            for (Integer integer : integers) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
        }

    private static String createPhrase(List<List<Integer>> pyrNum, Map<Integer, String> numWordMap) {
        StringBuilder sb = new StringBuilder();
        for (List<Integer> integers : pyrNum) {
            int lastIndex = integers.size() - 1;
            int lastNumber = integers.get(lastIndex);
            String word = numWordMap.getOrDefault(lastNumber, "Unknown");
            sb.append(word).append("");

        }
        return sb.toString().trim();
        }
    }






