import java.io.*;
import java.util.*;

public class PyramidDecoder {

    public static void main(String[] args) {
        try {
            Map<Integer, String> numWordMap = createNumWordMap("/resources/pyramidInfo.txt");
            List<Integer> numbers = new ArrayList<>(numWordMap.keySet());
            Collections.sort(numbers);
            List<List<Integer>> pyramid = createPyramid(numbers);
            decodePyr(pyramid, numWordMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<Integer, String> createNumWordMap(String filePath) throws IOException {
        Map<Integer, String> numWordMap = new HashMap<>();
        try (InputStream file = PyramidDecoder.class.getResourceAsStream("pyramidInfo.txt")) {
            if (file == null) {
                throw new FileNotFoundException("File not found: " + filePath);
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.trim().split("\\s+");
                    if (parts.length == 2) {
                        try {
                            int number = Integer.parseInt(parts[0]);
                            String word = parts[1];
                            numWordMap.put(number, word);
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid number format: " + parts[0]);
                        }
                    } else {
                        System.err.println("Invalid line format: " + line);
                    }
                }
            }
        }
        return numWordMap;
    }

    private static List<List<Integer>> createPyramid(List<Integer> numbers) {
        List<List<Integer>> pyramid = new ArrayList<>();
        int numRows = calculateNumRows(numbers.size());
        int currentIndex = 0;
        for (int i = 1; i <= numRows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                row.add(numbers.get(currentIndex++));
            }
            pyramid.add(row);
        }
        return pyramid;
    }

    private static int calculateNumRows(int numElements) {
        return (int) Math.ceil((-1 + Math.sqrt(1 + 8 * numElements)) / 2);
    }

    private static void decodePyr(List<List<Integer>> pyramid, Map<Integer, String> numWordMap) {
        StringBuilder sb = new StringBuilder();
        for (List<Integer> row : pyramid) {
            int lastNumber = row.get(row.size() - 1);
            String word = numWordMap.getOrDefault(lastNumber, "Unknown");
            sb.append(word).append(" ");
        }
        System.out.println("Decoded message: " + sb.toString().trim());
    }
}


