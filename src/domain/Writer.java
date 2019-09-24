package domain;

import java.io.*;

public class Writer {
    private PrintWriter printWriter;

    public void write(String value){
        this.printWriter.println(value);
    }

    public void closeWriter(){
        this.printWriter.close();
    }

    public Writer() throws IOException {
        File file = new File("file.txt");
        this.printWriter = new PrintWriter(new FileWriter(file, true));
    }
}
