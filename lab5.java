import java.io.*;
import java.util.*;

class lab5
{
    public static int lineNum = 0;

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
        caches.add(new Cache(1, 2, 2048, 2));
        caches.add(new Cache(1, 3, 2048, 4));
        caches.add(new Cache(2, 4, 2048, 1));
        caches.add(new Cache(4, 5, 2048, 1));
        caches.add(new Cache(4, 6, 2048, 4));
        caches.add(new Cache(1, 7, 4096, 1));
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
                lineNum++;
            }

            printCaches(caches);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Fail: " + e);
        }
    }

}

