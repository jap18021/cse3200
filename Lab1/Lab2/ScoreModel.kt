class ScoreModel {
    private var score = 0

    fun incrementScore() {
        score++
    }

    fun getScore(): Int {
        return score
    }

    fun resetScore() {
        score = 0
    }
}