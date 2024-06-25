package mx.marco.util

fun String.isEmail():Boolean{
    return this.matches(Regex("[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,64}"))
}

fun String.isPhoneNumber():Boolean{
    return this.matches(Regex("""^\d{10}$"""))
}

fun String.isNumber():Boolean{
    return this.matches(Regex("[0-9]+"))
}

fun Int.twoDigits(): Boolean{
    return this in 1..100
}

fun Int.between1to100(): Boolean{
    return this in 1..100
}


