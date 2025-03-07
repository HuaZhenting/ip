package huke;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import huke.exception.HukeException;
import huke.task.*;

public class Storage {
    private static String FILE_PATH;

    public Storage(String path) {
        this.FILE_PATH = path;
        try {
            Path filePath = Paths.get(path);
            if (!Files.exists(filePath)) {
                Files.createDirectories(filePath.getParent());
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            System.out.println("Error: Create File Failure");
        }
    }

    public static ArrayList<Task> loadTask() throws HukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    tasks.add(parseTask(line));
                } catch (IllegalArgumentException e) {
                    System.out.println("Line corrupted:" + e.getMessage());
                }
            }
        } catch (HukeException e) {
            throw e;
        } catch (IOException e) {
            System.out.println("Error: Read File Failure");
        }
        return tasks;
    }

    private static Task parseTask(String line) throws HukeException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            throw new HukeException(HukeException.unknownCommandError());
        }
        boolean isDone = parts[1].equals("1");
        switch (parts[0]) {
        case "T":
            return new Todo(parts[2], isDone);
        case "D":
            if (parts.length < 4) {
                throw new HukeException(HukeException.deadlineError());
            }
            return new Deadline(parts[2], isDone, parts[3]);
        case "E":
            if (parts.length < 5) {
                throw new HukeException(HukeException.eventError());
            }
            return new Event(parts[2], isDone, parts[3], parts[4]);
        default:
            throw new HukeException(HukeException.unknownError());
        }
    }

    public static void saveTask(ArrayList<Task> tasks) {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                w.write(task.toFileFormat()); // Custom method to format each task
                w.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error: Save File Failure");
        }
    }
}