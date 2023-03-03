package com.ll.stage10.wiseSaying.table;

import com.ll.stage10.Util;
import com.ll.stage10.wiseSaying.entity.WiseSaying;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WiseSayingTable {

    private long lastId;
    private final List<WiseSaying> wiseSayings;

    public WiseSayingTable(){
        lastId = 0;
        wiseSayings = new ArrayList<>();
    }

    public static String getTableLastIdFilePath() {
        return getTableDirPath() + "/last_id.txt";
    }

    private static String getTableDirPath() {
        return "prod_data/wise_saying";
    }

    private static String getTableDataFilePath(long id) {
        return getTableDirPath() + "/" + id + ".json";
    }

    public List<WiseSaying> findAll() {
        List<Long> fileIds = getFileIds();

        return fileIds
                .stream()
                .map(id -> findById(id))
                .collect(Collectors.toList());
    }

    private List<Long> getFileIds() {
        String path = getTableDirPath();
        List<String> fileNames = Util.file.getFileNamesFromDir(path);

        return fileNames
                .stream()
                .filter(fileName -> !fileName.equals("last_id.txt"))
                .filter(fileName -> !fileName.equals("data.json"))
                .filter(fileName -> fileName.endsWith(".json"))
                .map(fileName -> fileName.replace(".json", ""))
                .mapToLong(Long::parseLong)
                .boxed()
                .collect(Collectors.toList());
    }

    public WiseSaying findById(long id) {
        for(WiseSaying wiseSaying : findAll()){
            if(wiseSaying.getId() == id){
                return wiseSaying;
            }
        }
        return null;
    }

    public long getLastId() {
        return Util.file.readNoFromFile(getTableLastIdFilePath(), 0);
    }

    public long save(WiseSaying wiseSaying) {
        Util.file.mkdir(getTableDirPath());
        String body = wiseSaying.toJson();
        String filePath = getTableDataFilePath(wiseSaying.getId());
        Util.file.saveToFile(filePath, body);
        
        saveLastId(wiseSaying.getId());

        return wiseSaying.getId();
    }

    private void saveLastId(long id) {
        Util.file.saveNoToFile(getTableLastIdFilePath(), id);
    }

    public void remove(WiseSaying wiseSaying) {
        String path = getTableDataFilePath(wiseSaying.getId());
        Util.file.deleteFile(path);
    }

    public void modify(WiseSaying wiseSaying, String content, String authorName) {
        wiseSaying.setContent(content);
        wiseSaying.setAuthorName(authorName);
    }
}
