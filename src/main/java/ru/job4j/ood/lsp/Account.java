package ru.job4j.ood.lsp;

public class Account {
    protected int capital;

    public void setMoney(int money) {
        if (money < 0) {
            throw new IllegalArgumentException("Money < 0");
        }
        this.capital = money;
    }

    public double getInterest(double sum, int month, int rate) {
        if (sum < 0 || month > 12 || month < 1 || rate < 0) {
            throw new IllegalArgumentException("Arguments illegal");
        }

        double result = sum;
        for (int i = 0; i < month; i++) {
            result += result * rate / 100;
        }

        if (sum >= 1000) {
            result += 100;
        }

        return result;
    }

    public void getCapital(int sum) {
        if (sum > capital || sum % 100 == 0) {
            throw new IllegalArgumentException("incorrect amount");
        }
        this.capital -= sum;
    }
}

