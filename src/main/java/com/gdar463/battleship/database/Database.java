package com.gdar463.battleship.database;

import java.nio.file.*;

public class Database {
    public static Database INSTANCE = new Database();
    private static final String STATE_PATH = "state.bin";
    private final Path db;

    Database() {
        db = FileSystems.getDefault().getPath("database");
        try {
            Files.createDirectories(db);
        } catch (FileAlreadyExistsException ignored) {
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for (BucketType value : BucketType.values()) {
            Path state_file = db.resolve(value.getPath()).resolve(STATE_PATH);
            if (Files.exists(state_file)) {
                String state;
                try {
                    state = Files.readString(state_file);
                } catch (Exception e) {
                    throw new RuntimeException();
                }
                if (!state.isEmpty()) {
                    value.setNextId(Integer.parseInt(state));
                }
            }
        }
    }

    public Object get(BucketType bucket, String id) {
        Path file = internalGet(bucket, id);
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
        return clazz.fromCsv(out, Integer.parseInt(id));
    }

    public String createOrUpdate(BucketType bucket, DbObject<?> object, String id) {
        boolean newId = false;
        if (id == null) {
            id = String.valueOf(bucket.getNextId());
            object.setId(Integer.parseInt(id));
            newId = true;
        }
        Path file = internalGet(bucket, id);
        String out = object.toCsv();
        try {
            Files.writeString(file, out, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (newId) {
            bucket.increaseId();
            Path state_file = db.resolve(bucket.getPath()).resolve(STATE_PATH);
            try {
                Files.writeString(state_file, Integer.toString(bucket.getNextId()), StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return id;
    }

    private Path internalGet(BucketType bucket, String id) {
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
