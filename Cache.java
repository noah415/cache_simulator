import java.io.*;
import java.util.*;

class Cache
{
    public int cacheNum;
    public int cacheSize;
    public int associativity;
    public int blockSize;
    public int hitCount = 0;
    public int totCount = 0;

    public Cache(int associativity, int cacheNum, int cacheSize, int blockSize)
    {
        this.associativity = associativity;
        this.cacheSize = cacheSize;
        this.cacheNum = cacheNum;
        this.blockSize = blockSize;

        // create the array of tags based on this info
    }

    public void printCache(){

        double hitRate = (hitCount / (double) totCount);

        System.out.println("Cache #" + cacheNum);
        System.out.println("Cache size: " + cacheSize + "B" + "\t\t" + "Associativity: " + associativity + "\t\t" + "Block size: " + blockSize);
        System.out.print("Hits: " + hitCount + "\t");
        System.out.printf("Hit Rate: %.2f\n", hitRate);
        System.out.print("---------------------------\n");
    }

    public void update(String line)
    {

    }

    private int getTag(String line)
    {
        int address, bitsOfIndex, tag, blockOffset, width, bitshift = 0;

        width = blockSize * 4;
        address = Integer.parseInt(line, 16);

        bitsOfIndex = (int)(Math.log(cacheSize/width) / Math.log(2));
        blockOffset = (int)(blockSize / 2);

        bitshift = bitsOfIndex + blockOffset + 2;

        tag = address >> bitshift;

        return tag;
    }

}
