package com.abc.general.ledger.system.dto;

/**
 * This data object class represents stock account position.
 * @author Hasnain Kapasi
 */
public class Position implements Cloneable {
    protected String stockSymbol;
    protected String accountNumber;
    protected AccountType accountType;
    protected Long quantity;
    public Position() {
    }
    public Position(String stockSymbolIn, String accountNumberIn, AccountType accountTypeIn, Long quantityIn) {
        this.stockSymbol = stockSymbolIn;
        this.accountNumber = accountNumberIn;
        this.accountType = accountTypeIn;
        this.quantity = quantityIn;
    }
    public String getStockSymbol() {
        return stockSymbol;
    }
    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public AccountType getAccountType() {
        return accountType;
    }
    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
    public Long getQuantity() {
        return quantity;
    }
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
    public String getKey() {
        return stockSymbol + accountType.name();
    }
    
    @Override
    public Object clone() {
        Position cloneObject = new Position(this.stockSymbol,
                this.accountNumber, this.accountType, this.quantity);
        return cloneObject;
    }
}
