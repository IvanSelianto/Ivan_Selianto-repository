

public class Credit {
    private double sum;
    private int term;
    private String target;
    private String typeOfCollateral;
    private String bankCreditor;

    public Credit() {
    }

    public Credit(double sum, int term, String target, String typeOfCollateral, String bankCreditor) {
        this.sum = sum;
        this.term = term;
        this.target = target;
        this.typeOfCollateral = typeOfCollateral;
        this.bankCreditor = bankCreditor;

    }


    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getTypeOfCollateral() {
        return typeOfCollateral;
    }

    @Override
    public String toString() {
        return
                "Кредит:\n" +
                        "Сумма=" + sum + "\n" +
                        "Срок=" + term + "\n" +
                        "Цель=" + target + "\n" +
                        "Тип обеспечение=" + typeOfCollateral + "\n" +
                        "Банк кредитор=" + bankCreditor + "\n";
    }

    public void setTypeOfCollateral(String typeOfCollateral) {
        this.typeOfCollateral = typeOfCollateral;
    }

    public String getBankCreditor() {
        return bankCreditor;
    }

    public void setBankCreditor(String bankCreditor) {
        this.bankCreditor = bankCreditor;
    }





}
