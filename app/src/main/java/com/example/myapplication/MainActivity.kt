package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.myapplication.dataClasses.ShoppingItem
import com.example.myapplication.database.ShoppingRepository
import com.example.myapplication.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import java.math.BigDecimal
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var shoppingRepository: ShoppingRepository
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                var showDialog by remember { mutableStateOf(false) }
                var shoppingList = remember { mutableStateListOf(ShoppingItem(0,"", BigDecimal.ZERO,"", BigDecimal.ZERO)) }
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(text = "MyFirstApp") }, modifier = Modifier.fillMaxWidth(),
                            colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.primaryContainer),
                            navigationIcon = {
                                IconButton(
                                    onClick = { /*TODO*/ }
                                ) {
                                    Icon( imageVector = Icons.Filled.Menu, contentDescription = "Localized Description"
                                    )

                                }},
                            actions = {
                                IconButton(
                                    onClick = { /*TODO*/ }
                                ) {
                                    Icon(imageVector = Icons.Filled.Settings, contentDescription = null)
                                }
                                IconButton(
                                    onClick = { /*TODO*/ }
                                ) {
                                    Icon(imageVector = Icons.Filled.Notifications, contentDescription = null)
                                }
                            }
                        )
                    },


                    floatingActionButton = {
                        FloatingActionButton(onClick ={
                            showDialog = true
                        }) {
                            Icon(imageVector = Icons.Filled.Add, contentDescription = null )

                        }
                    },

                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)){
                        ShoppingLayout(shoppingList)
                        ShoppingDialog(showDialog, onDismiss = {showDialog = false},shoppingList)
                    }
                }
            }
        }
    }
    @Composable
    fun ShoppingLayout(shoppingList : List<ShoppingItem>){
        LazyColumn {
            items(shoppingList){
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp),
                    shape = CardDefaults.elevatedShape,
                    colors = CardDefaults.cardColors(containerColor = Color.Gray)
                ) {
                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                        for (i in 0..2 ){
                            Text(
                                text = when (i) {
                                    0 -> it.id.toString()
                                    1 -> it.description
                                    else -> it.price.toString()
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(if (i == 1) 2f else 1f),
                                fontWeight = if (i == 0) FontWeight.Bold else FontWeight.Normal,
                                textAlign = TextAlign.Center,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
    @Composable
    fun ShoppingDialog(showDialog : Boolean, onDismiss:() -> Unit,shoppingList: List<ShoppingItem>){
        var description by remember { mutableStateOf("") }
        var price by remember { mutableStateOf("") }
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { },
                confirmButton = {
                    Row(modifier = Modifier.fillMaxWidth()){
                        IconButton(
                            onClick = { onDismiss() },
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)

                        ) {
                            Text(text = "Ακύρωση")
                        }
                        IconButton(
                            onClick = {

                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                        ) {
                            Text(text = "Καταχώρηση")
                        }
                    }
                },
                title = { Text(text = "Προσθήκη Είδους",Modifier.fillMaxWidth(), textAlign = TextAlign.Center) },
                text = {
                    Row ( modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
                        OutlinedTextField(
                            value = description,
                            onValueChange = {
                                description = it
                            },
                            modifier = Modifier
                                .padding(1.dp)
                                .fillMaxWidth()
                                .weight(1f),
                            enabled = true,
                            label = {
                                Text(text = "Περιγραφή")
                            },
                            maxLines = 1,
                        )
                        OutlinedTextField(
                            value = price,
                            onValueChange = { price = it },
                            modifier = Modifier
                                .padding(1.dp)
                                .fillMaxWidth()
                                .weight(1f),
                            enabled = true,
                            label = {
                                Text(text = "Τιμή")
                            },
                            maxLines = 1,
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, keyboardType = KeyboardType.Decimal)
                        )
                    }
                }
            )
        }

    }
}



