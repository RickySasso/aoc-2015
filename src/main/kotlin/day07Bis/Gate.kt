package day07Bis

abstract class Gate {
    abstract fun isReady(mem: Memory): Boolean
    abstract fun process(mem: Memory)

    companion object {
        fun parse(line: String): Gate {
            val parts = line.split(" ")
            val target = parts.last()
            return when (parts.size) {
                3 -> GSet(Source.parse(parts[0]), target)
                4 -> GNot(Source.parse(parts[1]), target)
                5 -> when (parts[1]) {
                    "AND" -> GAnd(Source.parse(parts[0]), Source.parse(parts[2]), target)
                    "OR" -> GOr(Source.parse(parts[0]), Source.parse(parts[2]), target)
                    "LSHIFT" -> GLShift(Source.parse(parts[0]), parts[2].toInt(), target)
                    "RSHIFT" -> GRShift(Source.parse(parts[0]), parts[2].toInt(), target)
                    else -> throw Exception("Unknown gate parts: ${parts[1]}")
                }

                else -> throw Exception("Unknown gate parts: ${parts[1]}")
            }
        }
    }

}

data class GSet(val source: Source, val target: String) : Gate() {
    override fun isReady(mem: Memory): Boolean = source.hasValue(mem)
    override fun process(mem: Memory) {
        mem[target] = source.getValue(mem)
    }
}

data class GNot(val source: Source, val target: String) : Gate() {
    override fun isReady(mem: Memory): Boolean = source.hasValue(mem)
    override fun process(mem: Memory) {
        mem[target] = source.getValue(mem).inv()
    }
}

data class GAnd(val s1: Source, val s2: Source, val target: String) : Gate() {
    override fun isReady(mem: Memory): Boolean = s1.hasValue(mem) && s2.hasValue(mem)
    override fun process(mem: Memory) {
        mem[target] = s1.getValue(mem) and s2.getValue(mem)
    }
}

data class GOr(val s1: Source, val s2: Source, val target: String) : Gate() {
    override fun isReady(mem: Memory): Boolean = s1.hasValue(mem) && s2.hasValue(mem)
    override fun process(mem: Memory) {
        mem[target] = s1.getValue(mem) or s2.getValue(mem)
    }
}

data class GRShift(val source: Source, val qty: Int, val target: String) : Gate() {
    override fun isReady(mem: Memory): Boolean = source.hasValue(mem)
    override fun process(mem: Memory) {
        mem[target] = source.getValue(mem) shr qty
    }
}

data class GLShift(val source: Source, val qty: Int, val target: String) : Gate() {
    override fun isReady(mem: Memory): Boolean = source.hasValue(mem)
    override fun process(mem: Memory) {
        mem[target] = source.getValue(mem) shl qty
    }
}
