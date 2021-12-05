class Board {
    private val row = 5
    private val column = 5
    val board = Array(row) { IntArray(column) }

    init {
        for (i in 0 until column) {
            for (j in 0 until row) {
                board[i][j] = 0
            }
        }
    }

    fun isOkW(): Boolean {
        var s = 0
        for (i in 0 until column) {
            for (j in 0 until row) {
                if (board[i][j] == -1) {
                    s += 1
                }
            }
            if (s == 5) {
                return true
            } else {
                s = 0
            }
        }
        return false
    }

    fun isOkH(): Boolean {
        var s = 0
        for (i in 0 until column) {
            for (j in 0 until row) {
                if (board[j][i] == -1) {
                    s += 1
                }
            }
            if (s == 5) {
                return true
            } else {
                s = 0
            }
        }
        return false
    }

    fun bingo(shot: Int) {
        for (i in 0 until column) {
            for (j in 0 until row) {
                if (board[i][j] == shot) {
                    board[i][j] = -1
                }
            }
        }
    }

    fun printBoard() {
        for (i in 0 until row) {
            for (j in 0 until column) {
                print(board[i][j].toString() + " ")
            }
            print("\n")
        }
    }
}


fun main() {

    val boards: MutableList<Board> = mutableListOf()
    val shots: MutableList<Int> = mutableListOf()
    var lastShot = 0
    val listOfSplitLines = mutableListOf<MutableList<String>>()

    fun initShots(input: List<String>) {
        val shotsStr = input[0].split(",")
        for (s in shotsStr) {
            shots.add(s.toInt())
        }
    }

    fun initSplitLines(input: List<String>) {
        for (n in 2 until input.size) {
            if (input[n] != "\n" && input[n] != " ") {
                listOfSplitLines.add(input[n].split(" ").toMutableList().filter { it.length > 0 }
                        as MutableList<String>)
            }
        }
    }

    fun createBoards(input: List<String>) {
        initSplitLines(input)
        var n = 0
        while (true) {
            if (n >= listOfSplitLines.size) {
                break
            }
            if (listOfSplitLines[n].isEmpty()) {
                n += 1
                continue
            }
            val board = Board()
            var w = 0
            for (i in 0..4) {
                for (j in 0..4) {
                    board.board[i][j] = listOfSplitLines[n][w].toInt()
                    w += 1
                }
                n += 1
                w = 0
            }
            boards.add(board)
        }
    }

    fun playStandardBingo(): Int {
        for (shot in shots) {
            var index = 0
            for (board in boards) {
                board.bingo(shot)
                if (board.isOkH() || board.isOkW()) {
                    lastShot = shot
                    return index
                }
                index += 1
            }
        }
        return -1
    }

    fun playReversedBingo(): Int {
        val winsIdx: MutableList<Int> = mutableListOf()
        for (s in 0 until shots.size) {
            var index = 0
            for (board in boards) {
                board.bingo(shots[s])
                if ((board.isOkH() || board.isOkW()) && !winsIdx.contains(index)) {
                    winsIdx.add(index)
                    if (winsIdx.size == boards.size - 1) {
                        for (i in 0 until boards.size) {
                            if (!winsIdx.contains(i)) {
                                boards[i].bingo(shots[s])
                                lastShot = shots[s]
                                return i
                            }
                        }
                    }
                }
                index += 1
            }
        }
        return -1
    }

    fun printBoards() {
        for (board in boards) {
            board.printBoard()
        }
    }

    fun printBoards(index: Int) {
        var i = 0
        for (board in boards) {
            if (i == index) {
                board.printBoard()
            }
            i += 1
        }
    }

    fun part1(): Int {
        val index = playStandardBingo()
        var result = 0

        for (i in 0..4) {
            for (j in 0..4) {
                if (boards[index].board[i][j] != -1) {
                    result += boards[index].board[i][j]
                }
            }
        }
        return result * lastShot
    }

    fun part2(): Int {
        val index = playReversedBingo()
        var result = 0

        for (i in 0..4) {
            for (j in 0..4) {
                if (boards[index].board[i][j] != -1) {
                    result += boards[index].board[i][j]
                }
            }
        }
        return result * lastShot
    }

    val input = readInput("Day04")
    initShots(input)
    createBoards(input)
    println(part1())

    boards.clear()

    createBoards(input)
    println(part2())
}