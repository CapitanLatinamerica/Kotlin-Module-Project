import java.util.Scanner

class ArchiveManager : Creatable<Archive>, Selectable<Archive> {
    val archives = mutableListOf<Archive>()

    override fun create(scanner: Scanner): Archive {
        print("${orange}Введите имя архива: $reset")
        val name = scanner.nextLine().trim()
        if (name.isEmpty()) {
            println("${red}Имя архива не должно быть пустым!$reset")
            return create(scanner)
        }
        val archive = Archive(name)
        archives.add(archive)
        println("${green}Архив '$name' создан.$reset")
        return archive
    }

    override fun select(scanner: Scanner): Archive? {
        if (archives.isEmpty()) {
            println("${red}Нет доступных архивов.$reset")
            return null
        }
        println("${blue}Список доступных архивов:$reset")
        archives.forEachIndexed { index, archive ->
            println("${index + 1}) ${archive.name}")
        }
        while (true) {
            print("${orange}Выберите архив или введите '0' для возврата: $reset")
            val choice = readInt(scanner, archives.size)
            if (choice == 0) return null
            if (choice != null) {
                return archives[choice - 1]
            } else {
                println("${red}Неверный выбор. Пожалуйста, введите число от 0 до ${archives.size}.$reset")
            }
        }
    }

    fun view(scanner: Scanner){
        if (archives.isEmpty()) {
            println("${red}Нет доступных архивов.$reset")
            return
        }
        println("${blue}Список доступных архивов:$reset")
        archives.forEachIndexed { index, archive ->
            println("${index + 1}) ${archive.name}")
        }
        print("${orange}Введите '0' для возврата: $reset")
        while (true){
            val input = scanner.nextLine()
            if (input=="0"){
                return
            }
            else print("${red}Ошибка ввода! ${orange}Введите '0' для возврата: $reset")
        }
    }
}