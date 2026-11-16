package com.study.enterprise.structural;

import java.util.List;

public class Bridge {
    interface Sender {
        String send(String message);
    }

    static class EmailSender implements Sender {
        @Override
        public String send(String message) {
            return "Email: " + message;
        }
    }

    static class SmsSender implements Sender {
        @Override
        public String send(String message) {
            return "SMS: " + message;
        }
    }

    abstract static class Notification {
        protected final Sender sender;

        Notification(Sender sender) {
            this.sender = sender;
        }

        abstract String notifyUser();
    }

    static class OrderNotification extends Notification {
        OrderNotification(Sender sender) {
            super(sender);
        }

        @Override
        String notifyUser() {
            return sender.send("Заказ оплачен");
        }
    }

    static class WarningNotification extends Notification {
        WarningNotification(Sender sender) {
            super(sender);
        }

        @Override
        String notifyUser() {
            return sender.send("Товар заканчивается");
        }
    }

    public static String demo() {
        // Тип уведомления и способ отправки изменяются независимо.
        return List.of(new OrderNotification(new EmailSender()).notifyUser(),
                new OrderNotification(new SmsSender()).notifyUser(),
                new WarningNotification(new EmailSender()).notifyUser(),
                new WarningNotification(new SmsSender()).notifyUser()).toString();
    }
}
