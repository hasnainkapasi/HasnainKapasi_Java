package com.abc.general.ledger.system.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This data object class represents stock transaction.
 * @author Hasnain Kapasi
 */
public class Transaction {
    @JsonProperty(value = "TransactionId")
    private String id;
    @JsonProperty(value = "Instrument")
    private String stockSymbol;
    @JsonProperty(value = "TransactionType")
    private TransactionType type;
    @JsonProperty(value = "TransactionQuantity")
    private Long quantity;
    public Transaction() {
    }
    public Transaction(String idIn, String stockSymbolIn, TransactionType typeIn, Long quantityIn) {
        this.id = idIn;
        this.stockSymbol = stockSymbolIn;
        this.type = typeIn;
        this.quantity = quantityIn;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getStockSymbol() {
        return stockSymbol;
    }
    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }
    public TransactionType getType() {
        return type;
    }
    public void setType(TransactionType type) {
        this.type = type;
    }
    public Long getQuantity() {
        return quantity;
    }
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
