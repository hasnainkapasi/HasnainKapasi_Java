package com.abc.general.ledger.system.file.reader;

import java.util.List;

import com.abc.general.ledger.system.dto.Position;

/**
 * Interface to read Stock Positions file
 * @author Hasnain Kapasi
 *
 */
public interface PositionFileReader {
    /**
     * This method reads stock position file and returns list of Postion objects. 
     * @param filePath - position file path
     * @return list of Postion objects
     */
    List<Position> read(String filePath);
}
