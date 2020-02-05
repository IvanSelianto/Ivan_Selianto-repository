
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public class Writer {


    public void write(String filename, String string) {
        try (FileWriter fileWriter = new FileWriter(filename);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void writeToFileNewRandomCredits(String fileName, int amount) {
        Bank bank = new Bank();
        try (
                //   FileWriter fileWriter = new FileWriter(filename, true);
                //     BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                OutputStream out = new FileOutputStream(new File(fileName), true)
        ) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonGenerator generator = objectMapper.getFactory().createGenerator(out);
            generator.writeRaw('[');

            for (int i = 0; i < amount; i++) {
                generator.writeObject(bank.creditGenerator());
                generator.writeRaw(',');
                //     objectMapper.writeValue(generator, bank.creditGenerator());


            }
            generator.writeRaw(']');
            generator.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
