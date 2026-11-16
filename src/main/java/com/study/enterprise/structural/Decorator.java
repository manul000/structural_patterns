package com.study.enterprise.structural;

import java.math.BigDecimal;

public class Decorator {
    interface Drink {
        BigDecimal price();

        String description();
    }

    static class Coffee implements Drink {
        @Override
        public BigDecimal price() {
            return new BigDecimal("100");
        }

        @Override
        public String description() {
            return "Кофе";
        }
    }

    abstract static class Additive implements Drink {
        protected final Drink drink;

        Additive(Drink drink) {
            this.drink = drink;
        }
    }

    static class Milk extends Additive {
        Milk(Drink drink) {
            super(drink);
        }

        @Override
        public BigDecimal price() {
            return drink.price().add(new BigDecimal("30"));
        }

        @Override
        public String description() {
            return drink.description() + ", молоко";
        }
    }

    static class Syrup extends Additive {
        Syrup(Drink drink) {
            super(drink);
        }

        @Override
        public BigDecimal price() {
            return drink.price().add(new BigDecimal("20"));
        }

        @Override
        public String description() {
            return drink.description() + ", сироп";
        }
    }

    public static String demo() {
        Drink drink = new Syrup(new Milk(new Coffee()));
        return drink.description() + ": " + drink.price().toPlainString();
    }
}
