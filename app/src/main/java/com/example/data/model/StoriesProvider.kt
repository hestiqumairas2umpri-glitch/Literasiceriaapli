package com.example.data.model

object StoriesProvider {
    val allStories: List<ReadingStory> by lazy {
        StoriesDataPart1.stories + StoriesDataPart2.stories
    }

    fun getStoryById(id: Int): ReadingStory? {
        return allStories.find { it.id == id }
    }

    fun getQuestionsForStory(storyId: Int): List<QuizQuestion> {
        return QuestionsDataPart1.questionsMap[storyId]
            ?: QuestionsDataPart2.questionsMap[storyId]
            ?: emptyList()
    }
}
