package com.mokshith.mvvmclean.data.remote.dto

import com.mokshith.mvvmclean.domain.models.HPModel
import com.mokshith.mvvmclean.domain.models.HPModelDetails

data class HPModelDto(
    val actor: String,
    val alive: Boolean,
    val alternate_actors: List<Any>,
    val alternate_names: List<String>,
    val ancestry: String,
    val dateOfBirth: String,
    val eyeColour: String,
    val gender: String,
    val hairColour: String,
    val hogwartsStaff: Boolean,
    val hogwartsStudent: Boolean,
    val house: String,
    val id: String,
    val image: String,
    val name: String,
    val patronus: String,
    val species: String,
    val wand: Wand,
    val wizard: Boolean,
    val yearOfBirth: Int
)

fun HPModelDto.getHpList(): HPModel {
    return HPModel(
        id = id,
        name = name,
        alive = alive,
        gender = gender,
        image = image
    )
}

fun HPModelDto.getHpDetails(): HPModelDetails {
    return HPModelDetails(
        image = image,
        gender = gender,
        alive = alive,
        name = name,
        actor = actor,
        dateOfBirth = dateOfBirth,
        house = house
    )
}