package com.gdar463.battleship.database;

import java.nio.file.*;
import java.util.*;

public class Database {
    public static Database INSTANCE = new Database();
    private static final String STATE_PATH = "state.bin";
    private final Map<BucketType, ArrayList<DbEntry<?>>> cache;
    private final Path db;

    Database() {
        db = FileSystems.getDefault().getPath("database");
        try {
            Files.createDirectories(db);
        } catch (FileAlreadyExistsException ignored) {
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        cache = new HashMap<>();
        for (BucketType value : BucketType.values()) {
            cache.put(value, new ArrayList<>());
            ArrayList<DbEntry<?>> curr = cache.get(value);
            Path state_file = db.resolve(value.getPath()).resolve(STATE_PATH);
            if (Files.exists(state_file)) {
                List<String> state;
                try {
                    state = Files.readAllLines(state_file);
                } catch (Exception e) {
                    throw new RuntimeException();
                }
                curr.ensureCapacity(Integer.parseInt(state.getFirst()));
                state.removeFirst();
                for (String line : state) {
                    DbObject<?> obj = (DbObject<?>) _get(value, line);
                    curr.add(new DbEntry<>(line, obj));
                }
            }
        }
    }

    public Object get(BucketType bucket, String id) {
        ArrayList<DbEntry<?>> curr = cache.get(bucket);
        for (DbEntry<?> entry : curr) {
            if (Objects.equals(entry.getId(), id)) {
                return entry.getObj();
            }
        }
        return null;
    }

    private Object _get(BucketType bucket, String id) {
        Path file = getCsvPath(bucket, id);
        if (!Files.exists(file)) {
            return null;
        }
        DbObject<?> clazz = bucket.getClazz().cast(DbObject.class);
        String out;
        try {
            out = Files.readString(file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return clazz.fromCsv(out, id);
    }

    public String createOrUpdate(BucketType bucket, DbObject<?> object, String id) {
        Path file = getCsvPath(bucket, id);
        String out = object.toCsv();
        try {
            Files.writeString(file, out, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ArrayList<DbEntry<?>> curr = cache.get(bucket);
        Path state_file = db.resolve(bucket.getPath()).resolve(STATE_PATH);
        StringBuilder sb = new StringBuilder();
        sb.append(curr.size()).append("\n");
        for (DbEntry<?> obj : curr) {
            sb.append(obj.getId()).append("\n");
        }
        try {
            Files.writeString(state_file, sb.toString(), StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    private Path getCsvPath(BucketType bucket, String id) {
        Path cur_db = db.resolve(bucket.getPath());
        if (!Files.exists(cur_db)) {
            try {
                Files.createDirectories(cur_db);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return cur_db.resolve(id + ".csv");
    }
}
