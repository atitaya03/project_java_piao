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

    public boolean loginSuccess(String username,String password){
        Account acc = searchAccountByUsername(username);
        if (acc != null & acc.loginSuccess(username,password))
            return true;
        return false;
    }

    public Account searchAccountByUsername(String username){
        for (Account acc: account)
            if (acc.checkAccount(username))
                return acc;
        return null;
    }

    public ArrayList<Account> getAllAccount(){return account;}

}

