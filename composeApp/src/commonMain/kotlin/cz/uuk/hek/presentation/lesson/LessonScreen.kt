package cz.uuk.hek.presentation.lesson

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cz.uuk.hek.domain.model.Answer
import cz.uuk.hek.domain.model.Card
import cz.uuk.hek.domain.model.Question
import cz.uuk.hek.domain.vm.LessonVM
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LessonPage(vm: LessonVM = koinViewModel()) {
    val state by vm.uiState.collectAsStateWithLifecycle()
    LessonContent(
        state = state,
        onAction = vm::onAction,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun LessonContent(
    state: LessonUiState = LessonUiState(),
    onAction: (LessonUiAction) -> Unit = {},
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { onAction(LessonUiAction.Back) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Zpět",
                        )
                    }
                },
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            if (state.lesson == null) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary,
                        trackColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
                    )
                }
            } else {
                val pagerState = rememberPagerState(pageCount = { state.lesson.cards.size + 1 })
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxSize(),
                    userScrollEnabled = state.result == null,
                ) { index ->
                    if (index < state.lesson.cards.size) {
                        CardView(
                            card = state.lesson.cards[index],
                            selectedQuestion = state.selectedQuestion,
                            answeredQuestions = state.answeredQuestions,
                            isEvaluated = state.result != null,
                            onAction = onAction,
                        )
                    } else {
                        val allQuestions = state.lesson.cards.flatMap { it.questions }
                        val allAnswered = allQuestions.all { q -> state.answeredQuestions.containsKey(q.id) }
                        val result = state.result

                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            if (result != null) {
                                val correct = allQuestions.count { q ->
                                    state.answeredQuestions[q.id]?.id == q.correctAnswerId
                                }
                                Text(
                                    text = "Výsledek: $correct / ${allQuestions.size}",
                                    style = MaterialTheme.typography.headlineMedium,
                                    modifier = Modifier.padding(bottom = 24.dp),
                                )
                                Button(onClick = { onAction(LessonUiAction.Finish) }) {
                                    Text("Finish")
                                }
                            } else {
                                if (!allAnswered) {
                                    Text(
                                        text = "Nejdřív zodpověz všechny otázky.",
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                                        modifier = Modifier.padding(bottom = 24.dp),
                                    )
                                }
                                Button(
                                    onClick = { onAction(LessonUiAction.Complete) },
                                    enabled = allAnswered,
                                ) {
                                    Text("Dokončit")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CardView(
    card: Card,
    selectedQuestion: Question? = null,
    answeredQuestions: Map<Int, Answer> = emptyMap(),
    isEvaluated: Boolean = false,
    onAction: (LessonUiAction) -> Unit = {},
) {
    Column(modifier = Modifier.fillMaxSize()) {
        // Code questions — scrollable, fills available space
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(
                top = 24.dp,
                bottom = 16.dp,
            ),
        ) {
            items(card.questions) { question ->
                QuestionItem(
                    question = question,
                    isSelected = selectedQuestion?.id == question.id,
                    selectedAnswer = answeredQuestions[question.id],
                    onSlotClick = { onAction(LessonUiAction.SelectQuestion(question)) },
                )
            }
        }

        // Answer grid — slides in below when a question is selected
        AnimatedVisibility(
            visible = selectedQuestion != null && card.questions.any { it.id == selectedQuestion.id },
            enter = expandVertically(expandFrom = Alignment.Bottom),
            exit = shrinkVertically(shrinkTowards = Alignment.Bottom),
        ) {
            selectedQuestion?.let { question ->
                AnswerGrid(
                    question = question,
                    answeredQuestions = answeredQuestions,
                    isEvaluated = isEvaluated,
                    onAnswerSelected = { answer -> onAction(LessonUiAction.SelectAnswer(answer)) },
                )
            }
        }
    }
}

private data class Segment(val text: String, val isSlot: Boolean)

private fun parseContent(content: String): List<Segment> {
    val parts = content.split("@@@")
    return buildList {
        parts.forEachIndexed { index, part ->
            if (part.isNotEmpty()) add(Segment(part, false))
            if (index < parts.size - 1) add(Segment("@@@", true))
        }
    }
}

@Composable
private fun QuestionItem(
    question: Question,
    isSelected: Boolean,
    selectedAnswer: Answer?,
    onSlotClick: () -> Unit,
) {
    val segments = parseContent(question.content)
    val hasSlot = segments.any { it.isSlot }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
    ) {
        CodeContentView(
            segments = segments,
            selectedAnswer = selectedAnswer,
            isQuestionSelected = isSelected,
            onSlotClick = if (hasSlot) onSlotClick else null,
        )
    }
}

@Composable
private fun CodeContentView(
    segments: List<Segment>,
    selectedAnswer: Answer?,
    isQuestionSelected: Boolean,
    onSlotClick: (() -> Unit)?,
) {
    val codeStyle = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontSize = 14.sp,
        color = MaterialTheme.colorScheme.onBackground,
    )

    // Split into lines while preserving slot positions within lines
    val lines = buildList<List<Segment>> {
        val currentLine = mutableListOf<Segment>()
        segments.forEach { seg ->
            if (seg.isSlot) {
                currentLine.add(seg)
            } else {
                val lineParts = seg.text.split("\n")
                lineParts.forEachIndexed { i, part ->
                    if (i > 0) {
                        add(currentLine.toList())
                        currentLine.clear()
                    }
                    if (part.isNotEmpty()) currentLine.add(Segment(part, false))
                }
            }
        }
        if (currentLine.isNotEmpty()) add(currentLine.toList())
    }

    Column(verticalArrangement = Arrangement.spacedBy(1.dp)) {
        lines.forEach { line ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                line.forEach { seg ->
                    if (seg.isSlot) {
                        SlotChip(
                            selectedAnswer = selectedAnswer,
                            isQuestionSelected = isQuestionSelected,
                            onClick = onSlotClick,
                        )
                    } else {
                        Text(text = seg.text, style = codeStyle)
                    }
                }
            }
        }
    }
}

@Composable
private fun SlotChip(
    selectedAnswer: Answer?,
    isQuestionSelected: Boolean,
    onClick: (() -> Unit)?,
) {
    val shape = RoundedCornerShape(6.dp)
    val bgColor = when {
        selectedAnswer != null -> MaterialTheme.colorScheme.primary
        isQuestionSelected -> MaterialTheme.colorScheme.primary
        else -> MaterialTheme.colorScheme.onBackground.copy(alpha = 0.15f)
    }
    val textColor = when {
        selectedAnswer != null -> MaterialTheme.colorScheme.onPrimary
        isQuestionSelected -> MaterialTheme.colorScheme.onPrimary
        else -> MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
    }

    Box(
        modifier = Modifier
            .clip(shape)
            .background(bgColor)
            .then(if (onClick != null) Modifier.clickable { onClick() } else Modifier)
            .padding(horizontal = 12.dp, vertical = 3.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = selectedAnswer?.text ?: "        ",
            style = TextStyle(
                fontFamily = FontFamily.Monospace,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = textColor,
            ),
        )
    }
}

@Composable
private fun AnswerGrid(
    question: Question,
    answeredQuestions: Map<Int, Answer>,
    isEvaluated: Boolean = false,
    onAnswerSelected: (Answer) -> Unit,
) {
    val currentAnswer = answeredQuestions[question.id]
    val answers = question.answers

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        answers.chunked(2).forEach { rowAnswers ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                rowAnswers.forEach { answer ->
                    AnswerChip(
                        answer = answer,
                        isSelected = currentAnswer?.id == answer.id,
                        isCorrect = answer.id == question.correctAnswerId,
                        isEvaluated = isEvaluated,
                        modifier = Modifier.weight(1f),
                        onClick = { onAnswerSelected(answer) },
                    )
                }
                // Fill empty slot in last row if odd count
                if (rowAnswers.size == 1) {
                    Box(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
private fun AnswerChip(
    answer: Answer,
    isSelected: Boolean,
    isCorrect: Boolean,
    isEvaluated: Boolean = false,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val shape = RoundedCornerShape(50)
    val bgColor = when {
        isSelected && isEvaluated && isCorrect -> MaterialTheme.colorScheme.primary
        isSelected && isEvaluated -> MaterialTheme.colorScheme.error.copy(alpha = 0.8f)
        isSelected -> MaterialTheme.colorScheme.primary
        else -> MaterialTheme.colorScheme.onBackground.copy(alpha = 0.12f)
    }
    val textColor = when {
        isSelected -> Color.White
        else -> MaterialTheme.colorScheme.onBackground.copy(alpha = 0.85f)
    }

    Box(
        modifier = modifier
            .clip(shape)
            .background(bgColor)
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 14.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = answer.text,
            style = TextStyle(
                fontFamily = FontFamily.Monospace,
                fontSize = 13.sp,
                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                color = textColor,
            ),
        )
    }
}
