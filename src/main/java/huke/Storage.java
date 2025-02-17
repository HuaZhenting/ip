package huke;

import huke.task.Deadline;
import huke.task.Event;
import huke.task.Task;
import huke.task.Todo;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String FILE_PATH = "./data/huke.txt";

    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            System.out.println("Looks like you are new here!");
            System.out.println("I've created a new list for you!");
            return tasks;
        }

        System.out.println("Please task a look at your list!");
        try {
            printFileContents(FILE_PATH);
            Scanner s = new Scanner(new File(FILE_PATH));
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] parts = line.split(" \\| ");
                if (parts.length < 3) {
                    continue;
                }
                Task task;
                boolean isDone = parts[1].equals("1");

                switch (parts[0]) {
                case "T":
                    task = new Todo(parts[2]);
                    break;
                case "D":
                    if (parts.length < 4) continue;
                    task = new Deadline(parts[2], parts[3]);
                    break;
                case "E":
                    if (parts.length < 5) continue;
                    task = new Event(parts[2], parts[3], parts[4]);
                    break;
                default:
                    continue;
                }

                if (isDone) {
                    task.setDone();
                }

                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Error loading saved tasks");
        }
        return tasks;
    }

    public static void saveTasks(ArrayList<Task> tasks) {
        try {
            new FileWriter(FILE_PATH, false).close();
            for (Task task : tasks) {
                appendToFile(FILE_PATH, task.toFileString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks.");
        }
    }

    public static void printFileContents(String filepath) throws FileNotFoundException {
        File f = new File(filepath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // Append mode
        fw.write(textToAppend);
        fw.close();
    }
}
