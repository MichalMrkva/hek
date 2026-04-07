package cz.uuk.hek.presentation.interfaces

sealed interface DashboardActions {
    data class SetIsLoading(val isLoading: Boolean): DashboardActions
    data class LoadLessons(val lessons:List<String>): DashboardActions
    data class OpenLesson(val id: String): DashboardActions
}