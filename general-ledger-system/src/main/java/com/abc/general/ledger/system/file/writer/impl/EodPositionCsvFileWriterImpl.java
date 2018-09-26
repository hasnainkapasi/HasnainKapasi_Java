package com.abc.general.ledger.system.file.writer.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.abc.general.ledger.system.dto.EodPosition;
import com.abc.general.ledger.system.file.writer.EodPositionFileWriter;

/**
 * End of day CSV file writer implementation class.
 * @author Hasnain Kapasi
 */
public class EodPositionCsvFileWriterImpl implements EodPositionFileWriter {

    private static final String FILE_HEADER = "Instrument,Account,AccountType,Quantity,Delta";
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";

    @Override
    public boolean write(List<EodPosition> eodPositions, String filePath) {
        FileWriter fileWriter = null;
        try {
            File file = new File(filePath);
            fileWriter = new FileWriter(file, false);
            fileWriter.append(FILE_HEADER.toString());
            fileWriter.append(NEW_LINE_SEPARATOR);
            for (EodPosition eodPosition : eodPositions) {
                fileWriter.append(String.valueOf(eodPosition.getStockSymbol()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(eodPosition.getAccountNumber()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(eodPosition.getAccountType().name()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(eodPosition.getQuantity()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(eodPosition.getDelta()));
                fileWriter.append(NEW_LINE_SEPARATOR);
            }
        } catch (IOException e) {
            throw new RuntimeException(
                    String.format("Encountered error while writing eod positions CSV file %s",
                            filePath), e);
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(
                        String.format("Encountered error while flushing/closing eod positions CSV file %s",
                                filePath), e);
            }
        }
        return true;
    }

}
