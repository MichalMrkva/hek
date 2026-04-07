package cz.uuk.hek.presentation.uistates

data class DashboardUiState(
    val isLoading: Boolean=false,
    val lessons: List<String>?=null,
    val currentLesson:String?=null

)
