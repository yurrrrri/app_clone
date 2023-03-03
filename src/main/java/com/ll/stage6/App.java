package com.ll.stage6;

import com.ll.stage6.system.controller.SystemController;
import com.ll.stage6.wiseSaying.controller.WiseSayingController;

import java.util.HashMap;
import java.util.Map;

public class App {
     public void run() {
        System.out.println("== 명언 앱 ==");

        SystemController systemController = new SystemController();
        WiseSayingController wiseSayingController = new WiseSayingController();

        while(true){
            System.out.print("명령) ");
            String command = Container.getSc().nextLine().trim();
            Rq rq = new Rq(command);

            switch (rq.getActionCode()){
                case "종료" :
                    systemController.exit();
                    return;
                case "등록" :
                    wiseSayingController.write();
                    break;
                case "목록" :
                    wiseSayingController.list();
                    break;
                case "삭제" :
                    wiseSayingController.remove(rq);
                    break;
            }
        }
    }
}
