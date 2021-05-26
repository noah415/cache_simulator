
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
            processLine(scanner.nextLine());
        }
    }
}
