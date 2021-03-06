package bank;

import java.util.ArrayList;
import java.util.List;

public class InMemoryTransactions implements Transactions {

    public static final int INITIAL_BALANCE = 0;
    private final List<Transaction> transactions;
    private final SystemDate systemDate;

    public InMemoryTransactions(final SystemDate systemDate) {
        transactions = new ArrayList<Transaction>();
        this.systemDate = systemDate;
    }

    @Override
    public void register(final int amount) {
        transactions.add(new Transaction(amount, systemDate.now()));
    }

    @Override
    public Boolean contains(final Transaction transaction) {
        return transactions.contains(transaction);
    }

    @Override
    public Statement generateStatement() {
        List<StatementLine> statementLines = getStatementLinesInTransactionsOrder();

       return Statement.create(statementLines);
    }

    private List<StatementLine> getStatementLinesInTransactionsOrder() {
        List<StatementLine> statementLines = new ArrayList<>();
        int balance = INITIAL_BALANCE;
        for (final Transaction transaction : transactions) {
            balance += transaction.amount();
            statementLines.add(new StatementLine(transaction, balance));
        }
        return statementLines;
    }
}
