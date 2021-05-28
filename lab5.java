import java.io.*;
import java.util.*;

class lab5
{

    private static void processLine(String line, ArrayList<Cache> caches)
    {
        // calculate and mod tag for all caches
        for (Cache cache : caches)
        {
            cache.update(line);
        }
    }

    private static void printCaches(ArrayList<Cache> caches)
    {
        // print each cache in order
        for (Cache cache : caches)
        {
            cache.printCache();
        }
    }

    private static void initCaches(ArrayList<Cache> caches)
    {
        caches.add(new Cache(1, 1, 2048, 1));
    }

    public static void main(String[] args)
    {
        try
        {
            File file = new File(args[0]);
            Scanner scanner = new Scanner(file);

            ArrayList<Cache> caches = new ArrayList<Cache>();

            initCaches(caches);

            while (scanner.hasNextLine())
            {
                processLine((scanner.nextLine().split("\\s+"))[1], caches);
            }

            printCaches(caches);
        }
        catch (Exception e)
        {
            System.out.println("Fail: " + e);
        }
    }

}

