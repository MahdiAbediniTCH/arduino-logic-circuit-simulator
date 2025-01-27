package controller;

import javafx.scene.control.Tab;
import model.Circ;
import model.Table;
import model.VarLed;
import org.group24.truthtable.App;
import org.group24.truthtable.ApplicationRunner;

import java.io.*;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CircController {
    public void evaluateVarLed(VarLed varLed) {
        varLed.setEvaluated(true);
        if (varLed.getOutputIn() == -1) {
            if (varLed.IS_LED)
                for (int i = 0; i < Circ.STATE_NUM; i++)
                    varLed.setStatus(Circ.getDefaultLed(), i);
            else
                for (int i = 0; i < Circ.STATE_NUM; i++)
                    varLed.setStatus(Circ.getDefaultVariable(), i);
        } else {
            Table table = Table.getFromAllTables(varLed.getOutputIn());
            int[] pseudoStatus = table.getOutPutStatus(varLed);
            ArrayList<Integer> varNums = table.getVarNumbers();
            ArrayList<Integer> switchNums = table.getSwitchNumbers();
            for(int i : varNums) {
                evaluateVarLed(Objects.requireNonNull(Circ.getVar(i)));
            }
            for(int i =0; i < Circ.STATE_NUM; i ++) {
                //TODO : see which element in the array should I check and fill the status for varLed
                int sum = 0;
                
            }
        }
    }

    public void prepareLEDs() {
        for (int i = 0; i < Circ.LEDNUM; i++)
            evaluateVarLed(Objects.requireNonNull(Circ.getLed(i)));
    }

    public void writeInFile() {
//        prepareLEDs();
        // TODO : generate proper hex string for data.bin
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 64; i++) {
            sb.append("1234");  // Sample data
        }
        String str = sb.toString();
        byte[] byteArray = str.getBytes();
        URL url = App.class.getResource("serial/data.bin");
        assert url != null;
        File dat = new File(url.getFile());
        try (FileOutputStream fos = new FileOutputStream(dat)) {
            fos.write(byteArray);
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendThroughSerial() throws IOException, InterruptedException {
        URL url = App.class.getResource("serial/serial_write.py");
        URL dir_url = App.class.getResource("serial");
        ProcessBuilder processBuilder = new ProcessBuilder("python", url.getFile().substring(1));
        File dir = new File(dir_url.getFile());
        processBuilder.directory(dir);
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        int exitCode = process.waitFor();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        // Test function. remove if not needed:
        CircController c = new CircController();
        c.writeInFile();
        c.sendThroughSerial();
    }

    private static void TEST_DATA() { // Test function. remove if not needed
        URL url = App.class.getResource("serial/data.bin");
        String filePath = url.getFile();

        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            byte[] data = fileInputStream.readAllBytes();
            String fileContent = new String(data);
            System.out.println(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
