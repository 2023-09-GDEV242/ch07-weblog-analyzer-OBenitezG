/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version    2016.02.29
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        //Exercise 7.12: Modify the LogAnalyzer Class:
        reader = new LogfileReader("demoLog.txt");
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }

    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }

    /**
     * Exercise 7.14:
     * Returns the number of accesses recorded in the log file
     *
     * @return    The total of all accessesss
     */
    public int numberOfAccessess()
    {
        //inital total starts at zero
        int total = 0;

        //For loops the array and adds every index into the total.
        for(int hour = 0; hour < hourCounts.length; hour++) {
            total += hourCounts[hour];
        }

        //Returns that total to the user.
        return total;
    }

    /**
     * Exercise 7.15:
     * Comapers every hour to find which has the highest count
     *
     * @return    The highest count hour
     */
    public int busiestHour()
    {
        //Two variables to compare highest value to
        int highestCount = 0;
        int highestHour = 0;

        //For loop to check every index
        for (int index = 0; index < hourCounts.length; index++) {
            //Compares the value of the hour to the newest highest count
            if (hourCounts[index] > highestCount) {
                //If the new highest count beats the old one, replace all info with its.
                highestCount = hourCounts[index];
                highestHour = index;
            }
        }

        //After the array has been checked, return the highest hour
        return highestHour;
    }

    /**
     * Exercise 7.16:
     * Comapers every hour to find which has the lowest count
     *
     * @return    The lowest count hour
     */
    public int quitestHour()
    {
        //Two variables to compare lowest value to
        int lowestCount = hourCounts[0];
        int lowestHour = 0;

        //For loop to check every index
        for (int index = 0; index < hourCounts.length; index++) {
            //Compares the value of the hour to the newest lowest count
            if (hourCounts[index] < lowestCount) {
                //If the new lowest count beats the old one, replace all info with its.
                lowestCount = hourCounts[index];
                lowestHour = index;
            }
        }

        //After the array has been checked, return the lowest hour
        return lowestHour;
    }

    /**
     * Exercise 7.18:     
     * Comapers every two hours to find which has the highest copmbined count
     * 
     * @return    The highest count of two block period
     */
    public int busiestTwoHour()
    {
        //Two variables to compare highest value to
        int highestCount = hourCounts[0] + hourCounts[1];
        int highestHour = 0;
        
        //For loop to check every index, then add 2 to skip 1
        for (int index = 0; index < hourCounts.length; index += 2) {
            //current index value is added with its next index to compare its score
            int checkCount = hourCounts[index] + hourCounts[index + 1];
                //Compares the value of the hour to the newest highest count
                if (checkCount > highestCount) {
                    //If the new highest count beats the old one, replace all info with its.
                    highestCount = checkCount;
                    highestHour = index;
                }
        }
        
        //After the array has been checked, return the highest hour
        return highestHour;
    }

}
