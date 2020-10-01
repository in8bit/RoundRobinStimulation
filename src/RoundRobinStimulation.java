import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class RoundRobinStimulation {
    private static final Scanner userInput = new Scanner(System.in);
    static List<String> str = new ArrayList<String>();
    static PriorityQueue<Process> processQueue = new PriorityQueue<Process>();
    private static FileWriter outFile;
    private static FileWriter logFile;

    public static void Stimulation(PriorityQueue<Process> processQueue, int quantum) throws Exception {
        int clock = 0;
        LinkedList<Integer> readyQueue = new LinkedList<Integer>();
        LinkedList<Integer> IOQueue = new LinkedList<Integer>();
        ArrayList<Integer> cpu = new ArrayList<Integer>();
        ArrayList<Integer> IO = new ArrayList<Integer>();
        Iterator<Process> it = processQueue.iterator();

        while (it.hasNext()) {
            Process job = processQueue.poll();
            while (job.getArrivalTime() != clock) {
                cpu.add(0);
                IO.add(0);
                readyQueue.add(0);
                IOQueue.add(0);
                logFile.write(clock + " : " + "No event. \n");
                System.out.println(clock + " : " + "No event");
                clock++;
            }
            if (job.getArrivalTime() == clock) {
                cpu.add(0);
                IO.add(0);
                readyQueue.add(0);
                IOQueue.add(0);
                String status = job.getStatus();
                switch (status) {
                    case "newJob":
                        if (cpu.get(job.getArrivalTime()) == 0) {
                            int burst = job.getCurrentCPUBurstTime();
                            if (quantum >= burst) {
                                for (int i = job.getArrivalTime(); i == i + burst; i++) {
                                    cpu.add(job.getProcessID());
                                }
                                System.out.println("CPU" + " : " + cpu.toString());
                                //this condition means the cpu burst is finished. now if there is an ioburst then follow up there
                                //update stats
                                //write down to the file
                            } else if (burst > quantum) {
                                for (int i = job.getArrivalTime(); i == i + quantum; i++) {
                                    cpu.add(i, job.getProcessID());
                                }
                                System.out.println("CPU" + " : " + cpu.toString());
                                //this condition means the cpu burst is not finished. now the job has to move to ready queue with preempive state.
                                //update stats
                                //write down to the file
                            }

                        } else {
                            readyQueue.add(job.getProcessID());
                            //update stats
                            //write down to the file
                        }
                        logFile.write(clock + " : " + job.getProcessID() + "\n");
                        break;
//                    case "ready":
//                        System.out.println("ready");
//                        break;
//                    case "running":
//                        System.out.println("running");
//                        break;
//                    case "blocked":
//                        System.out.println("blocked");
//                        break;
//                    case "terminaed":
//                        System.out.println("terminated");
//                        break;
//                    case "preempted":
//                        System.out.println("preempted");
//                        break;

                }

                System.out.println(clock + " : " + "At the start of " + clock + " job with process id : " + job.getProcessID() + " came ");
                clock++;
            }
        }
        outFile.flush();
        outFile.close();
        logFile.flush();
        logFile.close();
    }

    public static void main(String[] args) throws Exception {
        File outputFile = getOutputFile();                             //first command takes the output file to write the 5 unit progress to
        File inputFile = getInputFile();                               //second command gets input file to read the processes from
        if (!inputFileChecked(inputFile)) {
            System.out.println("Error: please check the data provided in the file.\n It must be all integers with value more than 0.\n" +
                    "Each line must have more than 3 integers and with a space in between.\n The number of CPU bursts must be same as the ones provided in the job.\n" +
                    "Since either or all conditions mentioned here are not satifsified,\n" +
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
            processQueue.add(new Process(str.get(i)));
        }

        outFile = new FileWriter(outputFile);
        logFile = new FileWriter(outputLOGFile);
        Stimulation(processQueue, quantum);
    }

    public static File getOutputFile() throws IOException {
        String input = "";
        File file = null;
        do {
            System.out.println("Enter a name of the output file that doesnt exist.");
            input = userInput.nextLine().replaceAll("\\s+", "");
        } while (!new File(input + ".txt").createNewFile());
        file = new File(input + ".txt");
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


