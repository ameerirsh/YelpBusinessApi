package com.example.example

import com.google.gson.annotations.SerializedName


data class ExampleJson2KtKotlin (

  @SerializedName("businesses" ) var businesses : ArrayList<Businesses> = arrayListOf(),
  @SerializedName("total"      ) var total      : Int?                  = null,
  @SerializedName("region"     ) var region     : Region?               = Region()

)