class Cache
{
    public int cacheNum;
    public int cacheSize;
    public int assiciativity;
    public int blockSize;
    public int hitCount = 0;
    public int totCount = 0;

    public Cache(int assiciativity, int cacheNum, int cacheSize, int blockSize)
    {
        this.assiciativity = assiciativity;
        this.cacheSize = cacheSize;
        this.cacheNum = cacheNum;
        this.blockSize = blockSize;
    }

    public void printCache(int cacheNum, int cacheSize,
                                  int associativity, int blockSize,
                                  int hits, double hitRate){
        System.out.println("Cache #" + cacheNum);
        System.out.println("Cache size: " + cacheSize + "B" + "\t\t" + "Associativity: " + associativity + "\t\t" + "Block size: " + blockSize);
        System.out.print("Hits: " + hits + "\t");
        System.out.printf("Hit Rate: %.2f\n", hitRate);
        System.out.print("---------------------------\n");
    }
}
