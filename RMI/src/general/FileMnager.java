package general;

import java.io.*;

public class FileMnager {
    private static final String FILE_NAME = "users.data";

    public static void save(RegUser regUser) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(regUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static RegUser load() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (RegUser) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // File not found or corrupted — start fresh
            return new RegUser();
        }
    }
}
