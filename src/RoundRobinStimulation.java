import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RoundRobinStimulation {
    static List<String> str = new ArrayList<String>();
    static List<Process> processList = new ArrayList<Process>();
    private static final Scanner userInput = new Scanner(System.in);

    public static void main(String[] args) {

        File outputFile = getOutputFile();                             //first command takes the output file to write the 5 unit progress to
        File inputFile = getInputFile();                               //second command gets input file to read the processes from
        if (!inputFileChecked(inputFile)) {
            System.out.println("Error: please check the data provided in the file. It must be all integers with value more than 0." +
                    "Each line must have more than 3 integers and with a space in between. The number of CPU bursts must be same as the ones provided in the job." +
                    "Since either or all conditions mentioned here are not satifsified," +
                    " Stimulation ends now.");
            System.exit(0);
        }
        System.out.println("*Output Log file*");
        File outputLOGFile = getOutputFile();                       //third command gets the output log file, where the data from every time unit is to be written
        System.out.println("Enter a time Quantum.");                //fourth command gets the quantum for the stimulation
        int quantum = userInput.nextInt();
        while (quantum <= 0) {
            System.out.println("Please enter a quantum number more than 0.");
            quantum = userInput.nextInt();
        }
        for (int i = 0; i < str.size(); i++) {
            processList.add(new Process(str.get(i)));
        }
        //******************************************************************************testing
        Process job1 = processList.get(0);
        Process job2 = processList.get(1);
        Process job3 = processList.get(2);

        System.out.println("job 1 arrival time : " + job1.getArrivalTime());
        System.out.println("job 2 arrival time : " + job2.getArrivalTime());
        System.out.println("job 3 arrival time : " + job3.getArrivalTime());

        System.out.println("job 1 cpu bursts : " + job1.getNumOfCPUBursts());
        System.out.println("job 2 cpu bursts : " + job2.getNumOfCPUBursts());
        System.out.println("job 3 cpu bursts : " + job3.getNumOfCPUBursts());

        System.out.println("job 1 cpu burst list : " + job1.getCpuburstList());
        System.out.println("job 2 cpu burst list : " + job2.getCpuburstList());
        System.out.println("job 3 cpu burst list : " + job3.getCpuburstList());
        //******************************************************************************
    }

    public static File getOutputFile() {
        File file = null;
        String input = "";
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
        while (!new File(input + ".txt").exists()) {
            System.out.println("Enter Input file name.");
            input = userInput.nextLine().replaceAll("\\s+", "");
        } //while loops makes sure to get the name of a file that exists
        file = new File(input + ".txt");
        return file;

    }

    public static boolean inputFileChecked(File file) {
        Scanner scan = null;
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (scan.hasNextInt()) {                                                  //check here, i made a change &&hasNext()
            while (scan.hasNext()) {
                String line = scan.nextLine();                                   //get the line, convert one line into one arrayList
                str.add(line);
            }
            for (int i = 0; i < str.size(); i++) {
                String line = str.get(i);
                String[] subStr = line.split(" ");
                if (subStr.length < 3)
                    return false;                              //if the length of a line is less than 3 there are no jobs and data is invalid.
                for (String s : subStr) {
                    if (Integer.parseInt(s) <= 0)                                   // no value in the job can be less than or equal to zero.
                        return false;
                }
                int totalCpuBursts = Integer.parseInt(subStr[1]);
                int jobLength = (subStr.length - 2);                            // job length = total-(timeofArrival +CpuBursts)
                if (jobLength != (totalCpuBursts * 2) - 1)
                    return false;          //num of CPU bursts mentioned are not provided then data is invalid
            }
            scan.close();
            return true;
        } else return false;

    }
}


