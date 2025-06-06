package com.example.softmarket

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.softmarket.ui.navigation.NavigationScreens
import com.example.softmarket.ui.navigation.Screen
import com.example.softmarket.ui.theme.SoftmarketTheme
import com.example.softmarket.ui.viewmodel.ProductViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val viewModel = remember { ProductViewModel() }
            val scope = rememberCoroutineScope()
            SoftmarketTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(stringResource(R.string.app_name))},
                            actions = {
                                IconButton(onClick = { navController.navigate(Screen.CreateProduct.route) }) {
                                    Icon(Icons.Filled.Add, contentDescription = "Add Product")
                                }
                            }
                        )
                    },
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomNavigation {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentRoute = navBackStackEntry?.destination?.route

                            BottomNavigationItem(
                                icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
                                label = { Text("Home") },
                                selected = currentRoute == Screen.ProductList.route,
                                onClick = { navController.navigate(Screen.ProductList.route) }
                            )

                            BottomNavigationItem(
                                icon = { Icon(Icons.Filled.Add, contentDescription = "Add") },
                                label = { Text("Add") },
                                selected = currentRoute == Screen.CreateProduct.route,
                                onClick = { navController.navigate(Screen.CreateProduct.route) }
                            )
                        }
                    }
                ) { innerPaddings ->
                    NavigationScreens(
                        navController, viewModel,
                        paddingValues = innerPaddings,
                        modifier = Modifier.fillMaxSize(),
                        )
                    viewModel.toast.value?.let { message ->
                        scope.launch {
                            Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
                            viewModel.toast.value = null
                        }
                    }
                }
            }
        }
    }
}