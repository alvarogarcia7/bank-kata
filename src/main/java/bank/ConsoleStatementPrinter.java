package bank;

public class ConsoleStatementPrinter implements StatementPrinter {

    private final StatementLineFormatter statementLineFormatter;
    private Console console;

    public ConsoleStatementPrinter(final Console console,
                                   final StatementLineFormatter statementLineFormatter) {
        this.statementLineFormatter = statementLineFormatter;
        this.console = console;
    }

    @Override
    public void print(Statement statement) {
        if (statement.isEmpty()) {
            return;
        }

        printHeader();

        for (StatementLine statementLine : statement) {
            printStatementLine(statementLine);
        }
    }

    private void printStatementLine(StatementLine statementLine) {
        console.printLine(statementLineFormatter.format(statementLine));
    }

    private void printHeader() {
        console.printLine("DATE | AMOUNT | BALANCE");
    }
}
