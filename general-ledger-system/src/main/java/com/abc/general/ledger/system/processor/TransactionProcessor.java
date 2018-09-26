package com.abc.general.ledger.system.processor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.abc.general.ledger.system.dto.AccountType;
import com.abc.general.ledger.system.dto.EodPosition;
import com.abc.general.ledger.system.dto.Position;
import com.abc.general.ledger.system.dto.Transaction;
import com.abc.general.ledger.system.dto.TransactionType;

/**
 * This class processes stock transactions and update the internal and external stock account position.
 * @author Hasnain Kapasi
 */
public class TransactionProcessor {
    /**
     * This method processes stock transactions and updates respective stock accounts.
     * It returns end of day stock positions.
     * @param transactions - List of stock transactions.
     * @param positions - List of start of day position of stocks.
     * @return list of end of day stock positions.
     */
    public List<EodPosition> process(List<Transaction> transactions, List<Position> positions) {
        Map<String, Position> positionMap = positions.stream()
                .collect(Collectors.toMap(
                        Position::getKey, position -> (Position) position.clone()));
        transactions.forEach(transaction -> updatePosition(transaction, positionMap));
        List<EodPosition> eodPositions = positions.stream()
                .map(position -> createEodPostion(position, positionMap.get(position.getKey())))
                .collect(Collectors.toList());
        return eodPositions;
    }

    /**
     * This method creates end of day position object based on start of day position and latest stock position.
     * @param sodPosition -  Start of day stock position object.
     * @param latestPosition - Latest position of stock.
     * @return EodPosition - end of day stock position object.
     */
    private EodPosition createEodPostion(Position sodPosition, Position latestPosition) {
        Long delta = latestPosition.getQuantity() - sodPosition.getQuantity();
        return new EodPosition(latestPosition, delta);
    }

    /**
     * This method updates the position of stocks in internal and external stock account based on transaction type.
     * @param transaction - Stock transaction object.
     * @param positionMap - Map of stock positions.
     */
    private void updatePosition(Transaction transaction, Map<String, Position> positionMap) {
        Position externalAccountPosition = positionMap.get(transaction.getStockSymbol() + AccountType.E.name());
        if (externalAccountPosition == null) {
            throw new RuntimeException(
                    String.format("External account position for stock symbol %s does not exists",
                            transaction.getStockSymbol()));
        }
        Position internalAccountPosition = positionMap.get(transaction.getStockSymbol() + AccountType.I.name());
        if (internalAccountPosition == null) {
            throw new RuntimeException(
                    String.format("Internal account position for stock symbol %s does not exists",
                            transaction.getStockSymbol()));
        }
        if (transaction.getType() == TransactionType.B) {
            //For AccountType=E, Quantity=Quantity + TransactionQuantity
            externalAccountPosition.setQuantity(externalAccountPosition.getQuantity() + transaction.getQuantity());
            //For AccountType=I, Quantity=Quantity - TransactionQuantity
            internalAccountPosition.setQuantity(internalAccountPosition.getQuantity() - transaction.getQuantity());
        } else if (transaction.getType() == TransactionType.S) {
            //For AccountType=E, Quantity=Quantity - TransactionQuantity
            externalAccountPosition.setQuantity(externalAccountPosition.getQuantity() - transaction.getQuantity());
            //For AccountType=I, Quantity=Quantity + TransactionQuantity
            internalAccountPosition.setQuantity(internalAccountPosition.getQuantity() + transaction.getQuantity());
        }
    }
}
