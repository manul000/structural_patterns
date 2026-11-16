package com.study.enterprise.structural;

import java.util.ArrayList;
import java.util.List;

public class Facade {
    static class Inventory {
        void reserve(List<String> events) {
            events.add("Товар зарезервирован");
        }
    }

    static class Payment {
        void charge(List<String> events) {
            events.add("Оплата проведена");
        }
    }

    static class Notification {
        void send(List<String> events) {
            events.add("Подтверждение отправлено");
        }
    }

    static class CheckoutFacade {
        private final Inventory inventory = new Inventory();
        private final Payment payment = new Payment();
        private final Notification notification = new Notification();

        List<String> checkout() {
            List<String> events = new ArrayList<>();
            inventory.reserve(events);
            payment.charge(events);
            notification.send(events);
            return List.copyOf(events);
        }
    }

    public static String demo() {
        // Клиент вызывает одну операцию вместо трёх подсистем.
        return new CheckoutFacade().checkout().toString();
    }
}
