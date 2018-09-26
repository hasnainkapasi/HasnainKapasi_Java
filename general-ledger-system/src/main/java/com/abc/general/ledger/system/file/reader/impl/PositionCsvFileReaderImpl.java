package com.abc.general.ledger.system.file.reader.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import com.abc.general.ledger.system.dto.AccountType;
import com.abc.general.ledger.system.dto.Position;
import com.abc.general.ledger.system.file.reader.PositionFileReader;

/**
 * This is a JSON transaction file reader implementation class.
 * @author Hasnain Kapasi
 */
public class PositionCsvFileReaderImpl implements PositionFileReader {

    private static final String CSV_DELIMINITER = ",";

    @Override
    public List<Position> read(String filePath) {
        List<Position> positions = null;
        try{
              File positionFile = new File(filePath);
              InputStream inputFS = new FileInputStream(positionFile);
              BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
              // skip the header of the csv
              positions = br.lines().skip(1).map(positionLine -> createPosition(positionLine))
                      .collect(Collectors.toList());
              br.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(String.format("%s File not found", filePath), e);
            } catch (IOException e) {
                throw new RuntimeException(String.format("I/O exception while reading %s file", filePath), e);
            }
        return positions;
    }

    private Position createPosition(String positionLine) {
        String[] positionFields = positionLine.split(CSV_DELIMINITER);
        Position position = new Position(positionFields[0], positionFields[1], AccountType.valueOf(positionFields[2]), Long.parseLong(positionFields[3]));
        return position;
    }

}
