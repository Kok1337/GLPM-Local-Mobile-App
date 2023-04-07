package com.kok1337.toolbar.presentation.navigator

interface ToolbarNavigator {
    /**
     * Вернуться назад
     */
    fun goBack()

    /**
     * Открыть фрагмент "Карта"
     */
    fun launchMapScreen()

    /**
     * Открыть фрагмент "Камера"
     */
    fun launchCameraScreen()

    /**
     * Открыть фрагмент "Виды работ"
     */
    fun launchWorkTypesScreen()

    /**
     * Открыть фрагмент "Синхронизация"
     */
    fun launchSynchronizationScreen()

    /**
     * Открыть фрагмент "Синхронизация"
     */
    fun launchSettingsScreen()

    /**
     * Установить стартовый фрагмент
     */
    fun setupStartFragment()
}