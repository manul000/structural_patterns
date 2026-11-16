package com.study.enterprise.structural;

public class Proxy {
    interface Image {
        String display();
    }

    static class Loader {
        private int loads;
    }

    static class RealImage implements Image {
        private final String name;

        RealImage(String name, Loader loader) {
            this.name = name;
            loader.loads++;
        }

        @Override
        public String display() {
            return "Показ " + name;
        }
    }

    static class ImageProxy implements Image {
        private final String name;
        private final Loader loader;
        private RealImage image;

        ImageProxy(String name, Loader loader) {
            this.name = name;
            this.loader = loader;
        }

        @Override
        public String display() {
            if (image == null) {
                image = new RealImage(name, loader);
            }
            return image.display();
        }
    }

    public static String demo() {
        Loader loader = new Loader();
        Image proxy = new ImageProxy("каталог.png", loader);
        int before = loader.loads;
        String first = proxy.display();
        proxy.display();
        return "Загрузок до показа: " + before + "; после двух показов: " + loader.loads + "; " + first;
    }
}
