/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author Sam Thornton
 * @version 2017.11.14
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
    public LogAnalyzer(String filename)
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader(filename);
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
     * Return the number of accesses recorded in the log file.
     */
    public int numberOfAccesses()
    {
        int total = 0;
        // Add the value in each element of hourCounts to total
        for(int hour = 0; hour < hourCounts.length; hour++)
        {
            total = total + hourCounts[hour];
        }
        return total;
    }    
    
    /**
     * Return the busiest hour.
     */
    public int busiestHour()
    {
      int busiestHour = 0;
         for (int hour = 0; hour < hourCounts.length; hour++)
         {
             if(hourCounts[hour] > hourCounts[busiestHour])
             {
                 busiestHour = hour;
             }
         }
         return busiestHour;  
    }
    
    /**
     * Return the quietest hour.
     */
    public int quietestHour()
    {
      int quietest = hourCounts[0];
      int quietestHour = 0;
        
        for(int hour = 0; hour < hourCounts.length; hour++) {
            if (quietest > hourCounts[hour])
            {
                quietest = hourCounts[hour];
                quietestHour= hour;
            }
        }
         return quietestHour;  
    }
        
    /**
     * Return the busiest two-hour period.
     */
    public int busiestTwoHour()
    {
      int busiestPeriod = 0;
        int busiestPeriodHour = 0;

        for (int hour = 0; hour < hourCounts.length - 1; hour++) 
        {
            int periodCount = hourCounts[hour] + hourCounts[hour+1];
            if (periodCount > busiestPeriodHour)
            {
                busiestPeriod = hour;
                busiestPeriodHour = periodCount;
            }
        }
        return busiestPeriodHour;
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
}
