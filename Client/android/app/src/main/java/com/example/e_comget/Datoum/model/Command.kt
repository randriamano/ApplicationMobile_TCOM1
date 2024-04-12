package com.example.e_comget.Datoum.model

data class Command (
    val nomProduct : String,
    val couleur : String,
    val taille : String,
    val prix : Int,
    val etat : String,

)

val command = listOf(
    Command("T-shirt GET2024","Rouge", "M", 20000,"En Cours"),
    Command("Polo GET2024","Jaune", "XL", 20000,"En Cours"),
    Command("Sweat","Vert", "S", 20000,"En Cours"),
    Command("Jogging","Viollet", "S", 20000,"En Cours"),
    Command("Polo nike","Vert", "S", 20000,"En Cours"),
)