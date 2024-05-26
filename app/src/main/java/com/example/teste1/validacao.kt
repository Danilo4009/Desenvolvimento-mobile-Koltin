package com.example.teste1


fun isValidNome(nome: String): Boolean {
    val words = nome.trim().split("\\s+".toRegex())
    return words.size >= 2 && nome.replace("\\s".toRegex(), "").length >= 3
}

fun isValidTelefone(telefone: String, ddd: String): Boolean {
    val phonePattern = Regex("^9\\d{8}\$")
    return telefone.matches(phonePattern) && ddd.matches(Regex("^\\d{2}\$"))
}

fun isValidEmail(email: String): Boolean {
    val emailPattern = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$")
    return email.matches(emailPattern)
}
