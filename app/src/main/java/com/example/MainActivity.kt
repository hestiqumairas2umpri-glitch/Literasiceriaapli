package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.LiterasiViewModel
import com.example.ui.ScreenRoute
import com.example.ui.components.LiteracyBottomNavigationBar
import com.example.ui.screens.GamePlayerScreen
import com.example.ui.screens.GamesMenuScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.LearningObjectivesScreen
import com.example.ui.screens.MotivationScreen
import com.example.ui.screens.ProfileScreen
import com.example.ui.screens.ProgressDashboardScreen
import com.example.ui.screens.QuizResultScreen
import com.example.ui.screens.QuizScreen
import com.example.ui.screens.SettingsScreen
import com.example.ui.screens.SongsScreen
import com.example.ui.screens.StoryListScreen
import com.example.ui.screens.StoryReaderScreen
import com.example.ui.screens.TeacherReportScreen
import com.example.ui.screens.TrophiesScreen
import com.example.ui.screens.VideosScreen
import com.example.ui.theme.PetualanganLiterasiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: LiterasiViewModel = viewModel()
            val highContrast by viewModel.highContrastMode.collectAsState()
            val currentRoute by viewModel.currentRoute.collectAsState()

            // Back button handling
            BackHandler(enabled = currentRoute != ScreenRoute.HOME) {
                viewModel.navigateBack()
            }

            PetualanganLiterasiTheme(highContrastMode = highContrast) {
                val showBottomBar = currentRoute in listOf(
                    ScreenRoute.HOME,
                    ScreenRoute.STORY_LIST,
                    ScreenRoute.GAMES_MENU,
                    ScreenRoute.PROGRESS_DASHBOARD
                )

                Scaffold(
                    bottomBar = {
                        if (showBottomBar) {
                            LiteracyBottomNavigationBar(
                                currentRoute = currentRoute,
                                onTabSelected = { route -> viewModel.navigateTo(route) }
                            )
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    val contentModifier = Modifier.padding(innerPadding)
                    when (currentRoute) {
                        ScreenRoute.HOME -> HomeScreen(viewModel, contentModifier)
                        ScreenRoute.LEARNING_OBJECTIVES -> LearningObjectivesScreen(viewModel, contentModifier)
                        ScreenRoute.STORY_LIST -> StoryListScreen(viewModel, contentModifier)
                        ScreenRoute.STORY_READER -> StoryReaderScreen(viewModel, contentModifier)
                        ScreenRoute.QUIZ -> QuizScreen(viewModel, contentModifier)
                        ScreenRoute.QUIZ_RESULT -> QuizResultScreen(viewModel, contentModifier)
                        ScreenRoute.GAMES_MENU -> GamesMenuScreen(viewModel, contentModifier)
                        ScreenRoute.GAME_PLAYER -> GamePlayerScreen(viewModel, contentModifier)
                        ScreenRoute.MOTIVATION -> MotivationScreen(viewModel, contentModifier)
                        ScreenRoute.VIDEOS -> VideosScreen(viewModel, contentModifier)
                        ScreenRoute.PROGRESS_DASHBOARD -> ProgressDashboardScreen(viewModel, contentModifier)
                        ScreenRoute.TROPHIES -> TrophiesScreen(viewModel, contentModifier)
                        ScreenRoute.TEACHER_REPORT -> TeacherReportScreen(viewModel, contentModifier)
                        ScreenRoute.SONGS -> SongsScreen(viewModel, contentModifier)
                        ScreenRoute.PROFILE -> ProfileScreen(viewModel, contentModifier)
                        ScreenRoute.SETTINGS -> SettingsScreen(viewModel, contentModifier)
                    }
                }
            }
        }
    }
}
