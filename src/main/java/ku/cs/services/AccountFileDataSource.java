package ku.cs.services;

import ku.cs.models.AccountList;
import ku.cs.models.Account;
import java.io.*;


public class AccountFileDataSource implements DataSource<AccountList> {
    private String directoryName;
    private String fileName;

    public AccountFileDataSource(){
        this("executablefiles_csv/csv/","userData.csv");
    }
    public AccountFileDataSource(String directoryName, String fileName) {
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

    public AccountList readData(){
        AccountList accountList = new AccountList();
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
                Account acc = new Account(
                        data[0].trim(), data[1].trim(),(data[2].trim())
                );
                accountList.addAccount(acc);

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
            return accountList;

        }

    public void writeData(AccountList acList,boolean a){
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
                for (Account ac : acList.getAllAccount()){
                    String line = ac.getUsername() + "," + ac.getPassword() + "," + ac.getRole();
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


