package com.ameer.turoevent.businesssearch.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.ameer.turoevent.R
import com.example.example.Businesses

@Preview(showBackground = true)
@Composable
fun SearchResultItem() {
    val business = Businesses("PTFxtXS47ZVRCdZIrEWvGw",
        "golden-boy-pizza-san-francisco",
        "Golden Boy Pizza",
        "https://s3-media1.fl.yelpcdn.com/bphoto/SfosPh2iWkVTMC4LLVIL8g/o.jpg",
        false,
        "https://www.yelp.com/biz/golden-boy-pizza-san-francisco?adjust_creative=51SsU9NI_tuXrEuO3-2lqg&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_search&utm_source=51SsU9NI_tuXrEuO3-2lqg",
        4047,
        null,
        4.5,
        null,
        null,
        "$",
        null,
        "+14159829738",
        "(415) 982-9738",
        1205.687
    )
    SearchResultItem(businesses = business, 0,  0) {
            i ->
    }
}


@Composable
fun SearchResultItem(businesses: Businesses, index: Int, selectedIndex: Int, onClick: (Int) -> Unit) {

    val backgroundColor =
        if (index == selectedIndex) MaterialTheme.colors.primary else MaterialTheme.colors.background
    Card(
        modifier = Modifier
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .clickable { onClick(index) }
            .height(110.dp), shape = RoundedCornerShape(8.dp), elevation = 4.dp
    ) {
        Surface(color = backgroundColor) {

            Row(
                Modifier
                    .padding(4.dp)
                    .fillMaxSize()
            ) {

                Image(
                    painter = rememberImagePainter(
                        data = businesses.imageUrl,

                        builder = {
                            scale(Scale.FILL)
                            placeholder(R.drawable.ic_launcher_foreground)
                            transformations(CircleCropTransformation())

                        }
                    ),
                    contentDescription = businesses.alias,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.2f)
                )


                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                        .weight(0.8f)
                ) {
                    Text(
                        text = businesses.name!!,
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = businesses.rating.toString(),
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier
                            .background(
                                Color.Magenta
                            )
                            .padding(4.dp)
                    )
                    Text(
                        text = businesses.displayPhone.toString(),
                        style = MaterialTheme.typography.body1,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                }
            }
        }
    }

}

