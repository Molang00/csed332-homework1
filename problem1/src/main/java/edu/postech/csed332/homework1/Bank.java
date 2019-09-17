package edu.postech.csed332.homework1;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Bank manages a collection of accounts. An account number is assigned
 * incrementally from 100000. E.g., the first account has 100000, the second
 * has 100001, etc. There are also functions for finding specific accounts.
 */
public class Bank {
    /**
     * A list of account instances.
     */
    List<Account> accs;

    /**
     * A map from an ownwer name to accounts for searching.
     */
    Map<String, Account> accsByName;

    /**
     * Create a bank and initialize its collections.
     */
    Bank() {
        // TODO implement this
        accs = new ArrayList<Account>();
        accsByName = new HashMap<String, Account>();
    }

    /**
     * Find an account by a given account number.
     *
     * @param accNum an account number
     * @return the account with number accNum; null if no such account exists
     */
    Account findAccount(int accNum) {
        // TODO implement this
        for (Account acc : accs){
            System.out.println(acc.getAccountNumber() + "  " + accNum);
            if(accNum == acc.getAccountNumber()){
                System.out.println("Account find correct");
                return acc;
            }
        }
        return null;
    }

    /**
     * Find an account by owner's name.
     *
     * @param name owner's name
     * @return a list of accounts by name
     */
    List<Account> findAccountByName(String name) {
        // TODO implement this
        Iterator<String> keys = accsByName.keySet().iterator();
        while(keys.hasNext()){
            String key = keys.next();
            System.out.println("infindAccount");
            System.out.println( String.format("키 : %s, 값 : %s", key, accsByName.get(key)) );
            if(name == key){
                System.out.println("find correct");
                return (List<Account>)accsByName.get(key);
            }
        }
        return null;
    }

    /**
     * Create a new ccount and register it to the bank. The collections
     * accs and accsByName must be updated accordingly.
     *
     * @param name    owner name
     * @param accType HIGH or LOW
     * @param initial initial balance
     * @return the newly created account; null if not possible
     */
    Account createAccount(String name, ACCTYPE accType, double initial) {
        // TODO implement this
        Account newAccount = null;
        System.out.println(accs.size());
        if(accType == ACCTYPE.LOW){
            newAccount = new LowInterestAccount(accs.size()+100000, name, initial);
        }
        else if(accType == ACCTYPE.HIGH){
            newAccount = new HighInterestAccount(accs.size()+100000, name, initial);
        }
        System.out.println("createAccount"+newAccount.getAccountNumber());
        accs.add(newAccount);
        System.out.println(accs.size());
        return newAccount;
    }

    /**
     * Transfer a given amount of money from src account to dst account.
     *
     * @param src    source account
     * @param dst    destination acount
     * @param amount of money
     * @throws IllegalOperationException if not possible
     */
    void transfer(Account src, Account dst, double amount) throws IllegalOperationException {
        // TODO implement this
        try{
            src.withdraw(amount);
            dst.deposit(amount);
        }catch(IllegalOperationException e){
            System.out.println("Illegal Operation Exeption");
        }
    }
}
