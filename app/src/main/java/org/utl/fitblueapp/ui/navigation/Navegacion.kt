package org.utl.fitblueapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.utl.fitblueapp.db.AppDataBase
import org.utl.fitblueapp.db.repository.CategoriaRepositorio
import org.utl.fitblueapp.db.repository.EjercicioRepositorio
import org.utl.fitblueapp.db.repository.SesionRepositorio
import org.utl.fitblueapp.db.repository.SerieRepositorio
import org.utl.fitblueapp.db.viewModel.CategoriaViewModel
import org.utl.fitblueapp.db.viewModel.CategoriaViewModelFactory
import org.utl.fitblueapp.db.viewModel.EjercicioViewModel
import org.utl.fitblueapp.db.viewModel.EjercicioViewModelFactory
import org.utl.fitblueapp.db.viewModel.SesionViewModel
import org.utl.fitblueapp.db.viewModel.SesionViewModelFactory
import org.utl.fitblueapp.db.viewModel.SerieViewModel
import org.utl.fitblueapp.db.viewModel.SerieViewModelFactory
import org.utl.fitblueapp.ui.screens.CategoriaScreen
import org.utl.fitblueapp.ui.screens.EjercicioScreen
import org.utl.fitblueapp.ui.screens.SesionScreen
import org.utl.fitblueapp.ui.screens.SerieScreen

// Rutas de navegación
const val CATEGORIAS_ROUTE = "categorias"
const val EJERCICIOS_ROUTE = "ejercicios/{categoriaId}"
const val SESIONES_ROUTE = "sesiones/{ejercicioId}"
const val SERIES_ROUTE = "series/{sesionId}"
const val CATEGORIA_ID_ARG = "categoriaId"
const val EJERCICIO_ID_ARG = "ejercicioId"
const val SESION_ID_ARG = "sesionId"

@ExperimentalCoroutinesApi
@Composable
fun FitBlueNavigation(
    navController: NavHostController = rememberNavController(),
    database: AppDataBase
) {
    NavHost(
        navController = navController,
        startDestination = CATEGORIAS_ROUTE,
        modifier = Modifier
    ) {
        // Pantalla de Categorías
        composable(CATEGORIAS_ROUTE) {
            val categoriaViewModel: CategoriaViewModel = viewModel(
                factory = CategoriaViewModelFactory(CategoriaRepositorio(database.categoriaDao()))
            )
            
            CategoriaScreen(
                viewModel = categoriaViewModel,
                onCategoriaClick = { categoria ->
                    navController.navigate("ejercicios/${categoria.idCategoria}")
                }
            )
        }
        
        // Pantalla de Ejercicios (filtrados por categoría)
        composable(
            route = EJERCICIOS_ROUTE,
            arguments = listOf(
                navArgument(CATEGORIA_ID_ARG) {
                    type = NavType.LongType
                    nullable = false
                }
            )
        ) { backStackEntry ->
            val categoriaId = backStackEntry.arguments?.getLong(CATEGORIA_ID_ARG) ?: 1L
            
            val ejercicioViewModel: EjercicioViewModel = viewModel(
                factory = EjercicioViewModelFactory(EjercicioRepositorio(database.ejercicioDao()), categoriaId)
            )
            
            EjercicioScreen(
                viewModel = ejercicioViewModel,
                categoriaId = categoriaId,
                onEjercicioClick = { ejercicio ->
                    navController.navigate("sesiones/${ejercicio.idEjercicio}")
                }
            )
        }
        
        // Pantalla de Sesiones (filtradas por ejercicio)
        composable(
            route = SESIONES_ROUTE,
            arguments = listOf(
                navArgument(EJERCICIO_ID_ARG) {
                    type = NavType.LongType
                    nullable = false
                }
            )
        ) { backStackEntry ->
            val ejercicioId = backStackEntry.arguments?.getLong(EJERCICIO_ID_ARG) ?: 1L
            
            val sesionViewModel: SesionViewModel = viewModel(
                factory = SesionViewModelFactory(SesionRepositorio(database.sesionDao()), ejercicioId)
            )
            
            SesionScreen(
                viewModel = sesionViewModel,
                ejercicioId = ejercicioId,
                onSesionClick = { sesion ->
                    navController.navigate("series/${sesion.idSesion}")
                }
            )
        }
        
        // Pantalla de Series (filtradas por sesión)
        composable(
            route = SERIES_ROUTE,
            arguments = listOf(
                navArgument(SESION_ID_ARG) {
                    type = NavType.LongType
                    nullable = false
                }
            )
        ) { backStackEntry ->
            val sesionId = backStackEntry.arguments?.getLong(SESION_ID_ARG) ?: 1L
            
            val serieViewModel: SerieViewModel = viewModel(
                factory = SerieViewModelFactory(SerieRepositorio(database.serieDao()), sesionId)
            )
            
            SerieScreen(
                viewModel = serieViewModel,
                sesionId = sesionId
                // SerieScreen no tiene onSerieClick porque es el nivel final
            )
        }
    }
}

// Funciones de extensión para navegación tipada
fun NavHostController.navigateToEjercicios(categoriaId: Long) {
    navigate("ejercicios/$categoriaId")
}

fun NavHostController.navigateToSesiones(ejercicioId: Long) {
    navigate("sesiones/$ejercicioId")
}

fun NavHostController.navigateToSeries(sesionId: Long) {
    navigate("series/$sesionId")
}

