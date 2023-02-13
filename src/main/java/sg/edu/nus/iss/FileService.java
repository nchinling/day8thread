package sg.edu.nus.iss;

import java.io.File;
import java.io.IOException;

public class FileService {
    
    public Boolean createDirectory(String directoryName) {
        File directory = new File(directoryName);
        Boolean directoryCreated = directory.mkdir();
        return directoryCreated;
    }

    public Boolean createFile(String directoryName, String fileName) throws IOException {
        File newFile = new File(directoryName + File.separator + fileName);
        Boolean fileCreated = newFile.createNewFile();
        return fileCreated;
    }

    public void listDirectoryFiles(String directoryName) throws IOException {
        File fileDir = new File(directoryName);

        if (fileDir.exists()) {
            File fileList[] = fileDir.listFiles();

            for (File file : fileList) {
                System.out.println(file.getPath() + file.separator + file.getCanonicalFile().toString());
            }
        } else {
            System.out.println("Directory " + directoryName + "does not exist.");
        }
    }
}
