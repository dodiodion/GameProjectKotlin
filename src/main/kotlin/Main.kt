package tech.makers.game
fun main() {
    // A list of possible words - one will
    // be randomly picked by the program
    val listOfWords = listOf("MAKERS", "DEVELOPER", "SANDWICH")

    // An object of the `GameState` data class
    // (You will need to create this data class too).
    val gameState = createGameState(listOfWords)

    // Returns the partially masked
    // word to guess (e.g  "M_____" if the picked
    // word is "MAKERS")
    getWordToGuess(gameState)

    // Returns the number of remaining attempts
    // (initially 10)
    getRemainingAttempts(gameState)

    isFinished(gameState) // false

    // The letter is in the word - good guess
    val newGameState = attemptGuessLetter(gameState, "E")

    getWordToGuess(newGameState) // "M__E__"

    // The letter is not in the word - wrong guess
    val newGameState2 = attemptGuessLetter(newGameState, "O")

    getWordToGuess(newGameState2) // "M__E__"

    getRemainingAttempts(newGameState2) // 9

    val finalState = attemptGuessLetter(newGameState2, "S")

    getWordToGuess(finalState) // "MAKERS"

    isFinished(finalState) // true
}

fun createGameState(listOfWords: List<String>):GameState {
    val word = listOfWords.random()
    val newDiscoveredList = mutableListOf<String>(word[0].toString())
    return GameState(word, discovered = newDiscoveredList)
}

fun getWordToGuess(gameState: GameState): String {
    val stringBuilder = StringBuilder()
    stringBuilder.append(gameState.word[0])
    gameState.word.slice(1 until gameState.word.count()).forEach {
        letter -> if(gameState.discovered.contains(letter.toString())) {
            stringBuilder.append(letter)
        } else {
            stringBuilder.append("_")
        }
    }

    return stringBuilder.toString()
}

fun getRemainingAttempts(gameState: GameState): Int {
    return gameState.numberAttempts
}

fun isFinished(gameState: GameState): Boolean {
    return gameState.isFinished
}

fun attemptGuessLetter(gameState: GameState, letter: String): GameState {
    if (gameState.word.contains(letter)) {
        if(!gameState.discovered.contains(letter)) {
            println("The letter is in the word - good guess")
            val newDiscovered: List<String> = gameState.discovered + letter
            if (gameState.word.all{ newDiscovered.contains(it.toString()) }) {
                println("Congratulations, you found the word!")
                return GameState(gameState.word, gameState.numberAttempts, true, newDiscovered)
            }
            return GameState(gameState.word, gameState.numberAttempts, false, newDiscovered)
        }
        println("The letter has already been discovered")
        return GameState(gameState.word, gameState.numberAttempts, false, gameState.discovered)
    } else {
        println("The letter is not in the word - wrong guess")
        if (gameState.numberAttempts-1 <= 0) {
            println("No more attempts")
            return GameState(gameState.word, 0, true, gameState.discovered)
        }
        return GameState(gameState.word, gameState.numberAttempts-1, false, gameState.discovered)
    }

}