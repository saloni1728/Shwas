package com.example.Shwas.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.Shwas.R
import com.example.Shwas.ui.events.LanguageEvents
import com.example.Shwas.ui.theme.Colors
import com.example.Shwas.ui.theme.Shapes
import com.example.Shwas.ui.theme.Typography
import com.example.Shwas.ui.viewModels.LanguageViewModel

@Composable
fun LanguageScreen(
    modifier: Modifier,
    viewModel: LanguageViewModel = hiltViewModel(),
) {
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.choose_language),
            style = com.example.Shwas.ui.theme.Typography.default.titleLarge,
            color = Colors.TextPrimary,
            modifier = Modifier.padding(start = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.weight(1f)) {
            items(screenState.languageList) { lang ->
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }) {
                            viewModel.handleEvent(
                                LanguageEvents.UpdateSelectedLanguage(lang.languageCode)
                            )
                        }
                        .clip(Shapes.default.rounderCornerMedium16),
                    colors = CardDefaults.cardColors(
                        containerColor = if (screenState.selectedLanguageCode == lang.languageCode) Colors.SelectedCard else Colors.Background
                    ),
                    shape = Shapes.default.rounderCornerMedium16,
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 1.dp
                    ),
                    border = BorderStroke(width = if (screenState.selectedLanguageCode == lang.languageCode) 1.dp else 0.dp, color = Colors.SelectedCardBorder)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .height(60.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = lang.languageName,
                            style = com.example.Shwas.ui.theme.Typography.default.labelMedium,
                            color = Colors.TextPrimary,
                            textAlign = TextAlign.Center
                        )
                    }
                }

            }
        }

        Button(
            onClick = {
                screenState.selectedLanguageCode?.let {
                    viewModel.handleEvent(
                        LanguageEvents.UpdateSelectedLanguage(
                            selectedLanguageCode = it,
                            changeLocale = true
                        )
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            enabled = true,
            shape = Shapes.default.rounderCornerMedium16,
            contentPadding = PaddingValues(
                top = 12.dp,
                bottom = 12.dp,
                start = 16.dp,
                end = 16.dp
            ),
            colors = ButtonDefaults.buttonColors(
                containerColor = Colors.GentleBlue,
                contentColor = Colors.Background
            )
        ) {
            Text(
                stringResource(R.string.continue_text),
                color = Colors.Background,
                style = com.example.Shwas.ui.theme.Typography.default.titleSmall
            )
        }
    }
}