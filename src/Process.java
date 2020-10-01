import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Process implements Comparable<Process> {
    private final int processID;
    private String status;
    private final int arrivalTime;
    private final int numOfCPUBursts;
    private final List<Integer> cpuburstList = new ArrayList<Integer>();
    private final List<Integer> ioBurstList = new ArrayList<Integer>();
    private int currentCPUBurstTime;
    private int currentIOBurstTime;

    public Process(String job) {
        Random rand = new Random();
        String[] line = job.split(" ");

        processID = rand.nextInt(10000);
        status = "newJob";

        arrivalTime = Integer.parseInt(line[0]);
        numOfCPUBursts = Integer.parseInt(line[1]);

        for (int i = 2; i < line.length; i += 2) {
            cpuburstList.add(Integer.parseInt(line[i]));
        }
        if (line.length > 3) {
            for (int i = 3; i < line.length; i += 2) {
                ioBurstList.add(Integer.parseInt(line[i]));
            }
        }

        currentCPUBurstTime = Integer.parseInt(line[2]);
        currentIOBurstTime = 0; //not initialising for cases with only 1 cpu burst

    }

    public int getProcessID() {
        return processID;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getNumOfCPUBursts() {
        return numOfCPUBursts;
    }

    public List<Integer> getCpuburstList() {
        return cpuburstList;
    }

    public List<Integer> getIoBurstList() {
        return ioBurstList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCurrentCPUBurstTime() {
        return currentCPUBurstTime;
    }

    public void setCurrentCPUBurstTime(int currentCPUBurstTime) {
        this.currentCPUBurstTime = currentCPUBurstTime;
    }

    public int getCurrentIOBurstTime() {
        return currentIOBurstTime;
    }

    public void setCurrentIOBurstTime(int currentIOBurstTime) {
        this.currentIOBurstTime = currentIOBurstTime;
    }

    @Override
    public int compareTo(Process p) {
        //sorting by arrival time
        if (this.getArrivalTime() > p.getArrivalTime()) {
            return 1;
        } else if (this.getArrivalTime() < p.getArrivalTime()) {
            return -1;
        } else return 0;

       /*
        //sorting by status
        String status =this.getStatus();
        String statusOfOtherJob = p.getStatus();
        if (status.equals("newJob") && statusOfOtherJob.equals("blocked")|| statusOfOtherJob.equals("preempted")){
            return 1;
        }else if (status.equals("blocked") && statusOfOtherJob.equals("preempted")){
            return 1;
        }else if (statusOfOtherJob.equals("newJob")&& status.equals("blocked")|| status.equals("preempted")){
            return -1;
        }else if (statusOfOtherJob.equals("blocked") && status.equals("preempted")){
            return -1;
        }
        else
        return 0;
    */
    }
}
