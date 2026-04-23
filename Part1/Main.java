import java.io.*;
import java.util.*;

public class Main {

    static String[] table;
    static int size = 50;
    static int collisions = 0;



    public static void main(String[] args)
    {
        table = new String[size];
        getPatients("patient.txt");

        Scanner scan = new Scanner(System.in);
        int select = 0;

        while(select != 3)
        {
            System.out.println("\n ==== Choose an option ====");
            System.out.println("    1. Show Hash Table");
            System.out.println("    2. Rehash (Resize)");
            System.out.println("    3. Quit");
            
            select = scan.nextInt();
            if(select == 3) break;

            switch(select)
            {
                case 1:
                    showTable();
                    break;
                case 2: 
                    rehash();
                    break;
                default:
                    System.out.println("Please choose a valid option");
            }
        }
        scan.close();
    }




    static int hashing(String key, int size)
    {
        int sum = 0;
        for (int i = 0; i<key.length(); i++)
            sum += key.charAt(i);
        return sum % size;
    }

    static void insert(String key)
    {
        int i = hashing(key, size);
        if(table[i] == null)
            table[i] = key;
        else
        {
            System.out.println("Collision at position [" + i + "] for value [" + key + "]");
            collisions++;
            int newPos = (i+1) % size;
            while(table[newPos] != null && newPos != i)
            {
                newPos = (newPos+1) % size;
            }
            if(table[newPos] == null)
                table[newPos] = key;
            else
                System.out.println("Hash Table is full, cannot find space to insert [" + key + "]");
        }
    }

    static void getPatients(String file)
    {
        try
        {
            Scanner scan = new Scanner(new File(file));
            while(scan.hasNextLine())
            {
                String line = scan.nextLine().trim();
                if(!line.isEmpty())
                {
                    String[] sections = line.split(",");
                    if(sections.length > 0)
                    {
                        String lastName = sections[0].trim();
                        insert(lastName);
                    }
                }
            }
            scan.close();
            System.out.println("Total collisions: " + collisions);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("[ERROR] " + file + ".txt not found");
        }
    }

    static void showTable()
    {
        System.out.println("==== HASH TABLE ====");
        for(int i = 0; i<size; i++)
            System.out.println("Position " + i + ": " + (table[i] != null ? table[i] : "empty"));
    }

    static void rehash()
    {
        System.out.println("==== RE-HASHING ====");
        System.out.println("Increasing size from " + size +  " to " + (size*2));

        String[] tempTable = table;
        int temp = size;
        size *= 2;
        table = new String[size];
        collisions = 0;

        for(int i =0; i<temp; i++)
        {
            if(tempTable[i] != null)
                insert(tempTable[i]);
        }

        System.out.println("Collisions post re-hash: " + collisions);

    }
    

}