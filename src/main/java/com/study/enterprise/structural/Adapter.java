package com.study.enterprise.structural;

import java.math.BigDecimal;

public class Adapter {
    interface PaymentProcessor {
        String payRubles(BigDecimal rubles);
    }

    static class LegacyBank {
        String payKopecks(long kopecks) {
            return "Банк получил " + kopecks + " копеек";
        }
    }

    static class BankAdapter implements PaymentProcessor {
        private final LegacyBank bank;

        BankAdapter(LegacyBank bank) {
            this.bank = bank;
        }

        @Override
        public String payRubles(BigDecimal rubles) {
            if (rubles.signum() <= 0) {
                throw new IllegalArgumentException("Сумма должна быть положительной");
            }
            // Адаптируем несовместимые интерфейс и единицы измерения.
            return bank.payKopecks(rubles.movePointRight(2).longValueExact());
        }
    }

    public static String demo() {
        PaymentProcessor processor = new BankAdapter(new LegacyBank());
        return processor.payRubles(new BigDecimal("125.50"));
    }
}
