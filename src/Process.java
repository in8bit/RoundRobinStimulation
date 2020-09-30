import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Process {
    private int processID;
    private String status;

    private int arrivalTime;
    private int numOfCPUBursts;
    private List<Integer> cpuburstList = new ArrayList<Integer>();
    private List<Integer> ioBurstList = new ArrayList<Integer>();

    private int currentCPUBurstTime;
    private int currentIOBurstTime;

    private int turnAroundTime;
    private int readyQWaitTime;
    private int ioWaitTime;

    private int cpuUtilisation;
    private int avgTurnAroundTime;
    private int avgReadyQWaitTime;
    private int avgIOWaitTime;

    public Process(String job) {
        Random rand = new Random();
        String[] line = job.split(" ");

        processID = rand.nextInt(10000000);
        status = "New";

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

        turnAroundTime = 0;
        readyQWaitTime = 0;
        ioWaitTime = 0;

        cpuUtilisation = 0;
        avgTurnAroundTime = 0;
        avgReadyQWaitTime = 0;
        avgIOWaitTime = 0;

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

    public int getTurnAroundTime() {
        return turnAroundTime;
    }

    public void setTurnAroundTime(int turnAroundTime) {
        this.turnAroundTime = turnAroundTime;
    }

    public int getReadyQWaitTime() {
        return readyQWaitTime;
    }

    public void setReadyQWaitTime(int readyQWaitTime) {
        this.readyQWaitTime = readyQWaitTime;
    }

    public int getIoWaitTime() {
        return ioWaitTime;
    }

    public void setIoWaitTime(int ioWaitTime) {
        this.ioWaitTime = ioWaitTime;
    }

    public int getCpuUtilisation() {
        return cpuUtilisation;
    }

    public void setCpuUtilisation(int cpuUtilisation) {
        this.cpuUtilisation = cpuUtilisation;
    }

    public int getAvgTurnAroundTime() {
        return avgTurnAroundTime;
    }

    public void setAvgTurnAroundTime(int avgTurnAroundTime) {
        this.avgTurnAroundTime = avgTurnAroundTime;
    }

    public int getAvgReadyQWaitTime() {
        return avgReadyQWaitTime;
    }

    public void setAvgReadyQWaitTime(int avgReadyQWaitTime) {
        this.avgReadyQWaitTime = avgReadyQWaitTime;
    }

    public int getAvgIOWaitTime() {
        return avgIOWaitTime;
    }

    public void setAvgIOWaitTime(int avgIOWaitTime) {
        this.avgIOWaitTime = avgIOWaitTime;
    }

}
