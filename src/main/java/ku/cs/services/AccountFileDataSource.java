package ku.cs.services;

import ku.cs.models.AccountList;
import ku.cs.models.Account;
import ku.cs.models.ComplaintList;

import java.io.*;


public class AccountFileDataSource implements DataSource<AccountList> {
    private String directoryName;
    private String fileName;

    public AccountFileDataSource(){
        this("data/csv/","userData.csv");
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
                Account acc = new Account( //displayname,username,password,role,organization,isBaned,loginAttempt,imagePath,loginTime,unBannedRequest
                        data[0].trim(),data[1].trim(),data[2].trim(),data[3].trim(),data[4].trim(),Boolean.parseBoolean(data[5].trim()),Integer.parseInt(data[6].trim()),data[7].trim(),(data[8].trim()),(data[9].trim())
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
                    String line = ac.getDisplayname()+","+ac.getUsername() + "," + ac.getPassword() + "," + ac.getRole()+ "," + ac.getOrganization()+","+ac.isBanned()+","+ac.getLoginAttempt()+"," +ac.getImagePath()+","+ ac.getLoginTime()+","+ ac.getUnbanRequest();
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


