interface Graph<T> {

    fun createVertex(data: T): Vertex<T>

    fun addDirectedEdge(source: Vertex<T>,
                        destination: Vertex<T>,
                        weight: Double?)

    fun addUndirectedEdge(source: Vertex<T>,
                          destination: Vertex<T>,
                          weight: Double?)

    fun add(edge: EdgeType,
            source: Vertex<T>,
            destination: Vertex<T>,
            weight: Double?)

    fun edges(source: Vertex<T>): ArrayList<Edge<T>>

    fun weight(source: Vertex<T>,
               destination: Vertex<T>): Double?

    fun numberOfPaths(
        source: Vertex<T>,
        destination: Vertex<T>
    ): Int {
        val numberOfPaths = Ref(0) // 1
        val visitedS: MutableSet<Vertex<T>> = mutableSetOf() // 2
        val visitedB: MutableSet<Vertex<T>> = mutableSetOf()
        paths(source, destination, visitedS, visitedB, numberOfPaths) // 3
        return numberOfPaths.value
    }

    fun paths(
        source: Vertex<T>,
        destination: Vertex<T>,
        visitedS: MutableSet<Vertex<T>>,
        visitedB: MutableSet<Vertex<T>>,
        pathCount: Ref<Int>
    ) {
        if (isAllUppercase(source.data.toString())) {
            visitedB.add(source)
        } else {
            visitedS.add(source)
        } // 1
        if (source == destination) { // 2
            pathCount.value += 1
        } else {
            val neighbors = edges(source) // 3
            neighbors.forEach { edge ->
                // 4
                if (edge.destination !in visitedS && countInList(visitedB, edge.destination) <= 2) {
                    paths(edge.destination, destination, visitedS, visitedB, pathCount)
                }
            }
        }
        // 5
        if (isAllUppercase(source.data.toString())) {
            visitedB.remove(source)
        } else {
            visitedS.remove(source)
        }
    }

    private fun isAllUppercase(str: String): Boolean {
        for (c in str) {
            if (!c.isUpperCase()) {
                return false
            }
        }
        return true
    }

    private fun countInList(source: MutableSet<Vertex<T>>, el: Vertex<T>): Int {
        var result = 0
        for (e in source) {
            if (e == el) {
                result += 1
            }
        }
        return result
    }
}