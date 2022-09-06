package ku.cs.models;

import java.util.ArrayList;

public class AccountList {
    private ArrayList<Account> account;
    public AccountList(){
        account = new ArrayList<>();
    }

    public void addAccount(Account user){
        account.add(user);
    }

    public ArrayList<Account> getAllAccount(){return account;}

}

