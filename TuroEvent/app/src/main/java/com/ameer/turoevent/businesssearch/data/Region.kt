package com.example.example

import com.google.gson.annotations.SerializedName


data class Region (

  @SerializedName("center" ) var center : Center? = Center()

)