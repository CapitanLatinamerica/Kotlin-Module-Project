import java.util.Scanner

const val red = "\u001B[31m"
const val green = "\u001B[32m"
const val orange = "\u001B[33m"
const val blue = "\u001B[34m"
const val reset = "\u001B[0m"     //Раскрашивать текст в консоли

fun main() {
    val scanner = Scanner(System.`in`)
    val archiveManager = ArchiveManager()

    while (true) {
        println("${blue}Меню:")
        println("1) Добавить архив (набор заметок)")
        println("2) Посмотреть архивы")
        println("3) Добавить заметки в архив")
        println("4) Посмотреть заметки")
        println("0) Выход$reset")
        print("${orange}Выберите действие: $reset")

        when (scanner.nextLine()) {
            "1" -> archiveManager.create(scanner)
            "2" -> archiveManager.view(scanner)
            "3" -> addNoteToArchive(scanner, archiveManager)
            "4" -> Archive.viewNotes(scanner, archiveManager)
            "0" -> break
            else -> println("${red}Неверный ввод. Пожалуйста, введите число.$reset")
        }
    }
}

fun addNoteToArchive(scanner: Scanner, archiveManager: ArchiveManager) {
    val selectedArchive = archiveManager.select(scanner) ?: return
    val noteName = promptForNonEmptyInput(scanner, "Введите имя заметки: ", "Имя заметки не должно быть пустым!")
    val noteText = promptForNonEmptyInput(scanner, "Введите текст заметки: ", "Текст заметки не должен быть пустым!")
    val note = Note(noteName, noteText)
    selectedArchive.addNote(note)
    println("${green}Заметка '$noteName' добавлена в архив '${selectedArchive.name}'.${reset}")
}

fun promptForNonEmptyInput(scanner: Scanner, prompt: String, errorMessage: String): String {
    var input: String
    do {
        print("${orange}$prompt$reset")
        input = scanner.nextLine().trim()
        if (input.isBlank()) {
            println("${red}$errorMessage$reset")
        }
    } while (input.isBlank())
    return input
}

fun promptForIntInRange(scanner: Scanner, prompt: String, min: Int, max: Int): Int {
    var choice: Int?
    do {
        print("${orange}$prompt$reset")
        choice = readInt(scanner, max)
        if (choice != null && (choice < min || choice > max)) {
            println("${red}Неверный выбор. Введите число от $min до $max.$reset")
            choice = null
        }
    } while (choice == null)
    return choice
}

fun readInt(scanner: Scanner, max: Int): Int? {
    val input = scanner.nextLine().trim()
    return input.toIntOrNull()?.takeIf { it in 0..max }
}