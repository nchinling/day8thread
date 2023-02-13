package sg.edu.nus.iss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IdiomService implements Runnable {

    final Socket socket;

    private String fullFilename;
    private List<String> idioms;
    private String oneRandomIdiom;

    public IdiomService(Socket s, String fullFilename){
        this.socket = s;
        this.fullFilename = fullFilename;
    }

    public List<String> readFile(String fullPathFileName) throws IOException {

        // open a file for reading line-by-line
        File file = new File(fullPathFileName);
        BufferedReader br = new BufferedReader(new FileReader(file));

        // store the read idiom from file
        List<String> idiomList = new ArrayList<String>();

        // for reading each line in the file
        String line = "";

        while ((line = br.readLine()) != null) {
            idiomList.add(line);
        }

        br.close();

        return idiomList;
    }

    public String randomIdiom(List<String> idioms) {
        Random random = new Random();

        String idiom = "";
        if (idioms != null || idioms.size() > 0) {
            Integer idiomIndex = random.nextInt(idioms.size());
            idiom = idioms.get(idiomIndex);
        } else {
            idiom = "No idiom found!!!";
        }

        return idiom;
    }

    public void showAllIdioms(List<String> idioms) {
        // idioms.forEach(i -> System.out.println(i));
        idioms.forEach(System.out::println);
    }

    @Override 
    public void run(){
        try{
            InputStream is = socket.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            DataInputStream dis = new DataInputStream(bis);

            OutputStream os = socket.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(os);
            DataOutputStream dos = new DataOutputStream(bos);

            idioms = this.readFile(fullFileName);
            String msgRecv = "";
            while (!msgRecv.equalsIgnoreCase("quit"){
                msgRecv.dis.readUTF();

                if(msgRecv.equalsIgnoreCase("get-cookie")){
                    oneRandomIdiom = this.randomIdiom(idioms);
                }

                dos.writeUTF(oneRandomIdiom);
                dos.flush;
            }

            dos.close();
            bos.close();
            os.close();
            dis.close();
            bis.close();
            is.close();


        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
