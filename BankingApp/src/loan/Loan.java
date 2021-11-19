package loan;


public class Loan {
    public double Amount;
    public double Interest;
    public int LoanID;
    public int YearsTillCompletion;

    public Loan(Double amount, Double interest, Integer loanID, Integer years) {
        this.Amount = amount;
        this.Interest = interest;
        this.LoanID = loanID;
        this.YearsTillCompletion = years;
    }

    //https://www.investopedia.com/terms/e/equated_monthly_installment.asp
    public Double getEMI() {
        return (Amount + (Amount * YearsTillCompletion * Interest)) / (YearsTillCompletion * 12);
    }

    //https://homeguides.sfgate.com/calculate-prepayment-penalty-mortgage-7571.html
    public Double getPrepayment() {
        return ((Amount * Interest) / 12) * 6;
    }
}
