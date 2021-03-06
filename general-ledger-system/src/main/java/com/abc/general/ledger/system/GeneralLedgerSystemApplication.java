package com.abc.general.ledger.system;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.abc.general.ledger.system.dto.EodPosition;
import com.abc.general.ledger.system.dto.Position;
import com.abc.general.ledger.system.dto.Transaction;
import com.abc.general.ledger.system.file.reader.PositionFileReader;
import com.abc.general.ledger.system.file.reader.TransactionFileReader;
import com.abc.general.ledger.system.file.reader.impl.PositionCsvFileReaderImpl;
import com.abc.general.ledger.system.file.reader.impl.TransactionJsonFileReaderImpl;
import com.abc.general.ledger.system.file.writer.EodPositionFileWriter;
import com.abc.general.ledger.system.file.writer.impl.EodPositionCsvFileWriterImpl;
import com.abc.general.ledger.system.processor.TransactionProcessor;

/**
 * General Ledger System main class to process stock transactions and generate EOD positions of accounts. 
 * @author Hasnain Kapasi
 */
public class GeneralLedgerSystemApplication 
{
    public static void main(String[] args)
    {
        if (args.length != 3) {
            System.out.println("Invalid number of arguments are passed!!!");
            System.out.println("Execute jar as below:");
            System.out.println("java -jar general-ledger-system.jar "
                    + "<position file path> <transaction file path> <output file folder>");
            return;
        }
        String outputFileDirPath = args[2];
        File outputFileDirectory = new File(String.valueOf(outputFileDirPath));
        if (!outputFileDirectory.exists()) {
            outputFileDirectory.mkdir();
        }
        String positionFilePath = args[0];
        String transactionFilePath = args[1];
        String eodPositionFilePath = outputFileDirPath + "/Output_EndOfDay_Positions.txt";
        PositionFileReader positionFileReader = new PositionCsvFileReaderImpl();
        TransactionFileReader transactionFileReader = new TransactionJsonFileReaderImpl();
        List<Position> positions = positionFileReader.read(positionFilePath);
        List<Transaction> transactions = transactionFileReader.read(transactionFilePath);
        TransactionProcessor processor = new TransactionProcessor();
        List<EodPosition> eodPositions = processor.process(transactions, positions);
        EodPositionFileWriter eodPositionFileWriter = new EodPositionCsvFileWriterImpl();
        eodPositionFileWriter.write(eodPositions, eodPositionFilePath);
        eodPositions = eodPositions.stream()
                .sorted(Comparator.comparing(eodp -> Math.abs(((EodPosition)eodp).getDelta())))
                .collect(Collectors.toList());
        System.out.println(
              String.format("instrument with lowest net transaction volume for the day is %s with delta equal to %d",
                        eodPositions.get(0).getStockSymbol(), eodPositions.get(0).getDelta()));
        System.out.println(
              String.format("instrument with largest net transaction volume for the day is %s with delta equal to %d",
                        eodPositions.get(eodPositions.size() - 1).getStockSymbol(),
                        eodPositions.get(eodPositions.size() - 1).getDelta()));
    }
}
