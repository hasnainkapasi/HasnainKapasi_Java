package com.abc.general.ledger.system.file.writer;

import java.util.List;

import com.abc.general.ledger.system.dto.EodPosition;

/**
 * Interface to write end of day stock position file.
 * @author Hasnain Kapasi
 */
public interface EodPositionFileWriter {
	/**
	 * This method writes list of EodPosition objects in a file.
	 * @param eodPositions - list of end of day stock positions.
	 * @param filePath - end of day stock postions file path.
	 * @return boolean - true if file is written successfully.
	 */
    boolean write(List<EodPosition> eodPositions, String filePath);
}
