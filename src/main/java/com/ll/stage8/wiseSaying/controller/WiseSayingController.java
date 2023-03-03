package com.ll.stage8.wiseSaying.controller;

import com.ll.stage8.Container;
import com.ll.stage8.Rq;
import com.ll.stage8.wiseSaying.entity.WiseSaying;

import java.util.ArrayList;
import java.util.List;

public class WiseSayingController {
    private long lastId;
    private final List<WiseSaying> wiseSayings;

    public WiseSayingController(){
        lastId = 0;
        wiseSayings = new ArrayList<>();
    }

    private WiseSaying findById(int id) {
        for(WiseSaying wiseSaying : wiseSayings){
            if(wiseSaying.getId() == id){
                return wiseSaying;
            }
        }
        return null;
    }

    public void write() {
        long id = lastId + 1;
        System.out.print("명언 : ");
        String content = Container.getSc().nextLine().trim();
        System.out.print("작가 : ");
        String authorName = Container.getSc().nextLine().trim();

        WiseSaying wiseSaying = new WiseSaying(id, content, authorName);
        wiseSayings.add(wiseSaying);

        System.out.printf("%d번 명언이 등록되었습니다.\n", id);
        lastId = id;
    }

    public void list() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("-".repeat(30));

        for(int i=wiseSayings.size()-1; i>=0; i--){
            WiseSaying ws = wiseSayings.get(i);
            System.out.printf("%d / %s / %s\n", ws.getId(), ws.getAuthorName(), ws.getContent());
        }
    }

    public void remove(Rq rq) {
        int id = rq.getIntParam("id", -1);

        if(id == -1){
            System.out.println("id(정수)를 입력해주세요.");
            return;
        }

        WiseSaying wiseSaying = findById(id);

        if(wiseSaying == null){
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            return;
        }

        wiseSayings.remove(wiseSaying);
        System.out.printf("%d번 명언이 삭제되었습니다.\n", id);
    }

    public void modify(Rq rq) {
        int id = rq.getIntParam("id", -1);

        if(id == -1){
            System.out.println("id(정수)를 입력해주세요.");
            return;
        }

        WiseSaying wiseSaying = findById(id);

        if(wiseSaying == null){
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
            return;
        }

        System.out.printf("명언(기존) : %s", wiseSaying.getContent());
        System.out.print("명언 : ");
        String content = Container.getSc().nextLine().trim();

        System.out.printf("작가(기존) : %s", wiseSaying.getAuthorName());
        System.out.print("작가 : ");
        String authorName = Container.getSc().nextLine().trim();

        wiseSaying.setContent(content);
        wiseSaying.setAuthorName(authorName);

        System.out.printf("%d번 명언이 수정되었습니다.\n", id);
    }
}
