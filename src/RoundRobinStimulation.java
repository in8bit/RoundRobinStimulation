import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class RoundRobinStimulation {
    public static void main(String[] args) throws FileNotFoundException {
        File outputFile = getOutputFile();                          //first command takes the output file to write the 5 unit progress to
        File inputFile = getInputFile();                            //second command gets input file to read the processes from
        File outputLOGFile = getOutputFile();                       //third command gets the output log file, where the data from every time unit is to be written
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter a time Quantum.");                //fourth command gets the quantum for the stimulation
        int quantum = userInput.nextInt();
        if (quantum <= 0) {
            System.out.println("Please enter a quantum number more than 0.");
        }

    }

    public static File getOutputFile() {
        File file = null;
        String input = "";
        Scanner userInput = new Scanner(System.in);
        try {
            do {
                System.out.println("Enter a name of the output file that doesnt exist.");
                input = userInput.nextLine().replaceAll("\\s+", "");
            } while (!new File(input + ".txt").createNewFile());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static File getInputFile() {
        String input = "";
        File file = null;
        Scanner userInput = new Scanner(System.in);
        while (!new File(input + ".txt").exists()) {
            System.out.println("Enter Input file name.");
            input = userInput.nextLine().replaceAll("\\s+", "");
        } //while loops makes sure to get the name of a file that exists
        file = new File(input + ".txt");

        return file;
    }
}


