package com.ll.stage7;

public class Main {
    public static void main(String[] args) {
        Container.init();

        new App().run();

        Container.close();
    }
}
