package FinalTask;

import java.util.List;

public class Presenter {
    private Model model;
    private View view;

    public Presenter(View view) {
        this.view = view;
        model = new FileHandler(); // Используем FileHandler в качестве Model
    }

    public void onViewAllNotes() {
        List<Note> notes = model.getNotes();
        view.showNotes(notes);
        view.start(); // Возвращаемся к главному меню
    }

    public void onAddNote() {
        Note newNote = view.readNoteFromUser();
        model.addNote(newNote);
        view.showSuccessMessage("Запись успешно добавлена.");
        view.start(); // Возвращаемся к главному меню
    }

    public void onSaveToFile() {
        String filename = view.readFilenameFromUser();
        model.saveToFile(filename);
        view.showSuccessMessage("Данные успешно сохранены в файл.");
        view.start(); // Возвращаемся к главному меню
    }

    public void onLoadFromFile() {
        String filename = view.readFilenameFromUser();
        model.loadFromFile(filename);
        view.showSuccessMessage("Данные успешно загружены из файла.");
        view.start(); // Возвращаемся к главному меню
    }

    public void onSearchByDate() {
        String date = view.readSearchDateFromUser();
        List<Note> notes = model.getNotesForDay(date);
        view.showNotes(notes);
        view.start(); // Возвращаемся к главному меню
    }

    public void onSearchByWeekDay() {
        String weekDay = view.readDayOfWeekFromUser();
        List<Note> notes = model.getNotesForWeek(weekDay);
        view.showNotes(notes);
        view.start(); // Возвращаемся к главному меню
    }

    public void onSortByDate() {
        List<Note> notes = model.sortNotesByDate();
        view.showNotes(notes);
        view.start(); // Возвращаемся к главному меню
    }

    public void onSortByContent() {
        List<Note> notes = model.sortNotesByContent();
        view.showNotes(notes);
        view.start(); // Возвращаемся к главному меню
    }
}