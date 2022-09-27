package ku.cs.services;

import ku.cs.models.Account;
import ku.cs.models.AccountList;
import ku.cs.models.Complaint;
import ku.cs.models.ComplaintList;

import java.io.*;

public class ComplaintFileDataSource implements DataSource<ComplaintList> {
    private String directoryName;
    private String fileName;

    public ComplaintFileDataSource(){
        this("executablefiles+csv/csv/","complaintData.csv");
    }
    public ComplaintFileDataSource(String directoryName, String fileName) {
        this.directoryName = directoryName;
        this.fileName = fileName;
        checkFileIsExisted();
    }

    private void checkFileIsExisted(){
        File file = new File(directoryName);
        if ( !file.exists()) {
            file.mkdirs();

        }

        String filePath = directoryName + File.separator + fileName;
        file = new File(filePath);
        if (!file.exists()){
            try{
                file.createNewFile();
            } catch(IOException e){
                throw new RuntimeException(e);
            }
        }
    }

    public ComplaintList readData(){
        ComplaintList complaintList = new ComplaintList();
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader reader = null;
        BufferedReader buffer = null;

        try {
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);

            String line = "";
            while((line = buffer.readLine()) != null){
                String[] data = line.split(",");
                Complaint c = new Complaint(
                        data[0].trim(), data[1].trim(),(data[2].trim()),(data[3].trim())
                );
                complaintList.addComplaint(c);

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e){
            throw new RuntimeException(e);
        } finally {
            try {
                buffer.close();
                reader.close();

            } catch (IOException e){
                throw new RuntimeException(e);
            }
        }
        return complaintList;

    }

    @Override
    public void writeData(ComplaintList complaintList) {
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileWriter writer = null;
        BufferedWriter buffer = null;

        try {
            writer = new FileWriter(file,true);
            buffer = new BufferedWriter(writer);

            for (Complaint c : complaintList.getAllAccount()){
                String line = c.toCSV();
                buffer.append(line);
                buffer.newLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                buffer.close();
                writer.close();
            } catch (IOException e){
                throw new RuntimeException(e);
            }



        }





    }
    }



