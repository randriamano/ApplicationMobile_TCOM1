package com.example.e_comget.screens.Home.Components

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//TODO
//Adding logic to send request for searched data

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarSection() {
    var text by remember {
        mutableStateOf("")
    }
    var active by remember {
        mutableStateOf(false)
    }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .height(70.dp)
                .fillMaxWidth(1f)
                .padding(start = 10.dp, end = 10.dp)
        ){
            androidx.compose.material3.SearchBar(
                query = text,
                onQueryChange = {
                    text = it
                },
                active = active,
                onActiveChange = {
                    active = it
                },
                placeholder = {
                    Text(
                        fontSize = 15.sp,
                        text = "Recherche"
                    )
                },
                leadingIcon = {
                    if (active) {
                        Icon(
                            modifier = Modifier.clickable {
                                if (text.isNotEmpty()) {
                                    text = ""
                                } else {
                                    active = false
                                }
                            },
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close icon"
                        )
                    }else {
                        Icon(imageVector = Icons.Default.Search, contentDescription = null)
                    }
                },
                onSearch = {
                    active = false
                },
                colors = SearchBarDefaults.colors(Color.LightGray),

                modifier = Modifier
                    .fillMaxWidth(0.88f)

            ) {

            }
            Column(modifier = Modifier
                .padding(vertical = 15.dp)){
                Icon(
                    Icons.Outlined.Notifications,
                    contentDescription = null,
                    modifier = Modifier
                        .size(32.dp)

                )
            }
        }

}

@Preview
@Composable
fun prev(){
    SearchBarSection()
}