package com.study.enterprise.structural;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Composite {
    interface CatalogNode {
        BigDecimal totalPrice();
    }

    record Product(String name, BigDecimal price) implements CatalogNode {
        @Override
        public BigDecimal totalPrice() {
            return price;
        }
    }

    static class Category implements CatalogNode {
        private final List<CatalogNode> children = new ArrayList<>();

        void add(CatalogNode node) {
            if (node == this) {
                throw new IllegalArgumentException("Категория не может содержать себя");
            }
            children.add(node);
        }

        @Override
        public BigDecimal totalPrice() {
            return children.stream().map(CatalogNode::totalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        }
    }

    public static String demo() {
        Category accessories = new Category();
        accessories.add(new Product("Клавиатура", new BigDecimal("3000")));
        accessories.add(new Product("Мышь", new BigDecimal("1500")));
        Category catalog = new Category();
        catalog.add(accessories);
        catalog.add(new Product("Монитор", new BigDecimal("12000")));
        return "Сумма вложенного каталога: " + catalog.totalPrice().toPlainString();
    }
}
