package com.example.uas.api

import com.google.gson.annotations.SerializedName

data class UnivResponse(

	@field:SerializedName("UnivResponse")
	val univResponse: List<UnivResponseItem?>? = null
)

data class UnivResponseItem(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("web_pages")
	val webPages: List<String?>? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("domains")
	val domains: List<String?>? = null,

	@field:SerializedName("alpha_two_code")
	val alphaTwoCode: String? = null,

	@field:SerializedName("state-province")
	val stateProvince: Any? = null
)
