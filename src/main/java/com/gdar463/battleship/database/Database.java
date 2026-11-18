package com.gdar463.battleship.database;

import com.gdar463.battleship.model.CsvSerializable;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class Database {
    public static Database INSTANCE = new Database();
    private final Path db;

    Database() {
        db = FileSystems.getDefault().getPath("database");
        try {
            Files.createDirectories(db);
        } catch (FileAlreadyExistsException ignored) {
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Object get(BucketType bucket, String id) {
        Path file = internalGet(bucket, id);
        if (!Files.exists(file)) {
            return null;
        }
        CsvSerializable<?> clazz = bucket.getClazz().cast(CsvSerializable.class);
        String out;
        try {
            out = Files.readString(file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return clazz.fromCsv(out);
    }

    public void create(BucketType bucket, CsvSerializable<?> object) throws FileAlreadyExistsException {
        String id = String.valueOf(bucket.getCount());
        Path file = internalGet(bucket, id);
        if (Files.exists(file)) {
            throw new FileAlreadyExistsException(file.toString());
        }
        String out = object.toCsv();
        try {
            Files.writeString(file, out);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        bucket.increaseCount();
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
