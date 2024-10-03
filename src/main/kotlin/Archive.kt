import java.util.Scanner

class Archive (val name: String) {
    val notes = mutableListOf<Note>()   // список заметок типа Note

    fun addNote(note: Note) {   // Нужен только метод добавления заметок
        notes.add(note)
    }
    companion object {
        fun viewNotes(scanner: Scanner, archiveManager: ArchiveManager) {
            val selectedArchive = archiveManager.select(scanner) ?: return
            if (selectedArchive.notes.isEmpty()) {
                println("${red}Нет заметок в архиве '${selectedArchive.name}'.${reset}")
                return
            }
            println("${blue}Заметки в архиве '${selectedArchive.name}':$reset")
            selectedArchive.notes.forEachIndexed { index, note ->
                println("${index + 1}) ${note.name}")
            }
            while (true) {
                val noteNumber = promptForIntInRange(scanner, "Введите номер заметки для просмотра или '0' для возврата в главное меню: ",
                    0, selectedArchive.notes.size)
                if (noteNumber == 0) return
                val note = selectedArchive.notes.getOrNull(noteNumber - 1)
                if (note != null) {
                    println("${green}Заметка '${note.name}':${reset}\n${note.text}")
                    break
                } else {
                    println("${red}Неверный номер заметки. Попробуйте снова.$reset")
                }
            }
        }

    }
}