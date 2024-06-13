package com.example.mob_sae401.ui.reservation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mob_sae401.R

data class Reservation(
    val imageResId: Int,
    val title: String,
    val days: Int,
    val date: String,
    val isOverdue: Boolean
)

@Composable
fun ReservationItem(
    reservation: Reservation,
    sectionTitle: String // pour la section titre on passe ce param
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = sectionTitle,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Image(
                    painter = painterResource(id = reservation.imageResId),
                    contentDescription = reservation.title,
                    modifier = Modifier
                        .size(150.dp)
                        .clip(RoundedCornerShape(16.dp))
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = reservation.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = if (reservation.isOverdue) "${reservation.days} jours en retard" else "${reservation.days} jours restants",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = if (reservation.isOverdue) Color.Red else Color.Green
                )

                Text(
                    text = "(${reservation.date})",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            Icon(
                imageVector = Icons.Default.ArrowForwardIos,
                contentDescription = "Arrow",
                tint = Color.Red,
                modifier = Modifier
                    .size(24.dp)
                    .padding(start = 8.dp)
            )
        }
    }
}

@Composable
fun ReservationPage() {
    val overdueReservations = listOf(
        Reservation(
            imageResId = R.drawable.oppenheimer,
            title = "Titre du film",
            days = 4,
            date = "10/05",
            isOverdue = true
        )
    )

    val activeReservations = listOf(
        Reservation(
            imageResId = R.drawable.oppenheimer,
            title = "Titre du film",
            days = 6,
            date = "20/05",
            isOverdue = false
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Vos réservations",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Divider(color = Color(0xFFA52A2A), thickness = 2.dp)

        Spacer(modifier = Modifier.height(16.dp))

        overdueReservations.forEach { reservation ->
            ReservationItem(reservation = reservation, sectionTitle = "Réservations en retard")
        }

        Spacer(modifier = Modifier.height(16.dp))

        activeReservations.forEach { reservation ->
            ReservationItem(reservation = reservation, sectionTitle = "Réservations actives")
        }
    }
}
