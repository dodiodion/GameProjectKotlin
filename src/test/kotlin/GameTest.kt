package tech.makers.game
import kotlin.test.assertEquals
import kotlin.test.Test

class GameTest {
    @Test
    fun getsMaskedWordToGuessSuccess() {
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

        assertEquals(getWordToGuess(gameStateE), "M__E__")

        val gameStateX = attemptGuessLetter(gameStateE, "X")
        assertEquals(gameStateX.numberAttempts, 9)
        assertEquals(getWordToGuess(gameStateX), "M__E__")

        val gameStateA = attemptGuessLetter(gameStateX, "A")
        assertEquals(gameStateA.numberAttempts, 9)
        assertEquals(getWordToGuess(gameStateA), "MA_E__")

        val gameStateK = attemptGuessLetter(gameStateA, "K")
        assertEquals(gameStateK.numberAttempts, 9)
        assertEquals(getWordToGuess(gameStateK), "MAKE__")

        val gameStateR = attemptGuessLetter(gameStateK, "R")
        assertEquals(gameStateR.numberAttempts, 9)
        assertEquals(getWordToGuess(gameStateR), "MAKER_")
        assertEquals(isFinished(gameStateR), false)

        val gameStateS = attemptGuessLetter(gameStateR, "S")
        assertEquals(gameStateS.numberAttempts, 9)
        assertEquals(getWordToGuess(gameStateS), "MAKERS")

        assertEquals(isFinished(gameStateS), true)
    }
    @Test
    fun getsWordToGuessFailure() {
        var state = createGameState(listOf("FAILURE"))
        val maskedWord = getWordToGuess(state)
        assertEquals(maskedWord, "F______")
        for (i in 1..10) {
            state = attemptGuessLetter(state, "Y")
        }
        assertEquals(true, isFinished(state))
    }
}