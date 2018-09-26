package com.abc.general.ledger.system.dto;

/**
 * This data object class represents End of day stock account position.
 * @author Hasnain Kapasi
 */
public class EodPosition extends Position {
    private Long delta;

    public EodPosition() {
    }
    
    public EodPosition(Position positionIn, Long deltaIn) {
        this.stockSymbol = positionIn.getStockSymbol();
        this.accountNumber = positionIn.getAccountNumber();
        this.accountType = positionIn.getAccountType();
        this.quantity = positionIn.getQuantity();
        this.delta = deltaIn;
    }
    
    public Long getDelta() {
        return delta;
    }
    public void setDelta(Long delta) {
        this.delta = delta;
    }
}
