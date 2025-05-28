package com.example.Shwas.ui.screens

import android.content.Context
import android.media.AudioAttributes
import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.Voice
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.draw
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.example.Shwas.R
import com.example.Shwas.data.dataPersistence.RoomDb.entity.BreathingStepsEntity
import com.example.Shwas.domain.model.BreathType
import com.example.Shwas.domain.model.TimedInstruction
import com.example.Shwas.ui.events.MeditationEvents
import com.example.Shwas.ui.sideEffects.OneShotEffect
import com.example.Shwas.ui.theme.Colors
import com.example.Shwas.ui.theme.Shapes
import com.example.Shwas.ui.theme.Typography
import com.example.Shwas.ui.viewModels.MeditationViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Locale
import kotlin.math.min

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MeditationScreen(
    id: String,
    cardColor: Int,
    textColor: Int,
    accentColor: Int,
    viewModel: MeditationViewModel = hiltViewModel()
) {
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val textToSpeech = rememberTextToSpeechPlayer(
        voice = screenState.selectedVoice,
        currentLanguage = screenState.currentLanguage,
        context = context
    )
    val exoPlayer by rememberExoPlayer(context)
    var isSpeakerPaused by rememberSaveable { mutableStateOf(false) }
    var isMusicPaused by rememberSaveable { mutableStateOf(false) }
    val ttsBundle by rememberSaveable { mutableStateOf(Bundle().apply {  putFloat(TextToSpeech.Engine.KEY_PARAM_VOLUME, screenState.ttsVolume) }) }
    val backgroundColor by rememberUpdatedState(Color(cardColor))
    val widgetsColor by rememberUpdatedState(Color(textColor))
    val lightColor by rememberUpdatedState(Color(accentColor))


    LaunchedEffect(screenState.currentInstruction, isSpeakerPaused) {
        if (screenState.currentStep == 0) delay(3000L)
        if (!isSpeakerPaused) {
            textToSpeech?.speak(screenState.currentInstruction?.text ?: "", TextToSpeech.QUEUE_ADD, ttsBundle, "meditation_instruction")
            if (screenState.currentStep < (screenState.breathingExercisesSteps?.size ?: 0) - 1) {
                delay(screenState.currentInstruction?.durationMs?.plus(5000L) ?: 3000L)
                viewModel.handleEvent(MeditationEvents.UpdateCurrentStep(screenState.currentStep + 1))
            }
        }
    }

    OneShotEffect {
        viewModel.handleEvent(MeditationEvents.FetchInitData(id, context))
    }

    OneShotEffect {
        val mediaItem = MediaItem.fromUri("android.resource://${context.packageName}/${R.raw.background_music}")
        exoPlayer?.setMediaItem(mediaItem)
        exoPlayer?.setPlaybackSpeed(1f)
        exoPlayer?.prepare()
        exoPlayer?.play()
    }

    LaunchedEffect(screenState.ttsVolume) {
        ttsBundle.putFloat(TextToSpeech.Engine.KEY_PARAM_VOLUME, screenState.ttsVolume)
    }

    fun backHandler() {
        exoPlayer?.stop()
        textToSpeech?.stop()
        viewModel.handleEvent(MeditationEvents.NavigateBack)
    }

    fun setExoPlayerVolume(value: Float) {
        exoPlayer?.volume = value
        viewModel.handleEvent(MeditationEvents.UpdateMusicVolume(value))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor.copy(alpha = 0.5f)),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Back icon
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
            .weight(0.5f), contentAlignment = Alignment.TopStart) {
            IconButton(onClick = { backHandler() },) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    tint = Colors.OnSurface
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomVerticalVolumeSlider(
                fillColor = lightColor,
                trackColor = backgroundColor,
                value = screenState.musicVolume,
                onValueChange = { newValue ->
                    if (!isMusicPaused) {
                        setExoPlayerVolume(newValue)
                    }
                },
                onMicClick = {
                    if (!isMusicPaused) {
                        setExoPlayerVolume(0f)
                        exoPlayer?.pause()
                        isMusicPaused = true
                    } else {
                        setExoPlayerVolume(0.5f)
                        exoPlayer?.play()
                        isMusicPaused = false
                    }
                },
                modifier = Modifier.padding(top = 48.dp),
                iconComposable = {
                    Icon(
                        painter = painterResource(if (isMusicPaused || screenState.musicVolume == 0f) {
                            R.drawable.ic_no_music
                        } else R.drawable.ic_music),
                        contentDescription = stringResource(R.string.stop),
                        tint = Colors.TextPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            )
            PranayamCircle(
                stepColor = backgroundColor.copy(alpha = 0.5f),
                backgroundColor = lightColor,
                currentInstruction = screenState.currentInstruction,
                currentStep = screenState.currentStep,
                instructions = screenState.breathingExercisesSteps ?: emptyList(),
                numberOfCycle = screenState.numberOfCycles,
                language = screenState.currentLanguage,
                modifier = Modifier
            )

            CustomVerticalVolumeSlider(
                fillColor = lightColor,
                trackColor = backgroundColor,
                value = screenState.ttsVolume,
                onValueChange = { newValue ->
                    if (!isSpeakerPaused) {
                        viewModel.handleEvent(MeditationEvents.UpdateTTSVolume(newValue))
                    }
                },
                onMicClick = {
                    if (!isSpeakerPaused) {
                        textToSpeech?.stop()
                        viewModel.handleEvent(MeditationEvents.UpdateTTSVolume(0f))
                        isSpeakerPaused = true
                    } else {
                        viewModel.handleEvent(MeditationEvents.UpdateTTSVolume(0.5f))
                        isSpeakerPaused = false
                    }
                },
                modifier = Modifier.padding(top = 48.dp),
                iconComposable = {
                    Icon(
                        painter = painterResource(if (isSpeakerPaused || screenState.ttsVolume == 0f) {
                            R.drawable.ic_mute
                        } else R.drawable.ic_volume),
                        contentDescription = stringResource(R.string.stop),
                        tint = Colors.TextPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            )
        }



        // Stop button
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
                .weight(0.5f),
            contentAlignment = Alignment.BottomCenter
        ) {
            Card(
                modifier = Modifier
                    .width(96.dp)
                    .height(48.dp)
                    .clickable { backHandler() },
                elevation = CardDefaults.cardElevation(1.dp),
                colors = CardDefaults.cardColors(containerColor = lightColor),
                shape = Shapes.default.roundedCornerXSmall
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Icon(
                        painter = painterResource(R.drawable.stop),
                        tint = Colors.TextPrimary,
                        modifier = Modifier.size(12.dp),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(R.string.stop),
                        style = Typography.default.labelMedium,
                        color = Colors.TextPrimary
                    )
                }

            }
        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun rememberTextToSpeechPlayer(
    voice: Voice?,
    currentLanguage: String,
    context: Context,
): TextToSpeech? {
    val ttsState = remember { mutableStateOf<TextToSpeech?>(null) }

    DisposableEffect(context) {
        val textToSpeech = TextToSpeech(context, object : TextToSpeech.OnInitListener {
            override fun onInit(status: Int) {
                if (status == TextToSpeech.SUCCESS) {
                    ttsState.value?.language = Locale(currentLanguage, "IN")
                    if (voice != null) {
                        ttsState.value?.voice = voice
                    }
                    ttsState.value?.setAudioAttributes(
                        AudioAttributes.Builder()
                            .setUsage(AudioAttributes.USAGE_ASSISTANT)
                            .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                            .build()
                    )
                    ttsState.value?.setSpeechRate(0.5f)
                } else {
                    System.out.println("TTS Initialization failed")
                }
            }
        })
        ttsState.value = textToSpeech
        System.out.println("saloni 1 -> ${ttsState.value?.voices}")

        onDispose {
            ttsState?.value?.stop()
            ttsState?.value?.shutdown()
        }
    }
    return ttsState.value
}

@Composable
fun rememberExoPlayer(
    context: Context
): MutableState<ExoPlayer?> {
    val exoPlayer = remember { mutableStateOf<ExoPlayer?>(null) }
    DisposableEffect(context) {
        val player = ExoPlayer.Builder(context).build().apply {
            repeatMode = Player.REPEAT_MODE_ALL
            setHandleAudioBecomingNoisy(true)
        }
        exoPlayer.value = player
        onDispose {
            player.release()
        }
    }
    return exoPlayer
}

@Composable
private fun SafeCanvas(
    modifier: Modifier = Modifier,
    draw: DrawScope.(IntSize) -> Unit
) {
    var canvasSize by remember { mutableStateOf(IntSize.Zero) }

    Canvas(
        modifier = modifier.onGloballyPositioned {
            canvasSize = it.size
        }
    ) {
        if (canvasSize != IntSize.Zero) {
            draw(canvasSize)
        }
    }
}

@Composable
fun CustomVerticalVolumeSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    onMicClick: () -> Unit,
    iconComposable: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    sliderHeight: Dp = 150.dp,
    sliderWidth: Dp = 36.dp,
    trackColor: Color = Color.Gray,
    fillColor: Color = MaterialTheme.colorScheme.primary,
    cornerRadius: Dp = 12.dp
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp),
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .height(sliderHeight)
                .width(sliderWidth)
                .pointerInput(Unit) {
                    detectDragGestures { change, _ ->
                        val offsetY = change.position.y
                        val heightPx = size.height.toFloat()

                        // Invert Y because top = 0 (max volume at bottom)
                        val newValue = 1f - (offsetY / heightPx)
                        onValueChange(newValue.coerceIn(0f, 1f))
                        change.consume()
                    }
                }
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val radiusPx = cornerRadius.toPx()
                // Draw the full track
                drawRoundRect(
                    color = fillColor,
                    size = Size(size.width, size.height),
                    style = Stroke(width = 6f),
                    cornerRadius = CornerRadius(radiusPx, radiusPx)
                )

                // Draw the filled portion
                val filledHeight = size.height * value
                drawRoundRect(
                    color = fillColor,
                    topLeft = Offset(0f, size.height - filledHeight),
                    size = Size(size.width, filledHeight),
                    cornerRadius = CornerRadius(radiusPx, radiusPx)
                )
            }
            IconButton(modifier = Modifier.align(Alignment.BottomCenter),onClick = {onMicClick()}) {
                iconComposable.invoke()
            }
        }
    }
}

@Composable
fun UniversalBreathingAnimation(
    totalInstructions: Int,
    numberOfCycle: Int,
    currentStep: Int,
    stepColor: Color,
    backgroundColor: Color,
    currentInstruction: String,
    durationMillis: Long,
    modifier: Modifier,
    languageCode: String
) {
    // Detect breath phase (works for English/Hindi/Sanskrit)
    val updatedInstruction by rememberUpdatedState(currentInstruction)
    val (animationType, progress) = BreathingStepsEntity.detectBreathPhase(updatedInstruction)

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        // Static background circle
        val averageInstruction = (totalInstructions.toFloat() / numberOfCycle.toFloat()).toInt()

        SafeCanvas(modifier = Modifier.fillMaxSize()) {
            val sweepAngle = if (totalInstructions > 0 ) {
                360f * ((currentStep + 1) / totalInstructions.toFloat()).coerceIn(0f, 360f)
            } else 0f
            val stroke = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
            drawCircle(
                color = backgroundColor.copy(alpha = 0.5f),
                center = center,
                radius = (size.minDimension / 2) - stroke.width / 2,
            )
            drawArc(
                color = backgroundColor,
                startAngle = -90f,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = stroke
            )
        }

        // Dynamic elements based on breath type
        when (animationType) {
            BreathType.INHALE -> InhaleAnimation(progress, durationMillis, backgroundColor.copy(alpha = 0.5f))
            BreathType.EXHALE -> ExhaleAnimation(progress, durationMillis, backgroundColor.copy(alpha = 0.5f))
            BreathType.HOLD -> HoldAnimation(durationMillis, backgroundColor.copy(alpha = 0.7f))
            BreathType.SOUND -> SoundWaveAnimation(durationMillis, backgroundColor)
            else -> null
        }

        NeutralAnimation(backgroundColor)

        Text(
            text = "${(currentStep / averageInstruction) + 1}",
            style = com.example.Shwas.ui.theme.Typography.default.displayMedium,
            color = stepColor
        )
    }
}

@Composable
fun HoldAnimation(duration: Long, backgroundColor: Color) {
    val infiniteTransition = rememberInfiniteTransition()
    val pulse by infiniteTransition.animateFloat(
        initialValue = 0.9f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = (duration + 5000L).toInt(), easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    SafeCanvas(modifier = Modifier.size(200.dp)) {
        drawCircle(
            color = backgroundColor,
            radius = (pulse * size.minDimension / 2),
            style = Stroke(width = 8.dp.toPx())
        )
    }
}

@Composable
fun ExhaleAnimation(progress: Float, durationMillis: Long, backgroundColor: Color) {
    val transition = rememberInfiniteTransition()
    val animatedProgress by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween((durationMillis + 5000L).coerceAtLeast(100).toInt()),
            repeatMode = RepeatMode.Reverse
        )
    )
    val radiusFactor = (1f - animatedProgress * 0.6f) * progress
    SafeCanvas(modifier = Modifier.size(200.dp)) {
        drawCircle(
            color = backgroundColor,
            radius = radiusFactor * size.minDimension / 2
        )
    }
}

@Composable
fun NeutralAnimation(backgroundColor: Color) {
    SafeCanvas(modifier = Modifier.size(100.dp)) {
        drawCircle(
            color = backgroundColor,
            radius = size.minDimension / 3
        )
    }
}

@Composable
private fun InhaleAnimation(progress: Float, durationMillis: Long, backgroundColor: Color) {
    val transition = rememberInfiniteTransition()
    val animatedProgress by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween((durationMillis + 5000L).coerceAtLeast(100).toInt()),
            repeatMode = RepeatMode.Reverse
        )
    )
    val size = (0.3f + (animatedProgress * 0.7f)) * progress
    SafeCanvas (modifier = Modifier.size(200.dp)) {
        drawCircle(
            color = backgroundColor,
            radius = size * this.size.minDimension / 2
        )
    }
}

@Composable
fun SoundWaveAnimation(duration: Long, backgroundColor: Color) {
    val waveCount = 5
    val delays = remember { List(waveCount) { it * 100L } } // Stagger waves
    val animatedHeights = remember { List(waveCount) { Animatable(0f) } }

    LaunchedEffect(duration) {
        animatedHeights.forEachIndexed { i, anim ->
            launch {
                while (true) {
                    anim.animateTo(
                        targetValue = 32f,
                        animationSpec = tween((duration / 2).toInt(), delayMillis = delays[i].toInt())
                    )
                    anim.animateTo(
                        targetValue = 0f,
                        animationSpec = tween((duration / 2).toInt())
                    )
                }
            }
        }
    }

    Row(
        modifier = Modifier.height(40.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        animatedHeights.forEach { anim ->
            Spacer(
                modifier = Modifier
                    .width(8.dp)
                    .height(anim.value.dp)
                    .background(backgroundColor)
            )
            Spacer(modifier = Modifier.width(4.dp)) // spacing between waves
        }
    }
}

@Composable
fun PranayamCircle(
    instructions: List<TimedInstruction>,
    currentStep: Int,
    stepColor: Color,
    backgroundColor: Color,
    numberOfCycle: Int,
    modifier: Modifier,
    currentInstruction: TimedInstruction? = null,
    language: String = "en"
) {
    val updatedInstruction by rememberUpdatedState(currentInstruction)

    Column(
        modifier = modifier
    ) {
        updatedInstruction?.let {
            UniversalBreathingAnimation(
                stepColor = stepColor,
                totalInstructions = instructions.size,
                numberOfCycle = numberOfCycle + 3,
                backgroundColor = backgroundColor,
                currentInstruction = it.text,
                durationMillis = it.durationMs,
                modifier = Modifier.size(200.dp),
                languageCode = language,
                currentStep = currentStep
            )
        }
    }
}