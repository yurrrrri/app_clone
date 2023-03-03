package com.ll.stage8;

public class Main {
    public static void main(String[] args) {
        Container.init();

        new App().run();

        Container.close();
    }
}
