package com.example.company;

public class Company {
    private String assets;
    private String cashFlow;
    private String currentAssets;
    private String equity;
    private String longTermDuties;
    private String percentageToBePaid;
    private String profitBeforeTax;
    private String retainedEarningsOfPreviousYears;
    private String revenue;
    private String revenueFromSales;
    private String shortTermLiabilities;
    private String tangibleAssets;
    private String undestributedProfits;
    private String workingСapital;

    public Company(String assets,
                   String cashFlow,
                   String currentAssets,
                   String equity,
                   String longTermDuties,
                   String percentageToBePaid,
                   String profitBeforeTax,
                   String retainedEarningsOfPreviousYears,
                   String revenue,
                   String revenueFromSales,
                   String shortTermLiabilities,
                   String tangibleAssets,
                   String undestributedProfits,
                   String workingСapital
    ) {
        this.assets = assets;
        this.cashFlow = cashFlow;
        this.currentAssets = currentAssets;
        this.equity = equity;
        this.longTermDuties = longTermDuties;
        this.percentageToBePaid = percentageToBePaid;
        this.profitBeforeTax = profitBeforeTax;
        this.retainedEarningsOfPreviousYears = retainedEarningsOfPreviousYears;
        this.revenue = revenue;
        this.revenueFromSales = revenueFromSales;
        this.shortTermLiabilities = shortTermLiabilities;
        this.tangibleAssets = tangibleAssets;
        this.undestributedProfits = undestributedProfits;
        this.workingСapital = workingСapital;
    }

    public String getAssets() {
        return assets;
    }
    public String getCashFlow() {
        return cashFlow;
    }
    public String getCurrentAssets() {
        return currentAssets;
    }
    public String getEquity() {
        return equity;
    }
    public String getLongTermDuties() {
        return longTermDuties;
    }
    public String getPercentageToBePaid() {
        return percentageToBePaid;
    }
    public String getProfitBeforeTax() {
        return profitBeforeTax;
    }
    public String getRetainedEarningsOfPreviousYears() {
        return retainedEarningsOfPreviousYears;
    }
    public String getRevenue() {
        return revenue;
    }
    public String getRevenueFromSales() {
        return revenueFromSales;
    }
    public String getShortTermLiabilities() {
        return shortTermLiabilities;
    }
    public String getTangibleAssets() {
        return tangibleAssets;
    }
    public String getUndestributedProfits() {
        return undestributedProfits;
    }
    public String getWorkingСapital() {
        return workingСapital;
    }

    public void printCompany() {
        System.out.println("assets: " + assets);
    }
}
