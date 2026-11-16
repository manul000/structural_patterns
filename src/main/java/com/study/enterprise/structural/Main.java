package com.study.enterprise.structural;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        Map<String, Supplier<String>> examples = new LinkedHashMap<>();
        examples.put("Adapter", Adapter::demo);
        examples.put("Composite", Composite::demo);
        examples.put("Proxy", Proxy::demo);
        examples.put("Flyweight", Flyweight::demo);
        examples.put("Facade", Facade::demo);
        examples.put("Bridge", Bridge::demo);
        examples.put("Decorator", Decorator::demo);
        if (args.length == 1 && args[0].equals("--list")) {
            examples.keySet().forEach(System.out::println);
            return;
        }
        if (args.length > 1 || (args.length == 1 && !examples.containsKey(args[0]))) {
            System.err.println("Укажите имя примера из списка: " + examples.keySet());
            System.exit(2);
        }
        if (args.length == 1) {
            System.out.println(args[0] + ": " + examples.get(args[0]).get());
        } else {
            examples.forEach((name, example) -> System.out.println(name + ": " + example.get()));
        }
    }
}
