import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PuttingIntoPractice {
    public static void main(String... args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        //1. Найти все транзакции за 2011 год и отсортировать их по сумме (от меньшей
        //к большей)
        System.out.println("1. Найти все транзакции за 2011 год и отсортировать их по сумме (от меньшего к большей");
        transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .forEach(System.out::println);

        System.out.println("==================================================");
        //2. Вывести список неповторяющихся городов, в которых работают трейдеры.
        System.out.println("2. Вывести список неповторяющихся городов, в которых работают трейдеры");
        transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);

        System.out.println("==================================================");
        //3. Найти всех трейдеров из Кембриджа и отсортировать их по именам.
        System.out.println("3. Найти всех трейдеров из Кембриджа и отсортировать их по именам");
        transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .filter(trader -> "Cambridge".equals(trader.getCity()))
                .sorted(Comparator.comparing(Trader::getName))
                .forEach(System.out::println);

        System.out.println("==================================================");
        //4. Вернуть строку со всеми именами трейдеров, отсортированными в алфавитном
        //порядке.
        System.out.println("4. Вернуть строку со всеми именами трейдеров, отсортированными в алфавитном порядке");
        List<Trader> list = transactions.stream()
                .map(Transaction :: getTrader)
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println(list);

        System.out.println("==================================================");
        //5. Выяснить, существует ли хоть один трейдер из Милана.
        System.out.println("5. Выяснить, существует ли хоть один трейдер из Милана");
        boolean anyTrader = transactions.stream()
                .map(Transaction::getTrader)
                .anyMatch(trader -> "Milan".equals(trader.getCity()));
        if(anyTrader) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

        System.out.println("==================================================");
        //6. Вывести суммы всех транзакций трейдеров из Кембриджа.
        System.out.println("6. Вывести суммы всех транзакций трейдеров из Кембриджа");
        transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .forEach(tr -> System.out.println(tr.getValue()));

        System.out.println("==================================================");
        //6* Вывести сумму всех транзакций трейдеров из Кембриджа.
        System.out.println("6* Вывести сумму всех транзакций трейдеров из Кембриджа");
        int sumValue = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .mapToInt(Transaction::getValue)
                .sum();
        System.out.println(sumValue);

        System.out.println("==================================================");
        //7. Какова максимальная сумма среди всех транзакций?
        System.out.println("7. Какова максимальная сумма среди всех транзакций?");
        transactions.stream()
                .max(Comparator.comparingInt(Transaction::getValue))
                .ifPresent(System.out::println);

        System.out.println("==================================================");
        //8. Найти транзакцию с минимальной суммой.
        System.out.println("8. Найти транзакцию с минимальной суммой");
        transactions.stream()
                .min(Comparator.comparingInt(Transaction::getValue))
                .ifPresent(System.out::println);
    }
}
