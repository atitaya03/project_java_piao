package ku.cs.services;

import ku.cs.models.ReportAccount;
import ku.cs.models.ReportAccList;
import java.io.*;
import java.time.LocalDateTime;

public class ReportedAccountFileDataSource implements DataSource<ReportAccList> {
    private String directoryName;
    private String fileName;

    public ReportedAccountFileDataSource(){
        this("data/csv/","reportedAccount.csv");
    }
    public ReportedAccountFileDataSource(String directoryName, String fileName) {
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
    public ReportAccList readData(){
        ReportAccList reportList = new ReportAccList();
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
                ReportAccount report = new ReportAccount( //reportedAccountName, subject,detail, reporterAccount
                        data[0].trim(),data[1].trim(),
                        data[2].trim(),data[3].trim(),data[4].trim(),data[5].trim()
                );
                reportList.addAccReport(report);

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

    public void writeData(ReportAccList repList, boolean a){
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
            for (ReportAccount report : repList.getAllReport()){
                String line = report.getReportedAccountUsername()+","
                        +report.getSubject()+","+report.getDetail()+","
                        +report.getReporterAccount()+","+report.getReportTime() +","
                        +report.getReportDate();
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

