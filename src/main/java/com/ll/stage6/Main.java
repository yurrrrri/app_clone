package com.ll.stage6;

public class Main {
    public static void main(String[] args) {
        Container.init();

        new App().run();

        Container.close();
    }
}
