import java.util.*;

public class ConsoleMenu {
    private static final String FORM_CREDIT = "1";
    private static final String EXIT_COMMAND = "exit";
    private static final String FIND_CREDIT = "find";


    public void showConsoleMenu() {
        Bank bank = new Bank();
        Writer writer = new Writer();
        Credit creditClient = new Credit();
        System.out.println("Вас привествует контора по подбору кредитов!");
        System.out.println("1. Сформировать потребительский кредит\n2. Выйти из программы");
        try (
                Scanner scanner = new Scanner(System.in)) {
            boolean isExit = false;
            while (!isExit) {
                try {

                    String string = scanner.next();
                    if (string.equals(EXIT_COMMAND) || string.equals("2")) {
                        isExit = true;
                        continue;
                    }
                    if (string.equals(FIND_CREDIT)) {
                        System.out.println("Вот предложения кредитов, возможно подходящих тебе:");
                        System.out.println(creditClient);
                        List<Credit> credits = bank.findCredit(creditClient);
                        for (int i = 0; i < credits.size(); i++) {
                            System.out.println((i + 1) + ". " + credits.get(i));

                        }
                        System.out.println("1.Выбрать кредит" +
                                "\n2.Сортировать список кредитов");

                        int consoleInt = scanner.nextInt();
                        if (consoleInt == 1) {
                            System.out.println("Выбери интересующий тебя кредит");

                            int creditNumber = scanner.nextInt() - 1;
                            creditClient = bank.getFilteredCreditsList().get(creditNumber);
                            System.out.println(creditClient);
                            System.out.println("Договор об оформлении кредита был сохранен в отдельном файле в корневой файловой директории.");
                            String contract = "Договор\n" +
                                    "1. Кредитор " + creditClient.getBankCreditor() + " предоставляет Заемщику кредит в сумме " + creditClient.getSum() + " рублей на срок " + creditClient.getTerm() + " на " + creditClient.getTarget()
                                    + "\n Типом обеспечения в данном случае является " + creditClient.getTypeOfCollateral();
                            writer.write("Contract.txt", contract);
                            break;


                        } else {

                            System.out.println("Сортировать по:" +
                                    "\n1. По сумме" +
                                    "\n2. По сроку кредита" +
                                    "\n3. По банку кредитору(по алфавиту)" +
                                    "\n4. По типу обеспечению(по алфавиту) " +
                                    "\n5. По цели(по алфавиту)");
                            String sortString = scanner.next();
                            switch (sortString) {
                                case "1":

                                    Collections.sort(bank.getFilteredCreditsList(), new SortBySum());
                                    for (int i = 0; i < bank.getFilteredCreditsList().size(); i++) {
                                        System.out.println((i + 1) + ". " + bank.getFilteredCreditsList().get(i));

                                    }

                                    break;
                                case "2":
                                    Collections.sort(bank.getFilteredCreditsList(), new SortByTerm());
                                    for (int i = 0; i < bank.getFilteredCreditsList().size(); i++) {
                                        System.out.println((i + 1) + ". " + bank.getFilteredCreditsList().get(i));

                                    }

                                    break;
                                case "3":
                                    Collections.sort(bank.getFilteredCreditsList(), new SortByBankCreditor());
                                    for (int i = 0; i < bank.getFilteredCreditsList().size(); i++) {
                                        System.out.println((i + 1) + ". " + bank.getFilteredCreditsList().get(i));

                                    }
                                    break;
                                case "4":
                                    Collections.sort(bank.getFilteredCreditsList(), new SortByTypeOfCollateral());
                                    for (int i = 0; i < bank.getFilteredCreditsList().size(); i++) {
                                        System.out.println((i + 1) + ". " + bank.getFilteredCreditsList().get(i));

                                    }
                                    break;
                                case "5":
                                    Collections.sort(bank.getFilteredCreditsList(), new SortByTarget());
                                    for (int i = 0; i < bank.getFilteredCreditsList().size(); i++) {
                                        System.out.println((i + 1) + ". " + bank.getFilteredCreditsList().get(i));

                                    }
                                    break;
                            }
                            System.out.println("Выбери интересующий тебя кредит");

                            int creditNumber = scanner.nextInt() - 1;
                            creditClient = bank.getFilteredCreditsList().get(creditNumber);
                            System.out.println(creditClient);
                            System.out.println("Договор об оформлении кредита был сохранен в отдельном файле в корневой файловой директории.");
                        }


                    }
                    if (string.equals(FORM_CREDIT) || string.equals("+")) {
                        System.out.println("Выбери один из интересущих тебя параметров." +
                                " Для подбора кредита по назначенным тобой параметрам напиши \"find\". Для добавления еще одного параметра напиши \"+ \"" +
                                "\n1.Сумма кредита;" +
                                "\n2.Срок кредита" +
                                "\n3.Банк кредитор" +
                                "\n4.Тип обеспечения" +
                                "\n5.Цель");
                        string = scanner.next();
                    }

                    switch (string) {
                        case "1":
                            System.out.println("Введи сумму кредита");
                            creditClient.setSum(scanner.nextDouble());

                            break;
                        case "2":
                            System.out.println("Введи желаемый срок выплаты кредита(месяцы)");

                            creditClient.setTerm((int) Math.ceil(scanner.nextDouble() / 6) * 6);


                            break;
                        case "3":
                            System.out.println("Выбери банк кредитор:" +
                                    "\n1.Любой" +
                                    "\n2.ВТБ" +
                                    "\n3.БеларусБанк" +
                                    "\n4.Москва-Минск банк" +
                                    "\n5.Тиньков банк");
                            switch (scanner.next()) {
                                case "1":
                                    creditClient.setBankCreditor("Любой");
                                    break;
                                case "2":
                                    creditClient.setBankCreditor("ВТБ");
                                    break;
                                case "3":
                                    creditClient.setBankCreditor("БеларусБанк");
                                    break;
                                case "4":
                                    creditClient.setBankCreditor("Москва-Минск банк");
                                    break;
                                case "5":
                                    creditClient.setBankCreditor("Тиньков банк");
                                    break;


                            }


                            break;
                        case "4":
                            System.out.println("Выбери тип обеспечения:" +
                                    "\n1.Любой" +
                                    "\n2.Гарантийный депозит" +
                                    "\n3.Залог" +
                                    "\n4.Поручительство");
                            switch (scanner.next()) {
                                case "1":
                                    creditClient.setTypeOfCollateral("Любой");
                                    break;
                                case "2":
                                    creditClient.setTypeOfCollateral("Гарантийный депозит");
                                    break;
                                case "3":
                                    creditClient.setTypeOfCollateral("Залог");
                                    break;
                                case "4":
                                    creditClient.setTypeOfCollateral("Поручительство");
                                    break;
                            }


                            break;
                        case "5":
                            System.out.println("Какова цель кредита?" +
                                    "\n1.Любая" +
                                    "\n2.На лечение" +
                                    "\n3.На образование" +
                                    "\n4.На отдых" +
                                    "\n5.На покупку товара");
                            switch (scanner.next()) {
                                case "1":
                                    creditClient.setTarget("Любой");
                                    break;
                                case "2":
                                    creditClient.setTarget("На лечение");
                                    break;
                                case "3":
                                    creditClient.setTarget("На образование");
                                    break;
                                case "4":
                                    creditClient.setTarget("На отдых");
                                    break;
                                case "5":
                                    creditClient.setTarget("На покупку товара");
                                    break;


                            }

                            break;

                    }


                } catch (InputMismatchException e) {
                    System.out.println("Ты ввел фигню, давай по новой");
                    scanner.nextLine();
                }

            }
        }
        bank.setCredit(creditClient);
    }
}
