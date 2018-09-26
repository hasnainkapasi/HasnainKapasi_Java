package com.abc.general.ledger.system.file.reader.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.abc.general.ledger.system.dto.Transaction;
import com.abc.general.ledger.system.file.reader.TransactionFileReader;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class reads transactions JSON file.
 * @author Hasnain Kapasi
 */
public class TransactionJsonFileReaderImpl implements TransactionFileReader {

    @Override
    public List<Transaction> read(String filePath) {
        List<Transaction> transactions = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            transactions = mapper.readValue(new File(filePath), new TypeReference<List<Transaction>>() {
            });
        } catch (FileNotFoundException e) {
            throw new RuntimeException(String.format("%s File not found", filePath), e);
        } catch (IOException e) {
            throw new RuntimeException(String.format("I/O exception while reading %s file", filePath), e);
        }
        return transactions;
    }
}
