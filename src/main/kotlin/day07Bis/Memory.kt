package day07Bis

class Memory : HashMap<String, Int>() {

    fun process(codeList: List<Gate>, mem: Memory): List<Gate> = buildList {
        codeList.forEach {
            if (it.isReady(mem))
                it.process(mem)
            else
                add(it)
        }
    }

}