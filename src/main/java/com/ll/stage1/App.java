package com.ll.stage1;

import java.util.Scanner;

public class App {
    private final Scanner sc;

    public App(Scanner sc) {
        this.sc = sc;
    }

    public void run() {
        System.out.println("== 명언 앱 ==");
        while(true){
            System.out.print("명령) ");
            String command = sc.nextLine().trim();
            if(command.equals("종료")){
                break;
            }
        }
    }
}
