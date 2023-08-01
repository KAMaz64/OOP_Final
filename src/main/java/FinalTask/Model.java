package FinalTask;

import java.util.List;

public interface Model {
    void addNote(Note note);
    List<Note> getNotes();
    List<Note> getNotesForDay(String day);
    List<Note> getNotesForWeek(String weekDay);
    List<Note> sortNotesByDate();
    List<Note> sortNotesByContent();
    void saveToFile(String filename);
    void loadFromFile(String filename);
}
