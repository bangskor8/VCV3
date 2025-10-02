import java.util.*;
import java.util.concurrent.TimeUnit;

public class VT {
    private Map<String, String> vocabulary;
    private Scanner scanner;
    private int correctAnswers;
    private int totalQuestions;

    public VT () {
        scanner = new Scanner(System.in);
        vocabulary = new LinkedHashMap<>();
        initializeVocabulary();
        correctAnswers = 0;
        totalQuestions = 0;
    }

    private void initializeVocabulary() {
        // Личные данные и основная информация (ниже слова для зазубривания)
        vocabulary.put("Personal details", "личные данные");
        vocabulary.put("Education", "образование");
        vocabulary.put("Experience/career/work history/employment", "опыт работы");
        vocabulary.put("Achievements", "профессиональные достижения");
        vocabulary.put("Capabilities/skills", "квалификация");
        vocabulary.put("Additional information", "доп. информация");
        vocabulary.put("Objective/position", "должность");
        vocabulary.put("Marital status: married/single", "семейное положение: женатый/одинокий");

        // Языки
        vocabulary.put("Native language", "родной язык");
        vocabulary.put("Fluent language", "свободное владение языком");
        vocabulary.put("Fair/working knowledge", "хорошее/рабочее владение языком");
        vocabulary.put("Basic", "базовый уровень");
        vocabulary.put("Driving License", "водительские права");

        // Навыки и качества
        vocabulary.put("Organizational skills", "организационные способности");
        vocabulary.put("Creativity", "творческие способности");
        vocabulary.put("Ability to work in a team", "умение работать в команде");
        vocabulary.put("Showing initiative skills", "проявление инициативы");
        vocabulary.put("Responsibility", "ответственность");
        vocabulary.put("Diligence", "усердность, старательность");

        // Работа и карьера
        vocabulary.put("Advertisement", "рекламное объявление");
        vocabulary.put("Look for", "искать");
        vocabulary.put("Salary", "зарплата");
        vocabulary.put("Trial period", "испытательный срок");
        vocabulary.put("Unemployed", "безработный");
        vocabulary.put("Redundancies", "сокращение штатов");
        vocabulary.put("References", "рекомендации");
    }

    public void showMenu() {
        System.out.println("=== ТРЕНАЖЕР АНГЛИЙСКИХ СЛОВ ===");
        System.out.println("1. Показать все слова");
        System.out.println("2. Учить слова (английский → русский)");
        System.out.println("3. Учить слова (русский → английский)");
        System.out.println("4. Тест с вариантами ответов (ВСЕ 26 СЛОВ)");
        System.out.println("5. Статистика");
        System.out.println("6. Выход");
        System.out.print("Выберите опцию: ");
    }

    public void showAllWords() {
        System.out.println("\n=== ВСЕ СЛОВА ДЛЯ ИЗУЧЕНИЯ ===");
        int counter = 1;
        for (Map.Entry<String, String> entry : vocabulary.entrySet()) {
            System.out.printf("%2d. %-45s - %s\n", counter++, entry.getKey(), entry.getValue());
        }
        System.out.println();
    }

    public void learnEnglishToRussian() {
        System.out.println("\n=== РЕЖИМ ОБУЧЕНИЯ: Английский → Русский ===");
        System.out.println("Напишите перевод для каждого слова. Для выхода введите 'exit'");

        List<String> keys = new ArrayList<>(vocabulary.keySet());
        Collections.shuffle(keys);

        for (String englishWord : keys) {
            System.out.print("\n" + englishWord + " - ");
            String answer = scanner.nextLine().trim().toLowerCase();

            if (answer.equals("exit")) {
                break;
            }

            String correctTranslation = vocabulary.get(englishWord).toLowerCase();
            if (answer.equals(correctTranslation)) {
                System.out.println("✓ Правильно!");
                correctAnswers++;
            } else {
                System.out.println("✗ Неправильно. Правильный ответ: " + vocabulary.get(englishWord));
            }
            totalQuestions++;

            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void learnRussianToEnglish() {
        System.out.println("\n=== РЕЖИМ ОБУЧЕНИЯ: Русский → Английский ===");
        System.out.println("Напишите английский вариант для каждого слова. Для выхода введите 'exit'");

        List<Map.Entry<String, String>> entries = new ArrayList<>(vocabulary.entrySet());
        Collections.shuffle(entries);

        for (Map.Entry<String, String> entry : entries) {
            System.out.print("\n" + entry.getValue() + " - ");
            String answer = scanner.nextLine().trim();

            if (answer.equalsIgnoreCase("exit")) {
                break;
            }

            if (answer.equalsIgnoreCase(entry.getKey())) {
                System.out.println("✓ Правильно!");
                correctAnswers++;
            } else {
                System.out.println("✗ Неправильно. Правильный ответ: " + entry.getKey());
            }
            totalQuestions++;

            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void multipleChoiceTest() {
        System.out.println("\n=== ТЕСТ С ВАРИАНТАМИ ОТВЕТОВ ===");
        System.out.println("Вам будет показано английское слово и 4 варианта перевода. Выберите правильный.");
        System.out.println("Тест включает ВСЕ 26 слов.\n");

        List<Map.Entry<String, String>> entries = new ArrayList<>(vocabulary.entrySet());
        Collections.shuffle(entries);

        int testCorrect = 0;
        int testTotal = entries.size(); // Теперь ВСЕ 26 слов

        for (int i = 0; i < testTotal; i++) {
            Map.Entry<String, String> currentEntry = entries.get(i);
            String correctAnswer = currentEntry.getValue();

            // Создаем список вариантов ответов
            List<String> options = new ArrayList<>();
            options.add(correctAnswer);

            // Добавляем 3 случайных неправильных варианта
            List<String> allTranslations = new ArrayList<>(vocabulary.values());
            allTranslations.remove(correctAnswer);
            Collections.shuffle(allTranslations);

            for (int j = 0; j < 3 && j < allTranslations.size(); j++) {
                options.add(allTranslations.get(j));
            }

            // Перемешиваем варианты ответов
            Collections.shuffle(options);

            // Находим индекс правильного ответа
            int correctIndex = options.indexOf(correctAnswer) + 1;

            // Выводим вопрос
            System.out.println("Переведите: " + currentEntry.getKey());
            for (int k = 0; k < options.size(); k++) {
                System.out.println((k + 1) + ". " + options.get(k));
            }

            // Получаем ответ пользователя
            int userAnswer = -1;
            while (userAnswer < 1 || userAnswer > 4) {
                System.out.print("Ваш ответ (1-4): ");
                try {
                    userAnswer = scanner.nextInt();
                    scanner.nextLine(); // очистка буфера

                    if (userAnswer < 1 || userAnswer > 4) {
                        System.out.println("Пожалуйста, введите число от 1 до 4");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Пожалуйста, введите число от 1 до 4");
                    scanner.nextLine(); // очистка буфера
                }
            }

            // Проверяем ответ
            if (userAnswer == correctIndex) {
                System.out.println("✔ Верно!\n");
                testCorrect++;
                correctAnswers++;
            } else {
                System.out.println("✗ Неверно. Правильный ответ: " + correctIndex + ". " + correctAnswer + "\n");
            }
            totalQuestions++;

            // Небольшая пауза между вопросами
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Выводим результаты теста
        double percentage = (double) testCorrect / testTotal * 100;
        System.out.println("=== РЕЗУЛЬТАТЫ ТЕСТА ===");
        System.out.printf("Правильных ответов: %d/%d (%.1f%%)\n", testCorrect, testTotal, percentage);

        if (percentage == 100) {
            System.out.println("ИДЕАЛЬНО! Ты знаешь все слова! 🎉🏆");
        } else if (percentage >= 90) {
            System.out.println("Отлично! Ты готов к сдаче! 🎉");
        } else if (percentage >= 80) {
            System.out.println("Очень хорошо! Почти идеально! 👍");
        } else if (percentage >= 70) {
            System.out.println("Хорошо! Продолжай заниматься! 💪");
        } else if (percentage >= 60) {
            System.out.println("Неплохо, но нужно еще повторить слова 📚");
        } else {
            System.out.println("Нужно больше практики! Ты сможешь! 🎓");
        }

        System.out.println("\nТест завершен! Вы ответили на все 26 вопросов.");
    }

    public void showStatistics() {
        System.out.println("\n=== СТАТИСТИКА ===");
        if (totalQuestions == 0) {
            System.out.println("Вы еще не ответили ни на один вопрос.");
        } else {
            double percentage = (double) correctAnswers / totalQuestions * 100;
            System.out.printf("Всего вопросов: %d\n", totalQuestions);
            System.out.printf("Правильных ответов: %d\n", correctAnswers);
            System.out.printf("Процент правильных: %.1f%%\n", percentage);
            System.out.printf("Слов для изучения: %d\n", vocabulary.size());
        }
    }

    public void run() {
        System.out.println("Добро пожаловать в тренажер английских слов!");
        System.out.println("Всего слов для изучения: " + vocabulary.size());

        while (true) {
            showMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    showAllWords();
                    break;
                case "2":
                    learnEnglishToRussian();
                    break;
                case "3":
                    learnRussianToEnglish();
                    break;
                case "4":
                    multipleChoiceTest();
                    break;
                case "5":
                    showStatistics();
                    break;
                case "6":
                    System.out.println("До свидания! Удачи в изучении английского! 🎓");
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }

            System.out.println("\nНажмите Enter чтобы продолжить...");
            scanner.nextLine();
        }
    }

    public static void main(String[] args) {
        VT trainer = new VT();
        trainer.run();
    }
}

/*написал bangskor8 в github*/