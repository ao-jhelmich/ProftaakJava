package domain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Writer {
    private PrintWriter printWriter;
    private File file;

    public void write(String value){
        String[] fileExtension = this.file.getName().split("\\.");

        // Determine if file is txt or json
        if(fileExtension[1].equals("txt")){
            this.printWriter.println(value);
        }else{
            String[] shapeValues = value.split(":");

            // Loop through all values of shape and build json structure
            for(int i = 0; i < shapeValues.length; i++){

                // Add the shape name and skip adding it again to values
                if(i == 0) {
                    this.printWriter.print("{\"" + shapeValues[i] + "\": [");
                    continue;
                }

                if(i != shapeValues.length - 1) {
                    this.printWriter.print("{ \"value\": \"" + shapeValues[i] + "\"},");
                }else{
                    this.printWriter.print("{ \"value\": \"" + shapeValues[i] + "\"}]}\n");
                }
            }
        }

        closeWriter();
    }

    private void closeWriter(){
        this.printWriter.close();
    }

    private void setFile(String fileName) {
        this.file = new File(fileName);
    }

    private void setPrintWriter() throws IOException {
        this.printWriter = new PrintWriter(new FileWriter(file, true));
    }

    public Writer(String name) throws IOException {
        setFile(name);
        setPrintWriter();
    }
}
