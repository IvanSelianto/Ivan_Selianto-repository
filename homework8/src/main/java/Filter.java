import java.util.List;
import java.util.Set;

public class Filter {
   private Set<Credit> filteredCredits;
  private   Credit creditClient;
  private   List<Credit> credits;
    public  Filter(Set<Credit> filteredCredits, Credit creditClient, List<Credit> credits){
        this.filteredCredits = filteredCredits;
        this.creditClient = creditClient;
        this.credits = credits;
    }

    public  Set<Credit> fullFilter(){
    return filterForTypeOfCollateral(filterForBankCreditor(filterForTarget(defaultFilter(filteredCredits))));
    }




    private Set<Credit> filterForTarget(Set<Credit> filteredCredits) {
        for (int i = 0; i < credits.size() - 1; i++) {

            if (creditClient.getTarget().equals("Любая")) {
                if (credits.get(i).getSum() == creditClient.getSum()
                        && credits.get(i).getTerm() == creditClient.getTerm()
                        && credits.get(i).getTypeOfCollateral().equals(creditClient.getTypeOfCollateral())
                        && credits.get(i).getBankCreditor().equals(creditClient.getBankCreditor())) {
                    filteredCredits.add(credits.get(i));
                }
            }
        }
        return filteredCredits;

    }

    private Set<Credit> filterForTypeOfCollateral(Set<Credit> filteredCredits) {
        for (int i = 0; i < credits.size() - 1; i++) {
            if (creditClient.getTypeOfCollateral().equals("Любой")) {
                if (credits.get(i).getSum() == creditClient.getSum()
                        && credits.get(i).getTerm() == creditClient.getTerm()
                        && credits.get(i).getTarget().equals(creditClient.getTarget())
                        && credits.get(i).getBankCreditor().equals(creditClient.getBankCreditor())) {
                    filteredCredits.add(credits.get(i));
                }
            }
        }
        return filteredCredits;


    }

    private Set<Credit> filterForBankCreditor(Set<Credit> filteredCredits) {
        for (int i = 0; i < credits.size() - 1; i++) {

            if (creditClient.getBankCreditor().equals("Любой")) {
                if (credits.get(i).getSum() == creditClient.getSum()
                        && credits.get(i).getTerm() == creditClient.getTerm()
                        && credits.get(i).getTarget().equals(creditClient.getTarget())
                        && credits.get(i).getTypeOfCollateral().equals(creditClient.getTypeOfCollateral())

                ) {
                    filteredCredits.add(credits.get(i));
                }
            }
        }
        return filteredCredits;
    }

    private Set<Credit> defaultFilter(Set<Credit> filteredCredits ) {
        for (int i = 0; i < credits.size() - 1; i++) {
            if (credits.get(i).getSum() == creditClient.getSum()
                    && credits.get(i).getTerm() == creditClient.getTerm()
                    && credits.get(i).getTarget().equals(creditClient.getTarget())
                    && credits.get(i).getTypeOfCollateral().equals(creditClient.getTypeOfCollateral())
                    && credits.get(i).getBankCreditor().equals(creditClient.getBankCreditor())
            ) {
                filteredCredits.add(credits.get(i));
            }
        }
        return filteredCredits;
    }
}
