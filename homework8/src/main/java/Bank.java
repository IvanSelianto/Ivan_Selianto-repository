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
        Filter filter = new Filter(filteredCredits, creditClient, credits);

        this.filteredCredits = filter.fullFilter();

        filteredCreditsList = new ArrayList<>(filteredCredits);

        return filteredCreditsList;
    }


}






