
class lab5
{

    private static int getTag(String line, int KB, int wordBlocks)
    {
        
    }

    private static void processLine(String line)
    {
        int tag;

        // calculate and mod tag for cache #1
        tag = getTag(line, 2, 1);

        // calculate and mod tag for cache #2
        // calculate and mod tag for cache #3
        // calculate and mod tag for cache #4
        // calculate and mod tag for cache #5
        // calculate and mod tag for cache #6
        // calculate and mod tag for cache #7
    }

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(args[1]);

        while (scanner.hasNextLine())
        {
            processLine(scanner.nextLine().split(" ")[1]);
        }
    }

    public static void printCache(int cacheNum, int cacheSize,
                                  int associativity, int blockSize,
                                  int hits, double hitRate){
        System.out.println("Cache #" + cacheNum);
        System.out.println("Cache size: " + cacheSize + "B" + "\t\t" + "Associativity: " + associativity + "\t\t" + "Block size: " + blockSize);
        System.out.print("Hits: " + hits + "\t");
        System.out.printf("Hit Rate: %.2f\n", hitRate);
        System.out.print("---------------------------\n");
    }
}

