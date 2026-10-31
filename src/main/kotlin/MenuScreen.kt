import java.util.Scanner

class MenuScreen(
    private val menuTitle: String,
    private val elements: List<MenuElement>
) {
    fun show() {
        println(menuTitle)

        elements.forEachIndexed { index, element ->
            println("$index. ${element.title}")
        }

        print("Введите цифру: ")
        val input = scanner.nextLine().trim()
        val choice = input.toIntOrNull()

        if (choice == null) {
            println("Ошибка: следует вводить цифру.")
            return
        }

        if (choice in elements.indices) {
            val selectedElement = elements[choice]
            selectedElement.action()
        } else {
            println("Ошибка: такой цифры нет в меню, введите корректный символ.")
        }
    }
}

fun readNonEmptyString(prompt: String, errorMessage: String): String {
    while (true) {
        println(prompt)
        val input = scanner.nextLine().trim()

        if (input.isEmpty()) {
            println(errorMessage)
            continue
        }
        return input
    }
}

