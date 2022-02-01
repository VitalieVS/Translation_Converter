
import java.io.*;
import java.nio.charset.*;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

        createResults();

        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"));
             OutputStreamWriter writer =
                     new OutputStreamWriter(
                             new FileOutputStream("results.txt", true), StandardCharsets.UTF_16)) {

            String st;

            String key;

            String toConvert;

            while ((st = br.readLine()) != null) {

                key = st.substring(0, st.indexOf("="));

                toConvert = st.substring(st.indexOf("=") + 1);

                char[] toEncode = toConvert.toCharArray();

                if (toEncode[0] == '\t' || toEncode[0] == ' ') {

                    toEncode = Arrays.copyOfRange(toEncode, 2, toEncode.length);
                }

                StringBuilder word = new StringBuilder();

                for (char c : toEncode) word.append((inList(c) ? c : "\\u0" + (Integer.toHexString(c))));

                writer.write(key + "=" + word + "\n");
            }

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    public static void createResults() {

        try {
            File myObj = new File("results.txt");

            if (myObj.createNewFile()) {

                System.out.println("File created: " + myObj.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean inList(char toCheck) {

        return IntStream.range(32, 127).boxed().collect(Collectors.toList()).stream().anyMatch(item -> item == toCheck);
    }
}
