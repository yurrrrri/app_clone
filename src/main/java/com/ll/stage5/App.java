package com.ll.stage5;

import com.ll.stage5.system.controller.SystemController;
import com.ll.stage5.wiseSaying.controller.WiseSayingController;

public class App {
     public void run() {
        System.out.println("== 명언 앱 ==");

        SystemController systemController = new SystemController();
        WiseSayingController wiseSayingController = new WiseSayingController();

        while(true){
            System.out.print("명령) ");
            String command = Container.getSc().nextLine().trim();

            if(command.equals("종료")){
                systemController.exit();
                break;
            } else if(command.equals("등록")){
                wiseSayingController.write();
            } else if(command.equals("목록")){
                wiseSayingController.list();
            }
        }
    }
}
