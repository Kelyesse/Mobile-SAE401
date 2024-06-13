package com.example.mob_sae401.ui.homepage

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mob_sae401.data.implementation.WorkList
import com.example.mob_sae401.data.models.Work
import com.example.mob_sae401.data.models.WorkType
import com.example.mob_sae401.ui.theme.Red
import com.example.mob_sae401.ui.util.Routes

@Composable
fun HomePage(navController: NavHostController) {
    val context = LocalContext.current
    var searchQuery by remember { mutableStateOf("") }
    var filter by remember { mutableIntStateOf(0) } // 0: rien, 1: livres, 2: films

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            IconButton(
                onClick = {
                    when (filter) {
                        0 -> {
                            filter++
                            Toast.makeText(context, "Filtré sur les livres", Toast.LENGTH_SHORT)
                                .show()
                        }

                        1 -> {
                            filter++
                            Toast.makeText(context, "Filtré sur les films", Toast.LENGTH_SHORT)
                                .show()
                        }

                        2 -> {
                            filter = 0
                            Toast.makeText(context, "Aucun filtre", Toast.LENGTH_SHORT).show()
                        }
                    }

                },
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.White)
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Tune,
                    contentDescription = "Filter",
                    modifier = Modifier.size(24.dp)
                )
            }

            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = {
                    Text("Search...", fontSize = 16.sp)
                },
                shape = RoundedCornerShape(25.dp),
                singleLine = true,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Red,
                    cursorColor = Red,
                    focusedContainerColor = MaterialTheme.colorScheme.background,
                    unfocusedContainerColor = MaterialTheme.colorScheme.background
                ),
            )
        }

        LazyColumn(
            Modifier.fillMaxSize(),
            contentPadding = PaddingValues(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(
                WorkList.list.filter { if (filter == 1) it.type == WorkType.BOOK else if (filter == 2) it.type == WorkType.MOVIE else true }.filter { it.name.lowercase().startsWith(searchQuery.lowercase()) },
                key = { it.id }) {
                ListItem(
                    work = it, modifier = Modifier
                        .fillMaxHeight(.2f)
                        .fillMaxWidth()
                ) {
                    navController.navigate("${Routes.Details.route}?id=${it.id}")
                }
            }
        }
    }
}

@Composable
fun ListItem(work: Work, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Column(
        Modifier
            .clip(RoundedCornerShape(15.dp))
            .clickable(onClick = onClick)
            .padding(8.dp)
            .then(modifier),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = work.image),
            contentDescription = "${work.name}'s image",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .fillMaxWidth(.5f)
        )
        Text(
            text = work.name,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold
        )
    }
}