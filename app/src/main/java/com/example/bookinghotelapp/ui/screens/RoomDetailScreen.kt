package com.example.bookinghotelapp.ui.screens


import androidx.compose.foundation.checkScrollableContainerConstraints
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.bookinghotelapp.data.Room

@Composable
fun RoomDetailScreen(
    room: Room,
    onBookingConfirmed: (Int) -> Unit,
    onCancel: () -> Unit,
    loi: Boolean =  false,
    modifier: Modifier = Modifier
) {
    val amenities = room.tiennghi
    val price = room.gia
    val roomType = stringResource(id = room.loai)
    val layoutDirection = LocalLayoutDirection.current
    var quantity by rememberSaveable { mutableStateOf("1") }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .statusBarsPadding()
            .padding(
                start = WindowInsets.safeDrawing.asPaddingValues()
                    .calculateStartPadding(layoutDirection),
                end = WindowInsets.safeDrawing.asPaddingValues()
                    .calculateEndPadding(layoutDirection),
            ),
    ) {
        Text(text = roomType)
        Text(text = "Giá: $price USD")
        Text(text = "Tiện nghi: " + amenities.map { stringResource(it) }.joinToString(", "))
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = quantity,
            onValueChange = { soluong -> quantity = soluong },
            label = { Text("Số phòng muốn đặt") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        if(loi){
            Text("Số lượng không đủ", color = Color.Red)
        }

        Spacer(modifier = Modifier.height(22.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = {
                val qty = quantity.toIntOrNull() ?: 1
                onBookingConfirmed(qty)
            }) {
                Text("Đặt phòng")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = onCancel) {
                Text("Huỷ")
            }
        }
    }
}