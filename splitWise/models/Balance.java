package splitWise.models;

public class Balance {
    private int debitorId;
    private int creditorId;
    private double amount;

    public Balance(int debitorId, int creditorId, double amount){
        this.debitorId = debitorId;
        this.creditorId = creditorId;
        this.amount = amount;
    }

    public int getDebitorId(){
        return debitorId;
    }

    public int getCreditorId(){
        return creditorId;
    }

    public double getAmount(){
        return amount;
    }

    public void updateBalance(double amount){
        this.amount += amount;
    }
}
