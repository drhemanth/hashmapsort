/**
 * Created by hemanth on 09/03/2016.
 */

import java.io.*;
import java.util.*;


public class SearchHashMap {

    public static void main(String[] args)
    {
        //file name list here
        String line;
        FileReader reader = null;
        String filePath = "/Users/hemanth/Documents/Cloudwick/WordcountHashmap/pg2600.txt";
        String wordcountfilepath = "/Users/hemanth/Documents/Cloudwick/WordcountHashmap/wordFrequency.csv";
        String topTenfilepath = "/Users/hemanth/Documents/Cloudwick/WordcountHashmap/top10.csv";


        // creating the Hashtag
        HashMap<String, Integer> counthashMap = new HashMap<String, Integer>();

        //counting and writing to a file

        try {
            reader = new FileReader(filePath);   // creating a new file path
            BufferedReader bufferedReader = new BufferedReader(reader); //Reading the file

            try {
                while((line = bufferedReader.readLine()) != null)
                {

                    String[] strArr = line.split(" "); //splitting the array

                    int strLength = strArr.length; // array size
                    for(int i=0; i<strLength; i++)
                    {
                        int frequencyVal = 0;
                        int newFrequencyVal = 0;

                        if(counthashMap.containsKey(strArr[i]))
                        {
                            frequencyVal = (int) counthashMap.get(strArr[i]);
                            newFrequencyVal = frequencyVal+1;
                            counthashMap.put(strArr[i], newFrequencyVal); //writing to the hashmap
                        }else
                            counthashMap.put(strArr[i],1); //writing to the hashmap
                    }
                }





                //write to a csv file
                writetofile(counthashMap, wordcountfilepath);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }




    private static void writetofile(Map<String,Integer> countdata, String filePath)
    {

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(filePath,"UTF-8");
            writer.println("Word, Frequency");

            for(String string : countdata.keySet())
            {
                writer.printf("%s, %d\n",string, countdata.get(string), ",");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            writer.flush();
            writer.close();
        }


    }
}
