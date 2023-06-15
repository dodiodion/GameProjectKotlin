package tech.makers.game
import kotlin.test.assertEquals
import kotlin.test.Test

class GameTest {
    @Test
    fun getsMaskedWordToGuess() {
        // Give the word to guess
        val state = createGameState(listOf("MAKERS"))

        val maskedWord = getWordToGuess(state)
        assertEquals(maskedWord, "M_____")

        val numberAttempts = getRemainingAttempts(state)
        assertEquals(numberAttempts, 10)

        val isFinished = isFinished(state)
        assertEquals(isFinished, false)

        val gameStateE = attemptGuessLetter(state, "E")
        assertEquals(gameStateE.numberAttempts, 10)

        val gameStateX = attemptGuessLetter(state, "X")
        assertEquals(gameStateX.numberAttempts, 9)

        val gameStateNotAlpha = attemptGuessLetter(state, "4")
        assertEquals(gameStateX.numberAttempts, 9)
    }
}