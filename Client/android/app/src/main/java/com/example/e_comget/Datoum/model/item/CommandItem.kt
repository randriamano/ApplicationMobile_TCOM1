package com.example.e_comget.Datoum.model.item

import com.example.e_comget.R
import com.example.e_comget.Routes.MyChartScreen

data class CommandItem(
    val productCategoryName: String = "",
    val productCategoryImageId: Int = 0,
    val productCategoryCommandedCount: Int = 0,
    val productCategoryNavigationRoute: String = "",
    val productCategoryEndpointName: String = ""
) {
    fun getCommandItems(): List<CommandItem> {
        return listOf(
            CommandItem(
                productCategoryEndpointName = "vetements",
                productCategoryName = "Vêtements",
                productCategoryImageId = R.drawable.vetements,
                productCategoryNavigationRoute = MyChartScreen.Vetement.route
            ),
            CommandItem(
                productCategoryEndpointName = "billets",
                productCategoryName = "Billets",
                productCategoryImageId = R.drawable.billet,
                productCategoryNavigationRoute = MyChartScreen.Billet.route
            ),
            CommandItem(
                productCategoryEndpointName = "goodies",
                productCategoryName = "Goodies",
                productCategoryImageId = R.drawable.goodies,
                productCategoryNavigationRoute = MyChartScreen.Goodies.route
            ),
        )
    }
}