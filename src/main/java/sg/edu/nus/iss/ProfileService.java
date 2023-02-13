package sg.edu.nus.iss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ProfileService {
    
    public String dirName = "day08data";
    public String fileName = "profile.txt";
    public String dirFileName = dirName + File.separator + fileName;

    public void readFile() throws FileNotFoundException, IOException {

        File file = new File(dirFileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String line = "";
        String [] buffer;
        int occurence = 0;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the word to search: ");
        String scannerInput = scanner.nextLine();

        while((line = br.readLine()) != null) {
            line = line.replace(',', ' ');
            line = line.replace('.', ' ');

            buffer = line.split(" ");

            for(String s : buffer) {
                System.out.println("Word:" + s);
                if (s.equalsIgnoreCase(scannerInput)) {
                    occurence++;
                }
            }
        }

        if (occurence == 0) {
            System.out.printf("Word \"%s\" is not found\n\n", scannerInput);
        } else {
            System.out.printf("Word \"%s\" occurs %d number of times\n\n", scannerInput, occurence);
        }

        br.close();
        fr.close();
    }
}
