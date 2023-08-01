package FinalTask;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FileHandler implements Model {
    private List<Note> notes = new ArrayList<>();

    @Override
    public void addNote(Note note) {
        notes.add(note);
    }

    @Override
    public List<Note> getNotes() {
        return notes;
    }

    @Override
    public List<Note> getNotesForDay(String day) {
        List<Note> result = new ArrayList<>();
        for (Note note : notes) {
            if (note.getDate().equals(day)) {
                result.add(note);
            }
        }
        return result;
    }

    @Override
    public List<Note> getNotesForWeek(String weekDay) {
        List<Note> result = new ArrayList<>();
        for (Note note : notes) {
            // Assuming that the weekDay parameter is in lowercase like 'понедельник'
            if (note.getDate().toLowerCase().contains(weekDay)) {
                result.add(note);
            }
        }
        return result;
    }

    @Override
    public List<Note> sortNotesByDate() {
        List<Note> sortedNotes = new ArrayList<>(notes);
        Collections.sort(sortedNotes, new Comparator<Note>() {
            @Override
            public int compare(Note note1, Note note2) {
                int dateComparison = compareDates(note1.getDate(), note2.getDate());
                if (dateComparison != 0) {
                    return dateComparison;
                }
                // Если даты совпадают, сортировать по времени
                return note1.getTime().compareTo(note2.getTime());
            }
        });
        return sortedNotes;
    }

    // Метод для сравнения дат в формате "dd.MM.yyyy"
    private int compareDates(String date1, String date2) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
            Date dateObj1 = format.parse(date1);
            Date dateObj2 = format.parse(date2);
            return dateObj1.compareTo(dateObj2);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Note> sortNotesByContent() {
        List<Note> sortedNotes = new ArrayList<>(notes);
        Collections.sort(sortedNotes, new Comparator<Note>() {
            @Override
            public int compare(Note note1, Note note2) {
                return note1.getContent().compareTo(note2.getContent());
            }
        });
        return sortedNotes;
    }

    @Override
    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), StandardCharsets.UTF_8))) {
            for (Note note : notes) {
                writer.write(note.getDate() + " " + note.getTime() + " - " + note.getContent());
                writer.newLine();
            }
            System.out.println("Данные успешно сохранены в файл.");
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении данных в файл: " + e.getMessage());
        }
    }



    @Override
    public void loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8))) {
            String line;
            notes.clear();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ", 4);
                if (parts.length == 4) {
                    String date = parts[0];
                    String time = parts[1];
                    String content = parts[3];
                    notes.add(new Note(date, time, content));
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при загрузке данных из файла: " + e.getMessage());
        }
    }
}
