package s.yarlykov.izilogic.sort

val range = ((1 .. 50 step 2).shuffled() + (60 .. 100 step 3).shuffled()).shuffled()

fun print(arr : Array<Int>) {

    println("Raw array. Size ${arr.size} elements.")
    arr.forEachIndexed { index, v ->
        print("$v")
        if (index < arr.lastIndex) print(", ")
    }

    println("\n")
}

fun main() {

    print(range.toTypedArray())

    Bubble(range.toTypedArray()).run {
        sort(rawData.lastIndex)
        print()
    }

    println("\n")

    Selection(range.toTypedArray()).run {
        sort(0)
        print()
    }

    println("\n")

    Quick(range.toTypedArray()).run {
        sort(0, rawData.lastIndex)
        print()
    }
}