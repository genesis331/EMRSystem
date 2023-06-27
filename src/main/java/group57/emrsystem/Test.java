package group57.emrsystem;

import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Test {
    public static List<Diagnosis> AdminReadCSV(String fileName) {
        String delimiter = ",";
        BufferedReader bReader = null;
        File file = new File(fileName);
        List<Diagnosis> data = new ArrayList<Diagnosis>();
        try {
            String line = "";
            bReader = new BufferedReader(new FileReader(file));
            bReader.readLine();
            while ((line = bReader.readLine()) != null) {
                String[] tokens = line.split(delimiter);
                if (tokens.length > 0) {
                    Diagnosis diagnosis = new Diagnosis(tokens[0], tokens[1], tokens[2], tokens[3]);
                    data.add(diagnosis);
                }
            }
        } catch (FileNotFoundException e) {
            try {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bReader != null)
                    bReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    public static void main(String[] args) throws IOException {
//        String filename = "diagnosis.csv";
//        List<Diagnosis> data = AdminReadCSV(Objects.requireNonNull(DemoController.class.getResource(filename)).getPath());
//        List<String> stringArrays = new ArrayList<>();
//
//        for (Diagnosis diagnosis : data) {
//            String diagnosisString = diagnosis.getId() + "," + diagnosis.getDate() + "," + diagnosis.getName() + "," + diagnosis.getDiagnosedSickness() + "\n";
//            stringArrays.add(diagnosisString);
//        }
//
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Objects.requireNonNull(DemoController.class.getResource(filename)).getPath()))) {
//            for (int i = 1; i < stringArrays.size(); i++) {
//                writer.write(stringArrays.get(i));
//            }
//            System.out.println("Data has been written to the file.");
//        } catch (IOException e) {
//            System.err.println("An error occurred while writing to the file: " + e.getMessage());
//        }
    }
}
