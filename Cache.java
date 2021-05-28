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
    public int cacheLen = 0;
    public Entry[] list1;
    public Entry[] list2;
    public Entry[] list3;
    public Entry[] list4;

    public Cache(int associativity, int cacheNum, int cacheSize, int blockSize)
    {
        this.associativity = associativity;
        this.cacheSize = cacheSize;
        this.cacheNum = cacheNum;
        this.blockSize = blockSize;

        // create the array of tags based on this info
        if (associativity == 1)
        {
            this.cacheLen = (cacheSize / (blockSize * 4));
            list1 = new Entry[this.cacheLen];
        }
        else if (associativity == 2)
        {
            this.cacheLen = (cacheSize / (blockSize * 4) / 2);
            list1 = new Entry[this.cacheLen];
            list2 = new Entry[this.cacheLen];
        }
        else if (associativity == 4)
        {
            this.cacheLen = (cacheSize / (blockSize * 4) / 4);
            list1 = new Entry[this.cacheLen];
            list2 = new Entry[this.cacheLen];
            list3 = new Entry[this.cacheLen];
            list4 = new Entry[this.cacheLen];
        }
    }

    public void printCache(){

        double hitRate = (hitCount / (double) totCount) * 100;

        System.out.println("Cache #" + cacheNum);
        System.out.println("Cache size: " + cacheSize + "B" + "\t\t" + "Associativity: " + associativity + "\t\t" + "Block size: " + blockSize);
        System.out.print("Hits: " + hitCount + "\t");
        System.out.printf("Hit Rate: %.2f%%\n", hitRate);
        System.out.print("---------------------------\n");
    }

    private int findIndexSmallest(int[] array)
    {
        int index = 0;
        int min = array[index];

        for (int i = 0; i < array.length; i++)
        {
            if (min > array[i])
            {
                min = array[i];
                index = i;
            }
        }
        return index;
    }

    private void replaceLRU(int tag, int index)
    {
        if (this.associativity == 2)
        {
            if (list1[index % this.cacheLen].lineNum < list2[index % this.cacheLen].lineNum)
                list1[index % this.cacheLen] = new Entry(tag, lab5.lineNum);
            else
                list2[index % this.cacheLen] = new Entry(tag, lab5.lineNum);
        }
        if (this.associativity == 4)
        {
            int[] lineNums = {
                    list1[index % this.cacheLen].lineNum, list2[index % this.cacheLen].lineNum,
                    list3[index % this.cacheLen].lineNum, list4[index % this.cacheLen].lineNum
                    };

            int smallestIndex = findIndexSmallest(lineNums);

            if (smallestIndex == 0)
                list1[index % this.cacheLen] = new Entry(tag, lab5.lineNum);
            else if (smallestIndex == 1)
                list2[index % this.cacheLen] = new Entry(tag, lab5.lineNum);
            else if (smallestIndex == 2)
                list3[index % this.cacheLen] = new Entry(tag, lab5.lineNum);
            else if (smallestIndex == 3)
                list4[index % this.cacheLen] = new Entry(tag, lab5.lineNum);
        }
    }

    public void update(String line)
    {
        int tag = getTag(line);
        int index = getIndex(line);

        if (this.associativity == 1)
        {
            if (list1[index % this.cacheLen] == null)
                list1[index % this.cacheLen] = new Entry(tag, lab5.lineNum);
            else if (list1[index % this.cacheLen].tag == tag)
                this.hitCount++;
            else
                list1[index % this.cacheLen].tag = tag;

            this.totCount++;
        }
        else if (this.associativity == 2)
        {
            if (list1[index % this.cacheLen] != null && list1[index % this.cacheLen].tag == tag)
            {
                list1[index % this.cacheLen].lineNum = lab5.lineNum;
                this.hitCount++;
            }
            else if (list2[index % this.cacheLen] != null && list2[index % this.cacheLen].tag == tag)
            {
                list2[index % this.cacheLen].lineNum = lab5.lineNum;
                this.hitCount++;
            }
            else if (list1[index % this.cacheLen] == null)
                list1[index % this.cacheLen] = new Entry(tag, lab5.lineNum);
            else if (list2[index % this.cacheLen] == null)
                list2[index % this.cacheLen] = new Entry(tag, lab5.lineNum);
            else
                replaceLRU(tag, index);

            this.totCount++;
        }

        else if (this.associativity == 4)
        {
            if (list1[index % this.cacheLen] != null && list1[index % this.cacheLen].tag == tag)
            {
                list1[index % this.cacheLen].lineNum = lab5.lineNum;
                this.hitCount++;
            }
            else if (list2[index % this.cacheLen] != null && list2[index % this.cacheLen].tag == tag)
            {
                list2[index % this.cacheLen].lineNum = lab5.lineNum;
                this.hitCount++;
            }
            else if (list3[index % this.cacheLen] != null && list3[index % this.cacheLen].tag == tag)
            {
                list3[index % this.cacheLen].lineNum = lab5.lineNum;
                this.hitCount++;
            }
            else if (list4[index % this.cacheLen] != null && list4[index % this.cacheLen].tag == tag)
            {
                list4[index % this.cacheLen].lineNum = lab5.lineNum;
                this.hitCount++;
            }
            else if (list1[index % this.cacheLen] == null)
                list1[index % this.cacheLen] = new Entry(tag, lab5.lineNum);
            else if (list2[index % this.cacheLen] == null)
                list2[index % this.cacheLen] = new Entry(tag, lab5.lineNum);
            else if (list3[index % this.cacheLen] == null)
                list3[index % this.cacheLen] = new Entry(tag, lab5.lineNum);
            else if (list4[index % this.cacheLen] == null)
                list4[index % this.cacheLen] = new Entry(tag, lab5.lineNum);
            else
                replaceLRU(tag, index);

            this.totCount++;
        }
    }

    private int getTag(String line)
    {
        int address, bitsOfIndex, tag, blockOffset, width, bitshift = 0;

        width = blockSize * 4;
        address = Integer.parseInt(line, 16);

        bitsOfIndex = (int)(Math.log(this.cacheLen/width) / Math.log(2));
        blockOffset = (int)(this.blockSize / 2);

        bitshift = bitsOfIndex + blockOffset + 2;

        tag = address >> bitshift;

        return tag;
    }

    private int getIndex(String line)
    {
        int address, bitsOfIndex, blockOffset, width, bitshift = 0, mask = 0, index;
        //System.out.println(line);

        width = blockSize * 4;
        address = Integer.parseInt(line, 16);

        bitsOfIndex = (int)(Math.log(this.cacheLen) / Math.log(2));
        blockOffset = (int)(this.blockSize / 2);

        index = address >> (blockOffset + 2);

        mask = ~(~0 << bitsOfIndex);

        index = index & mask;
        //System.out.println("mask: " + mask + " index: " + index);

        return index;
    }

}
