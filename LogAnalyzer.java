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
    
    //Exercise 7.19:
    private LogfileReader dayReader;
    private LogfileReader monthReader;
    
    private int[] dayCounts;
    private int[] monthCounts;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        
        //Exercise 7.19:
        dayCounts = new int[32];
        monthCounts = new int[13];
        
        // Create the reader to obtain the data.
        //Exercise 7.12: Modify the LogAnalyzer Class:
        reader = new LogfileReader("demoLog.txt");
        
        //Exercise 7.19:
        dayReader = new LogfileReader("demoLog.txt");
        monthReader = new LogfileReader("demoLog.txt");
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
     * Exercise 7.19:
     * Analyze the daily access data from the log file.
     */
    public void analyzeDailyData()
    {
        while(dayReader.hasNext()) {
            LogEntry entry = dayReader.next();
            int day = entry.getDay();
            dayCounts[day]++;
        }
    }
    
    /**
     * Exercise 7.19:
     * Analyze the monthly access data from the log file.
     */
    public void analyzeMonthlyData()
    {
        while(monthReader.hasNext()) {
            LogEntry entry = monthReader.next();
            int month = entry.getMonth();
            monthCounts[month]++;
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
        System.out.println("Number of accessers are " + total);
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
        System.out.println("The highest hour: " + highestHour + " with " + highestCount
                            + " visitors.");
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
        System.out.println("The lowest hour: " + lowestHour + " with " + lowestCount 
                            + " visitors.");
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
        System.out.println("The highest two hour period: " + highestHour + " with " 
                            + highestCount + " visitors.");
        return highestHour;
    }
    
    /**
     * Exercise 7.19:
     * Comapers every day to find which has the lowest count
     *
     * @return    The lowest count day
     */
    public int quitestDay()
    {
        //Two variables to compare lowest value to
        int lowestCount = dayCounts[1];
        int lowestDay = 1;

        //For loop to check every index
        for (int index = 1; index < hourCounts.length; index++) {
            //Compares the value of the day to the newest lowest count
            if (dayCounts[index] < lowestCount && dayCounts[index] > 0) {
                //If the new lowest count beats the old one, replace all info with its.
                lowestCount = dayCounts[index];
                lowestDay = index;
            }
        }

        //After the array has been checked, return the lowest day
        System.out.println("The lowest day: " + lowestDay + " with " + lowestCount 
                            + " visitors.");
        return lowestDay;
    }
    
    /**
     * Exercise 7.19:
     * Comapers every day to find which has the highest count
     *
     * @return    The highest count day
     */
    public int busiestDay()
    {
        //Two variables to compare highest value to
        int highestCount = 0;
        int highestDay = 1;

        //For loop to check every index
        for (int index = 1; index < dayCounts.length; index++) {
            //Compares the value of the hour to the newest highest count
            if (dayCounts[index] > highestCount) {
                //If the new highest count beats the old one, replace all info with its.
                highestCount = dayCounts[index];
                highestDay = index;
            }
        }

        //After the array has been checked, return the highest day
        System.out.println("The highest day: " + highestDay + " with " + highestCount
                            + " visitors.");
        return highestDay;
    }
    
    /**
     * Exercise 7.19:
     * Returns the number of monthly accesses recorded in the log file
     *
     * @return    The total of accessesss per month
     */
    public void numberOfMonthlyAccessess()
    {
        //For loop to go over every month starting on the first month
        for (int index = 1; index < monthCounts.length; index++) {
            //Prints out the month and the visitors
            System.out.println("Month " + index + ": " + monthCounts[index] + " accessess");
        }
    }
}
