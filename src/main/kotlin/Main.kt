import java.util.Scanner

val scanner = Scanner(System.`in`)
val archives = mutableListOf<Archive>()
var isRunning = true

fun main() {
    openMainMenu()
}

fun createArchive() {
    val name = readNonEmptyString(
        prompt = "Введите название нового архива:",
        errorMessage = "Ошибка: имя архива не может быть пустым. Попробуйте ещё раз."
    )
    archives.add(Archive(name))
    println("Архив '$name' успешно создан.")
}

fun createNote(archive: Archive) {
    val title = readNonEmptyString(
        prompt = "Введите название заметки:",
        errorMessage = "Ошибка: название заметки не может быть пустым. Попробуйте ещё раз."
    )

    val content = readNonEmptyString(
        prompt = "Введите содержание заметки:",
        errorMessage = "Ошибка: содержание заметки не может быть пустым. Попробуйте ещё раз."
    )

    archive.notes.add(Note(title, content))
    println("Заметка '$title' успешно добавлена в архив '${archive.name}'.")
}

fun openNoteView(note: Note) {
    println("=== Просмотр заметки: ${note.title} ===")
    println(note.content)
    println("========================================")
    println("Нажмите Enter, чтобы вернуться назад...")
    scanner.nextLine()
}

fun openMainMenu() {
    while (isRunning) {
        val menuElements = mutableListOf<MenuElement>()
        menuElements.add(MenuElement("Создать архив") { createArchive() })

        archives.forEach { archive ->
            menuElements.add(MenuElement(archive.name) { openArchiveMenu(archive) })
        }

        menuElements.add(MenuElement("Выход") { isRunning = false })

        val mainMenu = MenuScreen("Список архивов:", menuElements)
        mainMenu.show()
    }
}

fun openArchiveMenu(archive: Archive) {
    var inArchiveMenu = true
    while (inArchiveMenu) {
        val menuElements = mutableListOf<MenuElement>()
        menuElements.add(MenuElement("Создать заметку") { createNote(archive) })

        archive.notes.forEach { note ->
            menuElements.add(MenuElement(note.title) { openNoteView(note) })
        }

        menuElements.add(MenuElement("Назад") { inArchiveMenu = false })

        val archiveMenu = MenuScreen("Архив '${archive.name}'. Список заметок:", menuElements)
        archiveMenu.show()
    }
}