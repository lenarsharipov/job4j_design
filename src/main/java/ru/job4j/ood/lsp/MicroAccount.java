package ru.job4j.ood.lsp;

public class MicroAccount extends Account {
    @Override
    public void setMoney(int money) {
        if (money < 0) {
            throw new IllegalArgumentException("Money < 0");
        }

        if (money > 100) {
            throw new IllegalArgumentException("Money > 100");
        }

        this.capital = money;
    }

    @Override
    public double getInterest(double sum, int month, int rate) {
        if (sum < 0 || month > 12 || month < 1 || rate < 0) {
            throw new IllegalArgumentException("Некорректные данные");
        }

        double result = sum;
        for (int i = 0; i < month; i++) {
            result += result * rate / 100;
        }

        return result;
    }

    @Override
    public void getCapital(int sum) {
        this.capital -= sum;
    }
}
