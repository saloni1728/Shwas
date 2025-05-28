package com.example.Shwas.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.Shwas.R
import com.example.Shwas.data.dataPersistence.RoomDb.entity.BreathingExerciseEntity
import com.example.Shwas.ui.events.HomeEvents
import com.example.Shwas.ui.theme.Colors
import com.example.Shwas.ui.viewModels.HomeViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()
    val filteredExercises = screenState.breathingExercise.filter { exercise ->
        listOf(
            exercise.nameTranslations[screenState.currentLanguage],
            exercise.descriptionTranslations[screenState.currentLanguage]
        ).any { it?.contains(screenState.searchQuery, ignoreCase = true) == true }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Colors.Surface)
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(top = 24.dp)) {
            // Search Bar
            SearchBar(
                query = screenState.searchQuery,
                onQueryChange = { viewModel.handleEvent(HomeEvents.UpdateSearchQuery(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            // Exercises List
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp, end = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 40.dp)
            ) {
                items(filteredExercises, key = { it.id }) { exercise ->
                    BreathingExerciseCard(
                        currentLanguage = screenState.currentLanguage,
                        exercise = exercise,
                        isExpanded = exercise.isExpanded,
                        isFavorite = exercise.isFavorite,
                        onToggleFavorite = { viewModel.handleEvent(HomeEvents.MarkFavorite(exercise.id)) },
                        onClick = { viewModel.handleEvent(HomeEvents.UpdateExpandedCardId(exercise.id)) },
                        onPlayClick = { viewModel.handleEvent(HomeEvents.NavigateToMeditationScreen(
                            exercise.id,
                            exercise.cardColor,
                            exercise.textColor,
                            exercise.accentColor
                        )) }
                    )
                }
            }
        }
    }
}

@Composable
fun BreathingExerciseCard(
    currentLanguage: String,
    exercise: BreathingExerciseEntity,
    isExpanded: Boolean,
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit,
    onClick: () -> Unit,
    onPlayClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(exercise.cardColor),
            contentColor = Color(exercise.textColor)
        ),
        elevation = CardDefaults.cardElevation(if (isExpanded) 8.dp else 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    exercise.nameTranslations[currentLanguage]?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    exercise.descriptionTranslations[currentLanguage]?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = Color(exercise.textColor).copy(alpha = 0.8f)
                            )
                        )
                    }
                }

                IconButton(onClick = { onToggleFavorite() }) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = stringResource(R.string.fav),
                        tint = Color(exercise.accentColor)
                    )
                }
            }

            // Expanded content with animation
            AnimatedVisibility(
                visible = isExpanded,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.practice_text),
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    exercise.instructionTranslations[currentLanguage]?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    // Add animated play button
                    IconButton(
                        onClick = { onPlayClick.invoke() },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.PlayArrow,
                            contentDescription = stringResource(R.string.pause),
                            tint = Color(exercise.accentColor),
                            modifier = Modifier.size(48.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier,
        placeholder = { Text(stringResource(R.string.search_placeholder)) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(R.string.search)
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White.copy(alpha = 0.8f),
            focusedContainerColor = Color.White
        ),
        trailingIcon = {
            if (query.isNotBlank()) {
                IconButton(onClick = { onQueryChange("") }) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = stringResource(R.string.clear_search),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        },
        singleLine = true
    )
}