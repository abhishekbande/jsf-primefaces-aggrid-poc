package com.poc.jsf.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class LoanData implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String currentInterestRate;
    private String delinquencyDescription;
    private String escrowBalance;
    private Integer activeLoanCount;
    private BigDecimal currUPB;
    private int level;
    private boolean expandable;

    public LoanData() {}

    public LoanData(String currentInterestRate, String delinquencyDescription, 
                    String escrowBalance, Integer activeLoanCount, BigDecimal currUPB,
                    int level, boolean expandable) {
        this.currentInterestRate = currentInterestRate;
        this.delinquencyDescription = delinquencyDescription;
        this.escrowBalance = escrowBalance;
        this.activeLoanCount = activeLoanCount;
        this.currUPB = currUPB;
        this.level = level;
        this.expandable = expandable;
    }

    // Getters and Setters
    public String getCurrentInterestRate() { 
        return currentInterestRate; 
    }
    
    public void setCurrentInterestRate(String currentInterestRate) { 
        this.currentInterestRate = currentInterestRate; 
    }
    
    public String getDelinquencyDescription() { 
        return delinquencyDescription; 
    }
    
    public void setDelinquencyDescription(String delinquencyDescription) { 
        this.delinquencyDescription = delinquencyDescription; 
    }
    
    public String getEscrowBalance() { 
        return escrowBalance; 
    }
    
    public void setEscrowBalance(String escrowBalance) { 
        this.escrowBalance = escrowBalance; 
    }
    
    public Integer getActiveLoanCount() { 
        return activeLoanCount; 
    }
    
    public void setActiveLoanCount(Integer activeLoanCount) { 
        this.activeLoanCount = activeLoanCount; 
    }
    
    public BigDecimal getCurrUPB() { 
        return currUPB; 
    }
    
    public void setCurrUPB(BigDecimal currUPB) { 
        this.currUPB = currUPB; 
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }
}

