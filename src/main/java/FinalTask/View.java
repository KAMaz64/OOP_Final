package FinalTask;

import java.util.List;
import java.util.Scanner;

public class View {
    private Presenter presenter;
    private Scanner scanner;

    public View() {
        presenter = new Presenter(this);
        scanner = new Scanner(System.in);
    }


    public void start() {
        System.out.println("Добро пожаловать в записную книжку!");
        showMainMenu();
    }

    private void showMainMenu() {
        System.out.println("\nВыберите действие:");
        System.out.println("1 - Посмотреть все записи");
        System.out.println("2 - Добавить запись");
        System.out.println("3 - Сохранить записи в файл");
        System.out.println("4 - Загрузить записи из файла");
        System.out.println("5 - Поиск записей по дате");
        System.out.println("6 - Поиск записей по дню недели");
        System.out.println("7 - Сортировка записей по дате");
        System.out.println("8 - Сортировка записей по содержанию");
        System.out.println("0 - Выйти");

        int choice = readIntInput();
        switch (choice) {
            case 1:
                presenter.onViewAllNotes();
                break;
            case 2:
                presenter.onAddNote();
                break;
            case 3:
                presenter.onSaveToFile();
                break;
            case 4:
                presenter.onLoadFromFile();
                break;
            case 5:
                presenter.onSearchByDate();
                break;
            case 6:
                presenter.onSearchByWeekDay();
                break;
            case 7:
                presenter.onSortByDate();
                break;
            case 8:
                presenter.onSortByContent();
                break;
            case 0:
                System.out.println("До свидания!");
                return;
            default:
                System.out.println("Некорректный ввод, повторите попытку.");
                showMainMenu();
        }
    }

    public void showNotes(List<Note> notes) {
        if (notes.isEmpty()) {
            System.out.println("Записей нет.");
        } else {
            for (Note note : notes) {
                System.out.println(note);
            }
        }
    }

    public Note readNoteFromUser() {
        System.out.println("Введите дату записи (в формате dd.mm.yyyy):");
        String date = scanner.nextLine();
        System.out.println("Введите время записи (в формате hh:mm):");
        String time = scanner.nextLine();
        System.out.println("Введите текст записи:");
        String content = scanner.nextLine();
        return new Note(date, time, content);
    }

    public String readFilenameFromUser() {
        System.out.println("Введите имя файла:");
        return scanner.nextLine();
    }

    public String readDayOfWeekFromUser() {
        System.out.println("Введите день недели (например, 'понедельник'):");
        return scanner.nextLine();
    }

    public String readSearchDateFromUser() {
        System.out.println("Введите дату для поиска (например, '24.07.2023'):");
        return scanner.nextLine();
    }

    public void showErrorMessage(String message) {
        System.err.println("Ошибка: " + message);
    }

    public int readIntInput() {
        int choice = -1;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Некорректный ввод. Повторите попытку.");
        }
        return choice;
    }

    public void showSuccessMessage(String message) {
        System.out.println(message);
    }
}

