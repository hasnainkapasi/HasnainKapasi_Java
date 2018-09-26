package com.abc.general.ledger.system.file.reader;

import java.util.List;

import com.abc.general.ledger.system.dto.Transaction;

/**
 * Interface to read transaction file.
 * @author Hasnain Kapasi
 */
public interface TransactionFileReader {
    /**
     * This method reads transaction file and returns list of Transaction objects.
     * @param filePath - Transaction file path
     * @return - list of Transaction objects
     */
    List<Transaction> read(String filePath);
}
