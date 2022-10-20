package ku.cs.services;

import ku.cs.models.ReportAccount;
import ku.cs.models.ReportComplaint;
import ku.cs.models.ReportComplaintList;

import java.io.*;
import java.time.LocalDateTime;

public class ReportedComplaintFileDataSource implements DataSource<ReportComplaintList> {
    private String directoryName;
    private String fileName;

    public ReportedComplaintFileDataSource(){
        this("data/csv/","reportedComplaint.csv");
    }
    public ReportedComplaintFileDataSource(String directoryName, String fileName) {
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

    @Override
    public ReportComplaintList readData(){
        ReportComplaintList reportList = new ReportComplaintList();
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
                ReportComplaint report = new ReportComplaint( //reportedComplaintTitle, subject,detail, reporterAccount
                        data[0].trim(),data[1].trim(),
                        data[2].trim(),data[3].trim(),
                        data[4].trim(),data[5].trim()
                );
                reportList.addComplaintReport(report);

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
        return reportList;

    }

    @Override
    public void writeData(ReportComplaintList reportComplaint, boolean a) {
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileWriter writer = null;
        BufferedWriter buffer = null;

        try {
            if (a == true) {
                writer = new FileWriter(file, true);
                buffer = new BufferedWriter(writer);
            } else {
                writer = new FileWriter(file, false);
                buffer = new BufferedWriter(writer);

            }
            for (ReportComplaint report : reportComplaint.getAllReport()){
                String line = report.getReportedComplaintTitle()
                        +","+report.getSubject()+","+report.getDetail()
                        +","+report.getReporterAccount()+","+report.getReportTime()+","+report.getReportDate();
//
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

