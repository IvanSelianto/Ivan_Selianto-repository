import java.util.*;

public class Bank {
    private static final Random RANDOM = new Random();
    private List<Credit> credits = new ArrayList();
    private Set<Credit> filteredCredits = new HashSet<>();
    private List<Credit> filteredCreditsList = new ArrayList<>();
    private Credit credit;

    public List<Credit> getFilteredCreditsList() {
        return filteredCreditsList;
    }

    public void setFilteredCreditsList(List<Credit> filteredCreditsList) {
        this.filteredCreditsList = filteredCreditsList;
    }

    public List<Credit> getCredits() {
        return credits;
    }

    public void setCredits(List<Credit> credits) {
        this.credits = credits;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }


    public Credit getCredit() {
        return credit;
    }

    public Set<Credit> getFilteredCredits() {
        return filteredCredits;
    }

    public void setFilteredCredits(Set<Credit> filteredCredits) {
        this.filteredCredits = filteredCredits;
    }

    public Credit creditGenerator() {
        Credit credit = new Credit();
        int target = RANDOM.nextInt(6 - 1) + 1;

        switch (target) {
            case 1:
                credit.setTarget("Любая");
                break;
            case 2:
                credit.setTarget("На лечение");
                break;
            case 3:
                credit.setTarget("На образование");
                break;
            case 4:
                credit.setTarget("На отдых");
                break;
            case 5:
                credit.setTarget("На покупку товара");
                break;
        }

        int typeOfCollateral = RANDOM.nextInt((5 - 1)) + 1;
        switch (typeOfCollateral) {
            case 1:
                credit.setTypeOfCollateral("Любой");
                break;
            case 2:
                credit.setTypeOfCollateral("Гарантийный депозит");
                break;
            case 3:
                credit.setTypeOfCollateral("Залог");
                break;
            case 4:
                credit.setTypeOfCollateral("Поручительство");
                break;
        }

        int bankCreditor = RANDOM.nextInt(6 - 1) + 1;
        switch (bankCreditor) {
            case 1:
                credit.setBankCreditor("Любой");
                break;
            case 2:
                credit.setBankCreditor("ВТБ");
                break;
            case 3:
                credit.setBankCreditor("БеларусБанк");
                break;
            case 4:
                credit.setBankCreditor("Москва-Минск банк");
                break;
            case 5:
                credit.setBankCreditor("Тиньков банк");
                break;
        }

        int sum = RANDOM.nextInt(100001 - 10000) + 10000;
        credit.setSum(Math.floor(sum / 10000) * 10000);

        int term = RANDOM.nextInt(20 - 1) + 1;
        credit.setTerm(6 * term);


        return credit;
    }

    public List<Credit> findCredit(Credit creditClient) {

        if (creditClient.getTarget() == null) {
            creditClient.setTarget("Любая");
        }
        if (creditClient.getTypeOfCollateral() == null) {
            creditClient.setTypeOfCollateral("Любой");
        }
        if (creditClient.getBankCreditor() == null) {
            creditClient.setBankCreditor("Любой");
        }

        JSONReader jsonReader = new JSONReader("Credits.json");
        credits = jsonReader.read();


        for (int i = 0; i < credits.size() - 1; i++) {
            if (credits.get(i).getSum() == creditClient.getSum()
                    && credits.get(i).getTerm() == creditClient.getTerm()
                    && credits.get(i).getTarget().equals(creditClient.getTarget())
                    && credits.get(i).getTypeOfCollateral().equals(creditClient.getTypeOfCollateral())
                    && credits.get(i).getBankCreditor().equals(creditClient.getBankCreditor())
            ) {
                filteredCredits.add(credits.get(i));
            }

            if (creditClient.getTarget().equals("Любая")) {
               if (credits.get(i).getSum() == creditClient.getSum()
                       && credits.get(i).getTerm() == creditClient.getTerm()
                       && credits.get(i).getTypeOfCollateral().equals(creditClient.getTypeOfCollateral())
                       && credits.get(i).getBankCreditor().equals(creditClient.getBankCreditor())) {
                    filteredCredits.add(credits.get(i));
                }
          }
            if (creditClient.getTypeOfCollateral().equals("Любой")) {
                if (credits.get(i).getSum() == creditClient.getSum()
                        && credits.get(i).getTerm() == creditClient.getTerm()
                        && credits.get(i).getTarget().equals(creditClient.getTarget())
                        && credits.get(i).getBankCreditor().equals(creditClient.getBankCreditor())) {
                    filteredCredits.add(credits.get(i));
                }
            }
            if (creditClient.getBankCreditor().equals("Любой")) {
                if (credits.get(i).getSum() == creditClient.getSum()
                        && credits.get(i).getTerm() == creditClient.getTerm()
                        && credits.get(i).getTarget().equals(creditClient.getTarget())
                        && credits.get(i).getTypeOfCollateral().equals(creditClient.getTypeOfCollateral())

                ) {
                    filteredCredits.add(credits.get(i));
                }
            }

//            if (creditClient.getTypeOfCollateral().equals("Любая")) {
//                if (credits.get(i).getSum() == creditClient.getSum()
//                        && credits.get(i).getTerm() == creditClient.getTerm()
//                        || credits.get(i).getTerm() + 6 == creditClient.getTerm()
//                        || credits.get(i).getTerm() - 6 == creditClient.getTerm()
//                        && credits.get(i).getTarget().equals(creditClient.getTarget())
//                        || credits.get(i).getTarget().equals("Любая")
//                        && credits.get(i).getBankCreditor().equals(creditClient.getBankCreditor())
//                        || credits.get(i).getBankCreditor().equals("Любой")) {
//                    filteredCredits.add(credits.get(i));
//                }
//
//            }
//            if (creditClient.getBankCreditor().equals("Любая")) {
//                if (credits.get(i).getSum() == creditClient.getSum()
//                        && credits.get(i).getTerm() == creditClient.getTerm()
//                        || credits.get(i).getTerm() + 6 == creditClient.getTerm()
//                        || credits.get(i).getTerm() - 6 == creditClient.getTerm()
//                        && credits.get(i).getTypeOfCollateral().equals(creditClient.getTypeOfCollateral())
//                        || credits.get(i).getTypeOfCollateral().equals("Любой")
//                        && credits.get(i).getTarget().equals(creditClient.getTarget())
//                        || credits.get(i).getTarget().equals("Любая")) {
//                    filteredCredits.add(credits.get(i));
//                }
//
//            }
//
//            if (credits.get(i).getSum() == creditClient.getSum()
//                    && credits.get(i).getTerm() == creditClient.getTerm()
//                    || credits.get(i).getTerm() + 6 == creditClient.getTerm()
//                    || credits.get(i).getTerm() - 6 == creditClient.getTerm()
//                    && credits.get(i).getTarget().equals(creditClient.getTarget())
//                    || credits.get(i).getTarget().equals("Любая")
//                    && credits.get(i).getTypeOfCollateral().equals(creditClient.getTypeOfCollateral())
//                    || credits.get(i).getTypeOfCollateral().equals("Любой")
//                    && credits.get(i).getBankCreditor().equals(creditClient.getBankCreditor())
//                    || credits.get(i).getBankCreditor().equals("Любой")) {
//                filteredCredits.add(credits.get(i));
//            }

        }
        filteredCreditsList = new ArrayList<>(filteredCredits);

        return filteredCreditsList;
    }


}





