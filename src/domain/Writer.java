package domain;

import java.io.*;

public class Writer {
    private File file = new File("file.txt");
    private PrintWriter printWriter = new PrintWriter(new FileWriter(file, true));

    public void write(String value){
        this.printWriter.println(value);
    }

    public void closeWriter(){
        this.printWriter.close();
    }

    public Writer() throws IOException {

    }
}
