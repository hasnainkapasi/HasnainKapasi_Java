package com.abc.general.ledger.system.processor;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.abc.general.ledger.system.dto.AccountType;
import com.abc.general.ledger.system.dto.EodPosition;
import com.abc.general.ledger.system.dto.Position;
import com.abc.general.ledger.system.dto.Transaction;
import com.abc.general.ledger.system.dto.TransactionType;

public class TransactionProcessorTest {

	private TransactionProcessor processor;

	@Before
	public void init() {
		processor = new TransactionProcessor();
	}

	@Test
	public void testProcess() {
		List<Position> sodPositions = getPositions();
		List<Transaction> transactions = getTransactions();
		List<EodPosition> eodPositions = processor.process(transactions, sodPositions);
		Assert.assertTrue(eodPositions.get(0).getDelta() == 50L);
		Assert.assertTrue(eodPositions.get(0).getQuantity() == 1050L);
		Assert.assertTrue(eodPositions.get(1).getDelta() == -50L);
		Assert.assertTrue(eodPositions.get(1).getQuantity() == -1050L);
		Assert.assertTrue(eodPositions.get(2).getDelta() == -5000L);
		Assert.assertTrue(eodPositions.get(2).getQuantity() == -5500L);
		Assert.assertTrue(eodPositions.get(3).getDelta() == 5000L);
		Assert.assertTrue(eodPositions.get(3).getQuantity() == 5500L);
		Assert.assertTrue(eodPositions.get(4).getDelta() == 150L);
		Assert.assertTrue(eodPositions.get(4).getQuantity() == -850L);
		Assert.assertTrue(eodPositions.get(5).getDelta() == -150L);
		Assert.assertTrue(eodPositions.get(5).getQuantity() == 850L);
		Assert.assertTrue(eodPositions.get(6).getDelta() == 500L);
		Assert.assertTrue(eodPositions.get(6).getQuantity() == 6400L);
		Assert.assertTrue(eodPositions.get(7).getDelta() == -500L);
		Assert.assertTrue(eodPositions.get(7).getQuantity() == -6400L);
	}

	private List<Transaction> getTransactions() {
		List<Transaction> transactions = new ArrayList<>();
		transactions.add(new Transaction("1", "AMAZON", TransactionType.B, 100L));
		transactions.add(new Transaction("2", "AMAZON", TransactionType.S, 200L));
		transactions.add(new Transaction("3", "AMAZON", TransactionType.B, 50L));
		transactions.add(new Transaction("4", "INTEL", TransactionType.B, 5000L));
		transactions.add(new Transaction("5", "APPLE", TransactionType.S, 150L));
		transactions.add(new Transaction("6", "BMW", TransactionType.S, 500L));
		return transactions;
	}

	private List<Position> getPositions() {
		List<Position> positions = new ArrayList<>();
		positions.add(new Position("AMAZON", "101", AccountType.I, 1000L));
		positions.add(new Position("AMAZON", "102", AccountType.E, -1000L));
		positions.add(new Position("INTEL", "103", AccountType.I, -500L));
		positions.add(new Position("INTEL", "104", AccountType.E, 500L));
		positions.add(new Position("APPLE", "105", AccountType.I, -1000L));
		positions.add(new Position("APPLE", "106", AccountType.E, 1000L));
		positions.add(new Position("BMW", "107", AccountType.I, 5900L));
		positions.add(new Position("BMW", "108", AccountType.E, -5900L));
		return positions;
	}
}
