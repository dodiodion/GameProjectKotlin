package tech.makers.game
data class GameState(val word: String,
                     val numberAttempts: Int = 10,
                     val isFinished: Boolean = false,
                     val discovered: List<String> = mutableListOf<String>())