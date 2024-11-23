package day07Bis

abstract class Source {
    abstract fun hasValue(mem: Memory): Boolean
    abstract fun getValue(mem: Memory): Int

    companion object {
        fun parse(str: String): Source {
            val value = str.toIntOrNull()
            return if (value is Int)
                Constant(value)
            else
                Wire(str)
        }
    }
}

data class Constant(var value: Int) : Source() {
    override fun hasValue(mem: Memory): Boolean = true
    override fun getValue(mem: Memory): Int = value
}

data class Wire(val name: String) : Source() {
    override fun hasValue(mem: Memory): Boolean = mem.contains(name)
    override fun getValue(mem: Memory): Int = mem[name]!!
}