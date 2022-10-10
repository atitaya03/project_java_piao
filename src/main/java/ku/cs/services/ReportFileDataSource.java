package ku.cs.services;

import ku.cs.models.Report;
import ku.cs.models.ReportList;
import java.io.*;

public class ReportFileDataSource implements DataSource<ReportList> {
    private String directoryName;
    private String fileName;

    public ReportFileDataSource(){
        this("executablefiles_csv/csv/","reportData.csv");
    }
    public ReportFileDataSource(String directoryName, String fileName) {
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
    public ReportList readData(){
        ReportList reportList = new ReportList();
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
                Report report = new Report( //reporterUsername, reportedUsername,detail, title, category
                        data[0].trim(),data[1].trim(),data[2].trim(),data[3].trim(),data[4].trim()
                );
                reportList.addReport(report);

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

    public void writeData(ReportList repList,boolean a){
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
            for (Report report : repList.getAllReport()){
                String line = report.getReporterUsername()+","+report.getReportedUsername()+","+report.getDetail()+","+report.getDetail()+","+report.getCategory();
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

